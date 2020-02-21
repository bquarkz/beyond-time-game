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
package com.intrepid.nicge.utils.fsmachine;

public interface IFiniteState
{
	/**
	 * It is called when a "state" was selected and injected into execution pool, normally after a switchTo command
	 */
	void start();
	
	/**
	 * It is called when a "state" was removed from execution pool, normally after a switchTo command
	 */
	void stop();
	
	/**
	 * Called when a new state was called via "plug" command and current one will be "hibernating" for a while.
	 */
	void hibernate();
	
	/**
	 * When "unplug" command was called and the last state on the queue wake up again.
	 */
	void wakeup();
}
