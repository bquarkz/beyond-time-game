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
package com.intrepid.game.system.sheet;

public enum Attributes
{
    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
    REFLEX( "sheet.attribute.reflex" ),
    SPEED( "sheet.attribute.speed" ),
    LUCK( "sheet.attribute.luck" ),
    ;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    private Attributes( String internalName )
    {
        this.internalName = internalName;
    }

    // ****************************************************************************************
    // Fields & Ks
    // ****************************************************************************************
    private final String internalName;

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public String getName()
    {
        return internalName;
    }
}
