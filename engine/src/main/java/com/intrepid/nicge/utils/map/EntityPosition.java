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
 * all community without problems.
 */
package com.intrepid.nicge.utils.map;

import com.intrepid.nicge.entities.Entity;

public class EntityPosition {
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    public final int x;
    public final int y;
    public final Class< ? extends Entity > type;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public EntityPosition( int x, int y, Class< ? extends Entity > type ) {
        this.x = x;
        this.y = y;
        this.type = type;
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
