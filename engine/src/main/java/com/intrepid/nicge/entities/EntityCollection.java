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
package com.intrepid.nicge.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class EntityCollection
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private final Map< Long, Entity > allEntities;
    private final List< Entity > notMovables;
    private final List< Entity > movables;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public EntityCollection()
    {
        allEntities = new HashMap<>();
        notMovables = new ArrayList<>();
        movables = new ArrayList<>();
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    public void add( AbstractNotMovableEntity entity )
    {
        notMovables.add( entity );
        allEntities.put( entity.getId(), entity );
    }

    public void add( AbstractMovableEntity entity )
    {
        movables.add( entity );
        allEntities.put( entity.getId(), entity );
    }

    public void sortImmovableEntities()
    {
        Collections.sort( notMovables );
    }

    public void sortMovableEntities()
    {
        Collections.sort( movables );
    }

    public List< Entity > mergeEntities()
    {
        Iterator< Entity > movableIterator = movables.iterator();
        Iterator< Entity > immovableIterator = notMovables.iterator();

        Entity movable = null;
        Entity notMovable = null;

		if( movableIterator.hasNext() )
		{
			movable = movableIterator.next();
		}
		else
		{
			movable = null;
		}

		if( immovableIterator.hasNext() )
		{
			notMovable = immovableIterator.next();
		}
		else
		{
			notMovable = null;
		}

        EntityComparator c = new EntityComparator();
        List< Entity > result = new ArrayList<>( movables.size() + notMovables.size() );
        while( movable != null && notMovable != null )
        {
            if( c.compare( movable, notMovable ) < 0 )
            {
                result.add( movable );

				if( movableIterator.hasNext() )
				{
					movable = movableIterator.next();
				}
				else
				{
					movable = null;
				}
            }
            else
            {
                result.add( notMovable );

				if( immovableIterator.hasNext() )
				{
					notMovable = immovableIterator.next();
				}
				else
				{
					notMovable = null;
				}
            }
        }

        if( movable != null )
        {
            do
            {
                result.add( movable );

				if( movableIterator.hasNext() )
				{
					movable = movableIterator.next();
				}
				else
				{
					movable = null;
				}
            } while( movable != null );
        }
        else if( notMovable != null )
        {
            do
            {
                result.add( notMovable );

				if( immovableIterator.hasNext() )
				{
					notMovable = immovableIterator.next();
				}
				else
				{
					notMovable = null;
				}
            } while( notMovable != null );
        }

        return result;
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public List< Entity > getMovables()
    {
        return movables;
    }

    public List< Entity > getNotMovables()
    {
        return notMovables;
    }

    public Entity get( long index )
    {
        return allEntities.get( index );
    }

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
    private class EntityComparator implements Comparator< Entity >
    {
        @Override
        public int compare(
                Entity e1,
                Entity e2 )
        {
            return e1.compareTo( e2 );
        }

    }

}
