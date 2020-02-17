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
import com.intrepid.game.gui.FreakWindows;
import com.intrepid.nicge.gui.Bundle;
import com.intrepid.nicge.gui.Window;
import com.intrepid.nicge.gui.WindowsManager;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.theater.cameras.Camera;
import com.intrepid.nicge.theater.scene.GameScene;
import com.intrepid.nicge.theater.scene.IScene;
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
    private WindowsManager windowsManager;
    private Simulation simulation;
    private Texture background;

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
        windowsManager = WindowsManager.create();
        Bundle< Window > freakWindow1 = windowsManager.addComponent( new FreakWindows( 50, 50 ) );
        Bundle< Window > freakWindow2 = windowsManager.addComponent( new FreakWindows( 450, 450 ) );
        freakWindow1.getComponent().loadAssets();
        freakWindow2.getComponent().loadAssets();
        windowsManager.openWindow( freakWindow1 );
        windowsManager.openWindow( freakWindow2 );

        timer = new Timer();
        timer.start();

        simulation = new Simulation();
        c = 0;
        background = Game.common.getAsset( Resources.Textures.BACK_GROUND );
    }

    @Override
    public void update()
    {
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
        batch.draw( background, 250, 150 );
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

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
