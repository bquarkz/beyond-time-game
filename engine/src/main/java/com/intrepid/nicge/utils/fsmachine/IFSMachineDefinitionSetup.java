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
package com.intrepid.nicge.utils.fsmachine;

public interface IFSMachineDefinitionSetup< T extends IFiniteState > {
//	void setMachine( FSMachine< FiniteState > machine );
	public void registerState( Class< ? extends T > classState );
	public void registerState( Class< ? extends T > classState, boolean isStartState );
	public void validate();
}
