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
package com.intrepid.nicge.kernel;

public enum Configurations {
    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
    DEFAULT(
            60.0f,
            16,
            false,
            16,
            2,
            512 ),
    DEFAULT_ENCODED(
            60.0f,
            16,
            true,
            16,
            2,
            512 ),
    ;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    Configurations(
            float   updatePerStepSimulation,
            int     pixelsIsOneMeter,
            boolean encoded,
            int     tileSize,
            int     padSize,
            int     tileSetSize
            ) {
        this.updatePerStepSimulation = updatePerStepSimulation;
        this.pixelsIsOneMeter = pixelsIsOneMeter;
        this.encoded = encoded;
        this.tileSize = tileSize;
        this.padSize = padSize;
        this.tileSetSize = tileSetSize;
    }

    // ****************************************************************************************
    // Fields & Ks
    // ****************************************************************************************
    public final float updatePerStepSimulation;
    public final int pixelsIsOneMeter;
    public final boolean encoded;
    public final int tileSize;
    public final int padSize;
    public final int tileSetSize;

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    // get the actual configuration
    public static Configurations get() {
        return DEFAULT;
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
}
