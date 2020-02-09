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
package com.intrepid.nicge.theater.curtain;

import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.theater.cameras.Camera;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;

import java.util.Optional;

public class CurtainManager
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private Curtain[] curtains;
    private boolean isInTransition;

    private final GraphicsBatch batch;
    private final Camera camera;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public CurtainManager()
    {
        curtains = new Curtain[ CurtainLayer.values().length ];
        isInTransition = false;
        camera = Game.graphics.newNativeCamera();
        batch = new GraphicsBatch();
        batch.setProjectionMatrix( camera.combined );
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    private void update( CurtainLayer layer )
    {
        getCurtain( layer ).ifPresent( curtain -> {
            boolean isTransitionFinished = curtain.isTransitionFinished();
            isInTransition |= isTransitionFinished;
            if( !isTransitionFinished )
            {
                curtain.update();
            }
        } );
    }

    public void update()
    {
        camera.update();
        if( !isInTransition )
        {
            return;
        }

        update( CurtainLayer.MAIN );
        update( CurtainLayer.HUD_INTERFACE );
        update( CurtainLayer.BEHIND );
    }

    private void display( CurtainLayer layer )
    {
        getCurtain( layer )
                .filter( curtain -> !curtain.isTransitionFinished() )
                .ifPresent( curtain -> curtain.display( batch ) );
    }

    public void display()
    {
        if( !isInTransition )
        {
            return;
        }

        // following sequence are very VERY important
        // the main curtain should be last one
        batch.begin();
        display( CurtainLayer.BEHIND );
        display( CurtainLayer.HUD_INTERFACE );
        getCurtain( CurtainLayer.MAIN ).ifPresent( curtain -> curtain.display( batch ) );
        batch.end();
    }

    private void openCommand( CurtainLayer layer )
    {
        isInTransition = true;
        getCurtain( layer ).ifPresent( Curtain::openCommand );
    }

    private void closeCommand( CurtainLayer layer )
    {
        isInTransition = true;
        getCurtain( layer ).ifPresent( curtain -> {
            curtain.initRunBeforeCloseCommand();
            curtain.closeCommand();
        } );
    }

    public void openCommand()
    {
        openCommand( CurtainLayer.MAIN );
        openCommand( CurtainLayer.HUD_INTERFACE );
        openCommand( CurtainLayer.BEHIND );
    }

    public void closeCommand()
    {
        closeCommand( CurtainLayer.BEHIND );
        closeCommand( CurtainLayer.HUD_INTERFACE );
        closeCommand( CurtainLayer.MAIN );
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public void setCurtain(
            CurtainLayer layer,
            Curtain curtain )
    {
        curtains[ layer.getPosition() ] = curtain;
    }

    public Optional< Curtain > getCurtain( CurtainLayer layer )
    {
        return Optional.ofNullable( curtains[ layer.getPosition() ] );
    }

    public boolean isInTransition()
    {
        return isInTransition;
    }

    // ****************************************************************************************
    // Patterns
    // ***************************************************************************************

}
