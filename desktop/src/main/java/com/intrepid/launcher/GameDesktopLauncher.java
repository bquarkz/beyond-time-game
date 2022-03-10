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

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        final Set< String > packages = Stream
                .of( GamePackages.values() )
                .map( GamePackages::getPackageRoot )
                .collect( Collectors.toSet() );

        final GameConfigurationBuilder gcb = new GameConfigurationBuilder();
        gcb.setMouseLockedAndInvisible( false );
        gcb.setTitle( "Behind Time - Game Test" );
        gcb.setFullscreenMode( false );
        gcb.setResisable( false );

        int nativeResolutionWidth = 1600;
        int nativeResolutionHeight = 900;
        gcb.setNativeResolutionWidth( nativeResolutionWidth );
        gcb.setNativeResolutionHeight( nativeResolutionHeight );

        final float ratio = 1.0f;
        gcb.setWindowResolutionWidth( (int) (nativeResolutionWidth * ratio ) );
        gcb.setWindowResolutionHeight( (int) ( nativeResolutionHeight * ratio ) );

        gcb.setBitsPerPixel( 24 );
        gcb.setFPS( 60 );
        gcb.setMMSA( 16 );
        gcb.setVSync( true );
        gcb.setClassLoadersPackages( packages );

        GameBoot boot = new BeyondTimeGame( gcb );
        GameDesktopLauncher launcher = new GameDesktopLauncher( boot );
        launcher.run();
    }
}