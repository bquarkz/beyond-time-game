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
 * all community without problems.
 */
package com.intrepid.studio.utilz.tiled;

public class TiledReader {
	private String backgroundcolor;
	private int width;
	private int height;
	private String orientation;
	private int tilewidth;
	private int tileheight;
	
    private int version;
	private Properties properties;
	private TiledLayer[] layers;
	private TilesetReader[] tilesets;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder( "Background Color: " ).append( backgroundcolor ).append( "\n" );
		sb.append( "Orientation: " ).append( orientation ).append( "\n" );
		sb.append( "Map Size: " ).append( width ).append( " x " ).append( height ).append( "\n" );
		sb.append( "Map Size Area: " ).append( width * height ).append( "\n" );
		sb.append( "Tile Size: " ).append( tilewidth ).append( " x " ).append( tileheight ).append( "\n" );
		sb.append( "Version: " ).append( version ).append( "\n" );		
		sb.append( "\n--- TiledMap Properties ----\n" ).append( properties ).append( "\n" );

		sb.append( "--- Tiled Layers ----\n" );
		for( int i = 0; i < layers.length; i++ )
			sb.append( layers[ i ] ).append( "\n" );

		sb.append( "--- Tilesets ----\n" );
		for( int i = 0; i < tilesets.length; i++ )
			sb.append( tilesets[ i ] ).append( "\n" );
		
		return sb.toString();
	}
	
    public String getBackgroundcolor() {
        return backgroundcolor;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getOrientation() {
        return orientation;
    }

    public int getTilewidth() {
        return tilewidth;
    }

    public int getTileheight() {
        return tileheight;
    }

    public int getVersion() {
        return version;
    }

    public Properties getProperties() {
        return properties;
    }

    public TiledLayer[] getLayers() {
        return layers;
    }

    public TilesetReader[] getTilesets() {
        return tilesets;
    }
}
