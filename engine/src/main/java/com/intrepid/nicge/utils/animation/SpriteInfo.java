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
package com.intrepid.nicge.utils.animation;

public class SpriteInfo {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private int x;
	private int y;
	
	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public SpriteInfo() {
	}
	
	public SpriteInfo( int x, int y ) {
		this.x = x;
		this.y = y;
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	public void setOrigin( int x, int y ) {
		this.x += x;
		this.y += y;
	}

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
}
