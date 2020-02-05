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
package com.intrepid.nicge.utils.fsmachine.exceptions;

import com.intrepid.nicge.utils.fsmachine.FSMachine;

public class RENullFSMachineDefs extends RuntimeException {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************
	private static final long serialVersionUID = 8753211355563408988L;

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	@SuppressWarnings("rawtypes")
	public RENullFSMachineDefs( Class< ? extends FSMachine > machineClass ) {
		super( "In class: " + machineClass.getName() + "; The Finite State Machine Defs canot be null." );
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ***************************************************************************************
}
