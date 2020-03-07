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
package com.intrepid.studio.animation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.intrepid.nicge.utils.animation.AnimationInfo;
import com.intrepid.nicge.utils.animation.AnimationPackInfo;

class GroupTag
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private String name;
    private List< TagResource > tagResources;
    private Pixmap pixmap;
    private int size;
    private String outputTexture;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public GroupTag( String name )
    {
        this.name = name;
        this.tagResources = new ArrayList<>();
        this.size = 0;
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    public void createPixmapBase( int PIXMAP_BASE_SIZE )
    {
        pixmap = new Pixmap( PIXMAP_BASE_SIZE, PIXMAP_BASE_SIZE, Format.RGBA8888 );
    }

    public void addTagResource( TagResource tagResource )
    {
        this.tagResources.add( tagResource );
    }

    public void resetSize()
    {
        this.size = 0;
    }

    public void addSize( int size )
    {
        this.size += size;
    }

    public void orderResources()
    {
        Collections.sort( tagResources );
    }

    public AnimationPackInfo createAnimationPackInfo()
    {
        final AnimationInfo[] pack = new AnimationInfo[ tagResources.size() ];
        for( int i = 0; i < tagResources.size(); i++ )
        {
            pack[ i ] = tagResources.get( i ).getAnimationInfo();
        }

        return new AnimationPackInfo( pack );
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public String getName()
    {
        return name;
    }

    public List< TagResource > getResources()
    {
        return tagResources;
    }

    public Pixmap getPixmap()
    {
        return pixmap;
    }

    public int getSize()
    {
        return size;
    }

    public void setTextureOutput( String outputTexture )
    {
        this.outputTexture = outputTexture;
    }

    public String getTextureOutput()
    {
        return this.outputTexture;
    }

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************

}