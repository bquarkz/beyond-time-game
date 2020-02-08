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
package com.intrepid.nicge.utils.pool;

import com.intrepid.nicge.utils.logger.Log;
import com.intrepid.nicge.utils.pool.exceptions.EPoolableNotFoundInPool;

public final class PoolableWrapper< T extends IPoolable >
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private int id;
    private T poolable;
    @SuppressWarnings( "rawtypes" )
    private Pool master;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public PoolableWrapper(
            int id,
            T poolable,
            @SuppressWarnings( "rawtypes" ) Pool master )
    {
        this.id = id;
        this.poolable = poolable;
        this.master = master;
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    @SuppressWarnings( "unchecked" )
    public void release()
    {
        try
        {
            this.master.destroy( this );
        }
        catch( EPoolableNotFoundInPool e )
        {
            Log.from( this ).message( "IMPOSSIBLE THINGS" );
        }
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public T get()
    {
        return poolable;
    }

    public int getID()
    {
        return this.id;
    }

    @Override
    public int hashCode()
    {
        return this.id;
    }

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}