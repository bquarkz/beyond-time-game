package com.intrepid.nicge.gui;

import com.badlogic.gdx.graphics.Color;
import com.intrepid.nicge.utils.MathUtils;
import com.intrepid.nicge.utils.MathUtils.Vector;

public class WindowParameters
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
    private Vector position;
    private int width;
    private int height;
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
        this( p.position, p.width, p.height, p.scrollable, p.getTitleColor(), p.getBodyColor() );
    }

    public WindowParameters(
            Vector position,
            int width,
            int height,
            boolean scrollable,
            Color titleColor,
            Color bodyColor )
    {
        this.position = Vector.with( position );
        this.width = width;
        this.height = height;
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
    public Vector getPosition()
    {
        return position;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

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
//        return Dot.with( position.getX() + width, position.getY() + height );
        return MathUtils.conversion.gdxCoordinates( position.getX() + width, position.getY() + height );
    }

    private Vector buildBodyTopLeftCorner()
    {
//        return Dot.with( position.getX(), position.getY() + TITLE_SIZE );
        return MathUtils.conversion.gdxCoordinates( position.getX(), position.getY() + TITLE_SIZE );
    }

    private Vector buildTitleBottomRightCorner()
    {
//        return Dot.with( position.getX() + width, position.getY() + TITLE_SIZE );
        return MathUtils.conversion.gdxCoordinates( position.getX() + width, position.getY() + TITLE_SIZE );
    }

    private Vector buildTitleTopLeftCorner()
    {
//        return Dot.with( position );
        return MathUtils.conversion.gdxCoordinates( position );
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
        final Vector temp = MathUtils.conversion.gdxCoordinates( vector );
        return MathUtils.gdx.checkInside( titleTopLeftCorner, titleBottomRightCorner, vector );
    }

    public boolean isInsideBody( Vector vector )
    {
        //final Dot temp = MathUtils.conversion.innerCoordinates( dot );
        return MathUtils.gdx.checkInside( bodyTopLeftCorner, bodyBottomRightCorner, vector );
    }

    public void move( Vector distance )
    {
        this.position = Vector.sum( position, distance );
        this.titleTopLeftCorner = buildTitleTopLeftCorner();
        this.titleBottomRightCorner = buildTitleBottomRightCorner();
        this.bodyTopLeftCorner = buildBodyTopLeftCorner();
        this.bodyBottomRightCorner = buildBodyBottomRightCorner();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Inner Classes And Patterns
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
