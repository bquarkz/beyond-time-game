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
package com.intrepid.game.scenes;

import com.badlogic.gdx.graphics.Texture;
import com.intrepid.game.Resources;
import com.intrepid.game.curtains.AllCurtains;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.theater.cameras.SeekerCamera;
import com.intrepid.nicge.theater.scene.GameScene;
import com.intrepid.nicge.theater.scene.IScene;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;

@GameScene
public class RandomScene implements IScene
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private int c;
    private SeekerCamera seekerCamera;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public RandomScene()
    {
        c = 0;
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    @Override
    public void start()
    {
        c = 0;
    }

    @Override
    public void update()
    {
        Game.util.addDebugMessage( "SCENE", " MAP SCENE - " + c++ );

        if( c == 150 )
        {
            Game.scene.change( SimulationScene.class, AllCurtains.IMAGE_FADE );
        }
    }

    @Override
    public void display( GraphicsBatch batch )
    {
        Texture texture = Game.common.getAsset( Resources.Textures.BACK_GROUND );
        batch.begin();
        batch.draw( texture, 0, 0 );
        batch.end();
    }

    @Override
    public void configureGraphicsBatch( GraphicsBatch stageBatch )
    {
        int nativeResolutionWidth = Game.common.getGameConfiguration().getNativeResolutionWidth();
        int nativeResolutionHeight = Game.common.getGameConfiguration().getNativeResolutionHeight();
        seekerCamera = new SeekerCamera( nativeResolutionWidth, nativeResolutionHeight );

        stageBatch.setProjectionMatrix( seekerCamera.combined );
    }

    @Override
    public void stop()
    {
    }

    @Override
    public void hibernate()
    {
    }

    @Override
    public void wakeup()
    {
    }

    @Override
    public void inputControlLogic()
    {
    }

    @Override
    public void simulation()
    {
        Game.util.addDebugMessage( "LAST LOOP TIME", Game.time.getLastLoopTime_ns() + "ns" );
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************

}
