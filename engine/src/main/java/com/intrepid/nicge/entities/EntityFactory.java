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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class EntityFactory
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private final EntityCollection entities;
    private static long indexID = 0;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public EntityFactory( EntityCollection entities )
    {
        this.entities = entities;
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    private < T extends AbstractEntity > T createEntity(
            Class< T > clazz,
            String entityType )
    {
        Constructor< T > constructor = null;
        try
        {
            constructor = clazz.getConstructor( Long.class );
        }
        catch( NoSuchMethodException | SecurityException e )
        {
            throw new RuntimeException( "Default constructor (Long id) for " + entityType + " not found." );
        }

        T entity = null;
        try
        {
            entity = constructor.newInstance( indexID++ );
        }
        catch( InstantiationException | IllegalAccessException |
                IllegalArgumentException | InvocationTargetException e )
        {
            throw new RuntimeException( "Problems to instantiate " + entityType + "." );
        }

        return entity;
    }

    public < T extends AbstractMovableEntity > T createMovableEntity( Class< T > c )
    {
        T entity = createEntity( c, "Movable Entity" );
        entities.add( entity );
        return entity;
    }

    public < T extends AbstractNotMovableEntity > T createImmovableEntity( Class< T > c )
    {
        T entity = createEntity( c, "Immovable Entity" );
        entities.add( entity );
        return entity;
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
