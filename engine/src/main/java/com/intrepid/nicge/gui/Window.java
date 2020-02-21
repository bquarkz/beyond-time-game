package com.intrepid.nicge.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.intrepid.nicge.gui.controls.Button;
import com.intrepid.nicge.utils.MathUtils;
import com.intrepid.nicge.utils.MathUtils.Vector;
import com.intrepid.nicge.utils.containers.TopTailList;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;
import com.intrepid.nicge.utils.graphics.TextureWorks;

import static com.intrepid.nicge.gui.WindowParameters.TITLE_SIZE;
import static com.intrepid.nicge.gui.WindowsManager.*;

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
//    private final CloseButton closeButton;
    private final TopTailList.Node< Window > node;

    private Texture titleTexture;
    private Texture bodyTexture;
    private Texture shadowLighterTexture;
    private Texture shadowDarkerTexture;
    private Texture shadowTexture;

    private boolean couldBeDragged;
    private Vector v0;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Window( WindowParameters wp )
    {
        this.windowParameters = new WindowParameters( wp );
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
        this.titleTexture = TextureWorks.createTexture(
                windowParameters.getWidth(),
                TITLE_SIZE,
                windowParameters.getTitleColor() );

        this.bodyTexture = TextureWorks.createTexture(
                windowParameters.getWidth(),
                windowParameters.getHeight() - TITLE_SIZE,
                windowParameters.getBodyColor() );

        _bindAssets();
    }

    protected abstract void _bindAssets();

    public final void unBindAssets()
    {
        titleTexture.dispose();
        bodyTexture.dispose();

        _unBindAssets();
    }

    protected abstract void _unBindAssets();

    @Override
    public void update()
    {
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

        batch.draw( titleTexture, gdxTitleDisplayCoordinates.getX(), gdxTitleDisplayCoordinates.getY() );
        batch.draw( bodyTexture, gdxBodyDisplayCoordinates.getX(), gdxBodyDisplayCoordinates.getY() );

        super.display( batch );
    }

    @Override
    public boolean checkMouseOver(
            int screenX,
            int screenY )
    {
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
        Vector vector = Vector.with( screenX, screenY );
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
        getComponents().values().forEach( c -> c.getComponentParameters().setEnabled( true ) );
    }

    public void disable()
    {
        getComponents().values().forEach( c -> c.getComponentParameters().setEnabled( false ) );
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Inner Classes And Patterns
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static class CloseButton
            extends Button
    {
        public static CloseButton create()
        {
            return new CloseButton();
        }
    }
}
