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
package com.intrepid.nicge.theater.curtain;

import com.intrepid.nicge.utils.graphics.GraphicsBatch;

public abstract class AbstractCurtain implements Curtain
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private CurtainCondition status;
    private CurtainCondition goal;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    protected abstract void openedUpdate();

    protected abstract void openingUpdate();

    protected abstract void closedUpdate();

    protected abstract void closingUpdate();

    protected abstract void openedDisplay( GraphicsBatch batch );

    protected abstract void openingDisplay( GraphicsBatch batch );

    protected abstract void closedDisplay( GraphicsBatch batch );

    protected abstract void closingDisplay( GraphicsBatch batch );

    protected void _update()
    {
    }

    @Override
    public final void update()
    {
        switch( status )
        {
            case OPENED:
            {
                openedUpdate();
            }
            break;
            case OPENING:
            {
                openingUpdate();
            }
            break;
            case CLOSED:
            {
                closedUpdate();
            }
            break;
            case CLOSING:
            {
                closingUpdate();
            }
            break;
        }

        _update();
    }

    protected void _display( GraphicsBatch batch )
    {
    }

    @Override
    public final void display( GraphicsBatch batch )
    {
        _display( batch );

        switch( status )
        {
            case OPENED:
            {
                openedDisplay( batch );
            }
            break;
            case OPENING:
            {
                openingDisplay( batch );
            }
            break;
            case CLOSED:
            {
                closedDisplay( batch );
            }
            break;
            case CLOSING:
            {
                closingDisplay( batch );
            }
            break;
        }
    }

    @Override
    public final void closeCommand()
    {
        if( status == CurtainCondition.CLOSED )
        {
            return;
        }
        status = CurtainCondition.CLOSING;
        goal = CurtainCondition.CLOSED;
    }

    @Override
    public final void openCommand()
    {
        if( status == CurtainCondition.OPENED )
        {
            return;
        }
        status = CurtainCondition.OPENING;
        goal = CurtainCondition.OPENED;
    }

    @Override
    public final boolean isTransitionFinished()
    {
        return ( status == goal );
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    @Override
    public final CurtainCondition getStatus()
    {
        return status;
    }

    protected void setStatus( CurtainCondition status )
    {
        this.status = status;
    }

    protected CurtainCondition getGoal()
    {
        return goal;
    }

    protected void setGoal( CurtainCondition goal )
    {
        this.goal = goal;
    }

    // ****************************************************************************************
    // Patterns
    // ***************************************************************************************
}
