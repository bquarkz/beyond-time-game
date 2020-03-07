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
package com.intrepid.studio.utilities.tiled;

public class TiledLayer
{
    private int[] data;
    private int width;
    private int height;
    private String name;
    private String opacity;
    private String type;
    private boolean visible;
    private int x;
    private int y;

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder( "Layer Name: " ).append( name ).append( "\n" );

        sb.append( "Data: [ " );
        for( int i = 0; i < data.length; i++ )
        {
            sb.append( data[ i ] ).append( ", " );
        }
        sb.append( "]\n" );

        return sb.toString();
    }

    public int[] getData()
    {
        return data;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public String getName()
    {
        return name;
    }

    public String getOpacity()
    {
        return opacity;
    }

    public String getType()
    {
        return type;
    }

    public boolean isVisible()
    {
        return visible;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
}
