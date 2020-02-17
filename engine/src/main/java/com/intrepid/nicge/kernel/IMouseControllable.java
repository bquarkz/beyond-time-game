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
package com.intrepid.nicge.kernel;

public interface IMouseControllable
{
    // ****************************************************************************************
    // Constants
    // ****************************************************************************************
    int MOUSE_BUTTON_LEFT = 0;
    int MOUSE_BUTTON_RIGHT = 1;
    int MOUSE_BUTTON_MIDDLE = 2;
    int MOUSE_BUTTON_SUPORT_REAR = 3;
    int MOUSE_BUTTON_SUPORT_FRONT = 4;

    // ****************************************************************************************
    // Contracts
    // ****************************************************************************************
    void checkMouseOver(
            int screenX,
            int screenY );

    void mouseButtonPressed(
            int screenX,
            int screenY,
            int button );

    void mouseButtonUnPressed(
            int screenX,
            int screenY,
            int button );

    void dragged(
            int screenX,
            int screenY,
            int button );

    // ****************************************************************************************
    // Default methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Static Methods
    // ****************************************************************************************
}
