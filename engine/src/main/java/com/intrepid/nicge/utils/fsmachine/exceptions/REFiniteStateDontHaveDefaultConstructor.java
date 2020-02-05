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

import com.intrepid.nicge.utils.fsmachine.FiniteState;

public class REFiniteStateDontHaveDefaultConstructor extends RuntimeException {
	private static final long serialVersionUID = 4284025395997879936L;

	public REFiniteStateDontHaveDefaultConstructor( Class< ? extends FiniteState > classState ) {
		super( "The class state: " + classState.getName() +
			  "; dont have the default constructor.\n" );
	}
}
