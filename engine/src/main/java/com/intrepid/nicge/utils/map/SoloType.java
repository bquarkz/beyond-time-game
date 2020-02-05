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

public enum SoloType {
    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
    NORMAL,   // 0
    FALL,     // 1
    PORTAL,   // 2
    BUTTON,   // 3
    ;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    private SoloType() {
        this.id = counter.index++;        
    }

    // ****************************************************************************************
    // Fields & Ks
    // ****************************************************************************************
    private int id;
    
    // Somente para contagem dos termos da estrutura.
    protected static class counter {
        public static int index = 0;
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public static SoloType getSoloType( int index ) {
        if( ( index < 0 ) || ( index >= values().length ) ) {
            throw new RuntimeException( " >>>> index: " + index + "; not found on: " + SoloType.class.getName() + " <<<<" );
        } else {
            return values()[ index ];
        }
    }
    
    public int getId() {
        return id;
    }
}
