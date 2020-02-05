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

import com.intrepid.game.system.campaing.GroupInfo;
import com.intrepid.game.system.sheet.Sheet;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.utils.containers.Stack;

public class CombatManager
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private GroupInfo players;
    private GroupInfo enemies;
    private ActionQueue actionQueue;

    private float elapsedTime;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    protected CombatManager(
            GroupInfo players,
            GroupInfo enemies
    )
    {
        this.players = players;
        this.enemies = enemies;
        this.actionQueue = ActionQueue.create( players.getSheets(), enemies.getSheets() );
    }

    // ****************************************************************************************
    // Factories
    // ****************************************************************************************
    public static CombatManager create(
            GroupInfo players,
            GroupInfo enemies
    )
    {
        CombatManager o = new CombatManager( players, enemies );
        return o;
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    private void execTurnUpdate()
    {
        Stack< Sheet > actionSheets = actionQueue.selectActionSheets();
        while( !actionSheets.isEmpty() )
        {
            Sheet sheet = actionSheets.pop();
            //System.out.println( "ACTION: " + sheet );
        }
    }

    public void setupCombat()
    {
        System.out.println( "PLAYERS:" );
        System.out.println( players );

        System.out.println( "ENEMIES:" );
        System.out.println( enemies );
    }

    public void update()
    {
        elapsedTime += Game.time.getDeltaTime();

        if( elapsedTime >= 0.35f )
        {
            elapsedTime = 0.0f;
            execTurnUpdate();
        }
    }

    public void display()
    {
    }

    public boolean isFinished()
    {
        return false;
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
