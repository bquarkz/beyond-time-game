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
package com.intrepid.nicge.utils.pool.exceptions;

import com.intrepid.nicge.utils.pool.IPoolable;
import com.intrepid.nicge.utils.pool.PoolableWrapper;

public class EPoolableNotFoundInPool extends RuntimeException {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************
	private static final long serialVersionUID = 6430756396468083823L;

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public EPoolableNotFoundInPool(PoolableWrapper< ? extends IPoolable > poolable) {
		super( "Poolable: " + poolable.get().getClass().getName() + "; dont exist in Pool" );
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
}
