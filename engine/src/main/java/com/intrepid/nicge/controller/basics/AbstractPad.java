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
package com.intrepid.nicge.controller.basics;

import com.intrepid.nicge.theater.IControllable;
import com.intrepid.nicge.theater.IUpdatable;

public abstract class AbstractPad implements UnitControl, Pad {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************
	protected static final int MAX_DISPOSITIVES = 17;
	protected static final int BUTTON_X = 0;
	protected static final int BUTTON_Y = 1;
	protected static final int BUTTON_A = 2;
	protected static final int BUTTON_B = 3;
	protected static final int BUTTON_BACK = 4;
	protected static final int BUTTON_START = 5;
	protected static final int BUTTON_LB = 6;
	protected static final int BUTTON_L3 = 7;
	protected static final int BUTTON_RB = 8;
	protected static final int BUTTON_R3 = 9;
	protected static final int AXIS_LEFT_X = 10;
	protected static final int AXIS_LEFT_Y = 11;
	protected static final int AXIS_LEFT_TRIGGER = 12;
	protected static final int AXIS_RIGHT_X = 13;
	protected static final int AXIS_RIGHT_Y = 14;
	protected static final int AXIS_RIGHT_TRIGGER = 15;
	protected static final int DPAD = 16;
	
	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private UnitControl[] units;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public AbstractPad( ) {
		units = new UnitControl[ MAX_DISPOSITIVES ];
		createDevice( units );
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	protected abstract void createDevice( UnitControl[] units );

	@Override
	public void captureControl() {
		for( IControllable c : units ) {
			c.captureControl();
		}
	}

	@Override
	public void update() {
		for( IUpdatable u : units ) {
			u.update();
		}
	}

	@Override
	public GamePadButton get_BUTTON_X() {
		return ( GamePadButton ) units[ BUTTON_X ];
	}

	@Override
	public GamePadButton get_BUTTON_Y() {
		return ( GamePadButton ) units[ BUTTON_Y ];
	}

	@Override
	public GamePadButton get_BUTTON_A() {
		return ( GamePadButton ) units[ BUTTON_A ];
	}

	@Override
	public GamePadButton get_BUTTON_B() {
		return ( GamePadButton ) units[ BUTTON_B ];
	}

	@Override
	public GamePadButton get_BUTTON_BACK() {
		return ( GamePadButton ) units[ BUTTON_BACK ];
	}

	@Override
	public GamePadButton get_BUTTON_START() {
		return ( GamePadButton ) units[ BUTTON_START ];
	}

	@Override
	public GamePadButton get_BUTTON_LB() {
		return ( GamePadButton ) units[ BUTTON_LB ];
	}

	@Override
	public GamePadButton get_BUTTON_L3() {
		return ( GamePadButton ) units[ BUTTON_L3 ];
	}

	@Override
	public GamePadButton get_BUTTON_RB() {
		return ( GamePadButton ) units[ BUTTON_RB ];
	}

	@Override
	public GamePadButton get_BUTTON_R3() {
		return ( GamePadButton ) units[ BUTTON_R3 ];
	}

	@Override
	public Axis get_AXIS_LEFT_X() {
		return ( Axis ) units[ AXIS_LEFT_X ];
	}

	@Override
	public Axis get_AXIS_LEFT_Y() {
		return ( Axis ) units[ AXIS_LEFT_Y ];
	}

	@Override
	public Axis get_AXIS_LEFT_TRIGGER() {
		return ( Axis ) units[ AXIS_LEFT_TRIGGER ];
	}

	@Override
	public Axis get_AXIS_RIGHT_X() {
		return ( Axis ) units[ AXIS_RIGHT_X ];
	}

	@Override
	public Axis get_AXIS_RIGHT_Y() {
		return ( Axis ) units[ AXIS_RIGHT_Y ];
	}

	@Override
	public Axis get_AXIS_RIGHT_TRIGGER() {
		return ( Axis ) units[ AXIS_RIGHT_TRIGGER ];
	}

	@Override
	public POV get_DPAD() {
		return (POV) units[ DPAD ];
	}
	
	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
}
