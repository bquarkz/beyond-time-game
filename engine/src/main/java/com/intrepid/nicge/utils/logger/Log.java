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
package com.intrepid.nicge.utils.logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;

public class Log
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************
    private static final boolean USE_CONSOLE_LOG = true;

    private static final boolean USE_FILE_LOG = true;
    private static final String FILENAME = "output.log";
    private static final boolean APPEND_FILE_LOG = false;

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private static Map< String, DebugLogger > loggers;
    private static Handler fileHandler;
    private static Handler consoleHandler;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    static
    {
        loggers = new HashMap<>();

        if( Log.USE_FILE_LOG )
        {
            try
            {
                Log.fileHandler = new FileHandler( Log.FILENAME, Log.APPEND_FILE_LOG );
            }
            catch( SecurityException | IOException e )
            {
                throw new RuntimeException( e );
            }
        }

        if( Log.USE_CONSOLE_LOG )
        {
            Log.consoleHandler = new ConsoleHandler();
        }
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    private static final Set< Handler > createHandlers()
    {
        Set< Handler > handlers = new HashSet<>();

        if( Log.USE_FILE_LOG )
        {
            handlers.add( Log.fileHandler );
        }

        if( Log.USE_CONSOLE_LOG )
        {
            handlers.add( Log.consoleHandler );
        }

        for( Handler handler : handlers )
        {
            handler.setFormatter( new DebugFormatter() );
        }

        return handlers;
    }

    public static final ILogger from( Object object )
    {
        return Log.from( object.getClass() );
    }

    public static final ILogger from( Class< ? > clazz )
    {
        if( !Log.loggers.containsKey( clazz.getName() ) )
        {
            final DebugLogger log = new DebugLogger( clazz );
            log.addHandler( Log.createHandlers() );
            Log.loggers.put( clazz.getName(), log );
            return log;
        }
        else
        {
            return loggers.get( clazz.getName() );
        }
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
