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

import com.intrepid.nicge.kernel.IControllable;
import com.intrepid.nicge.kernel.IDisplayable;
import com.intrepid.nicge.kernel.ISimulation;
import com.intrepid.nicge.kernel.IUpdatable;
import com.intrepid.nicge.utils.fsmachine.IFiniteState;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;

public interface IScene
		extends IFiniteState, IControllable, ISimulation, IUpdatable, IDisplayable
{
	void configureGraphicsBatch( GraphicsBatch stageBatch );
}
