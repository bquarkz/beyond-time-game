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
package com.intrepid.nicge.kernel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;

public class KernelScreenVariables
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private int nativeResolutionWidth;
    private int nativeResolutionHeight;
    private float aspectRatio;
    private boolean isResizable;
    private boolean isFullscreen;

    private int windowResolutionWidth;
    private int windowResolutionHeight;
    private int bitsPerPixel;
    private int refreshRate;

    private int MSAA;
    private boolean isGL20;

    private DisplayMode displayMode;
    private boolean vsync;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public KernelScreenVariables()
    {
        this( null );
    }

    public KernelScreenVariables( AppConfiguration config )
    {
        if( config != null )
        {
            nativeResolutionWidth = config.getNativeResolutionWidth();
            nativeResolutionHeight = config.getNativeResolutionHeight();
            isFullscreen = config.isFullscreen();
            isResizable = config.isResizable();
            windowResolutionWidth = config.getWindowResolutionWidth();
            windowResolutionHeight = config.getWindowResolutionHeight();
            bitsPerPixel = config.getBitsPerPixel();
            refreshRate = config.getRefreshRate();
            vsync = config.getVSync();
        }
        else
        {
            // default values
            nativeResolutionWidth = 420;
            nativeResolutionHeight = 270;
            isFullscreen = false;
            isResizable = false;
            windowResolutionWidth = 1280;
            windowResolutionHeight = 720;
            bitsPerPixel = 24;
            refreshRate = 60;
            vsync = true;
        }

        aspectRatio = (float)nativeResolutionHeight / (float)nativeResolutionWidth;
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    public void selectDisplayMode()
    {
        selectDisplayMode( windowResolutionWidth, windowResolutionHeight );
    }

    public DisplayMode selectDisplayMode(
            int windowResolutionWidth,
            int windowResolutionHeight )
    {
        DisplayMode[] displayModes = Gdx.graphics.getDisplayModes();
        DisplayMode displayMode = null;
        for( DisplayMode d : displayModes )
        {
            if( ( windowResolutionHeight == d.height )
                    && ( windowResolutionWidth == d.width ) )
            {
                displayMode = d;
            }
        }

        if( displayMode == null )
        {
            int buffer = -1;
            for( DisplayMode d : displayModes )
            {
                if( d.height == ( aspectRatio * d.width ) )
                {
                    if( d.height > buffer )
                    {
                        buffer = d.height;
                        displayMode = d;
                    }
                }
            }
        }

        if( displayMode != null )
        {
            this.displayMode = displayMode;
        }

        return getDisplayMode();
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public int getNativeResolutionWidth()
    {
        return nativeResolutionWidth;
    }

    public int getNativeResolutionHeight()
    {
        return nativeResolutionHeight;
    }

    public int getWindowResolutionWidth()
    {
        return windowResolutionWidth;
    }

    public int getWindowResolutionHeight()
    {
        return windowResolutionHeight;
    }

    public boolean isFullscreen()
    {
        return isFullscreen;
    }

    public boolean isResizable()
    {
        return isResizable;
    }

    public int getRefreshRate()
    {
        return refreshRate;
    }

    public int getBitsPerPixel()
    {
        return bitsPerPixel;
    }

    public DisplayMode getDisplayMode()
    {
        return displayMode;
    }

    public float getAspectRatio()
    {
        return aspectRatio;
    }

    public int getMSAA()
    {
        return MSAA;
    }

    public boolean isGL20()
    {
        return isGL20;
    }

    public boolean getVSync()
    {
        return vsync;
    }

    public void setNativeResolutionWidth( int nativeResolutionWidth )
    {
        this.nativeResolutionWidth = nativeResolutionWidth;
    }

    public void setNativeResolutionHeight( int nativeResolutionHeight )
    {
        this.nativeResolutionHeight = nativeResolutionHeight;
    }

    public void setAspectRatio( float aspectRatio )
    {
        this.aspectRatio = aspectRatio;
    }

    public void setResizable( boolean isResizable )
    {
        this.isResizable = isResizable;
    }

    public void setFullscreen( boolean isFullscreen )
    {
        this.isFullscreen = isFullscreen;
    }

    public void setWindowResolutionWidth( int windowResolutionWidth )
    {
        this.windowResolutionWidth = windowResolutionWidth;
    }

    public void setWindowResolutionHeight( int windowResolutionHeight )
    {
        this.windowResolutionHeight = windowResolutionHeight;
    }

    public void setBitsPerPixel( int bitsPerPixel )
    {
        this.bitsPerPixel = bitsPerPixel;
    }

    public void setRefreshRate( int refreshRate )
    {
        this.refreshRate = refreshRate;
    }

    public void setMSAA( int mSAA )
    {
        MSAA = mSAA;
    }

    public void setGL20( boolean isGL20 )
    {
        this.isGL20 = isGL20;
    }

    public void setDisplayMode( DisplayMode displayMode )
    {
        this.displayMode = displayMode;
    }

    public void setVsync( boolean vsync )
    {
        this.vsync = vsync;
    }

    // ****************************************************************************************
    // Patterns
    // ***************************************************************************************
}
