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
package com.intrepid.nicge.kernel.game;

import com.intrepid.nicge.kernel.AbstractAdapter;
import com.intrepid.nicge.kernel.Application;
import com.intrepid.nicge.kernel.Boot;

public final class GameAdapter extends AbstractAdapter
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public GameAdapter( GameBoot boot )
    {
        super( boot );
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    @Override
    @SuppressWarnings( "rawtypes" )
    protected < T extends Boot > Application getApplication( T boot )
    {
        return new Game( (GameBoot)boot );
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ***************************************************************************************
}