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
package com.intrepid.studio;

public class Constants
{
    public static final String ABSOLUTE_APP_PATH;
    
    public static final String TEXTURE_EXTENSION = "png";
    public static final String SAI_EXTENSION = "sai";
    public static final String API_EXTENSION = "api";
    public static final String NEW_EXTENSION = "new";
    public static final String GROUP_TAG = "group.tag";
    public static final String RESOURCES_APINFO = "resources/apinfo/";
    public static final String RESOURCES_TEXTURE = "resources/textures/";
    public static final String RESOURCES_MPINFO = "resources/mpinfo/";

    public static final String ABSOLUTE_ASSET_PATH;
	public static final String WORKDIR_TILEMAP;
	public static final String WORKDIR_TILEMAP_TILESET;
	public static final String WORKDIR_TILEMAP_JSON_MAP;
    public static final String WORKDIR_ANIMATION;
    public static final String ABSOLUTE_RESOURCES_APINFO;
    public static final String ABSOLUTE_RESOURCES_TEXTURE;
    public static final String ABSOLUTE_RESOURCES_MPINFO;

    public static final boolean SHOW_TILEMAP_STATISTICS = true;

    static
    {
        String projectPath = System.getProperty( "project-root" );
        if( projectPath == null || projectPath.isEmpty() )
        {
            projectPath = "D:\\workarea\\git\\games\\beyond-time-game\\";
        }
        System.out.println( ">> using as project root: " + projectPath );

        ABSOLUTE_APP_PATH = projectPath;
        ABSOLUTE_ASSET_PATH = ABSOLUTE_APP_PATH + "assets/";
        WORKDIR_TILEMAP = ABSOLUTE_ASSET_PATH + "workdir/tilemap/";
        WORKDIR_TILEMAP_TILESET = WORKDIR_TILEMAP + "tileset/";
        WORKDIR_TILEMAP_JSON_MAP = WORKDIR_TILEMAP + "json/";
        WORKDIR_ANIMATION = ABSOLUTE_ASSET_PATH + "workdir/animations/";
        ABSOLUTE_RESOURCES_APINFO = ABSOLUTE_ASSET_PATH + RESOURCES_APINFO;
        ABSOLUTE_RESOURCES_TEXTURE = ABSOLUTE_ASSET_PATH + RESOURCES_TEXTURE;
        ABSOLUTE_RESOURCES_MPINFO = ABSOLUTE_ASSET_PATH + RESOURCES_MPINFO;

    }
}
