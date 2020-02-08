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
package com.intrepid.nicge.utils.threads;

public interface IThreadRunnable extends Runnable {
	void startThread();
	boolean executeThread();
	void shutdownThread();
	
	@Override
	default void run() {
		startThread();
		while( executeThread() );
		shutdownThread();
	}
}
