/*
  Copyleft (C) 2016  Constantino, Nilton Rogerio <niltonrc@gmail.com>

  @author "Nilton R Constantino"
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
import com.intrepid.game.gui.BlueWindow;
import com.intrepid.game.gui.GreenWindow;
import com.intrepid.game.gui.RedWindow;
import com.intrepid.nicge.gui.Bundle;
import com.intrepid.nicge.gui.Window;
import com.intrepid.nicge.gui.WindowsManager;
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
public class SimulationScene
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
    private final WindowsManager windowsManager;
    private Simulation simulation;
    private Texture background;

    private final Button button;

    private Bundle< Window > blue, red, green;

    private Timer timer;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public SimulationScene()
    {
        this.windowsManager = WindowsManager.create();
        this.button = Button.create();
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    @Override
    public void start()
    {
        windowsManager.openWindow( blue );
        windowsManager.openWindow( red );
        windowsManager.openWindow( green );

        timer = new Timer();
        timer.start();

        simulation = new Simulation();
        c = 0;
        background = Game.common.getAsset( Resources.Textures.BLANK );
    }

    @Override
    public void update()
    {
        camera.update();
        timer.update();
        windowsManager.update();

        Game.debug.addDebugMessage( "SCENE",
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
        batch.begin();
        batch.draw( background, 0, 0 );
        batch.end();

        windowsManager.display();
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

    @Override
    public void prepareEnvironment()
    {
        blue = windowsManager.addWindow( new BlueWindow( 100, 360 ) );
        green = windowsManager.addWindow( new GreenWindow( 450, 250 ) );
        red = windowsManager.addWindow( new RedWindow( 800, 150 ) );

        button.setSize( 32, 32 );
        button.setRelativePosition(
                Game.common.getGameConfiguration().getNativeResolutionWidth() - button.getParameters().getWidth() - 20,
                Game.common.getGameConfiguration().getNativeResolutionHeight()- 20 );
        button.setActionRun( () -> Game.scene.change( RandomScene.class, AllCurtains.IMAGE_FADE ) );
        windowsManager.addCommand( button );
    }

    @Override
    public void bindAssets()
    {
        AnimationPack pack = Game.common.getAsset( Resources.Animations.SENTINEL );
        Animation idle = pack.get( "sentinel.move.down" );
        Animation over = pack.get( "sentinel.move.up" );
        Animation actionClick = pack.get( "sentinel.move.left" );
        Animation supportClick = pack.get( "sentinel.move.right" );
        button.setIdle( idle );
        button.setMouserOverMe( over );
        button.setActionClicked( actionClick );
        button.setSupportClicked( supportClick );

        windowsManager.bindAssets();
    }

    @Override
    public void unBindAssets()
    {
        windowsManager.unBindAssets();
        windowsManager.clear();
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
