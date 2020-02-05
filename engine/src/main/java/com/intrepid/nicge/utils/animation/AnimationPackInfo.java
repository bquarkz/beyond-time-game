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

import java.util.Iterator;

public class AnimationPackInfo implements Iterable< AnimationInfo > {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private AnimationInfo[] animationsInfo;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public AnimationPackInfo() { // it will be used for json instanciation
	}
	
	public AnimationPackInfo( AnimationInfo[] animationsInfo ) {
		this.animationsInfo = animationsInfo;
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	@Override
	public Iterator< AnimationInfo > iterator() {
		return new Iterator< AnimationInfo >() {
			private int counter;
			
			{
				this.counter = 0;
			}
						
			@Override
			public boolean hasNext() {
				return ( counter < animationsInfo.length ? true : false );
			}

			@Override
			public AnimationInfo next() {
				return animationsInfo[ counter++ ];
			}
		};
	}

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************
	public AnimationInfo get( int index ) {
		return animationsInfo[ index ];
	}
	
	public int size() {
		return animationsInfo.length;
	}

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
}
