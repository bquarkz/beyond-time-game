package com.intrepid.nicge.gui;

import com.badlogic.gdx.InputProcessor;
import com.intrepid.nicge.kernel.IMouseControllable;
import com.intrepid.nicge.kernel.IUpdatable;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.theater.cameras.Camera;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;

public class WindowsManager
    implements InputProcessor, IMouseControllable, IUpdatable
{
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constants
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Special Fields And Injections
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Fields
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final ComponentContainer< Windows > container;
    private final GraphicsBatch gBatch;
    private final Camera camera;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected WindowsManager()
    {
        this.container = new ComponentContainer<>();
        this.camera = Game.graphics.newNativeCamera();
        this.gBatch = new GraphicsBatch();
        this.gBatch.setProjectionMatrix( this.camera.combined );
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Factories
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static WindowsManager create()
    {
        return new WindowsManager();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Getters And Setters
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Integer addComponent( Windows xwin )
    {
        return container.addComponent( xwin );
    }

    public void disable( Windows xwin )
    {
        container.disable( xwin );
    }

    public void enable( Windows xwin )
    {
        container.enable( xwin );
    }

    private int getXCorrection( int screenX )
    {
        int nativeWidth = Game.common.getGameConfiguration().getNativeResolutionWidth();
        int windowWidth = Game.graphics.getScreenWidth();

        float correction = windowWidth / (float)nativeWidth;

        return ( correction > 1 ? (int)( screenX / correction ) : screenX );
    }

    private int getYCorrection( int screenY )
    {
        int windowHeight = Game.graphics.getScreenHeight();
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

    public void display()
    {
        gBatch.begin();
        container.display( gBatch );
        gBatch.end();
    }

    @Override
    public void checkMouseOver(
            int screenX,
            int screenY )
    {
        container.checkMouseOver( screenX, screenY );
    }

    @Override
    public void mouseButtonPressed(
            int screenX,
            int screenY,
            int button )
    {
        container.mouseButtonPressed( screenX, screenY, button );
    }

    @Override
    public void mouseButtonUnPressed(
            int screenX,
            int screenY,
            int button )
    {
        container.mouseButtonUnPressed( screenX, screenY, button );
    }

    @Override
    public void update()
    {
        camera.update();
        container.update();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Inner Classes And Patterns
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
