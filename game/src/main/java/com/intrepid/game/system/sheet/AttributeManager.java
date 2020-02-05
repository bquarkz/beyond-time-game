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

import java.util.HashMap;
import java.util.Map;

import com.intrepid.nicge.theater.Updatable;

public class AttributeManager implements Updatable
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************


    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private Map< Attributes, Attribute > map;
    private final Attribute reflex;
    private final Attribute speed;
    private final Attribute luck;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    protected AttributeManager()
    {
        map = new HashMap<>();
        reflex = createAttribute( Attributes.REFLEX );
        speed = createAttribute( Attributes.SPEED );
        luck = createAttribute( Attributes.LUCK );
    }

    // ****************************************************************************************
    // Factories
    // ****************************************************************************************
    public static AttributeManager create(
            int reflex,
            int speed,
            int luck
    )
    {
        AttributeManager o = new AttributeManager();

        o.reflex.setPrimaryValue( reflex );
        o.speed.setPrimaryValue( speed );
        o.luck.setPrimaryValue( luck );

        return o;
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    private Attribute createAttribute( Attributes definition )
    {
        Attribute attribute = Attribute.create( definition );
        map.put( definition, attribute );
        return attribute;
    }

    @Override
    public void update()
    {
        map.values().forEach( ( attribute ) -> {
            attribute.update();
        } );
    }

    public void apply( Buff buff )
    {
        Attribute attribute = map.get( buff.affects() );
        attribute.affectedBy( buff );
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public Attribute get( Attributes definition )
    {
        return map.get( definition );
    }

    public Attribute getReflex()
    {
        return reflex;
    }

    public Attribute getSpeed()
    {
        return speed;
    }

    public Attribute getLuck()
    {
        return luck;
    }

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder( "{ " );

        map.values().forEach( ( attribute ) -> {
            sb.append( attribute ).append( ", " );
        } );

        sb.append( " }" );

        return sb.toString();
    }
}
