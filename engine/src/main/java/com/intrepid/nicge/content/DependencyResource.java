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
 * all community without problems.
 */
package com.intrepid.nicge.content;

import java.util.HashSet;
import java.util.Set;

import com.intrepid.nicge.kernel.game.Game;

public abstract class DependencyResource implements Loadable {
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private Set< Resource< ? > > resources;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public DependencyResource() {
        resources = new HashSet<>();
        setDependencies( resources );
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    protected abstract void setDependencies( Set< Resource< ? > > resources );
    
    @Override
    public void captureResources( Set< Resource< ? > > resources ) {
        for( Resource< ? > resource : this.resources ) {
            resources.add( resource );
        }
    }
    
    protected final void captureResourcesFrom(
            Class< ? > clazz,
            Set< Resource< ? > > resources
            ) {
        
        Loadable dependencies = Game.common.getDependencies( clazz );
        dependencies.captureResources( resources );
    }
    
    protected final void captureResourcesFrom(
            DynamicDependencyPack pack,
            Set< Resource< ? > > resources
            ) {
        
        for( Class< ? > clazz : pack.getDynamicDependencies() ) {
            captureResourcesFrom( clazz, resources );
        }
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
