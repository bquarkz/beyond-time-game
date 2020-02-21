package com.intrepid.nicge.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.intrepid.nicge.utils.MathUtils;
import com.intrepid.nicge.utils.MathUtils.Vector;
import com.intrepid.nicge.utils.containers.TopTailList;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;
import com.intrepid.nicge.utils.graphics.TextureWorks;

import static com.intrepid.nicge.gui.WindowParameters.TITLE_SIZE;

public abstract class Window
        extends ComponentContainer
        implements IComponent
{
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constants
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final Color SHADOW_COLOR = new Color( 0, 0, 0, 0.5f );
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Special Fields And Injections
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Fields
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final WindowParameters windowParameters;
    private final Texture titleTexture;
    private final Texture bodyTexture;
    private final Texture rightShadow;
    private final Texture bottomShadow;

    private final Texture debugSquare;
    private final TopTailList.Node< Window > node;

    private boolean couldBeDragged;
    private Vector v0;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Window( WindowParameters wp )
    {
        this.windowParameters = new WindowParameters( wp );
        this.titleTexture = TextureWorks.createTexture( wp.getWidth(), TITLE_SIZE, wp.getTitleColor() );
        this.bodyTexture = TextureWorks.createTexture( wp.getWidth(), wp.getHeight() - TITLE_SIZE, wp.getBodyColor() );
        this.rightShadow = TextureWorks.createTexture( 3, wp.getHeight(), SHADOW_COLOR );
        this.bottomShadow = TextureWorks.createTexture( wp.getWidth() - 3, 3, SHADOW_COLOR );
        this.debugSquare = TextureWorks.createTexture( 2, 2, Color.YELLOW );
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
    public abstract void loadAssets();

    @Override
    public void update()
    {
        super.update();
    }

    @Override
    public final void display( GraphicsBatch batch )
    {
        final Vector titleDisplayCoordinates = windowParameters.getTitleDisplayCoordinates();
        final Vector bodyDisplayCoordinates = windowParameters.getBodyDisplayCoordinates();


        batch.draw( bottomShadow, bodyDisplayCoordinates.getX() + 2, bodyDisplayCoordinates.getY() - 2 );
        batch.draw( rightShadow, bodyDisplayCoordinates.getX() + windowParameters.getWidth() - 1, bodyDisplayCoordinates.getY() - 2 );
        batch.draw( titleTexture, titleDisplayCoordinates.getX(), titleDisplayCoordinates.getY() );
        batch.draw( bodyTexture, bodyDisplayCoordinates.getX(), bodyDisplayCoordinates.getY() );

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
    public void mouseButtonPressed(
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
        super.mouseButtonPressed( screenX, screenY, button );
    }

    @Override
    public void mouseButtonUnPressed(
            int screenX,
            int screenY,
            int button )
    {
        couldBeDragged = false;
        super.mouseButtonUnPressed( screenX, screenY, button );
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
}
