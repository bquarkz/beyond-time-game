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

import com.badlogic.gdx.controllers.PovDirection;
import com.intrepid.nicge.controller.basics.POV;
import com.intrepid.nicge.utils.MathUtils;

public class KeyboardPOV implements POV {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private final InspectKeyboard inspect;

	private PovDirection direction;
	private int upCode;
	private int downCode;
	private int leftCode;
	private int rightCode;

	private boolean up;

	private boolean down;

	private boolean left;

	private boolean right;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public KeyboardPOV( InspectKeyboard inspect ) {
		this.inspect = inspect;
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	public void setCode( int upCode, int downCode, int leftCode, int rightCode ) {
		this.upCode = upCode;
		this.downCode = downCode;
		this.leftCode = leftCode;
		this.rightCode = rightCode;
	}

	@Override
	public void captureControl() {
		up = this.inspect.getKey( upCode );
		down = this.inspect.getKey( downCode );
		left = this.inspect.getKey( leftCode );
		right = this.inspect.getKey( rightCode );
	}

	@Override
	public void update() {
		direction = PovDirection.center;
		
		boolean x = MathUtils.logic.NXOR( left, right );
		boolean y = MathUtils.logic.NXOR( up, down );
		
		if( x && y ) {
			return;
		}
		
		if( x ) {
			if( left ) {
				direction = PovDirection.west;
			} else {
				direction = PovDirection.east;
			}
		}
		
		if( y ) {
			if( direction == PovDirection.center ) {
				if( up ) {
					direction = PovDirection.north;
				} else {
					direction = PovDirection.south;
				}
			} else {
				if( up ) {
					if( direction == PovDirection.east ) {
						direction = PovDirection.northEast;
					} else {
						direction = PovDirection.northWest;
					}
				} else {
					if( direction == PovDirection.east ) {
						direction = PovDirection.southEast;
					} else {
						direction = PovDirection.southWest;
					}
				}
			}
		}
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

