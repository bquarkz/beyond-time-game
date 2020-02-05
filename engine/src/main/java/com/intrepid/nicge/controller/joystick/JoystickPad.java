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

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.PovDirection;
import com.intrepid.nicge.controller.basics.AbstractPad;
import com.intrepid.nicge.controller.basics.UnitControl;

public class JoystickPad extends AbstractPad implements InspectJoystick {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************
	private static final float DEAD_ZONE = 0.2f;
	private static final float CAP_ZONE = 0.9f;
	
	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private Controller controller;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public JoystickPad( Controller controller, JoystickMap padMap ) {
		this.controller = controller;
		cfgUnitsControls( padMap );
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	private void cfgUnitsControls( JoystickMap padMap ) {
		( ( JoystickButton )get_BUTTON_X() ).setCode( padMap.id_BUTTON_X() );
		( ( JoystickButton )get_BUTTON_Y() ).setCode( padMap.id_BUTTON_Y() );
		( ( JoystickButton )get_BUTTON_A() ).setCode( padMap.id_BUTTON_A() );
		( ( JoystickButton )get_BUTTON_B() ).setCode( padMap.id_BUTTON_B() );
		( ( JoystickButton )get_BUTTON_BACK() ).setCode( padMap.id_BUTTON_BACK() );
		( ( JoystickButton )get_BUTTON_START() ).setCode( padMap.id_BUTTON_START() );
		( ( JoystickButton )get_BUTTON_LB() ).setCode( padMap.id_BUTTON_LB() );
		( ( JoystickButton )get_BUTTON_L3() ).setCode( padMap.id_BUTTON_L3() );
		( ( JoystickButton )get_BUTTON_RB() ).setCode( padMap.id_BUTTON_RB() );
		( ( JoystickButton )get_BUTTON_R3() ).setCode( padMap.id_BUTTON_R3() );

		( ( JoystickAxis )get_AXIS_LEFT_X() ).setCode( padMap.id_AXIS_LEFT_X() );
		( ( JoystickAxis )get_AXIS_LEFT_Y() ).setCode( padMap.id_AXIS_LEFT_Y() );
		( ( JoystickAxis )get_AXIS_LEFT_TRIGGER() ).setCode( padMap.id_AXIS_LEFT_TRIGGER() );
		( ( JoystickAxis )get_AXIS_RIGHT_X() ).setCode( padMap.id_AXIS_RIGHT_X() );
		( ( JoystickAxis )get_AXIS_RIGHT_Y() ).setCode( padMap.id_AXIS_RIGHT_Y() );
		( ( JoystickAxis )get_AXIS_RIGHT_TRIGGER() ).setCode( padMap.id_AXIS_RIGHT_TRIGGER() );
		
		( ( JoystickPOV )get_DPAD() ).setCode( padMap.id_DPAD() );
	}
	
	@Override
	public boolean getButton( int buttonCode ) {
		return controller.getButton( buttonCode );
	}
	
	@Override
	public float getAxis( int axisCode ) {
		return controller.getAxis( axisCode );
	}
	
	@Override
	public PovDirection getPOV( int povCode ) {
		return controller.getPov( povCode );
	}

	@Override
	protected void createDevice( UnitControl[] units ) {
		units[ BUTTON_X ]     = new JoystickButton( this );
		units[ BUTTON_Y ]     = new JoystickButton( this );
		units[ BUTTON_A ]     = new JoystickButton( this );
		units[ BUTTON_B ]     = new JoystickButton( this );
		units[ BUTTON_BACK ]  = new JoystickButton( this );
		units[ BUTTON_START ] = new JoystickButton( this );
		units[ BUTTON_LB ]    = new JoystickButton( this );
		units[ BUTTON_L3 ]    = new JoystickButton( this );
		units[ BUTTON_RB ]    = new JoystickButton( this );
		units[ BUTTON_R3 ]    = new JoystickButton( this );

		units[ AXIS_LEFT_X ]        = new JoystickAxis( this, DEAD_ZONE, CAP_ZONE );
		units[ AXIS_LEFT_Y ]        = new JoystickAxis( this, DEAD_ZONE, CAP_ZONE );
		units[ AXIS_LEFT_TRIGGER ]  = new JoystickAxis( this, DEAD_ZONE, CAP_ZONE );
		units[ AXIS_RIGHT_X ]       = new JoystickAxis( this, DEAD_ZONE, CAP_ZONE );
		units[ AXIS_RIGHT_Y ]       = new JoystickAxis( this, DEAD_ZONE, CAP_ZONE );
		units[ AXIS_RIGHT_TRIGGER ] = new JoystickAxis( this, DEAD_ZONE, CAP_ZONE );
		
		units[ DPAD ] = new JoystickPOV( this );
	}

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************
	public Controller getController() {
		return controller;
	}

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
}
