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
package com.intrepid.studio.kernel;

import com.intrepid.nicge.kernel.AbstractAdapter;
import com.intrepid.nicge.kernel.Application;
import com.intrepid.nicge.kernel.Boot;
import com.intrepid.nicge.kernel.game.GameConfiguration;

public final class StudioAdapter extends AbstractAdapter {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public StudioAdapter( Boot< GameConfiguration > boot ) {
		super( boot );
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Application getApplication( Boot boot ) {
		return new Studio( (Boot< GameConfiguration >) boot );
	}

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ***************************************************************************************
}