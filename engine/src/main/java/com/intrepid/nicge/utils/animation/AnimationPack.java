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

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationPack
{
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private final AnimationPackInfo animationPackInfo;
	private final Map< String, Animation > mapAnimationPack;
	private final Texture texture;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public AnimationPack( AnimationPackInfo animationPackInfo, Texture texture )
	{
		this.animationPackInfo = animationPackInfo;
		this.mapAnimationPack = new HashMap<>();
		this.texture = texture;
		
		for( int i = 0; i < animationPackInfo.size(); i++ )
		{
			AnimationInfo animationInfo = animationPackInfo.get( i );
			SpriteInfo[] spritesInfo = animationInfo.getSpritesInfo();
			TextureRegion[] textureRegions = new TextureRegion[ spritesInfo.length ]; 
			for( int c = 0; c < spritesInfo.length; c++ )
			{
				textureRegions[ c ] = new TextureRegion( texture,
									spritesInfo[ c ].getX(), spritesInfo[ c ].getY(),
									animationInfo.getWidth(), animationInfo.getHeight() );
			}
			Animation animation = new Animation( animationInfo, textureRegions );
			mapAnimationPack.put( animationInfo.getName(), animation );
		}
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************
	public Animation get( String key )
	{
	    Animation animation = mapAnimationPack.get( key );
	    if( animation == null )
	        throw new RuntimeException( "resource does not exist: " + key );
	    
		return animation;
	}
	
	public AnimationPackInfo getAnimationPackInfo() {
		return animationPackInfo;
	}
	
	public Texture getTexture() {
		return texture;
	}

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
}
