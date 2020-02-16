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

import com.intrepid.nicge.kernel.IUpdatable;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class ComponentContainer
    implements IComponent
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private final Map< Integer, ComponentWrapper > components;
    private final AtomicInteger currentId;
    private IComponent parent;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    protected ComponentContainer()
    {
        this( null );
    }

    protected ComponentContainer( ComponentWrapper parent )
    {
        this.components = new HashMap<>();
        this.currentId = new AtomicInteger( 0 );
        this.parent = parent;
    }

    // ****************************************************************************************
    // Factories
    // ****************************************************************************************

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************

    public Optional< IComponent > getComponent( int id )
    {
        return Optional
                .ofNullable( getComponents().get( id ) )
                .map( ComponentWrapper::getComponent );
    }

    public int size()
    {
        return components.size();
    }

    public < C extends IComponent > Bundle< C > addComponent( C component )
    {
        int id = currentId.addAndGet( 1 );
        ComponentWrapper wrapper = ComponentWrapper.create( id, component );
        component.setParent( this );
        getComponents().put( wrapper.getId(), wrapper );
        wrapper.getComponentParameters().setEnabled( false );
        return new Bundle<>( wrapper.getId(), component );
    }

    public void disable( Bundle< ? > cBundle )
    {
        disable( cBundle.getId() );
    }

    public void disable( int id )
    {
        disable( getComponents().get( id ) );
    }

    protected void disable( ComponentWrapper wrapper )
    {
        if( wrapper == null ) return;
        wrapper.disable();
    }

    public void enable( Bundle< ? > cBundle )
    {
        enable( cBundle.getId() );
    }

    public void enable( int id )
    {
        enable( getComponents().get( id ) );
    }

    protected void enable( ComponentWrapper wrapper )
    {
        if( wrapper == null ) return;
        wrapper.enable();
    }

    @Override
    public void checkMouseOver(
            int screenX,
            int screenY )
    {
        runOverAllEnabled( o -> o.checkMouseOver( screenX, screenY ) );
    }

    @Override
    public void mouseButtonPressed(
            int screenX,
            int screenY,
            int button )
    {
        runOverAllEnabled( o -> o.mouseButtonPressed( screenX, screenY, button ) );
    }

    @Override
    public void mouseButtonUnPressed(
            int screenX,
            int screenY,
            int button )
    {
        runOverAllEnabled( o -> o.mouseButtonUnPressed( screenX, screenY, button ) );
    }

    @Override
    public void update()
    {
        runOverAllEnabled( IUpdatable::update );
    }

    protected void runOverAllEnabled( Consumer< IComponent > consumer )
    {
        ComponentWrapper.runIfEnabled( getComponents().values(), consumer );
    }

    @Override
    public void display( GraphicsBatch batch )
    {
        getComponents().values().forEach( cc -> cc.display( batch ) );
    }

    @Override
    public void setParent( IComponent parent )
    {
        this.parent = parent;
        getComponents().values().forEach( cc -> cc.setParent( parent ) );
    }

    @Override
    public Optional< IComponent > getParent()
    {
        return Optional.ofNullable( parent );
    }

    public void enable()
    {
        getComponents().values().forEach( c -> c.getComponentParameters().setEnabled( true ) );
    }

    public void disable()
    {
        getComponents().values().forEach( c -> c.getComponentParameters().setEnabled( false ) );
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    protected Map< Integer, ComponentWrapper > getComponents()
    {
        return components;
    }

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
