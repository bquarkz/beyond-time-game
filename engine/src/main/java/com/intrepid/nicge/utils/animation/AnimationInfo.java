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
package com.intrepid.nicge.utils.animation;

public class AnimationInfo {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private String name;
	private int x0;
	private int y0;
	private int width;
	private int height;
	private int playMode;
	private float durationInMilliSecs;
	private SpriteInfo[] spritesInfo;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public AnimationInfo() {
	}
	
	AnimationInfo( String name,
			float durationInMilliSecs,
			int imgSizeX,
			int imgSizeY,
			int rows,
			int cols,
			int playMode ) {
		if( ( ( imgSizeX % rows ) != 0 )||
			( ( imgSizeY % cols ) != 0 ) ) {
			throw new IllegalParameterAnimation( name );
		}
		
		this.name = name;
		this.x0 = 0;
		this.y0 = 0;
		this.durationInMilliSecs = durationInMilliSecs;
		this.spritesInfo = createSpriteInfos( imgSizeX, imgSizeY, rows, cols );
		this.playMode = playMode;
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	public void setOrigin( int x, int y ) {
		this.x0 = x;
		this.y0 = y;
		for( SpriteInfo spriteInfo : spritesInfo ) {
			spriteInfo.setOrigin( x, y );
		}
	}

	private SpriteInfo[] createSpriteInfos( int imgSizeX, int imgSizeY, int rows, int cols ) {
		SpriteInfo[] r = new SpriteInfo[ rows * cols ];
		
		width = imgSizeX / rows;
		height = imgSizeY / cols;
		
		for( int i=0, j=0, c=0; c < r.length; ) {
			int x = width * i;
			int y = height * j;
			r[ c ] = new SpriteInfo( x, y );
			
			i = ++c % rows;
			if( i == 0 ) j++;
		}
		
		return r;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( "Animation Info:\n" );
		sb.append( "name: " + name + "\n" );
		sb.append( "duration: " + durationInMilliSecs + "\n" );
		sb.append( "play mode: " + playMode + "\n" );
		sb.append( "sprites: " );
		for( SpriteInfo si : spritesInfo ) {
			sb.append( "[x: " + si.getX() + ", y: " + si.getY() + "]" );
		}
		sb.append( "\n" );
		return sb.toString();
	}
	
	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************
	public String getName() {
		return name;
	}
	
	public float getDurationInMilliSecs() {
		return durationInMilliSecs;
	}

	public SpriteInfo[] getSpritesInfo() {
		return spritesInfo;
	}
	
	public int getOriginX() {
		return x0;
	}
	
	public int getOriginY() {
		return y0;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return width;
	}
	
	public int getPlayMode() {
		return playMode;
	}

//	public void setName( String name ) {
//		this.name = name;
//		
//	}


	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
}
