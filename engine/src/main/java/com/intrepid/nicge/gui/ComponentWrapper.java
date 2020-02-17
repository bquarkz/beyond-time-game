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

import com.intrepid.nicge.utils.graphics.GraphicsBatch;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;

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
    public void dragged(
            int screenX,
            int screenY,
            int button )
    {
        getComponent().dragged( screenX, screenY, button );
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
        componentParameters.setEnabled( true );
    }

    public void disable()
    {
        componentParameters.setEnabled( false );
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