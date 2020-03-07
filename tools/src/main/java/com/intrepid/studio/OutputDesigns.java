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
 * all community without problems.
 */
package com.intrepid.studio;

public class OutputDesigns
{
    public static final String BAR = "================================================================================================================================";
    public static final String EXOTIC_BAR = "- < - > - < - > - < - > - < - > - < - > - < - > - < - > - < - > - < - > - < - > - < - > - < - > - < - > - < - > - < - > - < - > ";
    public static final String BUBBLE_BAR = "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO";
    public static final String ERROR_BAR = "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ";
    public static final String CRAZY_BAR = "~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-";
    public static final String BUBBLE_EMPTY_BAR = "OO                                                                                                                            OO";
    public static final String BUBBLE = "OO";
    public static final String HASH_TAG_EMPTY_BAR = "#                                                                                                                              #";
    public static final String HASH_TAG = "#";
    public static final String STAND_PIPE_EMPTY_BAR = "||                                                                                                                            ||";
    public static final String STAND_PIPE = "||";

    public static final int lineSize = BAR.length();

    public static String createLineName(
            String name,
            String begin,
            String end )
    {
        StringBuilder lineName = new StringBuilder( begin ).append( "    " ).append( name );
        int size = lineSize - lineName.length() - end.length();
        if( size > 0 )
        {
            for( int i = 0; i < size; i++ )
            {
                lineName.append( " " );
            }
            lineName.append( end );
        }
        return lineName.toString();
    }

    public static String createTagForService( String serviceName )
    {
        return new StringBuilder()
                .append( "\n" )
                .append( BAR ).append( "\n" )
                .append( BUBBLE_BAR ).append( "\n" )
                .append( BUBBLE_EMPTY_BAR ).append( "\n" )
                .append( createLineName( serviceName, BUBBLE, BUBBLE ) ).append( "\n" )
                .append( BUBBLE_EMPTY_BAR ).append( "\n" )
                .append( BUBBLE_BAR ).append( "\n" )
                .append( BAR ).toString();
    }

    public static String createTagForWorkingFile( String workingFilename )
    {
        return new StringBuffer()
                .append( BAR ).append( "\n" )
                .append( HASH_TAG_EMPTY_BAR ).append( "\n" )
                .append( createLineName( workingFilename, HASH_TAG, HASH_TAG ) ).append( "\n" )
                .append( HASH_TAG_EMPTY_BAR ).append( "\n" )
                .append( BAR ).toString();
    }

    public static String createErrorCode( String errorCode )
    {
        StringBuilder sb = new StringBuilder();
        for( String s : errorCode.split( "\n" ) )
        {
            sb.append( createLineName( s, STAND_PIPE, STAND_PIPE ) ).append( "\n" );
        }

        return new StringBuffer()
                .append( ERROR_BAR ).append( "\n" )
                .append( CRAZY_BAR ).append( "\n" )
                .append( sb )
                .append( CRAZY_BAR ).append( "\n" )
                .append( ERROR_BAR ).toString();
    }
}
