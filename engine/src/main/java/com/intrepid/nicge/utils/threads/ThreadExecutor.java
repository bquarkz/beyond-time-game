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
package com.intrepid.nicge.utils.threads;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import com.intrepid.nicge.utils.logger.Log;

public final class ThreadExecutor
        implements Runnable
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************
    public static final ExecutorService pool = Executors
            .newFixedThreadPool( Runtime.getRuntime().availableProcessors() + 1, new ThreadFactory() );
    private static final long TIME_OUT_IN_MILLI_SECS = 1000; //1s

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private static final AtomicBoolean running;
    private static final Map< Long, TaskBundle > map;
    private static final AtomicBoolean allTasksAreFinished;
    private static final AtomicLong currentId;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    static
    {
        running = new AtomicBoolean( false );
        map = new ConcurrentHashMap<>();
        allTasksAreFinished = new AtomicBoolean( true );
        currentId = new AtomicLong( 0 );
    }

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
    public < T extends ITask > TaskBundle execute( String taskName, T command )
    {
        final Future< TaskResult > threadExecutionFuture = pool.submit( command );
        final long id = currentId.getAndIncrement();
        final TaskBundle bundle = new TaskBundle( id, taskName, command, threadExecutionFuture );
        ThreadExecutor.map.put( id, bundle );
        return bundle;
    }

    @Override
    public void run()
    {
        while( running.get() )
        {
            boolean allTasksCheck = true;
            for( Long id : ThreadExecutor.map.keySet() )
            {
                boolean taskDone = !ThreadExecutor.map.get( id ).getTask().isTaskRunning();
                allTasksCheck &= taskDone;
                if( taskDone )
                {
                    ThreadExecutor.map.remove( id );
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

    public List< TaskOverview > getTasksOverview()
    {
        return ThreadExecutor
                .map
                .keySet()
                .stream()
                .sorted()
                .map( ThreadExecutor.map::get )
                .map( bundle -> new TaskOverview( bundle.getTaskName(), bundle.getTask().getTaskCompletion() ) )
                .collect( Collectors.toList() );
    }

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
