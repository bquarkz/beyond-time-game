/**
 * Copyleft (C) 2016  Constantino, Nilton Rogerio <niltonrc@gmail.com>
 *
 * @author "Nilton R Constantino"
 * aKa bQUARKz <niltonrc@gmail, bquarkz@gmail.com>
 * <p>
 * Everything about the respective software copyright can be found in the
 * "LICENSE" file included in the project source tree.
 * <p>
 * The code was written based on study principles and can be enjoyed for
 * all community without problems.
 */
package com.intrepid.nicge.gui;

import java.util.Collection;
import java.util.function.Consumer;

import com.intrepid.nicge.kernel.IDisplayable;
import com.intrepid.nicge.kernel.IMouseControllable;
import com.intrepid.nicge.kernel.IUpdatable;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;

final public class ComponentWrapper
        implements Comparable< ComponentWrapper >, IMouseControllable, IUpdatable, IDisplayable
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private final Integer id;
    private final IComponent component;
    private final ComponentParameters componentParameters;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    protected ComponentWrapper(
            Integer id,
            IComponent component )
    {
        this.id = id;
        this.component = component;
        this.componentParameters = new ComponentParameters();
        this.component.setParent( this );
    }

    // ****************************************************************************************
    // Factories
    // ****************************************************************************************
    public static < C extends  IComponent > ComponentWrapper create(
            Integer id,
            C component )
    {
        return new ComponentWrapper( id, component );
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    static void runIfEnabled(
            Collection< ComponentWrapper > components,
            Consumer< IComponent > consumer )
    {
        components.stream()
                  .filter( cc -> cc.getComponentParameters().isEnabled() )
                  .forEach( cc -> consumer.accept( cc.getComponent() ) );
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj )
    {
        if( this == obj )
        {
            return true;
        }
        if( obj == null )
        {
            return false;
        }
        if( getClass() != obj.getClass() )
        {
            return false;
        }
        ComponentWrapper other = (ComponentWrapper)obj;
        if( id == null )
        {
            if( other.id != null )
            {
                return false;
            }
        }
        else if( !id.equals( other.id ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo( ComponentWrapper that )
    {
        return this.getId().compareTo( that.getId() );
    }

    @Override
    public void display( GraphicsBatch batch )
    {
        getComponent().display( batch );
    }

    @Override
    public void checkMouseOver(
            int screenX,
            int screenY )
    {
        getComponent().checkMouseOver( screenX, screenY );
    }

    @Override
    public void mouseButtonPressed(
            int screenX,
            int screenY,
            int button )
    {
        getComponent().mouseButtonPressed( screenX, screenY, button );
    }

    @Override
    public void mouseButtonUnPressed(
            int screenX,
            int screenY,
            int button )
    {
        getComponent().mouseButtonUnPressed( screenX, screenY, button );
    }

    @Override
    public void update()
    {
        getComponent().update();
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public Integer getId()
    {
        return id;
    }

    public IComponent getComponent()
    {
        return component;
    }

    public ComponentParameters getComponentParameters()
    {
        return componentParameters;
    }

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}