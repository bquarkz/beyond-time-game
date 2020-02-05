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
package com.intrepid.nicge.controller;

import com.intrepid.nicge.controller.joystick.JoystickMap;

public class Xbox360Mapping implements JoystickMap {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private int x;
	private int y;
	private int a;
	private int b;
	private int back;
	private int start;
	private int lb;
	private int l3;
	private int rb;
	private int r3;
	private int axis_left_x;
	private int axis_left_y;
	private int axis_left_trigger;
	private int axis_right_x;	
	private int axis_right_y;
	private int axis_right_trigger;
	private int dpad;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public Xbox360Mapping() {
		setDefaultControls(
			0,
			3,
			1,
			2,
			13,
			9,
			4,
			10,
			5,
			11,
			3,
			2,
			6,
			1,
			0,
			7,
			0 );			
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	private void setDefaultControls( 
			int x,
			int y,
			int a,
			int b,
			int back,
			int start,
			int lb,
			int l3,
			int rb,
			int r3,
			int axis_left_x,
			int axis_left_y,
			int axis_left_trigger,
			int axis_right_x,
			int axis_right_y,
			int axis_right_trigger,
			int dpad
			) {
		
		this.x = x;
		this.y = y;
		this.a = a;
		this.b = b;
		this.back = back;
		this.start = start;
		this.lb = lb;
		this.l3 = l3;
		this.rb = rb;
		this.r3 = r3;
		this.axis_left_x = axis_left_x;
		this.axis_left_y = axis_left_y;
		this.axis_left_trigger = axis_left_trigger;
		this.axis_right_x = axis_right_x;
		this.axis_right_y = axis_right_y;
		this.axis_right_trigger = axis_right_trigger;
		this.dpad = dpad;
	}

	@Override
	public int id_BUTTON_X() {
		return x;
	}

	@Override
	public int id_BUTTON_Y() {
		return y;
	}

	@Override
	public int id_BUTTON_A() {
		return a;
	}

	@Override
	public int id_BUTTON_B() {
		return b;
	}

	@Override
	public int id_BUTTON_BACK() {
		return back;
	}

	@Override
	public int id_BUTTON_START() {
		return start;
	}

	@Override
	public int id_BUTTON_LB() {
		return lb;
	}

	@Override
	public int id_BUTTON_L3() {
		return l3;
	}

	@Override
	public int id_BUTTON_RB() {
		return rb;
	}

	@Override
	public int id_BUTTON_R3() {
		return r3;
	}
	
	@Override
	public int id_AXIS_LEFT_X() {
		return axis_left_x;
	}
	
	@Override
	public int id_AXIS_LEFT_Y() {
		return axis_left_y;
	}
	
	@Override
	public int id_AXIS_LEFT_TRIGGER() {
		return axis_left_trigger;
	}

	@Override
	public int id_AXIS_RIGHT_X() {
		return axis_right_x;
	}
	
	@Override
	public int id_AXIS_RIGHT_Y() {
		return axis_right_y;
	}
	
	@Override
	public int id_AXIS_RIGHT_TRIGGER() {
		return axis_right_trigger;
	}

	@Override
	public int id_DPAD() {
		return dpad;
	}

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
}
