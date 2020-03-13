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
package com.intrepid.nicge.content;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.intrepid.nicge.utils.animation.AnimationPack;
import com.intrepid.nicge.utils.konstants.IResourcesPath;

public final class ResourceFactory
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private static int counter_id;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    static
    {
        ResourceFactory.counter_id = 0;
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    private static final < T > IResource< T > register(
            String name,
            Class< T > resourceClass )
    {
        return new ResourceWrapper<>( counter_id++, name, resourceClass );
    }

    public static final IResource< Texture > registerTexture( String name )
    {
        return register( IResourcesPath.TEXTURES_PATH + name, Texture.class );
    }

    public static final IResource< AnimationPack > registerAnimationPack( String name )
    {
        return register( IResourcesPath.ANIMATION_PACK_INFO_PATH + name, AnimationPack.class );
    }

    public static final IResource< BitmapFont > registerFont( String name )
    {
        return register( IResourcesPath.FONT_PATH + name, BitmapFont.class );
    }

//	public static final Resource< DynamicDependencyPack > registerDynamicDependencyPack( String name ) {
//	    return register( ResourcesPath.dynamicDependencyPack + name, DynamicDependencyPack.class ); 
//	}

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
