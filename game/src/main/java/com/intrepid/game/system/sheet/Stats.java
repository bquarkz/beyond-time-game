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

public enum Stats
{
    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
    INITIATIVE( "sheet.stats.initiative" )
    {
        @Override
        public int calculate( AttributeManager attManager )
        {
            Attribute speed = attManager.getSpeed();
            Attribute reflex = attManager.getReflex();
            Attribute luck = attManager.getLuck();
            
            int value = speed.getValue() * 3 + reflex.getValue() * 2 + luck.getValue();
            
            return MAX_STAT_VALUE - value;
        }
    },
    ACCURACY( "sheet.stats.accuracy" )
    {
        @Override
        public int calculate( AttributeManager attManager )
        {
            Attribute reflex = attManager.getReflex();
            
            int value = reflex.getValue() * 3;
            
            return value;
        }
    }
    ;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    private Stats( String internalName )
    {
        this.internalName = internalName;
    }

    // ****************************************************************************************
    // Fields & Ks
    // ****************************************************************************************
    private static final int MAX_STAT_VALUE = 100;
    
    private final String internalName;

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    public abstract int calculate( AttributeManager attManager );

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public String getName()
    {
        return internalName;
    }
}
