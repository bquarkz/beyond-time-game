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
package com.intrepid.nicge.utils.reflections;

import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Configuration;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.Scanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.scanners.TypeElementsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import com.intrepid.nicge.utils.logger.Log;

public final class Reflection
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************
    private static final String PACKAGE_CLASS_LOADER_DEFAULT = "PACKAGE_CLASS_LOADER_DEFAULT";
    private static final Scanner[] scanners = new Scanner[]{
            new TypeElementsScanner(),
            new SubTypesScanner( false ),
            new TypeAnnotationsScanner(),
            new MethodAnnotationsScanner(),
    };


    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private Map< String, Reflections > map;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public Reflection()
    {
        this( null );
    }

    public Reflection( Set< String > setPackage )
    {
		map = new HashMap<>();
		Configuration configuration = new ConfigurationBuilder()
				.addUrls( ClasspathHelper.forClassLoader() )
				.addScanners( Reflection.scanners );
		map.put( PACKAGE_CLASS_LOADER_DEFAULT, new Reflections( configuration ) );
		if( setPackage != null )
		{
			for( String pack : setPackage )
			{
				addPackage( pack );
			}
		}

    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    private void addPackage( String pack )
    {

        if( pack == null || pack.equals( "" ) )
        {
            Log.from( this ).failure( "package can not be null or void" );
            throw new RuntimeException( "package can not be null or void" );
        }

		final Collection< URL > url = ClasspathHelper.forPackage( pack );
		Configuration configuration = new ConfigurationBuilder()
				.addUrls( url )
				.addScanners( Reflection.scanners );
		map.put( pack, new Reflections( configuration ) );
    }

    public Reflections get()
    {
        return map.get( Reflection.PACKAGE_CLASS_LOADER_DEFAULT );
    }

    public Reflections from()
    {
        return get();
    }

    public Reflections from( String pack )
    {
        try
        {
            return map.get( pack );
        }
        catch( Exception e )
        {
            return get();
        }
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
