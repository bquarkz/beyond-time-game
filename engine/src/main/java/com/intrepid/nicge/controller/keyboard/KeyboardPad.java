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

import com.badlogic.gdx.Input;
import com.intrepid.nicge.controller.basics.AbstractPad;
import com.intrepid.nicge.controller.basics.UnitControl;

public class KeyboardPad extends AbstractPad implements InspectKeyboard {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private final Input input;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public KeyboardPad( Input input, KeyboardMap keyboardMap ) {
		this.input = input;
		cfgUnitsControls( keyboardMap );
	}
	
	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	private void cfgUnitsControls( KeyboardMap map ) {
		( (KeyboardButton) get_BUTTON_X() ).setCode( map.id_BUTTON_X() );
		( (KeyboardButton) get_BUTTON_Y() ).setCode( map.id_BUTTON_Y() );
		( (KeyboardButton) get_BUTTON_A() ).setCode( map.id_BUTTON_A() );
		( (KeyboardButton) get_BUTTON_B() ).setCode( map.id_BUTTON_B() );
		( (KeyboardButton) get_BUTTON_BACK() ).setCode( map.id_BUTTON_BACK() );
		( (KeyboardButton) get_BUTTON_START() ).setCode( map.id_BUTTON_START() );
		( (KeyboardButton) get_BUTTON_LB() ).setCode( map.id_BUTTON_LB() );
		( (KeyboardButton) get_BUTTON_L3() ).setCode( map.id_BUTTON_L3() );
		( (KeyboardButton) get_BUTTON_RB() ).setCode( map.id_BUTTON_RB() );
		( (KeyboardButton) get_BUTTON_R3() ).setCode( map.id_BUTTON_R3() );

		( (KeyboardAxis) get_AXIS_LEFT_X() ).setCode( map.id_AXIS_LEFT_X_POS(),
													  map.id_AXIS_LEFT_X_NEG() );
		( (KeyboardAxis) get_AXIS_LEFT_Y() ).setCode( map.id_AXIS_LEFT_Y_POS(),
													  map.id_AXIS_LEFT_Y_NEG() );
		( (KeyboardButton) get_AXIS_LEFT_TRIGGER() ).setCode( map.id_AXIS_LEFT_TRIGGER() );
		( (KeyboardAxis) get_AXIS_RIGHT_X() ).setCode( map.id_AXIS_RIGHT_X_POS(),
													   map.id_AXIS_RIGHT_X_NEG() );
		( (KeyboardAxis) get_AXIS_RIGHT_Y() ).setCode( map.id_AXIS_RIGHT_Y_POS(),
													   map.id_AXIS_RIGHT_Y_NEG() );
		( (KeyboardButton) get_AXIS_RIGHT_TRIGGER() ).setCode( map.id_AXIS_RIGHT_TRIGGER() );

		( (KeyboardPOV) get_DPAD() ).setCode( map.id_DPAD_UP(),
											  map.id_DPAD_DOWN(),
											  map.id_DPAD_LEFT(),
											  map.id_DPAD_RIGHT() );
	}

	@Override
	public boolean getKey( int keyCode ) {
		return input.isKeyPressed( keyCode ); 
	}

	@Override
	protected void createDevice( UnitControl[] units ) {
		units[ BUTTON_X ]     = new KeyboardButton( this );
		units[ BUTTON_Y ]     = new KeyboardButton( this );
		units[ BUTTON_A ]     = new KeyboardButton( this );
		units[ BUTTON_B ]     = new KeyboardButton( this );
		units[ BUTTON_BACK ]  = new KeyboardButton( this );
		units[ BUTTON_START ] = new KeyboardButton( this );
		units[ BUTTON_LB ]    = new KeyboardButton( this );
		units[ BUTTON_L3 ]    = new KeyboardButton( this );
		units[ BUTTON_RB ]    = new KeyboardButton( this );
		units[ BUTTON_R3 ]    = new KeyboardButton( this );

		units[ AXIS_LEFT_X ]        = new KeyboardAxis( this );
		units[ AXIS_LEFT_Y ]        = new KeyboardAxis( this );
		units[ AXIS_LEFT_TRIGGER ]  = new KeyboardButton( this );
		units[ AXIS_RIGHT_X ]       = new KeyboardAxis( this );
		units[ AXIS_RIGHT_Y ]       = new KeyboardAxis( this );
		units[ AXIS_RIGHT_TRIGGER ] = new KeyboardButton( this );
		
		units[ DPAD ] = new KeyboardPOV( this );
	}

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
}
