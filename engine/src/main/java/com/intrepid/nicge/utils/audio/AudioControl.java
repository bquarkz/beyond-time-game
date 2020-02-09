/**
 * Copyleft (C) 2016  Constantino, Nilton Rogerio <niltonrc@gmail.com>
 *
 * @author "Nilton R Constantino"
 * aKa bQUARKz <niltonrc@gmail, bquarkz@gmail.com>
 * <p>
 * Everything about the respective software copyright can be found in the
 * "LICENSE" file included in the project source tree.
 * <p>
 * The code was written based on study principles and can be enjoyed for
 * all comunity without problems.
 */
package com.intrepid.nicge.utils.audio;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.intrepid.nicge.content.AssetManager;
import com.intrepid.nicge.content.IResource;
import com.intrepid.nicge.kernel.IUpdatable;

public final class AudioControl implements IUpdatable
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private final SoundManager soundManager;
    private MusicPack musicPack;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public AudioControl()
    {
        soundManager = new SoundManager();
        musicPack = null;
    }


    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    public void playSound(
            IResource< Sound > soundResource,
            AssetManager assetManager )
    {
        playSound( SoundPriority.MIDDLE, soundResource, assetManager );
    }

    public void playSound(
            SoundPriority sp,
            IResource< Sound > soundResource,
            AssetManager assetManager )
    {
        SoundPack soundPack = new SoundPack( soundResource );
        soundPack.captureSoundFromLoader( assetManager );
        playSound( sp, soundPack );
    }

    public void playSound(
            SoundPriority sp,
            SoundPack sound )
    {
        soundManager.access( sp ).push( sound );
    }

    public void setMusicBoardFrom(
            IResource< Music > musicResource,
            AssetManager assetManager )
    {
        musicPack = new MusicPack( musicResource );
        musicPack.captureMusicFromLoader( assetManager );
    }

    public MusicPack getMusicBoard()
    {
        return musicPack;
    }

    @Override
    public void update()
    {
        if( musicPack != null )
        {
            musicPack.update();
        }
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public SoundManager getSoundManager()
    {
        return soundManager;
    }

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
