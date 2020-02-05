package com.intrepid.nicge.utils.threads;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadFactory implements java.util.concurrent.ThreadFactory {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

    // ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;
    
	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
    ThreadFactory() {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                              Thread.currentThread().getThreadGroup();
        namePrefix = "pool-" +
                      poolNumber.getAndIncrement() +
                     "-thread-";
    }

    
	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
    @Override
    public Thread newThread( Runnable runnable ) {
        Thread t = new Thread( group, runnable,
                               namePrefix + threadNumber.getAndIncrement(),
                               0 );
        if (t.isDaemon())
            t.setDaemon(false);
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);
        
        t.setUncaughtExceptionHandler( new SimpleExceptionHandler() );
        
        return t;
    }

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
}