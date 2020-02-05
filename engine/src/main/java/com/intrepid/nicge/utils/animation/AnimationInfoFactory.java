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

public class AnimationInfoFactory {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	public static AnimationInfo create( String name, String line ) {
		String[] splits = line.split( "," );
		if( splits.length < 6 )
			throw new IllegalParameterAnimation( "Strange pattern values in new animation" );
		
		int imgSizeX = Integer.parseInt( splits[ 0 ] );
		int imgSizeY = Integer.parseInt( splits[ 1 ] );
		int rows = Integer.parseInt( splits[ 2 ] );
		int cols = Integer.parseInt( splits[ 3 ] );
		float duration = Float.parseFloat( splits[ 4 ] );
		int playMode = Integer.parseInt( splits[ 5 ] );
		
		return new AnimationInfo( name, duration, imgSizeX, imgSizeY, rows, cols, playMode );
	}

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
}
