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

final class ComponentParameters
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************
    
    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private boolean enabled;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public ComponentParameters()
    {
        this.enabled = true;
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

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
