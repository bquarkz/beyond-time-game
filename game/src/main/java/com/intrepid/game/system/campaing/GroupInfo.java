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
package com.intrepid.game.system.campaing;

import java.util.ArrayList;
import java.util.List;

import com.intrepid.game.system.sheet.Sheet;
import com.intrepid.nicge.utils.readonlycollections.IReadOnlyList;

public class GroupInfo
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************
    public static final int MAX_GROUP_SIZE = 9;

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private final List< Sheet > sheets;
    private final IReadOnlyList< Sheet > readOnlySheets;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    protected GroupInfo()
    {
        sheets = new ArrayList<>( MAX_GROUP_SIZE );
        readOnlySheets = IReadOnlyList.wrap( sheets );
    }

    // ****************************************************************************************
    // Factories
    // ****************************************************************************************
    public static GroupInfo create()
    {
        GroupInfo o = new GroupInfo();
        return o;
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    public boolean add( Sheet sheet )
    {
        if( sheets.size() < MAX_GROUP_SIZE )
        {
            sheets.add( sheet );
            return true;
        }

        return false;
    }

    public boolean isFull()
    {
        return sheets.size() >= MAX_GROUP_SIZE;
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public IReadOnlyList< Sheet > getSheets()
    {
        return readOnlySheets;
    }

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        for( int i = 0; i < sheets.size(); i++ )
        {
            Sheet sheet = sheets.get( i );
            sb.append( i ).append( ". " ).append( sheet ).append( "\n" );
        }

        return sb.toString();
    }
}
