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

public class Queue< T > implements IContainer< T >
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private com.badlogic.gdx.utils.Queue< T > queue;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public Queue()
    {
        queue = new com.badlogic.gdx.utils.Queue<>();
    }

    public Queue( int size )
    {
        queue = new com.badlogic.gdx.utils.Queue<>( size );
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    @Override
    public T get()
    {
        return queue.last();
    }

    @Override
    public T pop()
    {
        return queue.removeLast();
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
