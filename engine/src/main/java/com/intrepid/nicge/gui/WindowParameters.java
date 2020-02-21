package com.intrepid.nicge.gui;

import com.badlogic.gdx.graphics.Color;
import com.intrepid.nicge.utils.MathUtils;
import com.intrepid.nicge.utils.MathUtils.Vector;

public class WindowParameters
    extends ComponentParameters
{
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constants
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static final int TITLE_SIZE = 30;
    private static final Color DEFAULT_TITLE_COLOR = new Color( 0xfc766aff );
    private static final Color DEFAULT_BODY_COLOR = new Color( 0x5b84b1af );

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Special Fields And Injections
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Fields
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private boolean scrollable;
    private final Color titleColor;
    private final Color bodyColor;

    private Vector titleTopLeftCorner;
    private Vector titleBottomRightCorner;

    private Vector bodyTopLeftCorner;
    private Vector bodyBottomRightCorner;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public WindowParameters(
            Vector position,
            int width,
            int height )
    {
        this( position, width, height, true, DEFAULT_TITLE_COLOR, DEFAULT_BODY_COLOR );
    }

    public WindowParameters( WindowParameters p )
    {
        this( p.getPosition(), p.getWidth(), p.getHeight(), p.scrollable, p.getTitleColor(), p.getBodyColor() );
    }

    public WindowParameters(
            Vector position,
            int width,
            int height,
            boolean scrollable,
            Color titleColor,
            Color bodyColor )
    {
        super( Vector.with( position ), width, height );
        this.scrollable = scrollable;
        this.titleColor = titleColor;
        this.bodyColor = bodyColor;

        this.titleTopLeftCorner = buildTitleTopLeftCorner();
        this.titleBottomRightCorner = buildTitleBottomRightCorner();
        this.bodyTopLeftCorner = buildBodyTopLeftCorner();
        this.bodyBottomRightCorner = buildBodyBottomRightCorner();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Factories
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Getters And Setters
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isScrollable()
    {
        return scrollable;
    }

    public Color getTitleColor()
    {
        return titleColor;
    }

    public Color getBodyColor()
    {
        return bodyColor;
    }

    public Vector getTitleTopLeftCorner()
    {
        return titleTopLeftCorner;
    }

    public Vector getTitleBottomRightCorner()
    {
        return titleBottomRightCorner;
    }

    public Vector getBodyTopLeftCorner()
    {
        return bodyTopLeftCorner;
    }

    public void setBodyTopLeftCorner( Vector bodyTopLeftCorner )
    {
        this.bodyTopLeftCorner = bodyTopLeftCorner;
    }

    public Vector getBodyBottomRightCorner()
    {
        return bodyBottomRightCorner;
    }

    public void setBodyBottomRightCorner( Vector bodyBottomRightCorner )
    {
        this.bodyBottomRightCorner = bodyBottomRightCorner;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Vector buildBodyBottomRightCorner()
    {
        return MathUtils.conversion.gdxCoordinates( getPosition().getX() + getWidth(), getPosition().getY() + getHeight() );
    }

    private Vector buildBodyTopLeftCorner()
    {
        return MathUtils.conversion.gdxCoordinates( getPosition().getX(), getPosition().getY() + TITLE_SIZE );
    }

    private Vector buildTitleBottomRightCorner()
    {
        return MathUtils.conversion.gdxCoordinates( getPosition().getX() + getWidth(), getPosition().getY() + TITLE_SIZE );
    }

    private Vector buildTitleTopLeftCorner()
    {
        return MathUtils.conversion.gdxCoordinates( getPosition() );
    }

    public Vector getGdxTitleDisplayCoordinates()
    {
        return MathUtils.conversion.gdxCoordinates( getPosition().getX(), ( getPosition().getY() + TITLE_SIZE ) );
    }

    public Vector getGdxBodyDisplayCoordinates()
    {
        return MathUtils.conversion.gdxCoordinates( getPosition().getX(), ( getPosition().getY() + getHeight() ) );
    }

    public boolean isInsideTitle( Vector vector )
    {
        return MathUtils.gdx.checkInside( titleTopLeftCorner, titleBottomRightCorner, vector );
    }

    public boolean isInsideBody( Vector vector )
    {
        return MathUtils.gdx.checkInside( bodyTopLeftCorner, bodyBottomRightCorner, vector );
    }

    public void move( Vector distance )
    {
        this.setPosition( Vector.sum( getPosition(), distance ) );
        this.titleTopLeftCorner = buildTitleTopLeftCorner();
        this.titleBottomRightCorner = buildTitleBottomRightCorner();
        this.bodyTopLeftCorner = buildBodyTopLeftCorner();
        this.bodyBottomRightCorner = buildBodyBottomRightCorner();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Inner Classes And Patterns
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
