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
package com.intrepid.nicge.theater.curtain.exceptions;

public class CourtainWasntSettedException extends Exception {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************
	private static final long serialVersionUID = 8312228473582683221L;

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public CourtainWasntSettedException() {
	}

	public CourtainWasntSettedException(String message) {
		super(message);
	}

	public CourtainWasntSettedException(Throwable cause) {
		super(cause);
	}

	public CourtainWasntSettedException(String message, Throwable cause) {
		super(message, cause);
	}

	public CourtainWasntSettedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
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
