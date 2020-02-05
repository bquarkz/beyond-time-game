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

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.intrepid.game.Resources;
import com.intrepid.game.courtains.AllCurtains;
import com.intrepid.game.system.campaing.GroupInfo;
import com.intrepid.game.system.combat.CombatManager;
import com.intrepid.game.system.sheet.Sheet;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.theater.cameras.SeekerCamera;
import com.intrepid.nicge.theater.scene.GameScene;
import com.intrepid.nicge.theater.scene.Scene;
import com.intrepid.nicge.ui.Button;
import com.intrepid.nicge.ui.Enviroment;
import com.intrepid.nicge.utils.animation.Animation;
import com.intrepid.nicge.utils.animation.AnimationPack;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;
import org.bquarkz.beyondtime.simulator.Simulation;

@GameScene
public class CombatScene implements Scene
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private int c;
    private SeekerCamera seekerCamera;
    private CombatManager combatManager;
    private Enviroment enviroment;
    private Simulation simulation;

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
        enviroment = Enviroment.create();
        enviroment.addComponent( b );
        Gdx.input.setInputProcessor( enviroment );

        Random random = new Random( System.currentTimeMillis() );
        GroupInfo players = GroupInfo.create();
        players.add( Sheet.createWithRandomAttributes( random, "LUKE" ) );
        players.add( Sheet.createWithRandomAttributes( random, "SUZANE" ) );
        players.add( Sheet.createWithRandomAttributes( random, "MARK" ) );
        players.add( Sheet.createWithRandomAttributes( random, "STEPHEN" ) );

        GroupInfo enemies = GroupInfo.create();
        enemies.add( Sheet.createWithRandomAttributes( random, "ROGER" ) );
        enemies.add( Sheet.createWithRandomAttributes( random, "MEGANE" ) );
        enemies.add( Sheet.createWithRandomAttributes( random, "SPARK" ) );
        enemies.add( Sheet.createWithRandomAttributes( random, "UNCLE BOB" ) );

        combatManager = CombatManager.create( players, enemies );
        combatManager.setupCombat();
    }

    @Override
    public void update()
    {
        Game.util.addDebugMessage( "SCENE", "COMBAT SCENE - " + c++ );

        if( c == 890 )
        {
            Game.scene.change( MapScene.class, AllCurtains.IMAGE_FADE );
        }

        combatManager.update();
        enviroment.update();
    }

    @Override
    public void display( GraphicsBatch batch )
    {
        batch.begin();
        enviroment.display( batch );
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
    public void captureControl()
    {
    }

    @Override
    public void simulation()
    {
        simulation.step( (long)( Game.time.getRawDeltaTime() * 1_000_000_000 ) );
        Game.util.addDebugMessage( "KOTLIN-SIMULATION-PERIOD",
                String.format( "%.2f", simulation.report().periodControl().currentPeriodPercent() ) );
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
