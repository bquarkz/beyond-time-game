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
package com.intrepid.nicge.entities;

import com.badlogic.gdx.math.Vector2;
import com.intrepid.nicge.kernel.IDisplayable;
import com.intrepid.nicge.kernel.IUpdatable;

public interface Entity
        extends Comparable< Entity >, IUpdatable, IDisplayable
{
    Vector2 getPosition();

    long getId();

    @Override
    default int compareTo( Entity that )
    {
        // crescent order
		if( this.getPosition().y > that.getPosition().y )
		{
			return -1;
		}
		if( this.getPosition().y < that.getPosition().y )
		{
			return 1;
		}
        return 0;
    }
}
