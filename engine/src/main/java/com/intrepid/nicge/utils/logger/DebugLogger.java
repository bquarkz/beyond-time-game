/**
 * Copyleft (C) 2016  Constantino, Nilton Rogerio <niltonrc@gmail.com>
 *
 * @author "Nilton R Constantino"
 * aKa bQUARKz <niltonrc@gmail, bquarkz@gmail.com>
 *
 * Everything about the respective software copyright can be found in the
 * "LICENSE" file included in the project source tree.
 *
 * The code was written based on study principles and can be enjoyed for
 * all comunity without problems.
 */
package com.intrepid.nicge.utils.logger;

import java.util.Set;
import java.util.logging.Handler;
import java.util.logging.Level;

/**
 * TODO: pensar na inferencia do LogRecord.class do Java
 * 
 * @author NiltonRogerio
 */
public final class DebugLogger implements ILogger
{
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private java.util.logging.Logger log;
	private Class< ? > classToLog;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public DebugLogger( Class< ? > clazz ) {
		this.classToLog = clazz;
		this.log = java.util.logging.Logger.getLogger( clazz.getName() );
		this.log.setLevel( Level.ALL );
		this.log.setUseParentHandlers( false );
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	@Override
	public void other( String message ) {
		log.logp( Level.FINER, classToLog.getName(), null, message );
	}
	
	@Override
	public void message( String message ) {
		log.logp( Level.FINE, classToLog.getName(), null, message );
	}

	@Override
	public void info( String message ) {
		log.logp( Level.INFO, classToLog.getName(), null, message ); 
	}
	
	@Override
	public void warning( String message ) {
		log.logp( Level.WARNING, classToLog.getName(), null, message );
	}

	@Override	
	public void failure( String message ) {
		log.logp( Level.SEVERE, classToLog.getName(), null, message );
	}
	
	@Override
	public void all( String message ) {
		log.logp( Level.ALL, classToLog.getName(), null, message );
	}	
	
	public void addHandler( Set< Handler > handlers ) {
		for( Handler handler : handlers ) {
			log.addHandler( handler );
		}
	}

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
}