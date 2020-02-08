/**
 * Copyleft (C) 2016  Constantino, Nilton Rogerio <niltonrc@gmail.com>
 *
 * @author "Nilton R Constantino"
 * aKa bQUARKz <niltonrc@gmail, bquarkz@gmail.com>
 * <p>
 * Everything about the respective software copyright can be found in the
 * "LICENSE" file included in the project source tree.
 * <p>
 * The code was written based on study principles and can be enjoyed for
 * all comunity without problems.
 */
package com.intrepid.nicge.utils.fsmachine;

import com.intrepid.nicge.utils.containers.IContainer;
import com.intrepid.nicge.utils.containers.Stack;
import com.intrepid.nicge.utils.fsmachine.exceptions.EFSMachineStop;
import com.intrepid.nicge.utils.fsmachine.exceptions.RENullFSMachineDefs;

public class FSMachine< T extends IFiniteState >
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************
    private static enum TransitionType
    {
        START, SWITCH_TO, PLUG, UNPLUG;
    }

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private IFSMachineDefinition< T > fsMachineDefinition;
    private T actualState;
    private T nextState;
    private IContainer< T > containerState;
    private TransitionType transitionType;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
//	@SuppressWarnings("unchecked")
    public FSMachine( IFSMachineDefinition< T > fsMachineDef )
    {
        if( fsMachineDef == null )
        {
            throw new RENullFSMachineDefs( this.getClass() );
        }
        fsMachineDef.validate();
        this.fsMachineDefinition = fsMachineDef;
//		this.fsMachineDefinition.setMachine( (FSMachine<FiniteState>) this );
        this.containerState = new Stack<>();

        startMachine();
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************

    /**
     *
     */
    public final void startMachine()
    {
        nextState = fsMachineDefinition.getStateStart();
        transitionType = TransitionType.START;
    }

    /**
     *
     * @param classState
     */
    public final void switchTo( Class< ? extends T > classState )
    {
        T state = fsMachineDefinition.get( classState );
        switchTo( state );
    }

    /**
     *
     * @param nextState
     */
    public final void switchTo( T nextState )
    {
        this.nextState = nextState;
        transitionType = TransitionType.SWITCH_TO;
    }

    /**
     *
     * @param classState
     */
    public void plug( Class< ? extends T > classState )
    {
        plug( fsMachineDefinition.get( classState ) );
    }

    /**
     *
     * @param nextState
     */
    public final void plug( T nextState )
    {
        this.nextState = nextState;
        transitionType = TransitionType.PLUG;
    }

    /**
     *
     */
    public final void unplug()
    {
		if( containerState.isEmpty() )
		{
			return;
		}
        transitionType = TransitionType.UNPLUG;
    }

    /**
     * @throws EFSMachineStop
     *
     */
    public void apply() throws EFSMachineStop
    {
        // do nothing if transition is null
		if( transitionType == null )
		{
			return;
		}

        switch( transitionType )
        {
            case START:
            {
                containerState.clear();
                containerState.push( nextState );
                nextState.start();
            }
            break;

            case SWITCH_TO:
            {
                containerState.pop().stop();
                containerState.push( nextState );
                nextState.start();
            }
            break;

            case PLUG:
            {
                containerState.get().hibernate();
                containerState.push( nextState );
                nextState.start();
            }
            break;

            case UNPLUG:
            {
                containerState.pop().stop();

                if( containerState.isEmpty() )
                {
                    throw new EFSMachineStop( this.getClass() );
                }
                nextState = containerState.get();
                nextState.wakeup();
            }
            break;
        }

        actualState = nextState;
        nextState = null;
        transitionType = null;
    }

//	/**
//	 * @throws EFSMachineStop 
//	 * 
//	 */
//	public boolean loop() throws EFSMachineStop {
//		if( containerState.isEmpty() ) {
//			throw new EFSMachineStop( this.getClass() );
//		}
//		
//		return containerState.get().state_onExecute();
//	}

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public T getActualFiniteState()
    {
        return actualState;
    }

    public IFSMachineDefinitionAccess< T > getDefinition()
    {
        return fsMachineDefinition;
    }

    public boolean inTransition()
    {
        return transitionType != null;
    }

    // ****************************************************************************************
    // Patterns
    // ***************************************************************************************
}