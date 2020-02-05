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
package com.intrepid.studio.enviroment;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.intrepid.nicge.theater.Displayable;
import com.intrepid.nicge.theater.cameras.Camera;
import com.intrepid.nicge.ui.IComponent;
import com.intrepid.nicge.utils.IProcessExecution;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;
import com.intrepid.studio.kernel.Studio;

public abstract class Enviroment implements IProcessExecution, InputProcessor {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************
	private static final GraphicsBatch batch;
	private static final int windowResolutionWidth;
	private static final int windowResolutionHeight;
	
	static {
		batch = new GraphicsBatch();
		windowResolutionWidth = Studio.common.getConfiguration().getWindowResolutionWidth();
		windowResolutionHeight = Studio.common.getConfiguration().getWindowResolutionHeight();
		Camera camera = new Camera( windowResolutionWidth, windowResolutionHeight );
		batch.setProjectionMatrix( camera.combined );
	}

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private List< IComponent > componentList;
	private List< Displayable > displayableList;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public Enviroment() {
		Gdx.input.setInputProcessor( this );
		componentList = new ArrayList<>();
		displayableList = new ArrayList<>();
		addComponents( componentList );
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	public abstract void addComponents( List< IComponent > list );
	
	public void addDisplayable( Displayable displayable ) {
		displayableList.add( displayable );
	}
	
	@Override
	public boolean keyDown( int keycode ) {
		return false;
	}
	
	@Override
	public boolean keyTyped( char character ) {
//		switch( ch ) {
//		case 8: {
//			int lenght = sb.length();
//			if( lenght > 0 ) sb.deleteCharAt( lenght - 1 );
//		}break;
//		
//		case 13: {
//			// muito primario mais eh mais ou menos isso
//			String s = sb.toString();
//			if( s.equals( "exit" ) ) Gdx.app.exit();
//			sb.append( "\n" );
//		}break;
//		
//		default: {
//			sb.append( ch );			
//		}
//		}

		return false;
	}
	
	@Override
	public boolean keyUp( int keycode ) {
		return false;
	}
	
	@Override
	public boolean mouseMoved( int screenX, int screenY ) {
		screenY = windowResolutionHeight - screenY;
		
		for( IComponent c : componentList ) {
			c.checkMouseOver( screenX, screenY );
		}

		return false;
	}
	
	@Override
	public boolean scrolled( int amount ) {
		return false;
	}
	
	@Override
	public boolean touchDown( int screenX, int screenY, int pointer, int button ) {
		screenY = windowResolutionHeight - screenY;
		
		for( IComponent c : componentList ) {
			c.checkMouseOver( screenX, screenY );
			c.mouseButtonPressed( screenX, screenY, button );
		}
		
		return false;
	}
	
	@Override
	public boolean touchDragged( int screenX, int screenY, int pointer ) {
		return false;
	}
	
	@Override
	public boolean touchUp( int screenX, int screenY, int pointer, int button ) {
		screenY = windowResolutionHeight - screenY;

		for( IComponent c : componentList ) {
			c.checkMouseOver( screenX, screenY );
			c.mouseButtonUnPressed( screenX, screenY, button );
		}
		
		return false;
	}
	
	@Override
	public void execute() {

		for( IComponent c : componentList ) {
			c.update();
		}
		
		batch.begin();
		for( IComponent c : componentList ) {
			c.display( batch );
		}
		
		for( Displayable d : displayableList ) {
			d.display( batch );
		}
		batch.end();
	}

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
}
