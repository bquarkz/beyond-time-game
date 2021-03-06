package com.intrepid.nicge.gui;

import com.badlogic.gdx.graphics.Texture;
import com.intrepid.nicge.gui.controls.AbstractControl;
import com.intrepid.nicge.gui.controls.Button;
import com.intrepid.nicge.utils.MathUtils;
import com.intrepid.nicge.utils.MathUtils.Vector;
import com.intrepid.nicge.utils.containers.TopTailList;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;

import static com.intrepid.nicge.gui.WindowParameters.TITLE_SIZE;
import static com.intrepid.nicge.gui.WindowsManager.*;
import static com.intrepid.nicge.utils.graphics.TextureWorks.createTexture;

public abstract class Window
        extends ComponentContainer
        implements IComponent
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
    private final WindowParameters windowParameters;
    private final CloseButton closeButton;
    private final TopTailList.Node< Window > node;

    private Texture titleTexture;
    private Texture bodyTexture;

    private boolean couldBeDragged;
    private Vector v0;
    private Vector relativePosition;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Window( WindowParameters wp )
    {
        this.windowParameters = WindowParameters.copy( wp );
        this.closeButton = CloseButton.create( this );
        this.node = new TopTailList.Node<>( this );
        this.couldBeDragged = false;
        this.relativePosition = Vector.with( 2, TITLE_SIZE + 2 );
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Factories
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Getters And Setters
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public TopTailList.Node< Window > getNode()
    {
        return node;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public final void bindAssets()
    {
        titleTexture = createTexture(
                windowParameters.getWidth(),
                TITLE_SIZE,
                windowParameters.getStyle().getWindowSchema().getTitleBarColor() );
        bodyTexture = createTexture(
                windowParameters.getWidth(),
                windowParameters.getHeight() - TITLE_SIZE,
                windowParameters.getStyle().getWindowSchema().getBodyBackgroundColor() );
        closeButton.bindAssets();
        getComponents().values().forEach( cc -> {
            if( cc.getComponent() instanceof AbstractControl )
            {
                ( (AbstractControl)cc.getComponent() ).bindAssets();
            }
        } );

        _bindAssets();
    }

    protected void _bindAssets()
    {
    }

    public final void unBindAssets()
    {
        titleTexture.dispose();
        bodyTexture.dispose();
        closeButton.unBindAssets();
        getComponents().values().forEach( cc -> {
            if( cc.getComponent() instanceof AbstractControl )
            {
                ( (AbstractControl)cc.getComponent() ).unBindAssets();
            }
        } );

        _unBindAssets();
    }

    protected void _unBindAssets()
    {
    }

    @Override
    public void update()
    {
        if( windowParameters.isCloseable() )
        {
            closeButton.update();
        }
        super.update();
    }

    @Override
    public final void display( GraphicsBatch batch )
    {
        final Vector gdxTitleDisplayCoordinates = windowParameters.getGdxTitleDisplayCoordinates();
        final Vector gdxBodyDisplayCoordinates = windowParameters.getGdxBodyDisplayCoordinates();

        batch.draw( SHADOW_LIGHTER_TEXTURE,
                gdxBodyDisplayCoordinates.getX() - SHADOW_LIGHTER_DRAW,
                gdxBodyDisplayCoordinates.getY() - SHADOW_LIGHTER_DRAW,
                windowParameters.getWidth() + SHADOW_LIGHTER_EXTRA_SIZE,
                windowParameters.getHeight() + SHADOW_LIGHTER_EXTRA_SIZE );

        batch.draw( SHADOW_DARKER_TEXTURE,
                gdxBodyDisplayCoordinates.getX() - SHADOW_DARKER_DRAW,
                gdxBodyDisplayCoordinates.getY() - SHADOW_DARKER_DRAW,
                windowParameters.getWidth() + SHADOW_DARKER_EXTRA_SIZE,
                windowParameters.getHeight() + SHADOW_DARKER_EXTRA_SIZE );

        batch.draw( SHADOW_TEXTURE,
                gdxBodyDisplayCoordinates.getX() - SHADOW_DRAW,
                gdxBodyDisplayCoordinates.getY() - SHADOW_DRAW,
                windowParameters.getWidth() + SHADOW_EXTRA_SIZE,
                windowParameters.getHeight() + SHADOW_EXTRA_SIZE );

        batch.draw( titleTexture,
                gdxTitleDisplayCoordinates.getX(),
                gdxTitleDisplayCoordinates.getY() );

        batch.draw( bodyTexture,
                gdxBodyDisplayCoordinates.getX(),
                gdxBodyDisplayCoordinates.getY() );

        if( windowParameters.isCloseable() )
        {
            closeButton.display( batch );
        }

        super.display( batch );
    }

    public void failSafe()
    {
        if( windowParameters.isCloseable() )
        {
            closeButton.mouseIsNotOverMe();
        }
    }

    @Override
    public boolean checkMouseOver(
            int screenX,
            int screenY )
    {
        if( windowParameters.isCloseable() )
        {
            closeButton.checkMouseOver( screenX, screenY );
        }

        getComponents().values().forEach( cc -> {
            if( cc.getComponent() instanceof AbstractControl )
            {
                cc.getComponent().checkMouseOver( screenX, screenY );
            }
        } );

        return MathUtils.gdx.checkInside(
                windowParameters.getTitleTopLeftCorner(),
                windowParameters.getBodyBottomRightCorner(),
                Vector.with( screenX, screenY ) );
    }

    @Override
    public boolean mouseButtonPressed(
            int screenX,
            int screenY,
            int button )
    {
        if( windowParameters.isCloseable() )
        {
            closeButton.mouseButtonPressed( screenX, screenY, button );
        }

        if( windowParameters.isMovable() )
        {
            final Vector vector = Vector.with( screenX, screenY );
            couldBeDragged = windowParameters.isInsideTitle( vector );
            if( couldBeDragged )
            {
                v0 = MathUtils.conversion.gdxCoordinates( vector );
            }
        }

        return super.mouseButtonPressed( screenX, screenY, button );
    }

    @Override
    public boolean mouseButtonUnPressed(
            int screenX,
            int screenY,
            int button )
    {
        if( windowParameters.isCloseable() )
        {
            closeButton.mouseButtonUnPressed( screenX, screenY, button );
        }

        couldBeDragged = false;
        return super.mouseButtonUnPressed( screenX, screenY, button );
    }

    @Override
    public boolean dragged(
            int screenX,
            int screenY,
            int button )
    {
        if( windowParameters.isMovable() && couldBeDragged )
        {
            Vector vf = MathUtils.conversion.gdxCoordinates( Vector.with( screenX, screenY ) );
            windowParameters.move( Vector.subtraction( vf, v0 ) );
            v0 = vf;
        }
        return super.dragged( screenX, screenY, button );
    }

    public void enable()
    {
        getComponents().values().forEach( c -> c.getParameters().setEnabled( true ) );
    }

    public void disable()
    {
        getComponents().values().forEach( c -> c.getParameters().setEnabled( false ) );
    }

    @Override
    public ComponentParameters getParameters()
    {
        return windowParameters;
    }

    void whenClosingDo( Button.IButtonAction buttonAction )
    {
        closeButton.setActionRun( buttonAction );
    }

    @Override
    protected < C extends IComponent > void configureComponent( C component )
    {
        component.getParameters().setEnabled( true );
        if( component instanceof AbstractControl )
        {
            AbstractControl control = (AbstractControl)component;
            control.setRelativePosition( 2, 30 + ( 32 * getComponents().size() ) );
            control.setSize( getParameters().getWidth() - 4, TITLE_SIZE );
        }
        else
        {
            component.getParameters().setPosition( Vector.with( 2, TITLE_SIZE + 2 ) );
            component.getParameters().setWidth( getParameters().getWidth() - 4 );
            component.getParameters().setHeight( TITLE_SIZE );
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Inner Classes And Patterns
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static class CloseButton
            extends Button
    {
        private Texture idle;
        private Texture mouseOverMe;
        private Texture actionClicked;
        private Texture supportClicked;

        private CloseButton( Window window )
        {
            this.setParent( window );
        }

        public static CloseButton create( Window window )
        {
            CloseButton closeButton = new CloseButton( window );
            closeButton.setSize( TITLE_SIZE, TITLE_SIZE );
            closeButton.setRelativePosition(window.getParameters().getWidth() - TITLE_SIZE, TITLE_SIZE );
            return closeButton;
        }

        @Override
        public void bindAssets()
        {
            getParent().ifPresent( parent -> {
                IStyle style = ( (WindowParameters)parent.getParameters() ).getStyle();
                IStyle.IButtonSchema schema = style.getWindowSchema().getCloseButtonSchema();
                idle = createTexture( 2, 2, schema.getIDLE() );
                mouseOverMe = createTexture( 2, 2, schema.getMouseOverMe() );
                actionClicked = createTexture( 2, 2, schema.getActionClicked() );
                supportClicked = createTexture( 2, 2, schema.getSupportClicked() );
                setIdle( idle );
                setMouserOverMe( mouseOverMe );
                setActionClicked( actionClicked );
                setSupportClicked( supportClicked );
            } );
        }

        @Override
        public void unBindAssets()
        {
            getParent().ifPresent( parent -> {
                idle.dispose();
                mouseOverMe.dispose();
                actionClicked.dispose();
                supportClicked.dispose();
            } );
        }
    }
}
