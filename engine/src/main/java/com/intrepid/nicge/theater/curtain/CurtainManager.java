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

import com.intrepid.nicge.kernel.AppConfiguration;
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
    private Curtain[] layers;
    private boolean isInTransition;

    private final GraphicsBatch batch;
    private final Camera camera;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public CurtainManager()
    {
        this.layers = new Curtain[ CurtainLayer.values().length ];
        isInTransition = false;

        AppConfiguration gameCfg = Game.common.getGameConfiguration();
        int nativeResolutionWidth = gameCfg.getNativeResolutionWidth();
        int nativeResolutionHeight = gameCfg.getNativeResolutionHeight();
        camera = new Camera( nativeResolutionWidth, nativeResolutionHeight );
        batch = new GraphicsBatch();
        batch.setProjectionMatrix( camera.combined );
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    public void update()
    {
        camera.update();

		if( !isInTransition )
		{
			return;
		}

        for( Curtain curtain : layers )
        {
            if( curtain != null )
            {
                boolean courtainTransitionCondition = curtain.isTransitionFinished();
                isInTransition |= courtainTransitionCondition;
                if( !courtainTransitionCondition )
                {
                    curtain.update();
                }
            }
        }
    }

    private void displayMainCurtain()
    {
        Curtain curtain = layers[ CurtainLayer.MAIN.getPosition() ];
        if( curtain != null )
        {
            curtain.display( batch );
        }
    }

    private void displayCurtain( Curtain curtain )
    {
        if( curtain != null )
        {
            boolean courtainTransitionCondition = curtain.isTransitionFinished();
            if( !courtainTransitionCondition )
            {
                curtain.display( batch );
            }
        }
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
        displayCurtain( layers[ CurtainLayer.BEHAIND.getPosition() ] );
        displayCurtain( layers[ CurtainLayer.HUD_INTERFACE.getPosition() ] );
        displayMainCurtain();
        batch.end();
    }

    public void closeCommand( CurtainLayer layer )
    {
        isInTransition = true;
        layers[ layer.getPosition() ].initRunBeforeCloseCommand();
        layers[ layer.getPosition() ].closeCommand();
    }

    public void openCommand( CurtainLayer layer )
    {
        isInTransition = true;
        layers[ layer.getPosition() ].openCommand();
    }

    public void isTransitionFinished( CurtainLayer layer )
    {
        layers[ layer.getPosition() ].isTransitionFinished();
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public void setCurtain(
            CurtainLayer layer,
            Curtain curtain )
    {
        layers[ layer.getPosition() ] = curtain;
    }

    public Optional< Curtain > getCurtain( CurtainLayer layer )
    {
        return Optional.ofNullable( layers[ layer.getPosition() ] );
    }

    public boolean isInTransition()
    {
        return isInTransition;
    }

    // ****************************************************************************************
    // Patterns
    // ***************************************************************************************

}
