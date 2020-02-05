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
package com.intrepid.studio.animation;

class FreeSpace implements Comparable< FreeSpace > {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************
	
	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	public final int x;
	public final int y;
	public final int width;
	public final int height;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public FreeSpace( int x, int y, int width, int height ) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	@Override
	public String toString() {
		return "x: " + x + ", y: " + y + ", width: " + width + ", height: " + height;
	}

	@Override
	public int compareTo( FreeSpace that ) {
		if( this.width < that.width ) return -1;
		if( this.width > that.width ) return 1;
		return 0;
	}
	
	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************
	
	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************

}