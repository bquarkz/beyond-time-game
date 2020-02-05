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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.intrepid.nicge.utils.containers.Stack;
import com.intrepid.nicge.utils.pool.exceptions.EPoolEmpty;

public final class Pool< T extends Poolable >
        implements Iterable< PoolableWrapper< T > >/*, Iterator< T > */
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private Stack< PoolableWrapper< Poolable > > free;
    private Class< T > poolalbleClass;
    //	private Map< Integer, PoolableWrapper< Poolable > > used;
//	private boolean needToUpdate;
    private Set< PoolableWrapper< T > > setOfUsedValues;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public Pool(
            Class< T > poolalbleClass,
            int size )
    {
        this.poolalbleClass = poolalbleClass;
        this.free = new Stack<>( size );
//		this.used = new HashMap<>();
        this.setOfUsedValues = new HashSet<>();
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    public void instancePool()
    {
        instancePool( null );
    }

    public void instancePool( Object[] arguments )
    {
        if( arguments == null )
        {
            instancePoolDefault();
        }
        else
        {
            instancePoolByArguments( arguments );
        }
    }

    private void instancePoolDefault()
    {
        for( int i = 0; i < free.size(); i++ )
        {
            Poolable poolable;
            try
            {
                poolable = poolalbleClass.newInstance();
            }
            catch( InstantiationException | IllegalAccessException e )
            {
                throw new RuntimeException( e );
            }
            free.push( new PoolableWrapper< Poolable >( i, poolable, this ) );
        }
    }

    private void instancePoolByArguments( Object[] arguments )
    {
        Class< ? >[] argumentClasses = new Class< ? >[ arguments.length ];
        for( int i = 0; i < arguments.length; i++ )
        {
            argumentClasses[ i ] = arguments[ i ].getClass();
        }

        for( int i = 0; i < free.size(); i++ )
        {
            Poolable poolable;
            try
            {
                Constructor< T > constructor = poolalbleClass.getConstructor( argumentClasses );
                poolable = constructor.newInstance( arguments );
            }
            catch( NoSuchMethodException |
                    SecurityException |
                    InstantiationException |
                    IllegalAccessException |
                    IllegalArgumentException |
                    InvocationTargetException e )
            {
                throw new RuntimeException( "Problems to instanciate the pool of " +
                        poolalbleClass.getSimpleName() );
            }

            free.push( new PoolableWrapper< Poolable >( i, poolable, this ) );
        }
    }

    @SuppressWarnings( "unchecked" )
    public PoolableWrapper< T > create() throws EPoolEmpty
    {
        if( free.isEmpty() )
        {
            throw new EPoolEmpty();
        }

        PoolableWrapper< T > poolable = (PoolableWrapper< T >)free.pop();
//		used.put( poolable.getID(), poolable );
        setOfUsedValues.add( poolable );

        return poolable;
    }

    @SuppressWarnings( "unchecked" )
    public void destroy( PoolableWrapper< T > poolable )
    {
        setOfUsedValues.remove( poolable.get() );
//		used.remove( poolable.getID() );
        free.push( (PoolableWrapper< Poolable >)poolable );
    }

    @SuppressWarnings( "unchecked" )
    public void destroyAll()
    {
//		for( PoolableWrapper<Poolable> poolable : used.values() ) {
        for( PoolableWrapper< T > poolable : setOfUsedValues )
        {
            free.push( (PoolableWrapper< Poolable >)poolable );
        }

//		PENSE
//		used.clear();
        setOfUsedValues.clear();
    }

    @Override
    public Iterator< PoolableWrapper< T > > iterator()
    {
        return setOfUsedValues.iterator();
    }

//	@Override
//	public boolean hasNext() {
//		return this.iterator().hasNext();
//	}
//	
//	@Override
//	public T next() {
//		return this.iterator().next().get();
//	}

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public int size()
    {
        return this.free.size();
    }


    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
