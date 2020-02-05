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
package com.intrepid.nicge.content;

import java.util.Base64;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.intrepid.nicge.kernel.Configurations;

public class DynamicDependencyPackLoader 
		extends AsynchronousAssetLoader< DynamicDependencyPack, DynamicDependencyPackLoader.DynamicDependencyPackParameter > {
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
	public DynamicDependencyPackLoader( FileHandleResolver resolver ) {
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
			DynamicDependencyPackParameter parameter
			) {
	}

	@Override
	public DynamicDependencyPack loadSync(
	        AssetManager manager,
			String fileName,
			FileHandle file,
			DynamicDependencyPackParameter parameter
			) {
	    
		byte[] bytes = file.readBytes();
		String json = ( ENCODED ? new String( Base64.getDecoder().decode( bytes ) ) : new String( bytes ) );
		System.out.println( fileName + ": " + json );
		return new Json().fromJson( DynamicDependencyPack.class, json );
	}

	@SuppressWarnings( "rawtypes" )
    @Override
	public Array< AssetDescriptor > getDependencies(
	        String fileName,
	        FileHandle file,
			DynamicDependencyPackParameter parameter
			) {
	    
		return new Array<>();
	}

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
	public class DynamicDependencyPackParameter extends AssetLoaderParameters< DynamicDependencyPack > {
	}
}
