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
package com.intrepid.launcher;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.ApplicationListener;
import com.intrepid.game.GamePackages;
import com.intrepid.game.BeyondTimeGame;
import com.intrepid.nicge.kernel.game.GameAdapter;
import com.intrepid.nicge.kernel.game.GameBoot;
import com.intrepid.nicge.kernel.game.GameConfigurationBuilder;

public class GameDesktopLauncher extends AbstractLauncher< GameBoot >
{
    public GameDesktopLauncher( GameBoot boot )
    {
        super( boot );
    }

    @Override
    protected ApplicationListener createAdapter( GameBoot boot )
    {
        return new GameAdapter( boot );
    }

    public static void main( String[] arg )
    {
        Set< String > packages = new HashSet<>();
        for( GamePackages gp : GamePackages.values() )
        {
            packages.add( gp.getPack() );
        }

        GameConfigurationBuilder gcb = new GameConfigurationBuilder();
        //gcb.setDynamicDependencyPack( );
        gcb.setMouseLockedAndInvisible( false );
        gcb.setTitle( "Titulo de Teste" );
        gcb.setFullscreenMode( false );
        gcb.setResisable( true );

//		int windowResolutionWidth = 1920;
//		int windowResolutionHeight = 1080;

		int windowResolutionWidth = 1280;
		int windowResolutionHeight = 720;

//        int windowResolutionWidth = 480;
//        int windowResolutionHeight = 270;

        gcb.setWindowResolutionWidth( windowResolutionWidth );
        gcb.setWindowResolutionHeight( windowResolutionHeight );

		int nrscl = 3;
//        int nrscl = 1;

        gcb.setNativeResolutionWidth( windowResolutionWidth / nrscl ); // by 4 = 480
        gcb.setNativeResolutionHeight( windowResolutionHeight / nrscl ); // by 4 = 270

        gcb.setBitsPerPixel( 24 );
        gcb.setFPS( 120 );
        gcb.setMMSA( 2 );
        gcb.setVSync( false );
        gcb.setClassLoadersPackages( packages );

        GameBoot boot = new BeyondTimeGame( gcb );
        GameDesktopLauncher launcher = new GameDesktopLauncher( boot );
        launcher.run();
    }
}