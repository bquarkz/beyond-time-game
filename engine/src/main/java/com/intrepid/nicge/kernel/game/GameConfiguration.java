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

import com.intrepid.nicge.kernel.AppConfiguration;

public interface GameConfiguration extends AppConfiguration {
	float getMETER_TO_PIXEL_RATIO();
	float getPIXEL_TO_METER_RATIO();
	boolean isEncoded();
	float getRAW_DELTA_TIME_IN_MILI_SECS();
	float getUPDATE_PER_STEP_SIMULATION();
	float getCONST_WORLD_UPDATE();
	int getTileSize();
    int getPadSize();
    int getTilesetSize();
}
