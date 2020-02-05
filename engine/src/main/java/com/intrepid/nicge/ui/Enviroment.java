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
 * all community without problems.
 */
package com.intrepid.nicge.ui;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;

public class Enviroment extends Container implements InputProcessor
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************
    static final int LAYER_BOTTON = 1;
    static final int LAYER_MIDDLE = 2;
    static final int LAYER_TOP = 3;

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private final List< ComponentControl > layerBotton;
    private final List< ComponentControl > layerMiddle;
    private final List< ComponentControl > layerTop;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    protected Enviroment()
    {
        this.layerBotton = new ArrayList<>();
        this.layerMiddle = new ArrayList<>();
        this.layerTop = new ArrayList<>();
    }

    // ****************************************************************************************
    // Factories
    // ****************************************************************************************
    public static Enviroment create()
    {
        Enviroment o = new Enviroment();
        return o;
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    @Override
    public Integer addComponent( IComponent component )
    {
        Integer id = super.addComponent( component );
        sendToMiddleLayer( id );
        return id;
    }

    public void sendToBottonLayer( Integer id )
    {
        ComponentControl cc = getComponents().get( id );
        if( cc == null )
        {
            return;
        }

        changeLayerTo( cc, LAYER_BOTTON );
    }

    public void sendToMiddleLayer( Integer id )
    {
        ComponentControl cc = getComponents().get( id );
        if( cc == null )
        {
            return;
        }

        changeLayerTo( cc, LAYER_MIDDLE );
    }

    public void sendToTopLayer( Integer id )
    {
        ComponentControl cc = getComponents().get( id );
        if( cc == null )
        {
            return;
        }

        changeLayerTo( cc, LAYER_TOP );
    }

    private void changeLayerTo(
            ComponentControl cc,
            int layer )
    {
        Integer oldLayer = cc.getConfig().getLayer();

        cc.getConfig().setLayer( layer );

        switch( oldLayer )
        {
            case LAYER_BOTTON:
            {
                layerBotton.remove( cc );
            }
            break;

            case LAYER_MIDDLE:
            {
                layerMiddle.remove( cc );
            }
            break;

            case LAYER_TOP:
            {
                layerTop.remove( cc );
            }
            break;
        }

        switch( layer )
        {
            case LAYER_BOTTON:
            {
                layerBotton.add( cc );
            }
            break;

            case LAYER_MIDDLE:
            {
                layerMiddle.add( cc );
            }
            break;

            case LAYER_TOP:
            {
                layerTop.add( cc );
            }
            break;
        }
    }

    @Override
    public void display( GraphicsBatch batch )
    {
        ComponentControl.runIfEnabled( layerBotton, o -> o.display( batch ) );
        ComponentControl.runIfEnabled( layerMiddle, o -> o.display( batch ) );
        ComponentControl.runIfEnabled( layerTop, o -> o.display( batch ) );
    }

    private int getXCorrection( int screenX )
    {
        int nativeWidth = Game.common.getGameConfiguration().getNativeResolutionWidth();
        int windowWidth = Gdx.graphics.getWidth();

        float correction = windowWidth / (float)nativeWidth;

        return ( correction > 1 ? (int)( screenX / correction ) : screenX );
    }

    private int getYCorrection( int screenY )
    {
        int windowHeight = Gdx.graphics.getHeight();
        screenY = windowHeight - screenY;

        int nativeHeight = Game.common.getGameConfiguration().getNativeResolutionHeight();

        float correction = windowHeight / (float)nativeHeight;

        return ( correction > 1 ? (int)( screenY / correction ) : screenY );
    }

    @Override
    public boolean keyDown( int keycode )
    {
        return false;
    }

    @Override
    public boolean keyUp( int keycode )
    {
        return false;
    }

    @Override
    public boolean keyTyped( char character )
    {
        return false;
    }

    @Override
    public boolean touchDown(
            int screenX,
            int screenY,
            int pointer,
            int button )
    {
        screenX = getXCorrection( screenX );
        screenY = getYCorrection( screenY );

        mouseButtonPressed( screenX, screenY, button );

        return false;
    }

    @Override
    public boolean touchUp(
            int screenX,
            int screenY,
            int pointer,
            int button )
    {
        screenX = getXCorrection( screenX );
        screenY = getYCorrection( screenY );

        mouseButtonUnPressed( screenX, screenY, button );

        return false;
    }

    @Override
    public boolean touchDragged(
            int screenX,
            int screenY,
            int pointer )
    {
        return false;
    }

    @Override
    public boolean mouseMoved(
            int screenX,
            int screenY )
    {
        screenX = getXCorrection( screenX );
        screenY = getYCorrection( screenY );

        checkMouseOver( screenX, screenY );

        return false;
    }

    @Override
    public boolean scrolled( int amount )
    {
        return false;
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
