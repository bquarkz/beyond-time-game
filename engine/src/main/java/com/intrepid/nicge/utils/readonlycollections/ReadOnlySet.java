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
 * all community without problems.
 */
package com.intrepid.nicge.utils.readonlycollections;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface ReadOnlySet< T > extends Iterable< T >
{
    // ****************************************************************************************
    // Constants
    // ****************************************************************************************

    // ****************************************************************************************
    // Contracts
    // ****************************************************************************************
    boolean contains( T o );

    int size();

    Stream<T> stream();

    // ****************************************************************************************
    // Default methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Static Methods
    // ****************************************************************************************
    @SuppressWarnings("unchecked")
    static <T> ReadOnlySet<T> empty() {
        return ReadOnlySet.of();
    }

    @SuppressWarnings("unchecked")
    static < T > ReadOnlySet< T > of(T... ts) {
        return of(Stream.of(ts).collect(Collectors.toSet()));
    }

    static < T > ReadOnlySet< T > of(final Set< T > set)
    {
        return new ReadOnlySet< T >()
        {
            @Override
            public Iterator< T > iterator()
            {
                return set.iterator();
            }

            @Override
            public int size()
            {
                return set.size();
            }

            @Override
            public Stream<T> stream() {
                return set.stream();
            }

            @Override
            public boolean contains( T o )
            {
                return set.contains( o );
            }

            @Override
            public String toString() {
                return set.toString();
            }
        };
    }
}
