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

public interface ILoadable
{
    static ILoadable with( final Set< IResource< ? > > fullSetResources )
    {
        return resources -> resources.addAll( fullSetResources );
    }

    void injectResourcesOn( Set< IResource< ? > > resources );
}
