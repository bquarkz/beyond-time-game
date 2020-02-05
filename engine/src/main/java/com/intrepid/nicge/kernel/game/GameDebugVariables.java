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
package com.intrepid.nicge.kernel.game;

public class GameDebugVariables {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private boolean debugPlayMusics;
	private boolean debugShowExtraInfo;
	private boolean debugExecMoinsters;
	private boolean debugExecOrnaments;
	private boolean debugPhysicsRenderer;
	private boolean debugGraphicsRenderer;
	private boolean debugTilemapRenderer;
	private boolean debugDisplayLinkingPoints;
	
	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public GameDebugVariables() {
		debugPlayMusics				= true;
		debugShowExtraInfo			= false;
		debugExecMoinsters			= true;
		debugExecOrnaments			= true;
		debugPhysicsRenderer		= false;
		debugGraphicsRenderer		= true;
		debugTilemapRenderer		= true;
		debugDisplayLinkingPoints	= false;
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************
	public boolean playMusics() {
		return debugPlayMusics;
	}
	
	public boolean displayLinkingPoints() {
		return debugDisplayLinkingPoints;
	}

	public boolean displayExtraInfo() {
		return debugShowExtraInfo;
	}
	
	public boolean execMoinsters() {
		return debugExecMoinsters;
	}
	
	public boolean execOrnaments() {
		return debugExecOrnaments;
	}
	
	public boolean rendererPhysics() {
		return debugPhysicsRenderer;
	}
	
	public boolean rendererGraphics() {
		return debugGraphicsRenderer;
	}
	
	public boolean rendererTilemap() {
		return debugTilemapRenderer;
	}

	// ****************************************************************************************
	// Patterns
	// ***************************************************************************************
}