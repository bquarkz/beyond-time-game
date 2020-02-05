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
package com.intrepid.nicge.theater.courtain;

import com.intrepid.nicge.utils.graphics.GraphicsBatch;

public abstract class AbstractCurtain implements Curtain
{
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	protected CurtainCondition status;
	protected CurtainCondition goal;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	protected abstract void openedUpdate();
	protected abstract void openingUpdate();
	protected abstract void closedUpdate();
	protected abstract void closingUpdate();

	protected abstract void openedDisplay( GraphicsBatch batch );
	protected abstract void openingDisplay( GraphicsBatch batch );
	protected abstract void closedDisplay( GraphicsBatch batch );
	protected abstract void closingDisplay( GraphicsBatch batch );

	@Override
	public final void update() {
		switch( status ) {
			case OPENED:	{ openedUpdate();	} break;
			case OPENING:	{ openingUpdate();	} break;
			case CLOSED:	{ closedUpdate();	} break;		
			case CLOSING:	{ closingUpdate();	} break;
		}
	}
	
	@Override
	public final void display( GraphicsBatch batch ) {
		switch( status ) {
			case OPENED:	{ openedDisplay( batch );	} break;
			case OPENING:	{ openingDisplay( batch );	} break;		
			case CLOSED:	{ closedDisplay( batch );	} break;		
			case CLOSING:	{ closingDisplay( batch );	} break;
		}
	}
	
	@Override
	public final void closeCommand() {
		if( status == CurtainCondition.CLOSED ) return;
		status = CurtainCondition.CLOSING;
		goal = CurtainCondition.CLOSED;
	}

	@Override
	public final void openCommand() {
		if( status == CurtainCondition.OPENED ) return;
		status = CurtainCondition.OPENING;
		goal = CurtainCondition.OPENED;
	}

	@Override
	public final boolean isTransitionFinished() {
		return ( status == goal );
	}
	
	@Override
	public final CurtainCondition getStatus() {
		return status;
	}

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ***************************************************************************************
}
