/*
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

import com.intrepid.nicge.utils.graphics.GraphicsBatch;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ComponentWrapper
        implements Comparable< ComponentWrapper >, IComponent
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private final Integer id;
    private final IComponent component;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    protected ComponentWrapper(
            Integer id,
            IComponent component )
    {
        this.id = id;
        this.component = component;
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
    @Override
    public boolean equals( Object o )
    {
        if( this == o )
        {
            return true;
        }

        if( o == null || getClass() != o.getClass() )
        {
            return false;
        }

        ComponentWrapper that = (ComponentWrapper)o;

        return new EqualsBuilder()
                .append( id, that.id )
                .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder( 17, 37 )
                .append( id )
                .toHashCode();
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
    public boolean checkMouseOver(
            int screenX,
            int screenY )
    {
        return getComponent().checkMouseOver( screenX, screenY );
    }

    @Override
    public boolean mouseButtonPressed(
            int screenX,
            int screenY,
            int button )
    {
        return getComponent().mouseButtonPressed( screenX, screenY, button );
    }

    @Override
    public boolean mouseButtonUnPressed(
            int screenX,
            int screenY,
            int button )
    {
        return getComponent().mouseButtonUnPressed( screenX, screenY, button );
    }

    @Override
    public boolean dragged(
            int screenX,
            int screenY,
            int button )
    {
        return getComponent().dragged( screenX, screenY, button );
    }

    @Override
    public void update()
    {
        getComponent().update();
    }

    @Override
    final public void setParent( IComponent parent )
    {
        this.component.setParent( parent );
    }

    @Override
    public Optional< IComponent > getParent()
    {
        return component.getParent();
    }

    public void enable()
    {
        component.getParameters().setEnabled( true );
    }

    public void disable()
    {
        component.getParameters().setEnabled( false );
    }

    @Override
    public ComponentParameters getParameters()
    {
        return component.getParameters();
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

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}