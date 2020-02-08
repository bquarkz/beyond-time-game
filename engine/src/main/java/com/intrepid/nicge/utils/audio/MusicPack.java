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
import com.intrepid.nicge.content.AssetManager;
import com.intrepid.nicge.content.IResource;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.theater.IUpdatable;
import com.intrepid.nicge.utils.pool.PoolableWrapper;
import com.intrepid.nicge.utils.timer.Timer;

public class MusicPack implements IUpdatable, IMusicBoard
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private IResource< Music > resource;
    private float volume;
    private float pan;
    private double fadeVolume;
    private double timeToFade_s;
    private double fadeStart_ms;
    private boolean fadein;
    private boolean fadeout;
    private PoolableWrapper< Timer > timer;
    private boolean delayed;
    private Music music;
    private float delay_ms;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public MusicPack(
            IResource< Music > resource,
            float volume,
            float pan )
    {
        this.resource = resource;
        setVolume( volume );
        setPan( pan );

        fadein = false;
        fadeout = false;
        delayed = false;

        timer = Game.time.getTimers().getTimer();
    }

    public MusicPack( IResource< Music > resource )
    {
        this( resource, 1.0f, 0.0f );
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    public void captureMusicFromLoader( AssetManager assetManager )
    {
        music = assetManager.get( resource );
    }

    @Override
    public void play()
    {
        music.setVolume( volume );
        music.play();
    }

    @Override
    public void pause()
    {
        music.pause();
    }

    @Override
    public void stop()
    {
        music.stop();
    }

    @Override
    public void setLoop()
    {
        music.setLooping( true );
    }

    @Override
    public void unsetLoop()
    {
        music.setLooping( false );
    }

    @Override
    public void fadeout( float time_ms )
    {
        if( !fadeout )
        {
            fade( time_ms );
            fadein = false;
            fadeout = true;
        }
    }

    @Override
    public void fadein( float time_ms )
    {
        if( !fadein )
        {
            fade( time_ms );
            fadeout = false;
            fadein = true;
            music.setVolume( 0.0f );
			if( !delayed )
			{
				music.play();
			}
        }
    }

    private void fade( float time_ms )
    {
        timeToFade_s = time_ms / 1_000.0f;
        fadeStart_ms = Game.time.getElapsedTime_ns() / 1_000_000.0f;
    }

    @Override
    public void update()
    {
        // fade effect??
        if( delayed )
        {
			if( music.isPlaying() )
			{
				music.pause();
			}
            if( timer.get().checkTime_ms( delay_ms ) )
            {
                delayed = false;
                music.play();
                fadeStart_ms += timer.get().getTotalTime_ms();
            }
        }
        else if( fadein || fadeout )
        {
            float elapsedTime_ms = Game.time.getElapsedTime_ns() / 1_000_000.0f;
            fadeVolume = ( elapsedTime_ms - fadeStart_ms ) / timeToFade_s;
			if( fadeVolume > 1.0f )
			{
				fadeVolume = 1.0f;
			}

			if( fadein )
			{
				music.setVolume( (float)( fadeVolume * volume ) );
			}
			if( fadeout )
			{
				music.setVolume( (float)( 1.0f - fadeVolume * volume ) );
			}

            if( fadeVolume == volume )
            {
                if( fadein )
                {
                    fadein = false;
                }

                if( fadeout )
                {
                    fadeout = false;
                    stop();
                }
            }
        }
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public float getVolume()
    {
        return volume;
    }

    public float getPan()
    {
        return pan;
    }

    public void setVolume( float volume )
    {
        if( volume > 1.0f )
        {
            volume = 1.0f;
        }
        else if( volume < 0.0f )
        {
            volume = 0.0f;
        }

        this.volume = volume;
    }

    public void setPan( float pan )
    {
        if( pan > 1.0f )
        {
            pan = 1.0f;
        }
        else if( pan < -1.0f )
        {
            pan = -1.0f;
        }

        this.pan = pan;
    }

    public void setDelay( float delay_ms )
    {
        this.delay_ms = delay_ms;

        if( music.isPlaying() )
        {
            music.pause();
        }

        timer.get().start();

        delayed = true;
    }

    // ****************************************************************************************
    // Patterns
    // ***************************************************************************************
}