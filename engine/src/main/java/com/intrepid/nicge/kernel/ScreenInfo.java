/*
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

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Disposable;
import com.intrepid.nicge.theater.cameras.Camera;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;
import com.intrepid.nicge.utils.graphics.TextureWorks;

public class ScreenInfo implements Disposable
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************
    private static final int SYS_PROCESS_BAR_WIDTH = 150;
    private static final int SYS_PROCESS_BAR_HEIGHT = 20;

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private GraphicsBatch batchDebug;
    private BitmapFont bitmapFontDebug;
    private List< String > allMessages;
    private List< SystemInfoProcess > sysInfo;
    private boolean showUp;

    private final Texture sysProcessBar;
    private final Texture sysProcessBarBackground;
    private final Texture sysProcessBarBackgroundOver;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public ScreenInfo(
            int windowResolutionWidth,
            int windowResolutionHeight,
            boolean showUp )
    {
        this.showUp = showUp;

        Camera camera = new Camera( windowResolutionWidth, windowResolutionHeight );
        batchDebug = new GraphicsBatch();
        batchDebug.setProjectionMatrix( camera.combined );

        bitmapFontDebug = new BitmapFont(); // we have to replace it with a mono font
        bitmapFontDebug.setColor( new Color( 0xffff00cc ) );

        allMessages = new ArrayList<>();
        sysInfo = new ArrayList<>();

        sysProcessBar = TextureWorks.createTexture( SYS_PROCESS_BAR_WIDTH, SYS_PROCESS_BAR_HEIGHT, new Color( 0x0000ff55 ) );
        sysProcessBarBackground = TextureWorks.createTexture( SYS_PROCESS_BAR_WIDTH, SYS_PROCESS_BAR_HEIGHT, new Color( 0xafafaf55 ) );
        sysProcessBarBackgroundOver = TextureWorks.createTexture( SYS_PROCESS_BAR_WIDTH, SYS_PROCESS_BAR_HEIGHT, new Color( 0xff00dd55 ) );
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    public void addSystemInfo( String systemVar, float use )
    {
		if( systemVar == null )
		{
			return;
		}
		if( systemVar.isEmpty() )
		{
			return;
		}

        sysInfo.add( new SystemInfoProcess( systemVar, use ) );
    }

    public void addSystemInfo( String systemVar, String complement )
    {
        sysInfo.add( new SystemInfoProcess( systemVar + " :: " + complement, 1 ) );
    }

    private final void _addProcessMessages(
            String process,
            Float executionTime,
            List< String > messages )
    {
		if( process == null )
		{
			return;
		}
		if( process.isEmpty() )
		{
			return;
		}

        if( executionTime != null )
        {
            allMessages.add( "[[ " + process + " : " + executionTime + "ms ]]" );
        }
        else
        {
            allMessages.add( "[[ " + process + " ]]" );
        }

        if( messages != null )
        {
            for( String msg : messages )
            {
                allMessages.add( "  > " + msg );
            }
        }
    }

    public void addProcessMessages(
            String process,
            Float executionTime,
            List< String > messages )
    {
        _addProcessMessages( process, executionTime, messages );
    }

    public void addProcessMessages(
            String process,
            List< String > messages )
    {
        _addProcessMessages( process, null, messages );
    }

    public void cleanup()
    {
        allMessages.clear();
        sysInfo.clear();
    }

    public void throwUp()
    {
        if( showUp )
        {
            batchDebug.begin();
            int x = 5;
            int counter = 0;
            for( SystemInfoProcess sip : sysInfo )
            {
                int y = ( SYS_PROCESS_BAR_HEIGHT * ( sysInfo.size() + 1 ) ) - ( ( SYS_PROCESS_BAR_HEIGHT + 2 ) * ( counter++ + 1 ) );
                float w = SYS_PROCESS_BAR_WIDTH * sip.processing;
                if( sip.processing >= 1 )
                {
                    batchDebug.draw( sysProcessBarBackgroundOver, x, y, SYS_PROCESS_BAR_WIDTH, SYS_PROCESS_BAR_HEIGHT );
                }
                else
                {
                    batchDebug.draw( sysProcessBarBackground, x, y, SYS_PROCESS_BAR_WIDTH, SYS_PROCESS_BAR_HEIGHT );
                }
                batchDebug.draw( sysProcessBar, x, y, w, SYS_PROCESS_BAR_HEIGHT );
                bitmapFontDebug.draw( batchDebug, sip.systemVar,15, y + 15 );
            }

            for( int i = 0; i < allMessages.size(); i++ )
            {
                bitmapFontDebug.draw( batchDebug, allMessages.get( i ), x,
                        ( counter * SYS_PROCESS_BAR_HEIGHT ) + 10 + ( allMessages.size() - i + 1 ) * 15 );
            }

            batchDebug.end();
        }

        cleanup();
    }

    @Override
    public void dispose()
    {
        batchDebug.dispose();
        bitmapFontDebug.dispose();
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
    private static class SystemInfoProcess
    {
        private final String systemVar;
        private final float processing;

        public SystemInfoProcess(
                String systemVar,
                float processing )
        {
            this.systemVar = systemVar;
            this.processing = processing;
        }
    }
}
