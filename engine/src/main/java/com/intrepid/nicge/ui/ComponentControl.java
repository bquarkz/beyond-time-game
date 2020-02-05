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
package com.intrepid.nicge.ui;

import java.util.function.Consumer;

import com.intrepid.nicge.utils.graphics.GraphicsBatch;

final public class ComponentControl implements Comparable< ComponentControl >
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private final Integer id;
    private final IComponent component;
    private final ComponentConfig config;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    protected ComponentControl(
            Integer id,
            IComponent component )
    {
        this.id = id;
        this.component = component;
        this.config = new ComponentConfig();
        this.component.setComponentControl( this );
    }

    // ****************************************************************************************
    // Factories
    // ****************************************************************************************
    public static ComponentControl create(
            Integer id,
            IComponent component )
    {
        ComponentControl o = new ComponentControl( id, component );
        return o;
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    static void runIfEnabled(
            Iterable< ComponentControl > components,
            Consumer< IComponent > consumer
    )
    {
        for( ComponentControl cc : components )
        {
            if( cc.getConfig().isEnabled() )
            {
                consumer.accept( cc.getComponent() );
            }
        }
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
        ComponentControl other = (ComponentControl)obj;
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
    public int compareTo( ComponentControl cc )
    {
        return getConfig().getLayer().compareTo( cc.getConfig().getLayer() );
    }

    public void display( GraphicsBatch batch )
    {
        getComponent().display( batch );
    }

    public void checkMouseOver(
            int screenX,
            int screenY )
    {
        getComponent().checkMouseOver( screenX, screenY );
    }

    public void mouseButtonPressed(
            int screenX,
            int screenY,
            int button )
    {
        getComponent().mouseButtonPressed( screenX, screenY, button );
    }

    public void mouseButtonUnPressed(
            int screenX,
            int screenY,
            int button )
    {
        getComponent().mouseButtonUnPressed( screenX, screenY, button );
    }

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

    public ComponentConfig getConfig()
    {
        return config;
    }

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}