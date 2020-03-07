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

public class TilesetReader
{
    private String name;
    private String transparentcolor;
    private String image;
    private int firstgid;
    private int margin;
    private int spacing;
    private int imagewidth;
    private int imageheight;
    private int tilewidth;
    private int tileheight;
    private TileMapOffSet tileoffset;
    private TileSetReaderProperties properties;

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder( "TileSet Name: " ).append( name ).append( "\n" );
        sb.append( "Margin: " ).append( margin ).append( "\n" );
        sb.append( "Spacing: " ).append( spacing ).append( "\n" );
        sb.append( "Image: " ).append( image ).append( "\n" );
        sb.append( "FirstGID: " ).append( firstgid ).append( "\n" );
        sb.append( "Size: " ).append( imagewidth ).append( " x " ).append( imageheight ).append( "\n" );
        sb.append( "TileSize: " ).append( tilewidth ).append( " x " ).append( tileheight ).append( "\n" );
        if( transparentcolor != null )
        {
            sb.append( "Transparent Color: " ).append( transparentcolor ).append( "\n" );
        }
        sb.append( "TileSet Properties: " ).append( properties ).append( "\n" );
        return sb.toString();
    }

    public String getName()
    {
        return name;
    }

    public String getTransparentcolor()
    {
        return transparentcolor;
    }

    public String getImage()
    {
        return image;
    }

    public int getFirstgid()
    {
        return firstgid;
    }

    public int getMargin()
    {
        return margin;
    }

    public int getSpacing()
    {
        return spacing;
    }

    public int getImagewidth()
    {
        return imagewidth;
    }

    public int getImageheight()
    {
        return imageheight;
    }

    public int getTilewidth()
    {
        return tilewidth;
    }

    public int getTileheight()
    {
        return tileheight;
    }

    public TileMapOffSet getTileoffset()
    {
        return tileoffset;
    }

    public TileSetReaderProperties getProperties()
    {
        return properties;
    }
}