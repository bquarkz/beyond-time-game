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

import com.intrepid.nicge.theater.Displayable;
import com.intrepid.nicge.theater.Updatable;

public interface IComponent extends IMouseControl, Updatable, Displayable
{
    // ****************************************************************************************
    // Constants
    // ****************************************************************************************

    // ****************************************************************************************
    // Contracts
    // ****************************************************************************************
    void setComponentControl( ComponentControl componentControl );

    ComponentControl getComponentControl();

    // ****************************************************************************************
    // Default methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Static Methods
    // ****************************************************************************************
}
