package com.intrepid.nicge.utils.threads;

		import java.lang.Thread.UncaughtExceptionHandler;

public class SimpleExceptionHandler implements UncaughtExceptionHandler
{
	@Override
	public void uncaughtException(
			Thread thread,
			Throwable e )
	{
		System.out.println( "thread exception: " + thread.getName() + " :: " + e.getMessage() );
	}
}
