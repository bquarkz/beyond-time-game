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

import java.util.List;

import com.intrepid.nicge.theater.Displayable;
import com.intrepid.nicge.theater.Updatable;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;

public class EntityManager implements Updatable, Displayable
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private final EntityCollection entities;
    private List< Entity > entityMergedList;
    public final EntityFactory factory;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public EntityManager()
    {
        this.entities = new EntityCollection();
        this.factory = new EntityFactory( entities );
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    @Override
    public void update()
    {
        for( Entity entity : entities.getNotMovables() )
        {
            entity.update();
        }

        for( Entity entity : entities.getMovables() )
        {
            entity.update();
        }

        entities.sortMovableEntities();
        entityMergedList = entities.mergeEntities();
    }

    @Override
    public void display( GraphicsBatch batch )
    {
        for( Entity entity : entityMergedList )
        {
            entity.display( batch );
        }
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
//	public Entity get( long id ) {
//		return entities.get( id );
//	}

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
