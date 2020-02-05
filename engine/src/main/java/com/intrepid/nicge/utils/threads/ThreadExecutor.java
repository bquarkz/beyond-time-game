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
package com.intrepid.nicge.utils.threads;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import com.intrepid.nicge.utils.logger.Log;

public final class ThreadExecutor implements Runnable
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************
    public static final ExecutorService pool = Executors.newCachedThreadPool( new ThreadFactory() );
    private static final long TIME_OUT_IN_MILLI_SECS = 1000; //1s

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private static final AtomicBoolean running = new AtomicBoolean( false );
    private static final Map< ThreadRunnable, Future< ? > > map = new ConcurrentHashMap<>();
    private static final AtomicBoolean allTasksAreFinished = new AtomicBoolean( true );


    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public ThreadExecutor()
    {
        if( !running.get() )
        {
            running.set( true );
            pool.submit( this );
        }
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    @SuppressWarnings( "unchecked" )
    public < T extends ThreadRunnable > Future< T > execute( T command )
    {
        final Future< T > threadExecutionFuture = (Future< T >)pool.submit( command );
        ThreadExecutor.map.put( command, threadExecutionFuture );
        return threadExecutionFuture;
    }

    @Override
    public void run()
    {
        while( running.get() )
        {
            boolean allTasksCheck = true;
            for( ThreadRunnable task : ThreadExecutor.map.keySet() )
            {
                boolean taskDone = ThreadExecutor.map.get( task ).isDone();
                allTasksCheck &= taskDone;
				if( taskDone )
				{
					ThreadExecutor.map.remove( task );
				}
            }
            allTasksAreFinished.set( allTasksCheck );
        }

        try
        {
            pool.awaitTermination( TIME_OUT_IN_MILLI_SECS, TimeUnit.MILLISECONDS );
            pool.shutdown();
        }
        catch( InterruptedException e )
        {
            Log.from( this ).info( "stopping all tasks" );
        }

    }

    public void shutdown()
    {
        boolean shutdown = pool.isShutdown();
        boolean terminated = pool.isTerminated();
        if( shutdown || terminated )
        {
            pool.shutdownNow();
        }

        pool.execute( () -> {
			while( !allTasksAreFinished.get() );
			running.set( false );
		} );
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public boolean isAllTasksFinished()
    {
        return allTasksAreFinished.get();
    }

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
