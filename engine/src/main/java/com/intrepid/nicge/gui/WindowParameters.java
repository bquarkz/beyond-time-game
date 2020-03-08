package com.intrepid.nicge.gui;

import com.badlogic.gdx.graphics.Color;
import com.intrepid.nicge.gui.layouts.Layouts;
import com.intrepid.nicge.gui.styles.Styles;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.utils.MathUtils;
import com.intrepid.nicge.utils.MathUtils.Vector;

public class WindowParameters
        extends ComponentParameters
{
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constants
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static final int TITLE_SIZE = 30 * ( Game.common.getGameConfiguration().getWindowResolutionHeight() / 720 );

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Special Fields And Injections
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private final Vector position;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Fields
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private boolean scrollable;
    private boolean movable;
    private boolean closeable;

    private IStyle style;
    private ILayout layout;

    private Vector titleTopLeftCorner;
    private Vector titleBottomRightCorner;

    private Vector bodyTopLeftCorner;
    private Vector bodyBottomRightCorner;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private WindowParameters( WindowParameters p )
    {
        this( p.getPosition(),
                p.getWidth(),
                p.getHeight(),
                p.scrollable,
                p.movable,
                p.closeable,
                p.getStyle(),
                p.getLayout() );
    }

    private WindowParameters(
            Vector position,
            int width,
            int height,
            boolean scrollable,
            boolean movable,
            boolean closeable,
            IStyle style,
            ILayout layout )
    {
        super( Vector.with( position ), width, height );
        this.position = position;
        this.scrollable = scrollable;
        this.movable = movable;
        this.closeable = closeable;
        this.style = style;
        this.layout = layout;

        this.titleTopLeftCorner = buildTitleTopLeftCorner();
        this.titleBottomRightCorner = buildTitleBottomRightCorner();
        this.bodyTopLeftCorner = buildBodyTopLeftCorner();
        this.bodyBottomRightCorner = buildBodyBottomRightCorner();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Factories
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static WindowParameters copy( WindowParameters windowParameters )
    {
        return new WindowParameters( windowParameters );
    }

    public static WindowParameters createStaticWindow(
            Vector position,
            int width,
            int height,
            ILayout layout )
    {
        return new WindowParameters(
                position,
                width,
                height,
                true,
                false,
                false,
                Styles.DEFAULT,
                layout );
    }

    public static WindowParameters createCommonWindow(
            Vector position,
            int width,
            int height )
    {
        return new WindowParameters(
                position,
                width,
                height,
                true,
                true,
                true,
                Styles.DEFAULT,
                Layouts.DEFAULT );
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Getters And Setters
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isMovable()
    {
        return movable;
    }

    public boolean isCloseable()
    {
        return closeable;
    }

    public boolean isScrollable()
    {
        return scrollable;
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

    public IStyle getStyle()
    {
        return style;
    }

    public ILayout getLayout()
    {
        return layout;
    }

    void setStyle( IStyle style )
    {
        this.style = style;
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
        return MathUtils.conversion.gdxCoordinates( getPosition().getX() + getWidth() - TITLE_SIZE, getPosition().getY() + TITLE_SIZE );
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
