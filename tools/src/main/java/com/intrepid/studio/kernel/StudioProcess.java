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
package com.intrepid.studio.kernel;

import com.intrepid.nicge.utils.IProcessExecution;
import com.intrepid.studio.enviroment.ButtonEnviroment;

public enum StudioProcess implements IProcessExecution
{
	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
	// The process sequency is important
	CONTROL		( new ButtonEnviroment()		),
	AUDIO		( new Studio.AudioUpdate()		),
	;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	StudioProcess( IProcessExecution processExecution ) {
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
	public void execute() {
		double ti = Studio.time.getSystemMicroTime();
		processExecution.execute();
		double tf = Studio.time.getSystemMicroTime();
		executionTime = tf - ti;
	}
	
	public double getExecutionTime() {
		return executionTime;
	}

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************
}
