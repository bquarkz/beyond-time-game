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

import com.badlogic.gdx.Input.Keys;
import com.intrepid.nicge.controller.keyboard.KeyboardMap;

public class KeyboardMapping implements KeyboardMap {
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
	private int axis_left_x_pos;
	private int axis_left_x_neg;
	private int axis_left_y_pos;
	private int axis_left_y_neg;
	private int left_trigger;
	private int axis_right_x_pos;	
	private int axis_right_x_neg;
	private int axis_right_y_pos;
	private int axis_right_y_neg;
	private int right_trigger;
	private int dpad_up;
	private int dpad_down;
	private int dpad_left;
	private int dpad_right;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public KeyboardMapping() {
		setDefaultControls(
			Keys.E,
			Keys.R,
			Keys.Q,
			Keys.W,
			Keys.SHIFT_LEFT,
			Keys.CONTROL_LEFT,
			Keys.NUM_1,
			Keys.D,
			Keys.NUM_3,
			Keys.A,
			Keys.C,
			Keys.Z,
			Keys.S,
			Keys.X,
			Keys.NUM_2,
			Keys.LEFT,
			Keys.RIGHT,
			Keys.UP,
			Keys.DOWN,
			Keys.NUM_4,
			Keys.HOME,
			Keys.END,
			Keys.DEL,
			Keys.PAGE_DOWN );
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
			int axis_left_x_pos,
			int axis_left_x_neg,
			int axis_left_y_pos,
			int axis_left_y_neg,
			int left_trigger,
			int axis_right_x_pos,
			int axis_right_x_neg,
			int axis_right_y_pos,
			int axis_right_y_neg,
			int right_trigger,
			int dpad_up,
			int dpad_down,
			int dpad_left,
			int dpad_right
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
		this.axis_left_x_pos = axis_left_x_pos;
		this.axis_left_x_neg = axis_left_x_neg;
		this.axis_left_y_pos = axis_left_y_pos;
		this.axis_left_y_neg = axis_left_y_neg;
		this.left_trigger = left_trigger;
		this.axis_right_x_pos = axis_right_x_pos;
		this.axis_right_x_neg = axis_right_x_neg;
		this.axis_right_y_pos = axis_right_y_pos;
		this.axis_right_y_neg = axis_right_y_neg;
		this.right_trigger = right_trigger;
		this.dpad_up = dpad_up;
		this.dpad_down = dpad_down;
		this.dpad_left = dpad_left;
		this.dpad_right = dpad_right;
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
	public int id_AXIS_LEFT_X_POS() {
		return axis_left_x_pos;
	}
	
	@Override
	public int id_AXIS_LEFT_X_NEG() {
		return axis_left_x_neg;
	}

	@Override
	public int id_AXIS_LEFT_Y_POS() {
		return axis_left_y_pos;
	}
	
	@Override
	public int id_AXIS_LEFT_Y_NEG() {
		return axis_left_y_neg;
	}

	@Override
	public int id_AXIS_LEFT_TRIGGER() {
		return left_trigger;
	}

	@Override
	public int id_AXIS_RIGHT_X_POS() {
		return axis_right_x_pos;
	}
	
	@Override
	public int id_AXIS_RIGHT_X_NEG() {
		return axis_right_x_neg;
	}

	@Override
	public int id_AXIS_RIGHT_Y_POS() {
		return axis_right_y_pos;
	}
	
	@Override
	public int id_AXIS_RIGHT_Y_NEG() {
		return axis_right_y_neg;
	}

	@Override
	public int id_AXIS_RIGHT_TRIGGER() {
		return right_trigger;
	}

	@Override
	public int id_DPAD_UP() {
		return dpad_up;
	}
	
	@Override
	public int id_DPAD_DOWN() {
		return dpad_down;
	}

	@Override
	public int id_DPAD_LEFT() {
		return dpad_left;
	}

	@Override
	public int id_DPAD_RIGHT() {
		return dpad_right;
	}

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
}
