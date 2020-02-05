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
package com.intrepid.nicge.controller.keyboard;

import com.intrepid.nicge.controller.basics.GamePadButton;
import com.intrepid.nicge.utils.timer.Timer;

public class KeyboardButton implements GamePadButton {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private final InspectKeyboard inspect;
	
	private int code;
	private boolean isEdgeUp;
	private boolean isPressed;
	private boolean isEdgeDown;
	private Timer timer;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public KeyboardButton( InspectKeyboard inspect ) {
		this.inspect = inspect;
		this.timer = new Timer();
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	private void press() {
		this.isEdgeDown = true;
		timer.start();
	}
	
	private void release() {
		this.isEdgeUp = true;
		timer.stop();		
	}
	
	@Override
	public boolean isEdgeDown() {
		return this.isEdgeDown;
	}

	@Override
	public boolean isEdgeUp() {
		return this.isEdgeUp;
	}

	@Override
	public boolean isPressed() {
		return this.isPressed;
	}

	@Override
	public boolean isPressedForMiliSecs( float time ) {
		return timer.checkTimeInMiliSecs( time );
	}

	@Override
	public float getHowMuchTimeInMiliSecsItIsPressed() {
		return timer.getTotalTime();
	}
	
	@Override
	public void captureControl() {
		if( inspect.getKey( code ) ) {
			press();
		} else {
			release();
		}
	}

	@Override
	public void update() {
		if( isEdgeUp ) {
			if( isPressed ) {
				isPressed = false;
				return;
			} else {
				isEdgeUp = false;
				return;
			}
		}
		
		if( isEdgeDown ) {
			if( isPressed ) {
				isEdgeDown = false;
			} else {
				isPressed = true;
			}
		}
		
		if( isPressed ) {
			timer.update();
		}			
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
