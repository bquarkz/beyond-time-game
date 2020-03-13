package com.intrepid.nicge.gui;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.intrepid.nicge.gui.controls.Button;
import com.intrepid.nicge.kernel.IMouseControllable;
import com.intrepid.nicge.kernel.IUpdatable;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.theater.cameras.Camera;
import com.intrepid.nicge.utils.MathUtils;
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
    private IStyle style;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private WindowsManager( IStyle style )
    {
        this.windowsContainer = new WindowsContainer();
        this.commandContainer = new CommandContainer();
        this.camera = Game.graphics.newNativeCamera();
        this.gBatch = new GraphicsBatch();
        this.gBatch.setProjectionMatrix( this.camera.combined );
        this.style = style;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Factories
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static WindowsManager create()
    {
        return create( new DefaultStyle() );
    }

    public static WindowsManager create( IStyle style )
    {
        final WindowsManager windowsManager = new WindowsManager( style );
        Game.common.setInputProcessor( windowsManager );
        return windowsManager;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Getters And Setters
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
        screenX = MathUtils.conversion.getXCorrection( screenX );
        screenY = MathUtils.conversion.getYCorrection( screenY );

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
        screenX = MathUtils.conversion.getXCorrection( screenX );
        screenY = MathUtils.conversion.getYCorrection( screenY );

        mouseButtonUnPressed( screenX, screenY, button );

        return false;
    }

    @Override
    public boolean touchDragged(
            int screenX,
            int screenY,
            int pointer )
    {
        screenX = MathUtils.conversion.getXCorrection( screenX );
        screenY = MathUtils.conversion.getYCorrection( screenY );

        dragged( screenX, screenY, pointer );

        return false;
    }

    @Override
    public boolean mouseMoved(
            int screenX,
            int screenY )
    {
        screenX = MathUtils.conversion.getXCorrection( screenX );
        screenY = MathUtils.conversion.getYCorrection( screenY );

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
        if( windowBundle == null ) return;
        final Optional< IComponent > component = windowsContainer.getComponent( windowBundle.getId() );
        if( !component.isPresent() ) return;
        final Window window = (Window)component.get();
        windowsContainer.getWindows().push( window.getNode() );
        windowsContainer.enable( windowBundle );
    }

    public void closeWindow( Bundle< Window > windowBundle )
    {
        windowsContainer.disable( windowBundle );
        windowsContainer.getWindows().pop();
    }

    public Bundle< Window > addWindow( Window window )
    {
        final Bundle< Window > windowBundle = windowsContainer.addComponent( window );
        ((WindowParameters)windowBundle.getComponent().getParameters()).setStyle( style );
        windowBundle.getComponent().whenClosingDo( () -> closeWindow( windowBundle ) );
        return windowBundle;
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
