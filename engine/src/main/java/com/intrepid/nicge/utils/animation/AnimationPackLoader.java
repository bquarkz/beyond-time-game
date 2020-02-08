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
 * all comunity without problems.
 */
package com.intrepid.nicge.utils.animation;

import java.util.Base64;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.intrepid.nicge.kernel.Configurations;
import com.intrepid.nicge.utils.konstants.IResourcesPath;

public class AnimationPackLoader 
		extends AsynchronousAssetLoader< AnimationPack, AnimationPackLoader.AnimationPackParameter > {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************
	private static final boolean ENCODED = Configurations.get().encoded;	

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public AnimationPackLoader( FileHandleResolver resolver ) {
		super( resolver );
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	@Override
	public void loadAsync(
	        AssetManager manager,
			String fileName,
			FileHandle file,
			AnimationPackParameter parameter
			) {
	}

	@Override
	public AnimationPack loadSync(
	        AssetManager manager,
			String fileName,
			FileHandle file,
			AnimationPackParameter parameter
			) {
	    
		byte[] bytes = file.readBytes();
		String json = ( ENCODED ? new String( Base64.getDecoder().decode( bytes ) ) : new String( bytes ) );
		AnimationPackInfo animationPackInfo = new Json().fromJson( AnimationPackInfo.class, json );		

		String texturePath = IResourcesPath.TEXTURES_PATH + file.nameWithoutExtension() + ".png";
		Texture texture = manager.get( texturePath, Texture.class );

		return new AnimationPack( animationPackInfo, texture );
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Array< AssetDescriptor > getDependencies( String fileName,
													 FileHandle file,
													 AnimationPackParameter parameter ) {
		Array<AssetDescriptor > deps = new Array<>();
		
		String path = IResourcesPath.TEXTURES_PATH + file.nameWithoutExtension() + ".png";
		FileHandle resolved = resolve( path );
		
		TextureLoader.TextureParameter textureParams = new TextureLoader.TextureParameter();
		if( parameter != null ) {
			textureParams.genMipMaps = parameter.genMipMaps;
			textureParams.minFilter = parameter.minFilter;
			textureParams.magFilter = parameter.magFilter;
		} else {
			textureParams.genMipMaps = false;
			textureParams.minFilter = TextureFilter.Nearest;
			textureParams.magFilter = TextureFilter.Nearest;
		}

		deps.add( new AssetDescriptor( resolved, Texture.class, textureParams ) );		
		
		return deps;
	}

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
	public class AnimationPackParameter extends AssetLoaderParameters< AnimationPack > {
		public boolean genMipMaps = false;
		public TextureFilter minFilter = TextureFilter.Nearest;
		public TextureFilter magFilter = TextureFilter.Nearest;		
	}
}
