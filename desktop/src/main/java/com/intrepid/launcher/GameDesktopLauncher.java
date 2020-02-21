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
        gcb.setMouseLockedAndInvisible( false );
        gcb.setTitle( "Titulo de Teste" );
        boolean fullScreen = true;
        gcb.setFullscreenMode( fullScreen );
        gcb.setResisable( false );

//        int nativeResolutionWidth = 1920;
//        int nativeResolutionHeight = 1080;
        int nativeResolutionWidth = 1280;
        int nativeResolutionHeight = 720;
        gcb.setNativeResolutionWidth( nativeResolutionWidth );
        gcb.setNativeResolutionHeight( nativeResolutionHeight );

        final float ratio;
        if( fullScreen )
        {
            ratio = 1.5f;
        }
        else
        {
            ratio = 1.0f;
        }
        gcb.setWindowResolutionWidth( (int) (nativeResolutionWidth * ratio ) );
        gcb.setWindowResolutionHeight( (int) ( nativeResolutionHeight * ratio ) );

        gcb.setBitsPerPixel( 24 );
        gcb.setFPS( 60 );
        gcb.setMMSA( 2 );
        gcb.setVSync( false );
        gcb.setClassLoadersPackages( packages );

        GameBoot boot = new BeyondTimeGame( gcb );
        GameDesktopLauncher launcher = new GameDesktopLauncher( boot );
        launcher.run();
    }
}