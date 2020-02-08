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

import java.util.Set;

import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.MusicLoader;
import com.badlogic.gdx.assets.loaders.PixmapLoader;
import com.badlogic.gdx.assets.loaders.SoundLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.intrepid.nicge.utils.animation.AnimationPack;
import com.intrepid.nicge.utils.animation.AnimationPackLoader;

public class AssetManager extends com.badlogic.gdx.assets.AssetManager
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public AssetManager()
    {
        super( new InternalFileHandleResolver(), false );
        setAllLoaders( getFileHandleResolver() );
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    private void setAllLoaders( FileHandleResolver resolver )
    {
        setLoader( Pixmap.class, new PixmapLoader( resolver ) );
        setLoader( Texture.class, new TextureLoader( resolver ) );
        setLoader( Music.class, new MusicLoader( resolver ) );
        setLoader( Sound.class, new SoundLoader( resolver ) );
        setLoader( BitmapFont.class, new BitmapFontLoader( resolver ) );
        setLoader( AnimationPack.class, new AnimationPackLoader( resolver ) );
        setLoader( DynamicDependencyPack.class, new DynamicDependencyPackLoader( resolver ) );

        // all another default loaders
//		setLoader(TextureAtlas.class, new TextureAtlasLoader(resolver));
//		setLoader(Skin.class, new SkinLoader(resolver));
//		setLoader(ParticleEffect.class, new ParticleEffectLoader(resolver));
//		setLoader(com.badlogic.gdx.graphics.g3d.particles.ParticleEffect.class,
//			new com.badlogic.gdx.graphics.g3d.particles.ParticleEffectLoader(resolver));
//		setLoader(PolygonRegion.class, new PolygonRegionLoader(resolver));
//		setLoader(I18NBundle.class, new I18NBundleLoader(resolver));
//		setLoader(Model.class, ".g3dj", new G3dModelLoader(new JsonReader(), resolver));
//		setLoader(Model.class, ".g3db", new G3dModelLoader(new UBJsonReader(), resolver));
//		setLoader(Model.class, ".obj", new ObjLoader(resolver));
//		setLoader(ShaderProgram.class, new ShaderProgramLoader(resolver));
    }

    public < T > T get( IResource< T > resource )
    {
        return (T)this.get( resource.getPath(), resource.getResourceClass() );
    }

    public void load( IResource< ? > resource )
    {
        this.load( resource.getPath(), resource.getResourceClass() );
    }

    public void load( Set< IResource< ? > > resources )
    {
        for( IResource< ? > resource : resources )
        {
            this.load( resource.getPath(), resource.getResourceClass() );
        }
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
