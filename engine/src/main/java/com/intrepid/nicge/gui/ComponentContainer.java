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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import com.intrepid.nicge.kernel.IUpdatable;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;

public class ComponentContainer< C extends IComponent >
        implements IComponent
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private final Map< Integer, ComponentWrapper > components;
    private ComponentWrapper parent;

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
        this.parent = parent;
    }

    // ****************************************************************************************
    // Factories
    // ****************************************************************************************

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    public Integer addComponent( C component )
    {
        int id = getComponents().size();
        ComponentWrapper cc = ComponentWrapper.create( id, component );
        component.setParent( cc );
        getComponents().put( cc.getId(), cc );
        cc.getComponentParameters().setEnabled( true );

        return cc.getId();
    }

    public void disable( C component )
    {
        Optional.ofNullable( getComponents().get( component ) )
                .ifPresent( cc -> cc.getComponentParameters().setEnabled( false ) );
    }

    public void enable( C component )
    {
        Optional.ofNullable( getComponents().get( component ) )
                .ifPresent( cc -> cc.getComponentParameters().setEnabled( true ) );
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
    public void setParent( ComponentWrapper parent )
    {
        this.parent = parent;
    }

    @Override
    public ComponentWrapper getParent()
    {
        return parent;
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
