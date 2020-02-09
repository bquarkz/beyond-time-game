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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.intrepid.game.Resources;
import com.intrepid.game.curtains.AllCurtains;
import com.intrepid.nicge.gui.Windows;
import com.intrepid.nicge.gui.WindowsManager;
import com.intrepid.nicge.gui.WindowsParameters;
import com.intrepid.nicge.gui.controls.Button;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.theater.cameras.Camera;
import com.intrepid.nicge.theater.scene.GameScene;
import com.intrepid.nicge.theater.scene.IScene;
import com.intrepid.nicge.utils.animation.Animation;
import com.intrepid.nicge.utils.animation.AnimationPack;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;
import com.intrepid.nicge.utils.timer.Timer;
import org.bquarkz.beyondtime.simulator.Simulation;

@GameScene
public class CombatScene
        implements IScene
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************
    private static final long OPTIMAL_TIME = 1_000_000_000 / Game.common.getGameConfiguration().getRefreshRate();
    private static final int MAX_NUMBER_OF_UPDATES = 10;
    private static final long STEP = OPTIMAL_TIME / MAX_NUMBER_OF_UPDATES;

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private int c;
    private Camera camera;
    private WindowsManager xwManager;
    private Simulation simulation;

    private Timer timer;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    @Override
    public void start()
    {
        c = 0;

        simulation = new Simulation();

        AnimationPack pack = Game.common.getAsset( Resources.Animations.SENTINEL );

        Animation idle = pack.get( "sentinel.move.down" );
        Animation over = pack.get( "sentinel.move.up" );
        Animation aclicked = pack.get( "sentinel.move.left" );
        Animation sclicked = pack.get( "sentinel.move.right" );

        Button b = Button.create();
        b.setIdle( idle );
        b.setMouserOverMe( over );
        b.setActionClicked( aclicked );
        b.setSupportClicked( sclicked );
        b.setScreenPosition( 250, 150 );
        b.setSize( 32, 32 );
        b.setActionRun( () -> Game.scene.change( MapScene.class, AllCurtains.IMAGE_FADE ) );

        final Windows w = new Windows( new WindowsParameters( 150, 120, 300, 150, false, Color.BROWN, Color.FIREBRICK ) );
        w.addComponent( b );

        xwManager = WindowsManager.create();
        xwManager.addComponent( w );
        Gdx.input.setInputProcessor( xwManager );

        timer = new Timer();
        timer.start();
    }

    @Override
    public void update()
    {
        timer.update();
        xwManager.update();

        Game.util.addDebugMessage( "SCENE",
                "COUNTER: " + c,
                "TIMER: " + timer.getTotalTime_s() + "s",
                "SIMULATION-PERIOD: " + String.format( "%.2f", simulation
                        .report()
                        .periodControl()
                        .currentPeriodPercent() ) );
    }

    @Override
    public void display( GraphicsBatch batch )
    {
        xwManager.display();
    }

    @Override
    public void configureGraphicsBatch( GraphicsBatch stageBatch )
    {
        camera = Game.graphics.newNativeCamera();
        stageBatch.setProjectionMatrix( camera.combined );
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
        int nUpdates = 0;
        long lag = Game.time.getElapsedTime_ns();
        while( lag >= OPTIMAL_TIME && nUpdates++ < MAX_NUMBER_OF_UPDATES )
        {
            simulation.step( STEP );
            lag -= OPTIMAL_TIME;
        }
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
