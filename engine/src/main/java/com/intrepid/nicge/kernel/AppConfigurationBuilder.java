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

import java.util.Set;

public class AppConfigurationBuilder implements AppConfiguration
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private Set< String > packages;
    private boolean isMouseLockedAndInvisible;
    private String title;

    private float RAW_DELTA_TIME_IN_MILI_SECS;

    private KernelScreenVariables screen;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public AppConfigurationBuilder()
    {
        this.packages = null;
        this.isMouseLockedAndInvisible = false;
    }

    // ****************************************************************************************
    // Setters Methods
    // ****************************************************************************************
    public final AppConfigurationBuilder setMouseLockedAndInvisible( boolean b )
    {
        isMouseLockedAndInvisible = b;
        return this;
    }

    public final void setNativeResolutionWidth( int nativeResolutionWidth )
    {
        screen().setNativeResolutionWidth( nativeResolutionWidth );
    }

    public final void setNativeResolutionHeight( int nativeResolutionHeight )
    {
        screen().setNativeResolutionHeight( nativeResolutionHeight );
    }

    public final void setWindowResolutionWidth( int windowResolutionWidth )
    {
        screen().setWindowResolutionWidth( windowResolutionWidth );
    }

    public final void setWindowResolutionHeight( int windowResolutionHeight )
    {
        screen().setWindowResolutionHeight( windowResolutionHeight );
    }

    public final void setFPS( int fps )
    {
        screen().setRefreshRate( fps );
        this.RAW_DELTA_TIME_IN_MILI_SECS = 1000.0f / (float)fps;
    }

    public final void setTitle( String title )
    {
        this.title = title;
    }

    public final void setFullscreenMode( boolean mode )
    {
        screen().setFullscreen( mode );
    }

    public final void setResisable( boolean b )
    {
        screen().setResizable( b );
    }

    public final void setMMSA( int mmsa )
    {
        screen().setMSAA( mmsa );
    }

    public final void setVSync( boolean vsync )
    {
        screen().setVsync( vsync );
    }

    public final void setBitsPerPixel( int bpp )
    {
        screen().setBitsPerPixel( bpp );
    }

    public final void setClassLoadersPackages( Set< String > packages )
    {
        this.packages = packages;
    }

    // ****************************************************************************************
    // Getters Methods
    // ****************************************************************************************
    protected final KernelScreenVariables screen()
    {
		if( screen == null )
		{
			screen = new KernelScreenVariables();
		}
        return screen;
    }

    protected final float getRawDeltaTimeInMiliSecs()
    {
        return RAW_DELTA_TIME_IN_MILI_SECS;
    }

    @Override
    public final boolean isMouseLockedAndInvisible()
    {
        return isMouseLockedAndInvisible;
    }

    @Override
    public final String getTitle()
    {
        return title;
    }

    @Override
    public final int getMSAA()
    {
        return screen().getMSAA();
    }

    @Override
    public final boolean isGL20()
    {
        return screen().isGL20();
    }

    @Override
    public final int getRefreshRate()
    {
        return screen().getRefreshRate();
    }

    @Override
    public final boolean isResizable()
    {
        return screen().isResizable();
    }

    @Override
    public final int getNativeResolutionWidth()
    {
        return screen().getNativeResolutionWidth();
    }

    @Override
    public final int getNativeResolutionHeight()
    {
        return screen().getNativeResolutionHeight();
    }

    @Override
    public final int getWindowResolutionWidth()
    {
        return screen().getWindowResolutionWidth();
    }

    @Override
    public final int getWindowResolutionHeight()
    {
        return screen().getWindowResolutionHeight();
    }

    @Override
    public final boolean isFullscreen()
    {
        return screen().isFullscreen();
    }

    @Override
    public final boolean getVSync()
    {
        return screen().getVSync();
    }

    @Override
    public final int getBitsPerPixel()
    {
        return screen().getBitsPerPixel();
    }

    @Override
    public final Set< String > getClassLoadersPackages()
    {
        return this.packages;
    }

    // ****************************************************************************************
    // Patterns
    // ***************************************************************************************
}
