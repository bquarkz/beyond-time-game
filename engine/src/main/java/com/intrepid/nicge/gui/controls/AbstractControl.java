package com.intrepid.nicge.gui.controls;

import com.intrepid.nicge.gui.ComponentParameters;
import com.intrepid.nicge.gui.ComponentWrapper;
import com.intrepid.nicge.gui.IComponent;
import com.intrepid.nicge.gui.IControl;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.utils.MathUtils;
import com.intrepid.nicge.utils.MathUtils.Vector;

import java.util.Optional;

public abstract class AbstractControl
    implements IControl
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
    private Vector relativePosition;
    private ComponentParameters selfParameters;
    private IComponent parent;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected AbstractControl()
    {
        this.selfParameters = new ComponentParameters();
        this.parent = null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Factories
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Getters And Setters
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected Vector getRelativePosition()
    {
        return relativePosition;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public ComponentParameters getParameters()
    {
        return selfParameters;
    }

    @Override
    public void setParent( IComponent parent )
    {
        this.parent = parent;
    }

    @Override
    public Optional< IComponent > getParent()
    {
        return Optional.ofNullable( parent );
    }

    public Vector getParentPosition()
    {
        final Optional< IComponent > parent = getParent();
        final Vector parentPosition;
        if( parent.isPresent() )
        {
            ComponentParameters pp = parent.get().getParameters();
            if( pp != null ) parentPosition = pp.getPosition();
            else parentPosition = Vector.ZERO;
        }
        else
        {
            parentPosition = Vector.ZERO;
        }

        Game.debug.addDebugMessage( "Control " + getClass().getSimpleName(), "parent " + parentPosition );

        return parentPosition;
    }

    final public void setRelativePosition( Vector position )
    {
        setRelativePosition( position.getX(), position.getY() );
    }

    final public void setRelativePosition( int x, int y )
    {
        this.relativePosition = Vector.with( x, y );
    }

    final public void setSize(
            int width,
            int height )
    {
        getParameters().setWidth( width );
        getParameters().setHeight( height );
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Inner Classes And Patterns
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
