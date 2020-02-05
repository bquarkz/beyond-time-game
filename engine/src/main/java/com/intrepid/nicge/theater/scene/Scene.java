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
package com.intrepid.nicge.theater.scene;

import com.intrepid.nicge.theater.Controlable;
import com.intrepid.nicge.theater.Displayable;
import com.intrepid.nicge.theater.Simulation;
import com.intrepid.nicge.theater.Updatable;
import com.intrepid.nicge.utils.fsmachine.FiniteState;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;

public interface Scene extends 	FiniteState,
								Controlable,
								Simulation,
								Updatable,
								Displayable
{
	void configureGraphicsBatch( GraphicsBatch stageBatch );
}
