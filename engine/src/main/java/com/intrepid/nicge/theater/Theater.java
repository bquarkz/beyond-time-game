/**
 * Copyleft (C) 2016  Constantino, Nilton Rogerio <niltonrc@gmail.com>
 *
 * @author "Nilton R Constantino"
 * aKa bQUARKz <niltonrc@gmail, bquarkz@gmail.com>
 * <p>
 * Everything about the respective software copyright can be found in the
 * "LICENSE" file included in the project source tree.
 * <p>
 * The code was written based on study principles and can be enjoyed for
 * all comunity without problems.
 */
package com.intrepid.nicge.theater;

import com.intrepid.nicge.content.AssetManager;
import com.intrepid.nicge.content.ILoadable;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.theater.curtain.Curtain;
import com.intrepid.nicge.theater.curtain.CurtainCondition;
import com.intrepid.nicge.theater.curtain.CurtainLayer;
import com.intrepid.nicge.theater.curtain.CurtainManager;
import com.intrepid.nicge.theater.scene.IScene;
import com.intrepid.nicge.utils.IProcessExecution;
import com.intrepid.nicge.utils.fsmachine.FSMachine;
import com.intrepid.nicge.utils.fsmachine.IFSMachineDefinition;
import com.intrepid.nicge.utils.fsmachine.exceptions.EFSMachineStop;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;

import java.util.Optional;

/**
 * Class Theater is responsible to hold control about all scene management.
 * <p>
 * It has two instances for GraphicsBatch where teh first will be used to render a scene as usual and the second - and
 * on top - will be used to render the curtains. We do it to provide different solutions about cameras for both situations.
 */
public final class Theater
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private static FSMachine< IScene > fsMachine;
    private static StageHand stageHand;
    private static CurtainManager curtainManager;
    private static Class< ? extends IScene > sceneClassToChange;
    private static GraphicsBatch stageBatch;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public Theater(
            IFSMachineDefinition< IScene > fsMachineDef,
            AssetManager assetManager )
    {
        Theater.fsMachine = new FSMachine<>( fsMachineDef );
        Theater.stageHand = new StageHand( assetManager );
        Theater.curtainManager = new CurtainManager();
        Theater.stageBatch = new GraphicsBatch();
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************

    public void changeMainCurtain( Curtain curtain )
    {
        Theater.curtainManager.setCurtain( CurtainLayer.MAIN, curtain );
    }

    public final void changeScene(
            Class< ? extends IScene > sceneClass,
            Curtain curtain )
    {
        if( curtain == null )
        {
            throw new RuntimeException( "curtain can not be null" );
        }

        if( Theater.fsMachine.getDefinition().contains( sceneClass ) )
        {
            // load current curtain...
            changeMainCurtain( curtain );
            // ...and after send command to change a scene
            __changeScene( sceneClass );
        }
        else
        {
        	final String errorMessage = ( sceneClass == null )
					? "scene class can not be null"
					: "theater doesn't have the following scene: " + sceneClass.getName();
			throw new RuntimeException( errorMessage );
        }
    }

    private void __changeScene( Class< ? extends IScene > sceneClass )
    {
        final ILoadable dependencies = Game.common.getDependencies( sceneClass );
        Theater.stageHand.loadResourcesFrom( dependencies );
        sceneClassToChange = sceneClass;
        Theater.curtainManager.closeCommand();
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public CurtainManager getCurtainManager()
    {
        return curtainManager;
    }

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
    public static class KickStartFrame implements IProcessExecution
    {
        @Override
        public void execute()
        {
            try
            {
                // apply changes into the finite state machine.
                // bare in mind: all remainder updates will be in fact done on next cycle, principally scene transitions.
                Theater.fsMachine.apply();
            }
            catch( EFSMachineStop e )
            {
                throw new RuntimeException( "machine was stopped for some reason" + e.getMessage() );
            }
        }
    }

    public static class CaptureControlProcess implements IProcessExecution
    {
        @Override
        public void execute()
        {
            Theater.fsMachine.getActualFiniteState().inputControlLogic();
        }
    }

    public static class Simulation implements IProcessExecution
    {
        @Override
        public void execute()
        {
            Theater.fsMachine.getActualFiniteState().simulation();
        }
    }

    public static class UpdateProcess implements IProcessExecution
    {
        @Override
        public void execute()
        {
            // verify if we have a curtain into main layer
            final Optional< Curtain > curtain = Theater.curtainManager.getCurtain( CurtainLayer.MAIN );
            if( !curtain.isPresent() )
            {
                // case when no curtain was load into main layer, just update and let it go
                Theater.fsMachine.getActualFiniteState().update();
                return;
            }

            final CurtainCondition status = curtain.get().getStatus();
            if( status == CurtainCondition.CLOSED )
            {
                if( !Theater.stageHand.isLoadingFinished() )
                {
                    // it is a complete scene change
                    Theater.stageHand.update();
                    Theater.curtainManager.update();
                }
                else
                {
                    Theater.fsMachine.getActualFiniteState().unBindAssets();

                    // kick start a scene change
                    Theater.fsMachine.switchTo( sceneClassToChange );

                    // graphics batch configuration for the new scene
                    final IScene scene = Theater.fsMachine.getDefinition().get( sceneClassToChange );
                    scene.configureGraphicsBatch( stageBatch );
                    scene.prepareEnvironment();
                    scene.bindAssets();

                    Theater.curtainManager.openCommand();
                    sceneClassToChange = null;
                }

                return;
            }

            Theater.fsMachine.getActualFiniteState().update();
            Theater.curtainManager.update();
        }
    }

    public static class DisplayProcess implements IProcessExecution
    {
        @Override
        public void execute()
        {
			// verify if we have a curtain into main layer
			final Optional< Curtain > curtain = Theater.curtainManager.getCurtain( CurtainLayer.MAIN );
			if( !curtain.isPresent() )
			{
				// if no curtain on main layer only display
				// the stageBatch begin and end belongs to stage
				Theater.fsMachine.getActualFiniteState().display( stageBatch );
				return;
			}

			// Case 1: machine in middle of a scene transition. When status is OPENING all last scene resources
			// was already removed from memory and a new fresh scene should be shown, however at this specific
			// same cycle the transition was not fully done and will try to render the last scene.
			// Case 2: the curtain is not even closed.
            final CurtainCondition status = curtain.get().getStatus();
            if( ( !Theater.fsMachine.inTransition() ) && ( status != CurtainCondition.CLOSED ) )
			{
				// the stageBatch begin and end belongs to stage
				Theater.fsMachine.getActualFiniteState().display( stageBatch );
			}

			Theater.curtainManager.display();
        }
    }
}