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

import com.badlogic.gdx.math.Vector2;
import com.intrepid.nicge.kernel.game.Game;

abstract class AbstractEntity implements Entity
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private final long id;
    private final Vector2 position;
    private float elapsedTime;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public AbstractEntity( Long id )
    {
        this.id = id.longValue();
        this.position = new Vector2();
        this.elapsedTime = 0.0f;
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    @Override
    public void update()
    {
        elapsedTime += Game.time.getRawDeltaTime_ns();
    }

    @Override
    public Vector2 getPosition()
    {
        return position;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits( elapsedTime );
        result = prime * result + (int)id;
        result = prime * result + ( ( position == null ) ? 0 : position.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object that )
    {
		if( this == that )
		{
			return true;
		}
		if( that == null )
		{
			return false;
		}
		if( this.getClass() != that.getClass() )
		{
			return false;
		}
        return this.getId() == ( (Entity)that ).getId();
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public void setPosition( Vector2 position )
    {
        setPosition( position.x, position.y );
    }

    public void setPosition(
            float x,
            float y )
    {
        position.x = x;
        position.y = y;
    }

    protected float getElapsedTime()
    {
        return elapsedTime;
    }

    @Override
    public long getId()
    {
        return id;
    }

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
