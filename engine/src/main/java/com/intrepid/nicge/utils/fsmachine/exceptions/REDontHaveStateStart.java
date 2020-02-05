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

public class REDontHaveStateStart extends RuntimeException {
	private static final long serialVersionUID = -2002629579553785536L;

	public REDontHaveStateStart() {
		this( "Consistence error: The machine do not have a StateStart." );
	}

	public REDontHaveStateStart(String message) {
		super(message);
	}
	
	
}
