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
 * all community without problems.
 */
package com.intrepid.nicge.utils;

import java.util.Base64;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.intrepid.nicge.kernel.Configurations;

public class JsonHelper
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************
    private static final boolean PRETTY_PRINT = true;

    private static final Json json = new Json();
    private static final boolean ENCODED = Configurations.get().encoded;

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************


    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    public static void writeJson(
            FileHandle fileHandle,
            Object object,
            Class< ? > objectClass )
    {

        String toWrite = PRETTY_PRINT
                ? json.prettyPrint( object )
                : json.toJson( object, objectClass );

        if( ENCODED )
        {
            toWrite = new String( Base64.getEncoder().encode( toWrite.getBytes() ) );
        }

        fileHandle.writeString( toWrite, false );
    }

    public static < T > T readJson(
            Class< T > type,
            String content )
    {

        String toRead = ENCODED
                ? new String( Base64.getDecoder().decode( content.getBytes() ) )
                : content;
        return json.fromJson( type, toRead );
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public static Json getJson()
    {
        return json;
    }

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
