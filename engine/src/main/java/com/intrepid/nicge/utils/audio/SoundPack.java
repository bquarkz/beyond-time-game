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

import com.badlogic.gdx.audio.Sound;
import com.intrepid.nicge.content.AssetManager;
import com.intrepid.nicge.content.Resource;

public class SoundPack
{
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private Sound sound;
	private float volume;
	private float pan;
	private float pitch;
	private Resource< Sound > resource;
	private long soundID;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public SoundPack( Resource< Sound > resource, float volume, float pan, float pitch ) {
		sound = null;
		setVolume( volume );
		setPan( pan );
		setPitch( pitch );
		this.resource = resource;
	}
	
	public SoundPack( Resource< Sound > resource ) {
		this( resource, 1.0f, 0.0f, 1.0f );
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	public void captureSoundFromLoader( AssetManager assetManager ) {
		sound = assetManager.get( resource );
	}
	
	public void play() {
		soundID = sound.play( volume, pitch, pan );
	}
	
	public void stop() {
		sound.stop( soundID );
	}
    
	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************
	public Sound getSound() {
		return sound;
	}
	
	public float getVolume() {
		return volume;
	}
	
	public float getPan() {
		return pan;
	}
	
	public float getPitch() {
		return pitch;
	}
	
	public Resource< Sound > getResource() {
		return resource;
	}
	
	public long getSoundID() {
		return soundID;
	}
	
	public void setVolume( float volume ) {
		if( volume > 1.0f ) volume = 1.0f;
		else if( volume < 0.0f ) volume = 0.0f;
		this.volume = volume;
	}
	
	public void setPan( float pan ) {
		if( pan > 1.0f ) pan = 1.0f;
		else if( pan < -1.0f ) pan = -1.0f;
		this.pan = pan;
	}
	
	public void setPitch( float pitch ) {
		if( pitch < 0.0f ) pitch = 0.0f;
		this.pitch = pitch;
	}

	// ****************************************************************************************
	// Patterns
	// ***************************************************************************************
}
