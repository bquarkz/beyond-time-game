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
package com.intrepid.studio.utilities.animation;

import static com.intrepid.studio.Constants.ABSOLUTE_RESOURCES_APINFO;
import static com.intrepid.studio.Constants.ABSOLUTE_RESOURCES_TEXTURE;
import static com.intrepid.studio.Constants.API_EXTENSION;
import static com.intrepid.studio.Constants.GROUP_TAG;
import static com.intrepid.studio.Constants.NEW_EXTENSION;
import static com.intrepid.studio.Constants.SAI_EXTENSION;
import static com.intrepid.studio.Constants.TEXTURE_EXTENSION;
import static com.intrepid.studio.Constants.WORKDIR_ANIMATION;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.utils.Json;
import com.intrepid.nicge.utils.IFileExec;
import com.intrepid.nicge.utils.JsonHelper;
import com.intrepid.nicge.utils.RootContent;
import com.intrepid.nicge.utils.animation.AnimationInfo;
import com.intrepid.nicge.utils.animation.AnimationInfoFactory;
import com.intrepid.nicge.utils.animation.AnimationPackInfo;
import com.intrepid.nicge.utils.logger.Log;
import com.intrepid.nicge.utils.threads.ITask;
import com.intrepid.studio.OutputDesigns;

public class GenerateAnimationPackInfo implements ITask
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private Map< String, GroupTag > mapGroupTagResource;
    private Files files;
    private RootContent rootContent;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public GenerateAnimationPackInfo( Files files )
    {
        this.files = files;
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    private void generateNewAnimationInfo( Json json )
    {
        rootContent.foreachFilesInAllRootDirectories( new IFileExec()
        {
            @Override
            public void runOver( final FileHandle fHandle )
            {
                if( fHandle.extension().equals( NEW_EXTENSION ) )
                {
                    String line = null;
                    try( BufferedReader br = new BufferedReader( new FileReader( fHandle.file() ) ) )
                    {
                        line = br.readLine();
                    }
                    catch( IOException e )
                    {
                        e.printStackTrace();
                    }

                    String path = fHandle.path();
                    String[] split = path.split( "/" );
                    String dir = split[ split.length - 2 ];
                    String name = split[ split.length - 1 ];
                    String tagName = dir + "." + name;
                    AnimationInfo animationInfo = AnimationInfoFactory.create( tagName, line );

                    System.out.println( "  new found: [ " + fHandle.path() + " ]" );

                    String toWrite = json.prettyPrint( animationInfo );
                    String output = fHandle.pathWithoutExtension() + "." + SAI_EXTENSION;
                    files.local( output ).writeString( toWrite, false );

                    fHandle.delete();
                }
            }
        } );
    }

    private void createMapGroupTagResource()
    {
        rootContent.foreachFilesInAllRootDirectories( new IFileExec()
        {
            @Override
            public void runOver( final FileHandle fHandle )
            {
                if( fHandle.name().equals( GROUP_TAG ) )
                {
                    String resDir = fHandle.path().replace( GROUP_TAG, "" );
                    String groupTagName = fHandle.readString().trim().toLowerCase();

                    System.out.println( "\n-- GROUP TAG FOUND -----------------------------" );
                    System.out.println( "GROUP TAG: " + groupTagName );
                    System.out.println( "Resources Dir: " + resDir );

                    for( FileHandle fRes : getFileHandle( resDir ).list() )
                    {
                        if( fRes.isDirectory() )
                        { //ignore files
                            String path = fRes.path();
                            String[] split = path.split( "/" );
//							String dir = split[ split.length - 2 ];
                            String name = split[ split.length - 1 ];
                            String filename = path + "/" + name + ".";

                            FileHandle sai = new FileHandle( filename + SAI_EXTENSION );
                            FileHandle png = new FileHandle( filename + TEXTURE_EXTENSION );

                            System.out.println( "  " +
                                    sai.name() + ": " + ( sai.exists() ? "OK" : "ERROR" ) + ", " +
                                    png.name() + ": " + ( png.exists() ? "OK" : "ERROR" )
                            );

                            boolean hasData = sai.exists() && png.exists();
                            if( hasData )
                            {
                                TagResource tagResource = new TagResource( sai, png );

                                if( !mapGroupTagResource.containsKey( groupTagName ) )
                                {
                                    mapGroupTagResource.put( groupTagName, new GroupTag( groupTagName ) );
                                }
                                mapGroupTagResource.get( groupTagName ).addTagResource( tagResource );
                            }
                        }
                    }
                }
            }
        } );
    }

    private void loadingTagResourcesData( Json json )
    {
        for( String groupTagName : mapGroupTagResource.keySet() )
        {
            System.out.println( "------------------------------------------------" );
            System.out.println( "GROUP TAG: " + groupTagName );

            GroupTag groupTag = mapGroupTagResource.get( groupTagName );
            groupTag.resetSize();

            for( TagResource sp : groupTag.getResources() )
            {
                sp.loadData( json );
                System.out.println( "  loaded:[" + sp.getAnimationInfo().getName() + "] " +
                        sp.getSai().name() + " and " +
                        sp.getPng().name() );
                groupTag.addSize( sp.getPixmapSize() );
            }
        }
    }

    private void orderTheTagResourceList()
    {
        for( String groupTagName : mapGroupTagResource.keySet() )
        {
            System.out.println( "  ordering: " + groupTagName );
            GroupTag groupTag = mapGroupTagResource.get( groupTagName );
            groupTag.orderResources();
        }
    }

    private void createGroupAssets()
    {
        for( String groupTagName : mapGroupTagResource.keySet() )
        {
            GroupTag groupTag = mapGroupTagResource.get( groupTagName );

            int areaTotal = groupTag.getSize();
            int pixmapBaseSize = 0;
			if( areaTotal <= ( 32 * 32 ) )
			{
				pixmapBaseSize = 32;
			}
			else if( areaTotal <= ( 64 * 64 ) )
			{
				pixmapBaseSize = 64;
			}
			else if( areaTotal <= ( 128 * 128 ) )
			{
				pixmapBaseSize = 128;
			}
			else if( areaTotal <= ( 256 * 256 ) )
			{
				pixmapBaseSize = 256;
			}
			else if( areaTotal <= ( 512 * 512 ) )
			{
				pixmapBaseSize = 512;
			}
			else if( areaTotal <= ( 1024 * 1024 ) )
			{
				pixmapBaseSize = 1024;
			}
			else if( areaTotal <= ( 2048 * 2048 ) )
			{
				pixmapBaseSize = 2048;
			}
			else if( areaTotal <= ( 4096 * 4096 ) )
			{
				pixmapBaseSize = 4096;
			}
			else
			{
				Log.from( this ).failure( "AS IBAGENS MUITO GRANDES, ARRUMA AI MOISES!" );
				throw new RuntimeException();
			}
            groupTag.createPixmapBase( pixmapBaseSize );

            System.out.println( "------------------------------------------------" );
            System.out.println( "GROUP TAG: " + groupTagName );
            System.out.println( "  group size: " + groupTag.getSize() );
            System.out.println( "  pixmap base size: " + pixmapBaseSize + " x " + pixmapBaseSize );
        }
    }

    private void fitResourcesAndCreateAnimationPackInfo()
    {
        for( String groupTagName : mapGroupTagResource.keySet() )
        {
            GroupTag groupTag = mapGroupTagResource.get( groupTagName );

            List< FreeSpace > freeSpaceList = new LinkedList<>();
            int gTagPixmapWidth = groupTag.getPixmap().getWidth();
            int gTagPixmapHeight = groupTag.getPixmap().getHeight();
            freeSpaceList.add( new FreeSpace( 0, 0, gTagPixmapWidth, gTagPixmapHeight ) );

            for( TagResource tagRes : groupTag.getResources() )
            {

                FreeSpace freeSpaceToDelete = null;
                FreeSpace freeSpaceAtSide = null;
                FreeSpace freeSpaceAbove = null;

                for( FreeSpace freeSpace : freeSpaceList )
                {
                    boolean fitWidth = tagRes.getPixmap().getWidth() <= freeSpace.width;
                    boolean fitHeight = tagRes.getPixmap().getHeight() <= freeSpace.height;

                    if( fitWidth && fitHeight )
                    {
                        tagRes.getAnimationInfo().setOrigin( freeSpace.x, freeSpace.y );

                        Pixmap basePixmap = groupTag.getPixmap();
                        Pixmap tagResPixmap = tagRes.getPixmap();
                        basePixmap.drawPixmap( tagResPixmap, freeSpace.x, freeSpace.y );

                        int tagResWidth = tagRes.getPixmap().getWidth();
                        int tagResHeight = tagRes.getPixmap().getHeight();

                        freeSpaceToDelete = freeSpace;
                        if( ( freeSpace.x + tagResWidth ) > 0 )
                        {
                            freeSpaceAtSide = new FreeSpace( freeSpace.x + tagResWidth,
                                    freeSpace.y,
                                    freeSpace.width - tagResWidth,
                                    tagResHeight );
                        }

                        if( ( freeSpace.y + tagResHeight ) > 0 )
                        {
                            freeSpaceAbove = new FreeSpace( freeSpace.x,
                                    freeSpace.y + tagResHeight,
                                    freeSpace.width,
                                    freeSpace.height - tagResHeight );
                        }

                        break;
                    }
                }

                if( freeSpaceToDelete != null )
                {
                    freeSpaceList.remove( freeSpaceToDelete );
					if( freeSpaceAtSide != null )
					{
						freeSpaceList.add( freeSpaceAtSide );
					}
					if( freeSpaceAbove != null )
					{
						freeSpaceList.add( freeSpaceAbove );
					}
                }
                else
                {
                    System.out.println( "ON: " + groupTag.getName() +
                            "; CANNOT FIT: " + tagRes.getPng().name() );
                }

                Collections.sort( freeSpaceList );
            }

            float maxSize = gTagPixmapWidth * gTagPixmapHeight;
            float freeSpaceSize = 0f;
            for( FreeSpace freeSpace : freeSpaceList )
            {
                freeSpaceSize += freeSpace.width * freeSpace.height;
            }
            String p = new DecimalFormat( "#.00" ).format( 100.0f * ( 1.0f - freeSpaceSize / maxSize ) );
            System.out.println( "  space used: " + p + "%[ " + groupTag.getName() + " ]" );

            groupTag.createAnimationPackInfo();
        }
    }

    private void createAnimationOutput( Json json )
    {
        for( String groupTagName : mapGroupTagResource.keySet() )
        {
            GroupTag groupTag = mapGroupTagResource.get( groupTagName );

            String outputApInfo = ABSOLUTE_RESOURCES_APINFO + groupTag.getName() + "." + API_EXTENSION;
            String outputTexture = ABSOLUTE_RESOURCES_TEXTURE + groupTag.getName() + "." + TEXTURE_EXTENSION;

            groupTag.setTextureOutput( outputTexture );

            // Write Animation Pack Info
            AnimationPackInfo api = groupTag.createAnimationPackInfo();
            JsonHelper.writeJson( getFileHandle( outputApInfo ), api, AnimationInfo.class );
//			String toWrite = ( PRETTY_PRINT ?
//					json.prettyPrint( api ) : 
//					json.toJson( api, AnimationPackInfo.class ) );
//			if( ENCODED ) toWrite = new String( Base64.getEncoder().encode( toWrite.getBytes() ) );
//			getFileHandle( outputApInfo ).writeString( toWrite, false );

            // Write Texture file
            PixmapIO.writePNG( getFileHandle( outputTexture ), groupTag.getPixmap() );

            System.out.println( "GROUP TAG: " + groupTagName );
            System.out.println( "  " + outputApInfo );
            System.out.println( "  " + outputTexture );
        }
    }

    private FileHandle getFileHandle( String path )
    {
        return files.absolute( path );
    }

    @Override
    public float taskCompleted()
    {
        return 1;
    }

    public void taskStart()
    {
        this.mapGroupTagResource = new HashMap<>();
        rootContent = new RootContent( getFileHandle( WORKDIR_ANIMATION ) );
        System.out.println( OutputDesigns.createTagForService( "GENERATE ANIMATION PACK INFO SERVICE" ) );
    }

    @Override
    public boolean taskRun()
    {
        Json json = JsonHelper.getJson();

        System.out.println( "### GENERATING NEW ANIMATION INFO..." );
        generateNewAnimationInfo( json );

        System.out.println( "### CREATING MAP TAG RESOURCES...." );
        createMapGroupTagResource();

        System.out.println( "### LOADING DATA..." );
        loadingTagResourcesData( json );

        System.out.println( "### ORDER THE TAG RESOURCES LIST..." );
        orderTheTagResourceList();

        System.out.println( "### CREATING ASSETS FOR CURRENT/AVAIBLE GROUPS..." );
        createGroupAssets();

        System.out.println( "### FIT RESOURCES IN ASSETS..." );
        fitResourcesAndCreateAnimationPackInfo();

        System.out.println( "### CREATING ANIMATION PACK INFO..." );
        createAnimationOutput( json );

        return false;
    }

    @Override
    public void taskShutdown()
    {
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

}