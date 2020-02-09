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
package com.intrepid.nicge.theater.scene;

import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.theater.curtain.Curtain;
import com.intrepid.nicge.theater.curtain.DefaultCurtain;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;

public abstract class AbstractBootScene implements IScene
{
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private boolean isFirstTime;
	private Curtain curtain;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public AbstractBootScene() {
		curtain = new DefaultCurtain();
		isFirstTime = true;
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	public abstract Class<? extends IScene > getStartScene();
	
	@Override
	public final void start() {
		isFirstTime = true;
	}
	
	@Override
	public final void stop() {
		isFirstTime = false;
	}

	@Override
	public final void hibernate() {
	}

	@Override
	public final void wakeup() {
	}

	@Override
	public final void inputControlLogic() {
	}

	@Override
	public final void simulation() {
	}

	@Override
	public final void update() {
		if( isFirstTime ) {
			isFirstTime = false;
			Game.scene.change( getStartScene(), curtain );
		}
	}

	@Override
	public final void display( GraphicsBatch batch ) {
		Game.graphics.clearScreen();
	}

	@Override
	public final void configureGraphicsBatch( GraphicsBatch stageBatch ) {
	}

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
}
