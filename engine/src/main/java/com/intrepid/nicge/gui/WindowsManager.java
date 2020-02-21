package com.intrepid.nicge.gui;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.intrepid.nicge.gui.controls.Button;
import com.intrepid.nicge.kernel.IMouseControllable;
import com.intrepid.nicge.kernel.IUpdatable;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.theater.cameras.Camera;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;
import com.intrepid.nicge.utils.graphics.TextureWorks;

import java.util.Optional;

public final class WindowsManager
    implements InputProcessor, IMouseControllable, IUpdatable
{
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constants
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static final Color SHADOW_LIGHTER_COLOR = new Color( 0, 0, 0, 0.1f );
    static final Color SHADOW_DARKER_COLOR = new Color( 0, 0, 0, 0.3f );
    static final Color SHADOW_COLOR = new Color( 0, 0, 0, 0.5f );

    static final int SHADOW_LIGHTER_EXTRA_SIZE = 6;
    static final int SHADOW_DARKER_EXTRA_SIZE = 4;
    static final int SHADOW_EXTRA_SIZE = 2;

    static final int SHADOW_LIGHTER_DRAW = SHADOW_LIGHTER_EXTRA_SIZE / 2;
    static final int SHADOW_DARKER_DRAW = SHADOW_DARKER_EXTRA_SIZE / 2;
    static final int SHADOW_DRAW = SHADOW_EXTRA_SIZE / 2;

    static Texture SHADOW_LIGHTER_TEXTURE = TextureWorks.createTexture( 2, 2, SHADOW_LIGHTER_COLOR );
    static Texture SHADOW_DARKER_TEXTURE = TextureWorks.createTexture( 2, 2, SHADOW_DARKER_COLOR );
    static Texture SHADOW_TEXTURE = TextureWorks.createTexture( 2, 2, SHADOW_COLOR );

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Special Fields And Injections
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Fields
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final WindowsContainer windowsContainer;
    private final CommandContainer commandContainer;
    private final GraphicsBatch gBatch;
    private final Camera camera;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private WindowsManager()
    {
        this.windowsContainer = new WindowsContainer();
        this.commandContainer = new CommandContainer();
        this.camera = Game.graphics.newNativeCamera();
        this.gBatch = new GraphicsBatch();
        this.gBatch.setProjectionMatrix( this.camera.combined );
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Factories
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static WindowsManager create()
    {
        final WindowsManager windowsManager = new WindowsManager();
        Game.common.setInputProcessor( windowsManager );
        return windowsManager;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Getters And Setters
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
        screenX = getXCorrection( screenX );
        screenY = getYCorrection( screenY );

        dragged( screenX, screenY, pointer );

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
        windowsContainer.display( gBatch );
        commandContainer.display( gBatch );
        gBatch.end();
    }

    @Override
    public boolean checkMouseOver(
            int screenX,
            int screenY )
    {
        boolean windows = windowsContainer.checkMouseOver( screenX, screenY );
        boolean commands = commandContainer.checkMouseOver( screenX, screenY );
        return windows || commands;
    }

    @Override
    public boolean mouseButtonPressed(
            int screenX,
            int screenY,
            int button )
    {
        boolean windows = windowsContainer.mouseButtonPressed( screenX, screenY, button );
        boolean commands = commandContainer.mouseButtonPressed( screenX, screenY, button );
        return windows || commands;
    }

    @Override
    public boolean mouseButtonUnPressed(
            int screenX,
            int screenY,
            int button )
    {
        boolean windows = windowsContainer.mouseButtonUnPressed( screenX, screenY, button );
        boolean commands = commandContainer.mouseButtonUnPressed( screenX, screenY, button );
        return windows || commands;
    }

    @Override
    public void dragged(
            int screenX,
            int screenY,
            int button )
    {
        windowsContainer.dragged( screenX, screenY, button );
    }

    @Override
    public void update()
    {
        camera.update();
        windowsContainer.update();
        commandContainer.update();
    }

    public void openWindow( Bundle< Window > windowBundle )
    {
        final Optional< IComponent > component = windowsContainer.getComponent( windowBundle.getId() );
        if( !component.isPresent() ) return;
        final Window window = (Window)component.get();
        windowsContainer.getWindows().push( window.getNode() );
        windowsContainer.enable( windowBundle );
    }

    public Bundle< Window > addWindow( Window window )
    {
        return windowsContainer.addComponent( window );
    }

    public Bundle< Button > addCommand( Button command )
    {
        Bundle< Button > commandBundle = commandContainer.addComponent( command );
        commandContainer.enable( commandBundle );
        return commandBundle;
    }

    public void bindAssets()
    {
        SHADOW_LIGHTER_TEXTURE = TextureWorks.createTexture( 2, 2, SHADOW_LIGHTER_COLOR );
        SHADOW_DARKER_TEXTURE = TextureWorks.createTexture( 2, 2, SHADOW_DARKER_COLOR );
        SHADOW_TEXTURE = TextureWorks.createTexture( 2, 2, SHADOW_COLOR );

        for( ComponentWrapper component : windowsContainer.getComponents().values() )
        {
            ( (Window)component.getComponent() ).bindAssets();
        }
    }

    public void unBindAssets()
    {
        for( ComponentWrapper component : windowsContainer.getComponents().values() )
        {
            ( (Window)component.getComponent() ).unBindAssets();
        }

        SHADOW_TEXTURE.dispose();
        SHADOW_DARKER_TEXTURE.dispose();
        SHADOW_LIGHTER_TEXTURE.dispose();
    }

    public void clear()
    {
        windowsContainer.clear();
        commandContainer.clear();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Inner Classes And Patterns
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
