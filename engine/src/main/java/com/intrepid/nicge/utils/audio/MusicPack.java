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
package com.intrepid.nicge.utils.audio;

import com.badlogic.gdx.audio.Music;
import com.intrepid.nicge.content.AssetManager;
import com.intrepid.nicge.content.Resource;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.theater.Updatable;
import com.intrepid.nicge.utils.pool.PoolableWrapper;
import com.intrepid.nicge.utils.timer.Timer;

public class MusicPack implements Updatable, MusicBoard {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private Resource< Music > resource;	
	private float volume;
	private float pan;
	private double fadeVolume;	
	private double timeToFade;
	private double fadeStart;
	private boolean fadein;
	private boolean fadeout;
	private PoolableWrapper< Timer > timer;
	private boolean delayed;
	private Music music;
	private float timeInMiliSecsToDelay;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public MusicPack( Resource< Music > resource, float volume, float pan ) {
		this.resource = resource;
		setVolume( volume );
		setPan( pan );
		
		fadein = false;
		fadeout = false;
		delayed = false;
		
		timer = Game.time.getTimers().getTimer();
	}
	
	public MusicPack( Resource< Music > resource ) {
		this( resource, 1.0f, 0.0f );
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	public void captureMusicFromLoader( AssetManager assetManager ) {
//		System.out.println( "music from loader: " + resource.getName() );
		music = assetManager.get( resource );
	}
	
	@Override
	public void play() {
		music.setVolume( volume );
		music.play();
	}

	@Override
	public void pause() {
		music.pause();
	}
	
	@Override
	public void stop() {
		music.stop();
	}
	
	@Override
	public void setLoop() {
		music.setLooping( true );
	}
	
	@Override
	public void unsetLoop() {
		music.setLooping( false );
	}

	@Override
	public void fadeout( float timeInMilisecs ) {
		if( !fadeout ) {
			fade( timeInMilisecs );
			fadein = false;
			fadeout = true;
		}
	}

	@Override
	public void fadein( float timeInMilisecs ) {
		if( !fadein ) {
			fade( timeInMilisecs );
			fadeout = false;
			fadein = true;
			music.setVolume( 0.0f );
			if( !delayed ) music.play();
		}
	}

	private void fade( float timeInMilisecs ) {
		timeToFade = timeInMilisecs / 1000.0f;
		fadeStart = Game.time.getElapsedTime();
	}
	
	@Override
	public void update() {
		/* Primeiras tentativas de se montar um efeito de fade */
		if( delayed ) {
			if( music.isPlaying() ) music.pause();
			if( timer.get().checkTimeInMiliSecs( timeInMiliSecsToDelay ) ) {
				delayed = false;
				music.play();
				fadeStart += timer.get().getTotalTime();
			}
		} else if( fadein || fadeout ) {
			fadeVolume = ( Game.time.getElapsedTime() - fadeStart ) / timeToFade;
			if( fadeVolume > 1.0f ) fadeVolume = 1.0f;
			
			if( fadein ) music.setVolume( (float) ( fadeVolume * volume ) );
			if( fadeout ) music.setVolume( (float) ( 1.0f - fadeVolume * volume )  );
			
			if( fadeVolume == volume ) {
				if( fadein ) { 
					fadein = false;
				}
				
				if( fadeout ) {
					fadeout = false;
					stop();
				}
			}
		}
	}

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************
	public float getVolume() {
		return volume;
	}
	
	public float getPan() {
		return pan;
	}

	public void setVolume( float volume ) {
		if( volume > 1.0f ) {
			volume = 1.0f;
		} else if( volume < 0.0f ) {
			volume = 0.0f;
		}
		
		this.volume = volume;
	}
	
	public void setPan( float pan ) {
		if( pan > 1.0f ) {
			pan = 1.0f;
		} else if( pan < -1.0f ) {
			pan = -1.0f;
		}
		
		this.pan = pan;
	}
	
	public void setDelay( float timeInMiliSecsToDelay ) {
		this.timeInMiliSecsToDelay = timeInMiliSecsToDelay;
		
		if( music.isPlaying() ) {
			music.pause();
		}
		
		timer.get().start();
		
		delayed = true;
	}

	// ****************************************************************************************
	// Patterns
	// ***************************************************************************************	
}