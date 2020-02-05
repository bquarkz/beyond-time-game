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

import com.intrepid.nicge.utils.fsmachine.exceptions.REConsistenceError;
import com.intrepid.nicge.utils.fsmachine.exceptions.REDontHaveStateStart;
import com.intrepid.nicge.utils.fsmachine.exceptions.REFiniteStateDontHaveDefaultConstructor;

public abstract class AbstractFSMachineDefinition< T extends FiniteState >
		implements FSMachineDefinition< T >
{
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private boolean isStateStartChecked;
	private T finiteStateStart;
	
	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public AbstractFSMachineDefinition() {
		this.finiteStateStart = null;
		this.isStateStartChecked = false;
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	protected abstract void internal__registerState( T finiteState );
	
	/**
	 * 
	 */
	@Override
	public final void validate() {
		if( !isStateStartChecked ) {
			throw new REDontHaveStateStart();
		}
	}
	
	/**
	 * 
	 * @param classState
	 * @param isStartState
	 * @return
	 */
	public final void registerState( Class< ? extends T > classState, boolean isStartState ) {
		T state = null;
		try {
			state = classState.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new REFiniteStateDontHaveDefaultConstructor( classState );
		}
		
		registerState( state, isStartState );		
	}
	
	/**
	 * 
	 * @param classState
	 * @return
	 */
	public final void registerState( Class< ? extends T > classState ) {
		registerState( classState, false );
	}
	
	/**
	 * 
	 * @param state
	 * @return
	 */
	public final void registerState( T state ) {
		registerState( state, false );
	}
	
	/**
	 * 
	 * @param finiteState
	 * @param isStateStart
	 * @return
	 */
	public final void registerState( T finiteState, boolean isStateStart ) {
		internal__registerState( finiteState );
		
		if( isStateStart ) {
			if( this.isStateStartChecked ) {
				throw new REConsistenceError();
			}
			
			this.finiteStateStart = finiteState;
			this.isStateStartChecked = true;
		}
	}

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************
	protected boolean isStateStartChecked() { 
		return this.isStateStartChecked;
	}
	
	@Override
	public T getStateStart() {
		return finiteStateStart;
	}

	// ****************************************************************************************
	// Patterns
	// ***************************************************************************************
}
