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
package com.intrepid.nicge.theater.cameras;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera extends OrthographicCamera {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private int nativeResolutionWidth;
	private int nativeResolutionHeight;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public Camera( int nativeResolutionWidth, int nativeResolutionHeight ) {
		this.nativeResolutionWidth = nativeResolutionWidth;
		this.nativeResolutionHeight = nativeResolutionHeight;

		setToOrtho( false, nativeResolutionWidth, nativeResolutionHeight );
	}


	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	
	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************
	public int getNativeResolutionWidth() {
		return nativeResolutionWidth;
	}

	public int getNativeResolutionHeight() {
		return nativeResolutionHeight;
	}

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
}