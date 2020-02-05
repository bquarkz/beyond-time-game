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
package com.intrepid.studio.component;

import com.intrepid.nicge.utils.threads.ThreadRunnable;

public class ButtonFactory {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public ButtonFactory() {
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	public Button create( int x, int y, int width, int height, String text ) {
		return new Button( x, y, width, height, text );
	}
	
	public Button create( int x, int y, int width, int height, String text,
	        ThreadRunnable command, boolean lockSystem ) {
		return new Button( x, y, width, height, text, command, lockSystem );
	}
	
	public Button create( int x, int y, int width, int height, String text,
	        ThreadRunnable command ) {
		return new Button( x, y, width, height, text, command, true );
	}
	
    public Button create( int x, int y, int width, int height, String text,
            ThreadRunnable... commands ) {
        return new Button( x, y, width, height, text, true, commands );
    }

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
}
