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
package com.intrepid.nicge.theater;

import java.util.HashSet;
import java.util.Set;

import com.intrepid.nicge.content.AssetManager;
import com.intrepid.nicge.content.ILoadable;
import com.intrepid.nicge.content.IResource;
import com.intrepid.nicge.utils.logger.Log;

final class StageHand implements IUpdatable
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private final AssetManager assetManager;
    private boolean needToUnLoad;
    private ILoadable toLoad;
    private ILoadable assetsLoaded;

//	DEBUG
//	private int counter;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public StageHand( AssetManager assetManager )
    {
        this.assetManager = assetManager;
        this.toLoad = null;
        this.assetsLoaded = null;
        this.needToUnLoad = false;
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    @Override
    public void update()
    {
		if( this.toLoad == null )
		{
			return;
		}

        if( ( assetsLoaded != null ) && ( needToUnLoad ) )
        {
            final Set< IResource< ? > > resources = new HashSet<>();
            assetsLoaded.captureResources( resources );
            for( IResource< ? > resource : resources )
            {
                assetManager.unload( resource.getPath() );
            }
            needToUnLoad = false;
        }

        boolean isFinished = assetManager.update();
        if( isFinished )
        {
            loadingFinished();
        }
    }

    public void loadResourcesFrom( ILoadable toLoad )
    {
        final Set< IResource< ? > > resources = new HashSet<>();
        toLoad.captureResources( resources );

        if( resources.isEmpty() )
        {
			// just to be aware on logs if some resource is empty
            Log.from( this ).warning( "the resources is EMPTY in: " + toLoad.getClass().getName() );
            return;
        }

        // keep it for testing and protection...
        for( IResource< ? > resource : resources )
        {
            if( resource == null )
            {
                throw new RuntimeException( "we have a null resource in: " + toLoad.getClass().getName() );
            }
        }

        assetManager.load( resources );
        this.needToUnLoad = true;
        this.toLoad = toLoad;
    }

    private void loadingFinished()
    {
        this.assetsLoaded = this.toLoad;
        this.toLoad = null;
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public boolean isLoadingFinished()
    {
        return ( this.toLoad == null );
    }

    // ****************************************************************************************
    // Patterns
    // ***************************************************************************************
}
