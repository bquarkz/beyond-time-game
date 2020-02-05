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
package com.intrepid.nicge.utils.animation;

public class IllegalParameterAnimation extends RuntimeException {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************
	private static final long serialVersionUID = -1504048502732281191L;

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public IllegalParameterAnimation( String name ) {
		super( "Problems in parameters animation: " + name );
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
}
