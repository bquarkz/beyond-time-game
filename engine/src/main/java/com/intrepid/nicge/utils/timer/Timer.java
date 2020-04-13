package com.intrepid.nicge.utils.timer;

import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.kernel.IUpdatable;
import com.intrepid.nicge.utils.pool.IPoolable;

public class Timer implements IUpdatable, IPoolable
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private long elapsedTime;
    private long pausedTime;
    private boolean isRunning;
    private boolean isPaused;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public Timer()
    {
        this.elapsedTime = 0L;
        this.pausedTime = 0L;
        this.isRunning = false;
        this.isPaused = false;
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    public void start()
    {
        pausedTime = 0L;
        elapsedTime = 0L;
        isRunning = true;
        isPaused = false;
    }

    public void stop()
    {
        isRunning = false;
        isPaused = false;
    }

    public void pause()
    {
        isPaused = true;
    }

    public void unpause()
    {
        isPaused = false;
    }

    public boolean checkTime_ns( long time_ns )
    {
        return getTotalTime_ns() >= time_ns;
    }

    public boolean checkTime_ms( long time_ms )
    {
        long time_ns = time_ms * 1_000_000;
        return checkTime_ns( time_ns );
    }

    public boolean checkTime_ns( double time_ns )
    {
        return getTotalTime_ns() >= (long)time_ns;
    }

    public boolean checkTime_ms( double time_ms )
    {
        long time_ns = (long)time_ms * 1_000_000;
        return checkTime_ns( time_ns );
    }

    @Override
    public void update()
    {
        if( isRunning )
        {
            elapsedTime += Game.time.getRawDeltaTime_ns();

            if( isPaused )
            {
                pausedTime += Game.time.getRawDeltaTime_ns();
            }
        }
    }

    public void reset()
    {
        elapsedTime = 0L;
        pausedTime = 0;
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public long getTotalTime_ns()
    {
        long totalTime = elapsedTime - pausedTime;
        return Math.max( totalTime, 0L );
    }

    public long getTotalTime_ms()
    {
        return getTotalTime_ns() / 1_000_000L;
    }

    public long getTotalTime_s()
    {
        return getTotalTime_ns() / 1_000_000_000L;
    }

    public long getRunningTime()
    {
        return elapsedTime;
    }

    public long getPausedTime()
    {
        return pausedTime;
    }

    // ****************************************************************************************
    // Patterns
    // ***************************************************************************************
}
