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
package com.intrepid.nicge.utils.containers;

import com.badlogic.gdx.utils.Queue;

public class Stack< T > implements IContainer< T >
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private Queue< T > queue;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public Stack()
    {
        queue = new Queue<>();
    }

    public Stack( int size )
    {
        queue = new Queue<>( size );
    }


    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    @Override
    public T get()
    {
        return queue.first();
    }

    @Override
    public T pop()
    {
        return queue.removeFirst();
    }

    @Override
    public void push( T element )
    {
        queue.addFirst( element );
    }

    @Override
    public int size()
    {
        return queue.size;
    }

    @Override
    public boolean isEmpty()
    {
        return queue.size == 0;
    }

    @Override
    public void clear()
    {
        queue.clear();
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ***************************************************************************************
}
