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
package com.intrepid.nicge.ui;

final class ComponentConfig
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************
    
    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private boolean enabled;
    private Integer layer;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public ComponentConfig()
    {
        this.enabled = true;
        this.layer = Environment.LAYER_MIDDLE;
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

    public Integer getLayer()
    {
        return layer;
    }

    public void setLayer( Integer layer )
    {
        this.layer = layer;
    }
    
    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
