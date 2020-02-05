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
 * all comunity without problems.
 */
package com.intrepid.nicge.controller.joystick;

import com.badlogic.gdx.controllers.PovDirection;

public interface InspectJoystick {
	boolean getButton( int buttonCode );
	float getAxis( int axisCode );
	PovDirection getPOV( int povCode );
}
