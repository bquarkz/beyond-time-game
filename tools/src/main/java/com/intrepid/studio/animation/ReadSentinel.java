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
package com.intrepid.studio.animation;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Json;
import com.intrepid.nicge.kernel.IDisplayable;
import com.intrepid.nicge.utils.animation.Animation;
import com.intrepid.nicge.utils.animation.AnimationInfo;
import com.intrepid.nicge.utils.animation.AnimationPack;
import com.intrepid.nicge.utils.animation.AnimationPackInfo;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;
import com.intrepid.nicge.utils.threads.IThreadRunnable;
import com.intrepid.studio.enviroment.Enviroment;
import com.intrepid.studio.kernel.Studio;

public class ReadSentinel implements IThreadRunnable
{

	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private Files files;
	private Enviroment enviroment;
	private AnimationPack pack;
	private BitmapFont bitmapFont;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public ReadSentinel( Files files, Enviroment enviroment ) {
		this.files = files;
		this.enviroment = enviroment;
		this.bitmapFont = new BitmapFont();
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	@Override
	public void startThread() {
		String bytes = new String( files.internal( "resources/apinfo/sentinel.api" ).readBytes() );
		AnimationPackInfo animationPackInfo = new Json().fromJson( AnimationPackInfo.class, bytes );
		Texture texture = new Texture( "resources/textures/sentinel.png" );
		pack = new AnimationPack( animationPackInfo, texture );
	}

	@Override
	public boolean executeThread() {
		int counter = 0;
		int x = 5, y = 470;
		int spacex = 150;
		int spacey = 80;
		int size = 5;
		for( AnimationInfo apinfo : pack.getAnimationPackInfo() ) {
			String name = apinfo.getName();
			Animation animation = pack.get( name );
			
			enviroment.addDisplayable( new ShowAnimation( name, animation, bitmapFont, x, y ) );
			
			x += spacex;
			if( ++counter == size ) {
				counter = 0;
				x = 5;
				y -= spacey;
			}
		}
		
		return false;
	}

	@Override
	public void shutdownThread() {
	}

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
	private class ShowAnimation implements IDisplayable
    {
		private float elapsedTime;
		private String name;
		private Animation animation;
		private BitmapFont bitmapFont;
		
		private int x = 0;
		private int y = 0;
		
		public ShowAnimation( String name, Animation animation, BitmapFont bitmapFont, int x, int y ) {
			this.x = x;
			this.y = y;
			this.name = name;
			this.animation = animation;
			this.bitmapFont = bitmapFont;
			this.elapsedTime = 0.0f;
		}

		@Override
		public void display( GraphicsBatch batch ) {
			this.elapsedTime += Studio.time.getDeltaTime();
			TextureRegion tr = this.animation.getKeyFrame( elapsedTime );
			batch.draw( tr, x, y );
			bitmapFont.draw( batch, name, x, y - 6 );					
		}
	}
}
