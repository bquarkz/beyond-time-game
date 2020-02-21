package com.intrepid.nicge.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Window( WindowParameters wp )
    {
        this.windowParameters = new WindowParameters( wp );
        this.closeButton = CloseButton.create( this );
        this.node = new TopTailList.Node<>( this );
        this.couldBeDragged = false;
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
        titleTexture = createTexture( 2, 2, windowParameters.getTitleColor() );
        bodyTexture = createTexture( 2, 2, windowParameters.getBodyColor() );
        closeButton.bindAssets();

        _bindAssets();
    }

    protected abstract void _bindAssets();

    public final void unBindAssets()
    {
        titleTexture.dispose();
        bodyTexture.dispose();
        closeButton.unBindAssets();

        _unBindAssets();
    }

    protected abstract void _unBindAssets();

    @Override
    public void update()
    {
        closeButton.update();
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
                gdxTitleDisplayCoordinates.getY(),
                windowParameters.getWidth(),
                TITLE_SIZE );

        batch.draw( bodyTexture,
                gdxBodyDisplayCoordinates.getX(),
                gdxBodyDisplayCoordinates.getY(),
                windowParameters.getWidth(),
                windowParameters.getHeight() - TITLE_SIZE );

        closeButton.display( batch );

        super.display( batch );
    }

    @Override
    public boolean checkMouseOver(
            int screenX,
            int screenY )
    {
        closeButton.checkMouseOver( screenX, screenY );

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
        closeButton.mouseButtonPressed( screenX, screenY, button );

        final Vector vector = Vector.with( screenX, screenY );
        couldBeDragged = windowParameters.isInsideTitle( vector );
        if( couldBeDragged )
        {
            v0 = MathUtils.conversion.gdxCoordinates( vector );
        }
        return super.mouseButtonPressed( screenX, screenY, button );
    }

    @Override
    public boolean mouseButtonUnPressed(
            int screenX,
            int screenY,
            int button )
    {
        closeButton.mouseButtonUnPressed( screenX, screenY, button );

        couldBeDragged = false;
        return super.mouseButtonUnPressed( screenX, screenY, button );
    }

    @Override
    public void dragged(
            int screenX,
            int screenY,
            int button )
    {
        if( couldBeDragged )
        {
            Vector vf = MathUtils.conversion.gdxCoordinates( Vector.with( screenX, screenY ) );
            windowParameters.move( Vector.subtraction( vf, v0 ) );
            v0 = vf;
        }
        super.dragged( screenX, screenY, button );
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

        public void bindAssets()
        {
            idle = createTexture( 2, 2, Color.GRAY );
            mouseOverMe = createTexture( 2, 2, Color.GREEN );
            actionClicked = createTexture( 2, 2, Color.RED );
            supportClicked = createTexture( 2, 2, Color.BLUE );

            setIdle( idle );
            setMouserOverMe( mouseOverMe );
            setActionClicked( actionClicked );
            setSupportClicked( supportClicked );
        }

        public void unBindAssets()
        {
            idle.dispose();
            mouseOverMe.dispose();
            actionClicked.dispose();
            supportClicked.dispose();
        }
    }
}
