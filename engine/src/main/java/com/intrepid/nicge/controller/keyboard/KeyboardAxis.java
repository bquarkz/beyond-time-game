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

import com.intrepid.nicge.controller.basics.Axis;
import com.intrepid.nicge.utils.MathUtils;
import com.intrepid.nicge.utils.timer.Timer;

public class KeyboardAxis implements Axis {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private final InspectKeyboard inspect;
	
	private float axisValue;
	private boolean isActive;
	private int posCode;
	private int negCode;
	private Timer timer;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public KeyboardAxis( InspectKeyboard inspect ) {
		this.inspect = inspect;
		timer = new Timer();
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	@Override
	public void captureControl() {
		boolean pos = inspect.getKey( posCode );
		boolean neg = inspect.getKey( negCode );
		
		if( MathUtils.logic.NXOR( pos, neg ) ) {
			axisValue = 0;
			return;
		}

		if( pos ){
			axisValue = 1.0f;
		} else {
			axisValue = -1.0f;
		}
	}
	
	@Override
	public void update() {
		if( axisValue != 0 ) {
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
		return 0.0f;
	}

	@Override
	public float getCapZone() {
		return 1.0f;
	}

	public void setCode( int posCode, int negCode ) {
		this.posCode = posCode;
		this.negCode = negCode;
	}
	
	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
}
