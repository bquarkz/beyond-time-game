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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.intrepid.nicge.utils.fsmachine.exceptions.REConsistenceError;
import com.intrepid.nicge.utils.fsmachine.exceptions.REFiniteStateNotFound;

public class HashFSMachineDefinition< T extends FiniteState >
		extends AbstractFSMachineDefinition< T > {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private Map< String, T > states;
	private Iterator<T> statesIterator;
	
	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public HashFSMachineDefinition() {
		states = new HashMap<>();
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	@Override
	protected void internal__registerState( T finiteState ) {
		// verify if the states have the same type
		if( states.containsKey( finiteState.getClass().getName() ) ) {
			throw new REConsistenceError();
		}
		
		states.put( finiteState.getClass().getName(), finiteState );
	}
	
	@Override
	public boolean contains( Class< ? extends T > tClass ) {
		return states.containsKey( tClass.getName() );
	}

//	@Override
//	public void setMachine( FSMachine< FiniteState > machine ) {
//		for( FiniteState state : states.values() ) {
//			state.setMachine( machine );
//		}
//	}
	
	@Override
	public int size() {
		return states.size();
	}
	
	@Override
	public T get( Class< ? extends T > stateClass ) {
		try {
			return states.get( stateClass.getName() );
		} catch( NullPointerException | ClassCastException e ) {
			throw new REFiniteStateNotFound( this.getClass(), stateClass );
		}
	}

	@Override
	public boolean hasNext() {
		return statesIterator.hasNext();
	}

	@Override
	public T next() {
		return statesIterator.next();
	}

	@Override
	public Iterator<T> iterator() {
		statesIterator = states.values().iterator();
		return statesIterator;
	}

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ***************************************************************************************
}