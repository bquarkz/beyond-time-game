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

import java.util.Iterator;

import com.badlogic.gdx.utils.Array;
import com.intrepid.nicge.utils.fsmachine.exceptions.REConsistenceError;
import com.intrepid.nicge.utils.fsmachine.exceptions.REFiniteStateNotFound;

public class ArrayFSMachineDefinition< T extends IFiniteState >
		extends AbstractFSMachineDefinition< T > {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private Array< T > states;
	private Iterator<T> statesIterator;
	
	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public ArrayFSMachineDefinition() {
		states = new Array<>();
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	
	/**
	 * 
	 */
	@Override
	protected void internal__registerState( T finiteState ) {
		// verify if the states have the same type
		for( IFiniteState state : states ) {
			if( state.getClass() == finiteState.getClass() ) {
				throw new REConsistenceError();	
			}
		}
		
		states.add( finiteState );
	}
	
	@Override
	public boolean contains( Class< ? extends T > tClass ) {
		for( IFiniteState state : states ) {
			if( state.getClass() == tClass ) {
				return true;
			}
		}
		
		return false;
	}
	
//	@Override
//	public void setMachine( FSMachine< FiniteState > machine ) {
//		for( FiniteState state : states ) {
//			state.setMachine( (FSMachine<FiniteState>) machine );
//		}
//	}

	@Override
	public int size() {
		return states.size;
	}
	
	public IFiniteState get( int index ) {
		try{
			return states.get( index );
		} catch( IndexOutOfBoundsException e ) {
			throw new REFiniteStateNotFound( this.getClass(), null );
		}
	}

	@Override
	public T get( Class< ? extends T > stateClass ) {
		if( stateClass == null ) {
			throw new REFiniteStateNotFound( this.getClass(), stateClass );
		}
		
		for( T state : states ) {
			if( state.getClass() == stateClass ) {
				return state;
			}
		}
		
		throw new REFiniteStateNotFound( this.getClass(), stateClass );
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
		statesIterator = states.iterator(); 
		return statesIterator;
	}

	
	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ***************************************************************************************
}