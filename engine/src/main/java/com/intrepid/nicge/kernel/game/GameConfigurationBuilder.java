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
package com.intrepid.nicge.kernel.game;

import com.intrepid.nicge.kernel.AppConfigurationBuilder;
import com.intrepid.nicge.kernel.Configurations;

public class GameConfigurationBuilder extends AppConfigurationBuilder implements GameConfiguration {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
    private Configurations configurations;
    
	private float UPDATE_PER_STEP_SIMULATION;
	private float CONST_WORLD_UPDATE;
	private float METER_TO_PIXEL_RATIO;
	private float PIXEL_TO_METER_RATIO;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public GameConfigurationBuilder() {
	    internalConfiguration( Configurations.get() );
    }

	// ****************************************************************************************
	// Setters Methods
	// ****************************************************************************************
	private final void internalConfiguration( Configurations configurations ) {
	    this.configurations = configurations;
        this.setUpdatePerStepSimulation( configurations.updatePerStepSimulation );
	    this.howManyPixelsIsOneMeter( configurations.pixelsIsOneMeter );
	}
	
	public final void setUpdatePerStepSimulation( float value ) {
		UPDATE_PER_STEP_SIMULATION = value;
		CONST_WORLD_UPDATE = 1.0f / UPDATE_PER_STEP_SIMULATION;
	}
	
	public final void howManyPixelsIsOneMeter( int pixels ) {
		METER_TO_PIXEL_RATIO = (float) pixels;
		PIXEL_TO_METER_RATIO = 1.0f / METER_TO_PIXEL_RATIO;
	}

	// ****************************************************************************************
	// Getters Methods
	// ****************************************************************************************
	@Override
	public boolean isEncoded() {
		return configurations.encoded;
	}

	@Override
	public float getRAW_DELTA_TIME_IN_MILI_SECS() {
		return getRawDeltaTimeInMiliSecs();
	}

	@Override
	public float getUPDATE_PER_STEP_SIMULATION() {
		return UPDATE_PER_STEP_SIMULATION;
	}

	@Override
	public float getCONST_WORLD_UPDATE() {
		return CONST_WORLD_UPDATE;
	}

	@Override
	public int getTileSize() {
		return configurations.tileSize;
	}

	@Override
	public float getMETER_TO_PIXEL_RATIO() {
		return METER_TO_PIXEL_RATIO;
	}

	@Override
	public float getPIXEL_TO_METER_RATIO() {
		return PIXEL_TO_METER_RATIO;
	}

    @Override
    public int getPadSize() {
        return configurations.padSize;
    }

    @Override
    public int getTilesetSize() {
        return configurations.tileSetSize;
    }
	
	// ****************************************************************************************
	// Patterns
	// ***************************************************************************************
}
