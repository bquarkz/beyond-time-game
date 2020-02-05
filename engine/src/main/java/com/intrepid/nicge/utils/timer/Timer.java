package com.intrepid.nicge.utils.timer;

import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.theater.Updatable;
import com.intrepid.nicge.utils.pool.Poolable;

public class Timer implements Updatable, Poolable {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private float elapsedTime;
	private float pausedTime;
	private boolean isRunning;
	private boolean isPaused;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public Timer() {
		this.elapsedTime = 0.0f;
		this.pausedTime = 0.0f;
		this.isRunning = false;
		this.isPaused = false;
	}
	
	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	public void start() {
		pausedTime = 0.0f;
		elapsedTime = 0.0f;
		isRunning = true;
		isPaused = false;
	}

	public void stop() {
		isRunning = false;
		isPaused = false;
	}
	
	public void pause() {
		isPaused = true;
	}
	
	public void unpause() {
		isPaused = false;
	}
	
	public boolean checkTimeInMiliSecs( float timeInMili ) {
		float checkTime = timeInMili / 1000f;
		return getTotalTime() >= checkTime;
	}

	@Override
	public void update() {
		if( isRunning ) {
			elapsedTime += Game.time.getRawDeltaTime();
			
			if( isPaused ) {
				pausedTime += Game.time.getRawDeltaTime();
			}
		}
	}
	
	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************
	public float getTotalTime() {
		float totalTime = elapsedTime - pausedTime;
		return ( totalTime > 0.0f ) ? totalTime : 0.0f;
	}
	
	public float getRunningTime() {
		return elapsedTime;
	}
	
	public float getPausedTime() {
		return pausedTime;
	}
	
	// ****************************************************************************************
	// Patterns
	// ***************************************************************************************
}
