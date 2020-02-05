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

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import com.intrepid.nicge.utils.graphics.GraphicsBatch;

public class Container implements IComponent
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private final Map< Integer, ComponentControl > components;
    private ComponentControl componentControl;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    protected Container()
    {
        this.components = new HashMap<>();
    }

    // ****************************************************************************************
    // Factories
    // ****************************************************************************************

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    public Integer addComponent( IComponent component )
    {
        int id = getComponents().size();
        ComponentControl cc = ComponentControl.create( id, component );
        component.setComponentControl( cc );
        getComponents().put( cc.getId(), cc );
        cc.getConfig().setEnabled( true );

        return cc.getId();
    }

    public void disable( IComponent component )
    {
        ComponentControl cc = getComponents().get( component );
        if( cc == null )
        {
            return;
        }

        cc.getConfig().setEnabled( false );
    }

    public void enable( IComponent component )
    {
        ComponentControl cc = getComponents().get( component );
        if( cc == null )
        {
            return;
        }

        cc.getConfig().setEnabled( true );
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
        runOverAllEnabled( o -> o.update() );
    }

    protected void runOverAllEnabled( Consumer< IComponent > consumer )
    {
        ComponentControl.runIfEnabled( getComponents().values(), consumer );
    }

    @Override
    public void display( GraphicsBatch batch )
    {
        components.values().forEach( cc -> cc.display( batch ) );
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    protected Map< Integer, ComponentControl > getComponents()
    {
        return components;
    }

    @Override
    public void setComponentControl( ComponentControl componentControl )
    {
        this.componentControl = componentControl;
    }

    @Override
    public ComponentControl getComponentControl()
    {
        return componentControl;
    }

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
