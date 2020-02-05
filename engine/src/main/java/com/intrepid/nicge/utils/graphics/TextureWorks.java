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
package com.intrepid.nicge.utils.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

public class TextureWorks implements Disposable
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private Texture pixel_1x1;
    private Texture pixel_2x2;
    private Texture pixel_4x4;
    private Texture pixel_8x8;
    private Texture pixel_16x16;
    private Texture pixel_64x64;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public TextureWorks()
    {
        pixel_1x1 = createQuadTexture( 1 );
        pixel_2x2 = createQuadTexture( 2 );
        pixel_4x4 = createQuadTexture( 4 );
        pixel_8x8 = createQuadTexture( 8 );
        pixel_16x16 = createQuadTexture( 16 );
        pixel_64x64 = createQuadTexture( 64 );
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    public static Texture createTexture(
            int sizex,
            int sizey )
    {
        return createTexture( sizex, sizey, Color.WHITE );
    }

    public static Texture createTexture(
            int sizex,
            int sizey,
            Color color )
    {
        // For all
        Pixmap pixmap = new Pixmap( sizex, sizey, Format.RGBA8888 );
        pixmap.setColor( color );
        pixmap.fill();
        return new Texture( pixmap );
    }

    public static Texture createQuadTexture( int size )
    {
        return createTexture( size, size );
    }

    public static TextureRegion[] serialize_Texture(
            Texture tex,
            int sizex_px,
            int sizey_px )
    {
        int nFramesX = tex.getWidth() / sizex_px; //col
        int nFramesY = tex.getHeight() / sizey_px; //row

        TextureRegion[] R = new TextureRegion[ nFramesX * nFramesY ];

        int x = 0;
        int y = 0;
        int cont = 0;
        for( int row = 0; row < nFramesY; row++, y += sizey_px )
        {
            x = 0;
            for( int col = 0; col < nFramesX; col++, x += sizex_px )
            {
                R[ cont ] = new TextureRegion( tex, x, y, sizex_px, sizey_px );
                cont++;
            }
        }

        return R;
    }

    public static TextureRegion[] serialize_RegionOnTexture(
            Texture tex,
            int startX,
            int startY,
            int endX,
            int endY,
            int sizex_px,
            int sizey_px )
    {
        int nFramesX; //col
        if( endX > startX )
        {
            nFramesX = ( endX - startX ) / sizex_px;
        }
        else
        {
            nFramesX = ( startX - endX ) / sizex_px;
        }

        int nFramesY; //row
        if( endY > startY )
        {
            nFramesY = ( endX - startX ) / sizey_px;
        }
        else
        {
            nFramesY = ( startX - endX ) / sizey_px;
        }

        TextureRegion[] R = new TextureRegion[ nFramesX * nFramesY ];

        int x;
        int y = startY;
        int cont = 0;
        for( int row = 0; row < nFramesY; row++, y += sizey_px )
        {
            x = startX;
            for( int col = 0; col < nFramesX; col++, x += sizex_px )
            {
                R[ cont ] = new TextureRegion( tex, x, y, sizex_px, sizey_px );
                cont++;
            }
        }

        return R;
    }

    @Override
    public void dispose()
    {
        pixel_1x1.dispose();
        pixel_2x2.dispose();
        pixel_4x4.dispose();
        pixel_8x8.dispose();
        pixel_16x16.dispose();
        pixel_64x64.dispose();
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public Texture getPixel()
    {
        return pixel_1x1;
    }

    public Texture getPixel_2x2()
    {
        return pixel_2x2;
    }

    public Texture getPixel_4x4()
    {
        return pixel_4x4;
    }

    public Texture getPixel_8x8()
    {
        return pixel_8x8;
    }

    public Texture getPixel_16x16()
    {
        return pixel_16x16;
    }

    public Texture getPixel_64x64()
    {
        return pixel_64x64;
    }

    // ****************************************************************************************
    // Patterns
    // ***************************************************************************************
}
