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
package com.intrepid.studio.component;

import java.util.Optional;
import java.util.concurrent.Future;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.intrepid.nicge.gui.IComponent;
import com.intrepid.nicge.kernel.game.GameDebugVariables;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;
import com.intrepid.nicge.utils.graphics.TextureWorks;
import com.intrepid.nicge.utils.threads.ThreadExecutor;
import com.intrepid.nicge.utils.threads.IThreadRunnable;

public class Button implements IComponent {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************
	private static final int BUTTON_ACTION = MOUSE_BUTTON_LEFT;
	private static final ColorsStructure IDLE_COLOR = new ColorsStructure( Color.BLACK, Color.WHITE );
	private static final ColorsStructure OVER_COLOR = new ColorsStructure( Color.YELLOW, Color.GRAY );
	private static final ColorsStructure CLICKED_COLOR = new ColorsStructure( Color.RED, Color.DARK_GRAY );
	private static final ColorsStructure LOCKED_COLOR = new ColorsStructure( Color.YELLOW, Color.GREEN );

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private int width;
	private int x;
	private int y;
	private int height;
	private String text;
	private Texture texture;
	private boolean mouseOverMe;
	private boolean isClicked;
	private boolean isActived;
	private BitmapFont bitmapFont;
	private int diffx;
	private int diffy;
	private IThreadRunnable[] commands;
	private boolean cmdLockSystem;
	private Future< IThreadRunnable > execute;
	private boolean lockedIn;
    private int cmdIndex;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	Button( int x, int y, int width, int height, String text ) {
		this( x, y, width, height, text, null, false );
	}

    Button( int x, int y, int width, int height, String text,
            IThreadRunnable command,
            boolean cmdLockSystem ) {
        this( x, y, width, height, text, cmdLockSystem, new IThreadRunnable[] { command } );
    }

	Button( int x, int y, int width, int height,
			String text,
			boolean cmdLockSystem,
			IThreadRunnable[] commands
			 ) {
 		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.commands = commands;
		this.cmdLockSystem = cmdLockSystem;
		this.mouseOverMe = false;
		this.isClicked = false;
		this.isActived = false;
		this.lockedIn = false;
		this.bitmapFont = new BitmapFont();
		this.texture = TextureWorks.createTexture( width, height, Color.WHITE );
		
		this.cmdIndex = 0;
		
		calculateDiff(width, height, text);
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	private void calculateDiff( int width, int height, String text ) {
		int length = text.length();
		int letterWidth = 4;
		int letterHeight = 5;
		diffx = ( width / 2 ) - ( length * letterWidth );
		diffy = ( height / 2 ) + letterHeight;
	}

	@Override
	public void checkMouseOver( int screenX, int screenY ) {
		if( lockedIn ) return;
		
		boolean cx = ( ( screenX >= x ) && ( screenX <= ( x + width ) ) );
		boolean cy = ( ( screenY >= y ) && ( screenY <= ( y + height ) ) );
		
		if( cx && cy ) mouseOverMe = true;
		else mouseOverMe = false;
	}

	@Override
	public void mouseButtonPressed( int screenX, int screenY, int button ) {
		if( lockedIn ) return;
		
		checkMouseOver( screenX, screenY );
		
		if( mouseOverMe ) {
			if( button == BUTTON_ACTION ) {
				isClicked = true;
			}
		}
	}

	@Override
	public void mouseButtonUnPressed( int screenX, int screenY, int button ) {
		if( lockedIn ) return;
		
		checkMouseOver( screenX, screenY );
		
		if( button == BUTTON_ACTION ) {
			if( mouseOverMe && isClicked ) {
				isActived = true;
			} else {
				isActived = false;
			}
		}
		
		isClicked = false;
	}
	
	@Override
	public void update() {
		if( isActived && ( commands[ cmdIndex ] != null ) ) {
			if( cmdLockSystem ) {
				lockedIn = true;
				execute = new ThreadExecutor().execute( commands[ cmdIndex++ ] );
			} else {
			    for( IThreadRunnable command : commands ) {
			        command.run();
			    }
			}
		}
		
		if( lockedIn )
		{
    		if( cmdLockSystem ) {
    			if( execute != null && execute.isDone() ) {
    			    if( cmdIndex >= commands.length ) {
    			        lockedIn = false;
    			        cmdIndex = 0;
    			    } else {
    			        execute = new ThreadExecutor().execute( commands[ cmdIndex++ ] );
    			    }
    			}
    		} else {
    		    lockedIn = false;
    		}
		}
		
		isActived = false;
	}
	
	@Override
	public void display( GraphicsBatch batch ) {
		ColorsStructure color = IDLE_COLOR;
		if( mouseOverMe ) {
			color = OVER_COLOR;
		}

		if( isClicked ) {
			color = CLICKED_COLOR;
		}
		
		if( lockedIn ) {
			color = LOCKED_COLOR;
		}

		Color c = batch.getColor();
		batch.setColor( color.button );
		batch.draw( texture, x, y );
		batch.setColor( c );
		
		bitmapFont.setColor( color.font );
		bitmapFont.draw( batch, text, x + diffx, y + diffy );
	}

	@Override
	public void dragged(
			int screenX,
			int screenY,
			int button )
	{

	}

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************
	public boolean isActive() {
		return isActived;
	}

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
	private static class ColorsStructure {
		public Color font;
		public Color button;
		
		ColorsStructure( Color font, Color button ) {
			this.font = font;
			this.button = button;
		}
	}

    @Override
    public void setParent( IComponent parent )
    {
    }

    @Override
    public Optional< IComponent > getParent()
    {
    	return Optional.empty();
    }

}
