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
import com.intrepid.nicge.utils.animation.AnimationPack;
import com.intrepid.nicge.utils.konstants.ResourcesPath;

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
    private static final < T > Resource< T > register(
            String name,
            Class< T > resourceClass )
    {
        return new ResourceWrapper<>( counter_id++, name, resourceClass );
    }

    public static final Resource< Texture > registerTexture( String name )
    {
        return register( ResourcesPath.TEXTURES_PATH + name, Texture.class );
    }

    public static final Resource< AnimationPack > registerAnimationPack( String name )
    {
        return register( ResourcesPath.ANIMATION_PACK_INFO_PATH + name, AnimationPack.class );
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
