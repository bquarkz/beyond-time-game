/**
 * Copyleft (C) 2016  Constantino, Nilton Rogerio <niltonrc@gmail.com>
 *
 * @author "Nilton R Constantino"
 * aKa bQUARKz <niltonrc@gmail, bquarkz@gmail.com>
 *
 * Everything about the respective software copyright can be found in the
 * "LICENSE" file included in the project source tree.
 *
 * The code was written based on study principles and can be enjoyed for
 * all community without problems.
 */
package com.intrepid.game.system.sheet;

import java.util.Random;

import com.intrepid.nicge.theater.Updatable;

public class Sheet implements Updatable
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private final AttributeManager attributeManager;
    private final String name;
    private final MyStats myStats;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    protected Sheet(
            String name,
            int reflex,
            int speed,
            int luck
            )
    {
        this.name = name;
        this.attributeManager = AttributeManager.create( reflex, speed, luck );
        this.myStats = new MyStats( attributeManager );        
    }

    // ****************************************************************************************
    // Factories
    // ****************************************************************************************
    public static Sheet createWithRandomAttributes(
            Random random,
            String name
            )
    {
        int reflex = createRandomAttributeValue( random );
        int speed = createRandomAttributeValue( random );
        int luck = createRandomAttributeValue( random );
        
        Sheet o = new Sheet( name, reflex, speed, luck );
        return o;
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    private static int createRandomAttributeValue( Random random )
    {
        return random.nextInt( 10 );
    }

    @Override
    public void update()
    {
        attributeManager.update();
        myStats.calculate( attributeManager );
    }
    
    public void apply( Buff buff )
    {
        attributeManager.apply( buff );
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public int getPrimaryValue( Attributes attribute )
    {
        return attributeManager.get( attribute ).getPrimaryValue();
    }
    
    public int getReflex()
    {
        return attributeManager.getReflex().getValue();
    }
    
    public int getSpeed()
    {
        return attributeManager.getSpeed().getValue();
    }

    public int getLuck()
    {
        return attributeManager.getLuck().getValue();
    }
    
    public int getInitiative()
    {
        return myStats.initiative;
    }

    public int getAccuracy()
    {
        return myStats.accuracy;
    }
    
    public String getName()
    {
        return name;
    }

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append( "{ name: " ).append( name )
            .append( ", " ).append( attributeManager )
            .append( ", " ).append( myStats )
            .append( " }" );

        return sb.toString();
    }

    private static class MyStats
    {
        private int initiative;
        private int accuracy;

        public MyStats( AttributeManager attributeManager )
        {
            calculate( attributeManager );
        }

        public void calculate( AttributeManager attributeManager )
        {
            initiative = Stats.INITIATIVE.calculate( attributeManager );
            accuracy = Stats.ACCURACY.calculate( attributeManager );
        }
        
        @Override
        public String toString()
        {
            final StringBuilder sb = new StringBuilder( "{ " );
            
            sb.append( Stats.INITIATIVE.getName() ).append( ": " ).append( initiative ).append( ", " );
            sb.append( Stats.ACCURACY.getName() ).append( ": " ).append( accuracy ).append( ", " );
            
            sb.append( " }" );
            
            return sb.toString();
        }
    }
}