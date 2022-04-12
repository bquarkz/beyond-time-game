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

public interface ReadOnlyList< T > extends Iterable< T >
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

    Optional<T> randomPick(Random random);

    Stream<T> stream();

    boolean isEmpty();

    // ****************************************************************************************
    // Default methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Static Methods
    // ****************************************************************************************
    @SuppressWarnings("unchecked")
    static <T> ReadOnlyList<T> empty() {
        return ReadOnlyList.of();
    }

    @SuppressWarnings("unchecked")
    static <T> ReadOnlyList<T> of(T... ts) {
        return ReadOnlyList.wrap(Stream.of(ts).collect(Collectors.toList()));
    }

    static < T > ReadOnlyList< T > wrap(final List< T > list)
    {
        return new ReadOnlyList< T >()
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
            public Optional<T> randomPick(Random random) {
                int size = size();
                if (size == 0) return Optional.empty();
                final int index = random.nextInt(size);
                return Optional.ofNullable(get(index));
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

            @Override
            public String toString() {
                return list.toString();
            }

            @Override
            public Stream<T> stream() {
                return list.stream();
            }

            @Override
            public boolean isEmpty() {
                return list.isEmpty();
            }
        };
    }
}
