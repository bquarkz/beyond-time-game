/**
 * Copyleft (C) 2016  Constantino, Nilton Rogerio <niltonrc@gmail.com>
 *
 * @author "Nilton R Constantino"
 * aKa bQUARKz <niltonrc@gmail, bquarkz@gmail.com>
 *
 * Everything about the respective software copyright can be found in the
 * "LICENSE" file included in the project source tree.
 *
 * The code was written based on study principles and can be enjoyed for
 * all community without problems.
 */
package com.intrepid.nicge.gui;

import com.intrepid.nicge.utils.MathUtils.Vector;

public class ComponentParameters
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************
    
    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private Vector position;
    private int width;
    private int height;
    private boolean enabled;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public ComponentParameters()
    {
       this( Vector.ZERO, 0, 0 );
    }

    public ComponentParameters( Vector position, int width, int height )
    {
        this.enabled = true;
        this.position = position;
        this.width = width;
        this.height = height;
    }

    // ****************************************************************************************
    // Factories
    // ****************************************************************************************

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled( boolean disabled )
    {
        this.enabled = disabled;
    }

    public Vector getPosition()
    {
        return position;
    }

    public void setPosition( Vector position )
    {
        this.position = position;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth( int width )
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight( int height )
    {
        this.height = height;
    }
    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
