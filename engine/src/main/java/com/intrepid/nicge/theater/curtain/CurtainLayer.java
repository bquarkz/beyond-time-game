/**
 * Copyleft (C) 2016  Constantino, Nilton Rogerio <niltonrc@gmail.com>
 *
 * @author "Nilton R Constantino"
 * aKa bQUARKz <niltonrc@gmail, bquarkz@gmail.com>
 * <p>
 * Everything about the respective software copyright can be found in the
 * "LICENSE" file included in the project source tree.
 * <p>
 * The code was written based on study principles and can be enjoyed for
 * all comunity without problems.
 */
package com.intrepid.nicge.theater.curtain;

public enum CurtainLayer
{
    MAIN( 0 ),
    HUD_INTERFACE( 1 ),
    BEHIND( 2 ),
    ;

    private int position;

    CurtainLayer( int position )
    {
        this.position = position;
    }

    public int getPosition()
    {
        return position;
    }
}