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

import com.intrepid.nicge.controller.basics.Axis;
import com.intrepid.nicge.utils.timer.Timer;

public class JoystickAxis implements Axis {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private final InspectJoystick inspect;
	
	private float deadZone;
	private float capZone;
	private float axisValue;
	private int code;
	private boolean isActive;
	private float deltaOperative;
	private Timer timer;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public JoystickAxis( InspectJoystick inspect, float deadZone, float capZone ) {
		this.inspect = inspect;
		this.deadZone = deadZone;
		this.capZone = capZone;
		this.deltaOperative = ( capZone - deadZone );
		this.timer = new Timer();
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	@Override
	public void captureControl() {
		 float temp = inspect.getAxis( code );
		 
		 if( ( temp > capZone ) || ( temp < -capZone ) ) {
			 axisValue = 1.0f;
			 return;
		 } else if( ( temp < deadZone ) || ( temp > -deadZone ) ) {
			 axisValue = 0.0f;
			 return;
		 } else {
			 if( temp >= 0.0f ) {
				 axisValue = ( temp - deadZone ) / deltaOperative;
			 } else {
				 axisValue = ( temp + deadZone ) / deltaOperative;
			 }
			 return;
		 }
	}
	
	@Override
	public void update() {
		if( ( axisValue > deadZone ) || ( axisValue < -deadZone ) ) {
			isActive = true;
			timer.start();
		} else {
			isActive = false;
			timer.stop();
		}
		
		timer.update();
	}
	
	@Override
	public boolean isActive() {
		return isActive;
	}

	@Override
	public float getAxisValue() {
		return this.axisValue;
	}

	@Override
	public float getDeadZone() {
		return this.deadZone;
	}

	@Override
	public float getCapZone() {
		return this.capZone;
	}

	public void setCode( int code ) {
		this.code = code;
	}
	
	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
}
