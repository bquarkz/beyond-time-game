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
package com.intrepid.nicge.theater.courtain;

import java.util.Set;

import com.intrepid.nicge.content.Resource;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;

public class DefaultCurtain extends AbstractCurtain
{
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public DefaultCurtain() {
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	@Override
	public void initRunBeforeCloseCommand() {
	}

	@Override
	protected void openedUpdate() {
	}

	@Override
	protected void openingUpdate() {
		status = CurtainCondition.OPENED;
	}

	@Override
	protected void closedUpdate() {
	}

	@Override
	protected void closingUpdate() {
		status = CurtainCondition.CLOSED;
	}

	@Override
	protected void openedDisplay(GraphicsBatch batch) {
	}

	@Override
	protected void openingDisplay(GraphicsBatch batch) {
	}

	@Override
	protected void closedDisplay(GraphicsBatch batch) {
	}

	@Override
	protected void closingDisplay(GraphicsBatch batch) {
	}

	@Override
	public void captureResources( Set< Resource< ? > > resources ) {
	}

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
}
