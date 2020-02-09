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
import com.intrepid.nicge.controller.basics.POV;

public class JoystickPOV implements POV {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private final InspectJoystick inspect;
	private int code;
	
	private PovDirection direction;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public JoystickPOV( InspectJoystick inspect ) {
		this.inspect = inspect;
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	public void setCode( int code ) {
		this.code = code;
	}

	@Override
	public void update() {
	}
	
	@Override
	public void inputControlLogic() {
		this.direction = this.inspect.getPOV( code );
	}

	@Override
	public PovDirection getDirection() {
		return direction;
	}

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
}
