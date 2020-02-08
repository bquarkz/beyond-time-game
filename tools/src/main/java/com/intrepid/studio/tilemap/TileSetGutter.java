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
package com.intrepid.studio.tilemap;

import static com.intrepid.studio.kernel.Constants.ABSOLUTE_RESOURCES_TEXTURE;
import static com.intrepid.studio.kernel.Constants.WORKDIR_TILEMAP;
import static com.intrepid.studio.kernel.Constants.WORKDIR_TILEMAP_TILESET;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.intrepid.nicge.utils.IFileExec;
import com.intrepid.nicge.utils.RootContent;
import com.intrepid.nicge.utils.threads.IThreadRunnable;
import com.intrepid.studio.kernel.Designs;
import com.intrepid.studio.kernel.Studio;

public class TileSetGutter implements IThreadRunnable
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************
    private final static int TILE_SIZE = Studio.common.getConfiguration().getTileSize();
    private final static int PAD = Studio.common.getConfiguration().getPadSize();
    
    private final static int TILE_SIZE_MINUS_ONE = TILE_SIZE - 1;
    private final static int TILE_SIZE_MINUS_TWO = TILE_SIZE - 2;
    private final static int PAD_PLUS_ONE = PAD + 1;
    private final static int PAD_MINUS_ONE = PAD - 1;
    private static final int TILE_SIZE_EFFECTIVE = TILE_SIZE + 2 * PAD;
    
    private final Files files;
    
    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private RootContent rootContent;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public TileSetGutter( Files files ) {
        this.files = files;
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    private FileHandle getFileHandle( String path ) {
        return files.absolute( path );
    }
    
    private void copyTile( Pixmap pixmapO, Pixmap pixmapD, int tilex, int tiley ) {
        int posXO = tilex * TILE_SIZE;
        int posYO = tiley * TILE_SIZE;
        int posXD = tilex * TILE_SIZE_EFFECTIVE;
        int posYD = tiley * TILE_SIZE_EFFECTIVE;
        phaseOne( pixmapO, pixmapD, posXO, posYO, posXD, posYD );
        phaseTwo( pixmapO, pixmapD, posXO, posYO, posXD, posYD );
        phaseThree( pixmapO, pixmapD, posXO, posYO, posXD, posYD );
    }
    
    private void phaseOne( Pixmap pixmapO, Pixmap pixmapD, int posXO, int posYO, int posXD, int posYD )
    {
        // quadrado superior esquerdo
        copySquarePhaseOne( pixmapO, pixmapD,
                posXD, posYD,
                posXO, posYO );
        // quadrado superior direito
        copySquarePhaseOne( pixmapO, pixmapD, 
                posXD + TILE_SIZE + PAD_MINUS_ONE, posYD,
                posXO + TILE_SIZE_MINUS_ONE, posYO );
        // quadrado inferior esquerdo
        copySquarePhaseOne( pixmapO, pixmapD,
                posXD, posYD + TILE_SIZE + PAD_MINUS_ONE,
                posXO, posYO + TILE_SIZE_MINUS_ONE );
        // quadrado inferior direito
        copySquarePhaseOne( pixmapO, pixmapD,
                posXD + TILE_SIZE + PAD_MINUS_ONE, posYD + TILE_SIZE + PAD_MINUS_ONE,
                posXO + TILE_SIZE_MINUS_ONE, posYO + TILE_SIZE_MINUS_ONE );
    }
    
    private void copySquarePhaseOne(
            Pixmap pixmapO,
            Pixmap pixmapD,
            int oCorrX,
            int oCorry,
            int dPosx, 
            int dPosy
            )
    {
        for( int i = 0; i < PAD_PLUS_ONE; i++ )
        {
            for( int j = 0; j < PAD_PLUS_ONE; j++ )
            {
                pixmapD.drawPixmap( pixmapO, oCorrX + i, oCorry + j, dPosx, dPosy, 1, 1 );
            }
        }
    }

    private void phaseTwo( Pixmap pixmapO, Pixmap pixmapD, int posXO, int posYO, int posXD, int posYD )
    {
        for( int i = 0; i < PAD; i++ )
        {
            // top gut
            pixmapD.drawPixmap( pixmapO, posXD + PAD_PLUS_ONE, posYD + i,
                    posXO + 1, posYO + 0,
                    TILE_SIZE_MINUS_TWO, 1 );
            
            // botton gut
            pixmapD.drawPixmap( pixmapO, posXD + PAD_PLUS_ONE, posYD + TILE_SIZE + PAD + i,
                    posXO + 1, posYO + TILE_SIZE_MINUS_ONE,
                    TILE_SIZE_MINUS_TWO, 1 );

            // right gut
            pixmapD.drawPixmap( pixmapO, posXD + i, posYD + PAD_PLUS_ONE,
                    posXO, posYO + 1,
                    1, TILE_SIZE_MINUS_TWO );
            
            // left gut
            pixmapD.drawPixmap( pixmapO, posXD + TILE_SIZE + PAD + i, posYD + PAD_PLUS_ONE,
                    posXO + TILE_SIZE_MINUS_ONE, posYO + 1,
                    1, TILE_SIZE_MINUS_TWO );
        }
    }

    private void phaseThree( Pixmap pixmapO, Pixmap pixmapD, int posXO, int posYO, int posXD, int posYD )
    {
        pixmapD.drawPixmap( pixmapO, posXD + PAD, posYD + PAD,
                posXO + 0, posYO + 0,
                TILE_SIZE, TILE_SIZE );
    }

    @Override
    public void startThread()
    {
        rootContent = new RootContent( getFileHandle( WORKDIR_TILEMAP ) );
        System.out.println( Designs.createTagForService( "TILE SET GUTTER SERVICE" ) );
    }
    
    @Override
    public boolean executeThread()
    {
        rootContent.foreachFileInRootDirectory( WORKDIR_TILEMAP_TILESET, new IFileExec() {
            @Override
            public void runOver( final FileHandle fHandle ) {
                String filename = fHandle.name();
                System.out.println( Designs.createTagForWorkingFile( filename ) );
                
                Pixmap pixmapO = new Pixmap( fHandle );
                Pixmap pixmapD = new Pixmap( pixmapO.getWidth(), pixmapO.getHeight(), pixmapO.getFormat() );
                
                int MaxX = pixmapO.getWidth() / TILE_SIZE_EFFECTIVE;
                int MaxY = pixmapO.getHeight() / TILE_SIZE_EFFECTIVE;
                
                System.out.println( "Maximum effective tileset size: " + MaxX + " x " + MaxY );
                System.out.println( "Copying tiles" );
                for( int tilex = 0; tilex < MaxX; tilex++ )
                {
                    for( int tiley = 0; tiley < MaxY; tiley++ )
                    {
                        copyTile( pixmapO, pixmapD, tilex, tiley );
                    }
                }
                
                String outputTexture = ABSOLUTE_RESOURCES_TEXTURE + filename;
                PixmapIO.writePNG( getFileHandle( outputTexture ), pixmapD );
                System.out.println( "Stored on: " + outputTexture );
            }
        } );
        return false;
    }

    @Override
    public void shutdownThread()
    {
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
