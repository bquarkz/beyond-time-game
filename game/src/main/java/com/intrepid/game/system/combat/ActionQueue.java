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
package com.intrepid.game.system.combat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.intrepid.game.system.campaing.GroupInfo;
import com.intrepid.game.system.sheet.Sheet;
import com.intrepid.nicge.utils.containers.Stack;
import com.intrepid.nicge.utils.readonlycollections.IReadOnlyList;

public class ActionQueue
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************
    private static final ActionSort actionSort = new ActionSort();

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private final Stack< Sheet > actionSheets;
    private IReadOnlyList< ActionDetails > actionDetails;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    protected ActionQueue()
    {
        actionSheets = new Stack<>( GroupInfo.MAX_GROUP_SIZE * 2 );
    }

    // ****************************************************************************************
    // Factories
    // ****************************************************************************************
    public static ActionQueue create(
            IReadOnlyList< Sheet > players,
            IReadOnlyList< Sheet > enemies
    )
    {
        ActionQueue o = new ActionQueue();

        List< ActionDetails > actionDetails = new ArrayList<>( GroupInfo.MAX_GROUP_SIZE * 2 );
        for( Sheet sheet : players )
        {
            actionDetails.add( new ActionDetails( sheet ) );
        }
        for( Sheet sheet : enemies )
        {
            actionDetails.add( new ActionDetails( sheet ) );
        }
        o.actionDetails = IReadOnlyList.wrap( actionDetails );

        return o;
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    public Stack< Sheet > selectActionSheets()
    {
        // 1. sort action details
        actionDetails.sort( actionSort );

        //2. get the smaller value (the first one)
        int pointsToSpend = actionDetails.get( 0 ).pointsToSpend;

        //3. subtract values, find zeros and select actionable sheet
        actionDetails.forEach( ( ad ) -> {
            ad.turnFlow( pointsToSpend, actionSheets );
        } );

        return actionSheets;
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
    public static class ActionSort implements Comparator< ActionDetails >
    {
        @Override
        public int compare(
                ActionDetails ad1,
                ActionDetails ad2 )
        {
            if( ad1.getPointsToSpendToCurrentTurn() > ad2.getPointsToSpendToCurrentTurn() )
            {
                return 1;
            }
            else if( ad1.getPointsToSpendToCurrentTurn() < ad2.getPointsToSpendToCurrentTurn() )
            {
                return -1;
            }
            else
            {
                return 0;
            }
        }
    }

    public static class ActionDetails
    {
        private final Sheet sheet;
        private int pointsToSpend;
        private boolean actionCooldown;

        public ActionDetails( Sheet sheet )
        {
            this.sheet = sheet;
            this.pointsToSpend = sheet.getInitiative();
        }

        public void turnFlow(
                int actionPointsCost,
                Stack< Sheet > actionSheets
        )
        {
            if( actionCooldown )
            {
                actionCooldown = false;
                pointsToSpend = sheet.getInitiative();
                return;
            }

            pointsToSpend -= actionPointsCost;
            if( pointsToSpend <= 0 )
            {
                actionSheets.push( getSheet() );
                actionCooldown = true;
            }
        }

        public int getPointsToSpendToCurrentTurn()
        {
            return pointsToSpend;
        }

        public Sheet getSheet()
        {
            return sheet;
        }
    }
}
