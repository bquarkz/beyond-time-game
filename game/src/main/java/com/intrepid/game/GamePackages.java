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
package com.intrepid.game;

public enum GamePackages
{
    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
    ROOT( "com.intrepid" ),
    ;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    GamePackages( String pack )
    {
        this.pack = pack;
    }

    // ****************************************************************************************
    // Fields & Ks
    // ******************************************a**********************************************
    private String pack;

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public String getPack()
    {
        return pack;
    }
}
