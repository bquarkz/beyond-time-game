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
package com.intrepid.nicge.content;

import com.intrepid.nicge.kernel.game.Game;

public class ResourceWrapper< T > implements IResource< T >
{
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private final int id;
	private final String name;
	private final Class< T > resourceClass;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public ResourceWrapper( int id, String name, Class< T > resourceClass ) {
		this.id = id;
		this.name = name;
		this.resourceClass = resourceClass;
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	@Override
	public int getResourceId() {
		return this.id;
	}
	
	@Override
	public String getPath() {
		return this.name;
	}
	
	@Override
	public Class< T > getResourceClass() {
		return this.resourceClass;
	}

	@Override
	public T getAsset() {
		return ( T ) Game.common.getAsset( this );
	}

	@Override
	public int hashCode() {
		return getResourceId();
	}

	@Override
	public boolean equals( Object that ) {
		if( that == null ) return false;
		if( this == that ) return true;
		if( getClass() != that.getClass() ) return false;
		
		if( this.getResourceId() == ( (IResource< ? >)that ).getResourceId() )
			return true;
		else
			return false;
	}
	
	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
}
