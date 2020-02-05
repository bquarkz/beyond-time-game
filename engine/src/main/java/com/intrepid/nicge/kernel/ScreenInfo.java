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

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Disposable;
import com.intrepid.nicge.theater.cameras.Camera;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;

public class ScreenInfo implements Disposable
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private GraphicsBatch batchDebug;
    private BitmapFont bitmapFontDebug;
    private List< String > allMessages;
    private List< String > sysInfo;
    private boolean showUp;

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
        bitmapFontDebug.setColor( 1f, 1f, 0f, 0.7f );

        allMessages = new ArrayList<>();
        sysInfo = new ArrayList<>();
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    public void addSystemInfo( String info, Object... args )
    {
		if( info == null )
		{
			return;
		}
		if( info.isEmpty() )
		{
			return;
		}

        sysInfo.add( String.format( info, args ) );
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
        addProcessMessages( "SYSTEM INFO", sysInfo );

        if( showUp )
        {
            batchDebug.begin();
            for( int counter = 0; counter < allMessages.size(); counter++ )
            {
                bitmapFontDebug.draw( batchDebug, allMessages.get( counter ), 5,
                        ( allMessages.size() - counter + 1 ) * 15 );
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
}
