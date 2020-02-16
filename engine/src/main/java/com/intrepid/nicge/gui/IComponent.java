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

import com.intrepid.nicge.kernel.IDisplayable;
import com.intrepid.nicge.kernel.IMouseControllable;
import com.intrepid.nicge.kernel.IUpdatable;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface IComponent
        extends IMouseControllable, IUpdatable, IDisplayable
{
    // ****************************************************************************************
    // Constants
    // ****************************************************************************************

    // ****************************************************************************************
    // Contracts
    // ****************************************************************************************

    // ****************************************************************************************
    // Default methods
    // ****************************************************************************************
    default void setParent( IComponent parent )
    {
    }

    default Optional< IComponent > getParent()
    {
        return Optional.empty();
    }

    // ****************************************************************************************
    // Static Methods
    // ****************************************************************************************
}
