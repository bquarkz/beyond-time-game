/*
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
package com.intrepid.game;

import java.util.Set;

import com.intrepid.game.curtains.AllCurtains;
import com.intrepid.nicge.content.IResource;
import com.intrepid.nicge.kernel.game.GameBoot;
import com.intrepid.nicge.kernel.game.GameConfiguration;
import com.intrepid.nicge.theater.curtain.Curtain;
import com.intrepid.nicge.theater.scene.IScene;
import com.intrepid.nicge.utils.fsmachine.IFSMachineDefinition;
import com.intrepid.nicge.utils.logger.Log;

public class BeyondTimeGame
        implements GameBoot
{
    private GameConfiguration gameConfiguration;

    public BeyondTimeGame( GameConfiguration gameConfiguration )
    {
        this.gameConfiguration = gameConfiguration;
    }

    @Override
    public IFSMachineDefinition< IScene > instantiation()
    {
        Log.from( BeyondTimeGame.class ).info( "scenes instantiation" );
        return null;
    }

    @Override
    public void loader( Set< IResource< ? > > resources )
    {
        Log.from( BeyondTimeGame.class ).info( "loading assets" );
        for( Curtain curtain : AllCurtains.values() )
        {
            curtain.injectResourcesOn( resources );
        }
    }

    @Override
    public void initialization()
    {
        Log.from( BeyondTimeGame.class ).info( "objects initializing" );
    }

    @Override
    public GameConfiguration getConfig()
    {
        return gameConfiguration;
    }

}
