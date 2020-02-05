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

import com.intrepid.nicge.utils.fsmachine.FSMachineDefinition;
import com.intrepid.nicge.utils.fsmachine.FiniteState;

public class REFiniteStateNotFound extends RuntimeException {
	private static final long serialVersionUID = -7437583066247995484L;
	
	public REFiniteStateNotFound(
			@SuppressWarnings("rawtypes")
			Class< ? extends FSMachineDefinition > classMachine,
			Class< ? extends FiniteState > classState ) {
		super( "The FiniteMachineDefs: " + classMachine.getName() +
				"; dont have the FiniteState: " + classState.getName() + ";" );
	}
}
