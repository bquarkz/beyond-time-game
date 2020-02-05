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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class DebugFormatter extends Formatter {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************
    private static final DateFormat DF = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss.SSS");

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public DebugFormatter() {
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	public static String lvlTranslator( Level level ) {
		if( level == Level.FINEST )		return "TEXT";
		if( level == Level.FINE )		return "MESSAGE";
		if( level == Level.INFO )		return "INFO";
		if( level == Level.WARNING )	return "WARNING";
		if( level == Level.SEVERE )		return "FAILURE";
		if( level == Level.ALL )		return "ALL";
		return "OTHER";
	}
	
	@Override
	public String format( LogRecord record ) {
        StringBuilder sb = new StringBuilder(1000);
        // show time
        sb.append( DF.format( new Date( record.getMillis() ) ) ).append(" - ");
        
        // class name
        sb.append("[").append( record.getSourceClassName() );
        
        // method name
//        sb.append(".").append(record.getSourceMethodName());
        
        // level invoked
        sb.append("] - ").append("[").append( lvlTranslator( record.getLevel() ) ).append("]: ");
        
//        if( record.getLevel() == Level.FINEST ) {
//        	sb.append( "\n[!@ ================== \n" );
//        }
        
        // message
        sb.append( formatMessage( record ) );
        
//        if( record.getLevel() == Level.FINEST ) {
//        	sb.append( "\n ================== @!]\n" );
//        }
        
        // carrie return :)
        sb.append("\n");
        return sb.toString();
	}

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
}
