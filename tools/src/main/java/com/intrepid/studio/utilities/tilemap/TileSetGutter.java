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
package com.intrepid.studio.utilities.tilemap;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.utils.IFileExec;
import com.intrepid.nicge.utils.RootContent;
import com.intrepid.nicge.utils.threads.ITask;
import com.intrepid.studio.OutputDesigns;

import static com.intrepid.studio.Constants.*;

public class TileSetGutter
        implements ITask
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************
    private final static int TILE_SIZE = Game.common.getGameConfiguration().getTileSize();
    private final static int PAD = Game.common.getGameConfiguration().getPadSize();

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
    public TileSetGutter( Files files )
    {
        this.files = files;
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    private FileHandle getFileHandle( String path )
    {
        return files.absolute( path );
    }

    private void copyTile(
            Pixmap pixmapO,
            Pixmap pixmapD,
            int tilex,
            int tiley )
    {
        int posXO = tilex * TILE_SIZE;
        int posYO = tiley * TILE_SIZE;
        int posXD = tilex * TILE_SIZE_EFFECTIVE;
        int posYD = tiley * TILE_SIZE_EFFECTIVE;
        phaseOne( pixmapO, pixmapD, posXO, posYO, posXD, posYD );
        phaseTwo( pixmapO, pixmapD, posXO, posYO, posXD, posYD );
        phaseThree( pixmapO, pixmapD, posXO, posYO, posXD, posYD );
    }

    private void phaseOne(
            Pixmap pixmapO,
            Pixmap pixmapD,
            int posXO,
            int posYO,
            int posXD,
            int posYD )
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

    private void phaseTwo(
            Pixmap pixmapO,
            Pixmap pixmapD,
            int posXO,
            int posYO,
            int posXD,
            int posYD )
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

    private void phaseThree(
            Pixmap pixmapO,
            Pixmap pixmapD,
            int posXO,
            int posYO,
            int posXD,
            int posYD )
    {
        pixmapD.drawPixmap( pixmapO, posXD + PAD, posYD + PAD,
                posXO + 0, posYO + 0,
                TILE_SIZE, TILE_SIZE );
    }

    @Override
    public void taskStart()
    {
        rootContent = new RootContent( getFileHandle( WORKDIR_TILEMAP ) );
        System.out.println( OutputDesigns.createTagForService( "TILE SET GUTTER SERVICE" ) );
    }

    @Override
    public void taskRun()
    {
        rootContent.foreachFileInRootDirectory( WORKDIR_TILEMAP_TILESET, new IFileExec()
        {
            @Override
            public void runOver( final FileHandle fHandle )
            {
                final String filename = fHandle.name();
                System.out.println( OutputDesigns.createTagForWorkingFile( filename ) );

                final Pixmap pixmapO = new Pixmap( fHandle );
                final Pixmap pixmapD = new Pixmap( pixmapO.getWidth(), pixmapO.getHeight(), pixmapO.getFormat() );

                final int MaxX = pixmapO.getWidth() / TILE_SIZE_EFFECTIVE;
                final int MaxY = pixmapO.getHeight() / TILE_SIZE_EFFECTIVE;

                System.out.println( "Maximum effective tileset size: " + MaxX + " x " + MaxY );
                System.out.println( "Copying tiles" );
                for( int tilex = 0; tilex < MaxX; tilex++ )
                {
                    for( int tiley = 0; tiley < MaxY; tiley++ )
                    {
                        copyTile( pixmapO, pixmapD, tilex, tiley );
                    }
                }

                final String outputTexture = ABSOLUTE_RESOURCES_TEXTURE + filename;
                PixmapIO.writePNG( getFileHandle( outputTexture ), pixmapD );
                System.out.println( "Stored on: " + outputTexture );
            }
        } );
    }

    @Override
    public float getTaskCompletion()
    {
        return 0;
    }

    @Override
    public void taskShutdown()
    {
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
