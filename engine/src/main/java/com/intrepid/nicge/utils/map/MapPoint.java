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

public class MapPoint {
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
    public MapPoint() {
        this( 0, 0 );
    }

    public MapPoint( int x, int y ) {
        setX( x );
        setY( y );
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    public int getX() {
        return x;
    }

    public void setX( int x ) {
        this.x = ( x >= 0 ? x : 0 );
    }

    public int getY() {
        return y;
    }

    public void setY( int y ) {
        this.y = ( y >= 0 ? y : 0 );
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
