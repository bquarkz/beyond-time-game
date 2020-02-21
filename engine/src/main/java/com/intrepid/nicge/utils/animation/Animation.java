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
package com.intrepid.nicge.utils.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class Animation
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************
    public static final int NORMAL = 0;
    public static final int REVERSED = 1;
    public static final int LOOP = 2;
    public static final int LOOP_REVERSED = 3;
    public static final int LOOP_PINGPONG = 4;
    public static final int LOOP_RANDOM = 5;

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    final TextureRegion[] keyFrames;
    public float frameDuration;
    public float animationDuration;

    private int playMode = LOOP;

    private int frameNumber;


    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public Animation( Texture texture )
    {
        this.keyFrames = new TextureRegion[] { new TextureRegion( texture ) };
        this.frameDuration = 0f;
    }

    /** Constructor, storing the frame duration and key frames.
     *
     * @param frameDuration the time between frames in seconds.
     * @param keyFrames the {@link TextureRegion}s representing the frames. */
    public Animation(
            float frameDuration,
            Array< ? extends TextureRegion > keyFrames )
    {
        this.keyFrames = new TextureRegion[ keyFrames.size ];
        setFrameDuration( frameDuration );
        for( int i = 0, n = keyFrames.size; i < n; i++ )
        {
            this.keyFrames[ i ] = keyFrames.get( i );
        }

        this.playMode = NORMAL;
    }

    /** Constructor, storing the frame duration, key frames and play type.
     *
     * @param frameDuration the time between frames in seconds.
     * @param keyFrames the {@link TextureRegion}s representing the frames.
     * @param playType the type of animation play (NORMAL, REVERSED, LOOP, LOOP_REVERSED, LOOP_PINGPONG, LOOP_RANDOM) */
    public Animation(
            float frameDuration,
            Array< ? extends TextureRegion > keyFrames,
            int playType )
    {
        this.keyFrames = new TextureRegion[ keyFrames.size ];
        setFrameDuration( frameDuration );
        for( int i = 0, n = keyFrames.size; i < n; i++ )
        {
            this.keyFrames[ i ] = keyFrames.get( i );
        }

        this.playMode = playType;
    }

    /** Constructor, storing the frame duration and key frames.
     *
     * @param frameDuration the time between frames in seconds.
     * @param keyFrames the {@link TextureRegion}s representing the frames. */
    public Animation(
            float frameDuration,
            TextureRegion... keyFrames )
    {
        this.keyFrames = keyFrames;
        setFrameDuration( frameDuration );
        this.playMode = NORMAL;
    }

    /** Constructor, storing the frame duration and key frames.
     *
     * @param animationInfo
     * @param textureRegions
     */
    public Animation(
            AnimationInfo animationInfo,
            TextureRegion[] textureRegions )
    {
        keyFrames = textureRegions;
        playMode = animationInfo.getPlayMode();
        setFrameDuration( animationInfo.getDurationInMilliSecs() );
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************

    /** Returns a {@link TextureRegion} based on the so called state time. This is the amount of seconds an object has spent in the
     * state this Animation instance represents, e.g. running, jumping and so on. The mode specifies whether the animation is
     * looping or not.
     *
     * @param stateTime the time spent in the state represented by this animation.
     * @param looping whether the animation is looping or not.
     * @return the TextureRegion representing the frame of animation for the given state time. */
    public TextureRegion getKeyFrame(
            float stateTime,
            boolean looping )
    {
        // we set the play mode by overriding the previous mode based on looping
        // parameter value
        if( looping && ( playMode == NORMAL || playMode == REVERSED ) )
        {
			if( playMode == NORMAL )
			{
				playMode = LOOP;
			}
			else
			{
				playMode = LOOP_REVERSED;
			}
        }
        else if( !looping && !( playMode == NORMAL || playMode == REVERSED ) )
        {
			if( playMode == LOOP_REVERSED )
			{
				playMode = REVERSED;
			}
			else
			{
				playMode = LOOP;
			}
        }

        return getKeyFrame( stateTime );
    }

    /** Returns a {@link TextureRegion} based on the so called state time. This is the
     * amount of seconds an object has spent in the state this Animation instance
     * represents, e.g. running, jumping and so on using the mode specified by
     * {@link #setPlayMode(int)} method.
     *
     * @param stateTime
     * @return the TextureRegion representing the frame of animation for the given
     * state time. */
    public TextureRegion getKeyFrame( float stateTime )
    {
        frameNumber = getKeyFrameIndex( stateTime );
        return keyFrames[ frameNumber ];
    }

    /** Returns the last frame number.
     * @return current frame number */
    public int getKeyFrameIndex()
    {
        return frameNumber;
    }

    /** Returns the current frame number.
     * @param stateTime
     * @return current frame number */
    public int getKeyFrameIndex( float stateTime )
    {
        if( frameDuration == 0 ) return 0;

        int frameNumber = (int)( stateTime / frameDuration );

		if( keyFrames.length == 1 )
		{
			return 0;
		}

        switch( playMode )
        {
            case NORMAL:
                frameNumber = Math.min( keyFrames.length - 1, frameNumber );
                break;
            case LOOP:
                frameNumber = frameNumber % keyFrames.length;
                break;
            case LOOP_PINGPONG:
                frameNumber = frameNumber % ( ( keyFrames.length * 2 ) - 2 );
				if( frameNumber >= keyFrames.length )
				{
					frameNumber = keyFrames.length - 2 - ( frameNumber - keyFrames.length );
				}
                break;
            case LOOP_RANDOM:
                frameNumber = MathUtils.random( keyFrames.length - 1 );
                break;
            case REVERSED:
                frameNumber = Math.max( keyFrames.length - frameNumber - 1, 0 );
                break;
            case LOOP_REVERSED:
                frameNumber = frameNumber % keyFrames.length;
                frameNumber = keyFrames.length - frameNumber - 1;
                break;

            default:
                // play normal otherwise
                frameNumber = Math.min( keyFrames.length - 1, frameNumber );
                break;
        }

        return frameNumber;
    }

    /** Sets the animation play mode.
     *
     * @param playMode can be one of the following: Anime.NORMAL,
     * Anime.REVERSED, Anime.LOOP, Anime.LOOP_REVERSED, Anime.LOOP_PINGPONG,
     * Anime.LOOP_RANDOM */
    public void setPlayMode( int playMode )
    {
        this.playMode = playMode;
    }

    /** Whether the animation would be finished if played without looping
     * (PlayMode Animation#NORMAL), given the state time.
     * @param stateTime
     * @return whether the animation is finished. */
    public boolean isAnimationFinished( float stateTime )
    {
        int frameNumber = (int)( stateTime / frameDuration );
        return keyFrames.length - 1 < frameNumber;
    }

    /**
     * Change the values of frameDuration and animationDuration on-the-fly
     * @param frameDuration
     */
    public void setFrameDuration( float frameDuration )
    {
        this.frameDuration = frameDuration / 1_000f;
        this.animationDuration = keyFrames.length * this.frameDuration;
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************

}
