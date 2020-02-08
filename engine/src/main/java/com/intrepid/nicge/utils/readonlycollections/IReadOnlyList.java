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
 * all community without problems.
 */
package com.intrepid.nicge.utils.readonlycollections;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public interface IReadOnlyList< T > extends Iterable< T >
{
    // ****************************************************************************************
    // Constants
    // ****************************************************************************************

    // ****************************************************************************************
    // Contracts
    // ****************************************************************************************
    boolean contains( T o );

    void sort( Comparator< T > comparator );

    T get( int index );

    int size();

    // ****************************************************************************************
    // Default methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Static Methods
    // ****************************************************************************************
    public static < T > IReadOnlyList< T > wrap(
            final List< T > list
    )
    {
        return new IReadOnlyList< T >()
        {
            @Override
            public Iterator< T > iterator()
            {
                return list.iterator();
            }

            @Override
            public void sort( Comparator< T > comparator )
            {
                list.sort( comparator );
            }

            @Override
            public int size()
            {
                return list.size();
            }

            @Override
            public T get( int index )
            {
                return list.get( index );
            }

            @Override
            public boolean contains( T o )
            {
                return list.contains( o );
            }
        };
    }
}
