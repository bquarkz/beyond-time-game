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
package com.intrepid.nicge.kernel.game;

import com.intrepid.nicge.theater.Theater;
import com.intrepid.nicge.utils.IProcessExecution;

public enum GameProcess implements IProcessExecution
{
    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
    // sequence is VERY important
    KICK_START_FRAME( new Theater.KickStartFrame() ),
    CONTROL( new Theater.CaptureControlProcess() ),
    SIMULATION( new Theater.Simulation() ),
    UPDATE( new Theater.UpdateProcess() ),
    DISPLAY( new Theater.DisplayProcess() ),
    AUDIO( new Game.AudioUpdate() ),
    ;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    GameProcess( IProcessExecution processExecution )
    {
        this.processExecution = processExecution;
    }

    // ****************************************************************************************
    // Fields & Ks
    // ****************************************************************************************
    private IProcessExecution processExecution;
    private double executionTime;

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    @Override
    public void execute()
    {
        double ti = Game.time.getSystemMilliTime();
        processExecution.execute();
        double tf = Game.time.getSystemMilliTime();
        executionTime = tf - ti;
    }

    public double getExecutionTime()
    {
        return executionTime;
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
}
