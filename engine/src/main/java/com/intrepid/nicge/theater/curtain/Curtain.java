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
package com.intrepid.nicge.theater.curtain;

import com.intrepid.nicge.content.ILoadable;
import com.intrepid.nicge.kernel.IDisplayable;
import com.intrepid.nicge.kernel.IUpdatable;

public interface Curtain extends IUpdatable, IDisplayable, ILoadable
{
    void initRunBeforeCloseCommand();

    void closeCommand();

    void openCommand();

    boolean isTransitionFinished();

    CurtainCondition getStatus();
}
