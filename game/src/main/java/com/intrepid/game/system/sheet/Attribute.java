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
 * all community without problems.
 */
package com.intrepid.game.system.sheet;

import java.util.LinkedList;
import java.util.List;

import com.intrepid.nicge.theater.Updatable;

public class Attribute implements Updatable
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private final String name;
    private int primaryValue;
    private int buffedValue;
    private List< Buff > buffs;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    protected Attribute( String name )
    {
        this.name = name;
        this.primaryValue = 0;
        this.buffs = new LinkedList<>();
    }

    // ****************************************************************************************
    // Factories
    // ****************************************************************************************
    public static Attribute create( Attributes definition )
    {
        Attribute o = new Attribute( definition.getName() );
        return o;
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    @Override
    public void update()
    {
        cleanupExpiredBuffs();
        updateBuffValue();
    }

    private void updateBuffValue()
    {
        int fullBuff = 0;
        for( Buff buff : buffs )
        {
            fullBuff += buff.getValue();
        }
        buffedValue = primaryValue + fullBuff;
    }

    public void cleanupExpiredBuffs()
    {
        // all buffs will be updated on the same poll
        List< Buff > expiredBuffs = new LinkedList<>();
        buffs.forEach( ( buff ) -> {
            if( buff.isExpired() )
            {
                expiredBuffs.add( buff );
            }
        } );

        expiredBuffs.forEach( ( buff ) -> {
            buffs.remove( buff );
        } );
    }

    public void affectedBy( Buff buff )
    {
        buffs.add( buff );
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public String getName()
    {
        return name;
    }

    public void setPrimaryValue( int value )
    {
        this.primaryValue = value;
        this.buffedValue = value;
    }

    public int getPrimaryValue()
    {
        return primaryValue;
    }

    public int getBuffedValue()
    {
        return buffedValue;
    }

    public int getValue()
    {
        return buffedValue;
    }

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();

        sb.append( name ).append( ": " ).append( primaryValue ).append( "( " ).append( buffedValue ).append( " )" );

        return sb.toString();
    }
}
