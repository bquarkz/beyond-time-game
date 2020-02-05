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
package com.intrepid.nicge.utils.timer;

import com.intrepid.nicge.utils.logger.Log;
import com.intrepid.nicge.utils.pool.Pool;
import com.intrepid.nicge.utils.pool.PoolableWrapper;
import com.intrepid.nicge.utils.pool.exceptions.EPoolEmpty;
import com.intrepid.nicge.utils.pool.exceptions.EPoolableNotFoundInPool;

public class TimerManager {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************
	private static final int MAX = 50;

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private final Pool< Timer > pool;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public TimerManager() {
		this.pool = new Pool<>( Timer.class, TimerManager.MAX );
	}
	

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	public void instance() {
		this.pool.instancePool();
	}
	

	public PoolableWrapper< Timer > getTimer() {
		try {
			return this.pool.create();
		} catch( EPoolEmpty e ) {
			Log.from( TimerManager.class ).failure(
					"Overflow Pool Timer, MAX: " + this.pool.size() );
			throw new RuntimeException( e );
		}
	}
	
	public void releaseTimer( PoolableWrapper< Timer > timer ) {
		try {
			this.pool.destroy( timer );
		} catch( EPoolableNotFoundInPool e ) {
			Log.from( TimerManager.class ).other( "Timer dont exits in that pool" );
		}
	}
	
	public void pause() {
		for( PoolableWrapper< Timer > timer : this.pool ) {
			timer.get().pause();
		}
	}
	
	public void unpause() {
		for( PoolableWrapper< Timer > timer : this.pool ) {
			timer.get().unpause();
		}
	}
	
	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
}
