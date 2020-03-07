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
 * all comunity without problems.
 */
package com.intrepid.studio;

import com.badlogic.gdx.graphics.Texture;
import com.intrepid.nicge.content.IResource;
import com.intrepid.nicge.utils.animation.AnimationPack;

import static com.intrepid.nicge.content.ResourceFactory.registerAnimationPack;
import static com.intrepid.nicge.content.ResourceFactory.registerTexture;

public class Resources
{
    public enum Textures implements IResource< Texture >
    {
        ;

        private IResource< Texture > resource;

        Textures( String name )
        {
            resource = registerTexture( name );
        }

        @Override
        public int getResourceId()
        {
            return resource.getResourceId();
        }

        @Override
        public String getPath()
        {
            return resource.getPath();
        }

        @Override
        public Class< Texture > getResourceClass()
        {
            return resource.getResourceClass();
        }

        @Override
        public Texture getAsset()
        {
            return resource.getAsset();
        }
    }

    public enum Animations implements IResource< AnimationPack >
    {
        ;

        private IResource< AnimationPack > resource;

        Animations( String name )
        {
            resource = registerAnimationPack( name );
        }

        @Override
        public int getResourceId()
        {
            return resource.getResourceId();
        }

        @Override
        public String getPath()
        {
            return resource.getPath();
        }

        @Override
        public Class< AnimationPack > getResourceClass()
        {
            return resource.getResourceClass();
        }

        @Override
        public AnimationPack getAsset()
        {
            return resource.getAsset();
        }
    }
}