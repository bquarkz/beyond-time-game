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
package com.intrepid.game;

import static com.intrepid.nicge.content.ResourceFactory.*;

import com.badlogic.gdx.graphics.Texture;
import com.intrepid.nicge.content.Resource;
import com.intrepid.nicge.utils.animation.AnimationPack;

public class Resources
{
    public enum Textures implements Resource< Texture >
    {
        BADLOGIC_LOGO( "badlogic.jpg" ),
        OUTRO_LOGO( "badlogic_other.jpg" ),
        FUNDO2( "fundo2.png" ),
        TESTE_CARGA_01( "teste.carga.01.jpg" ),
        TESTE_CARGA_02( "teste.carga.02.jpg" ),
        TESTE_CARGA_03( "teste.carga.03.jpg" ),
        GREEN_COUTAIN( "GreenCourtain.png" ),
        BLUE_COUTAIN( "BlueCourtain.png" ),
        RED_COUTAIN( "RedCourtain.png" ),
        PURPLE_COUTAIN( "PurpleCourtain.png" ),
        ;

        private Resource< Texture > resource;

        private Textures( String name )
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

    public enum Animations implements Resource< AnimationPack >
    {
        SENTINEL( "sentinel.api" ),
        ;

        private Resource< AnimationPack > resource;

        private Animations( String name )
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