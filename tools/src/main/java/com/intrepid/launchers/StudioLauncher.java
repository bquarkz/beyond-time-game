package com.intrepid.launchers;

import com.badlogic.gdx.ApplicationListener;
import com.intrepid.nicge.kernel.game.GameAdapter;
import com.intrepid.nicge.kernel.game.GameBoot;
import com.intrepid.nicge.kernel.game.GameConfigurationBuilder;
import com.intrepid.studio.BeyondTimeStudio;

public class StudioLauncher extends AbstractLauncher< GameBoot >
{
    public StudioLauncher( GameBoot boot )
    {
        super( boot );
    }

    public static void main( String[] args )
    {
        int width = 1280;
        int height = 720;

        GameConfigurationBuilder gcb = new GameConfigurationBuilder();
        gcb.setMouseLockedAndInvisible( false );
        gcb.setTitle( "Game Studio" );
        gcb.setFullscreenMode( false );
        gcb.setResisable( false );
        gcb.setWindowResolutionWidth( width );
        gcb.setWindowResolutionHeight( height );
        gcb.setNativeResolutionWidth( width );
        gcb.setNativeResolutionHeight( height );
        gcb.setBitsPerPixel( 24 );
        gcb.setFPS( 30 );
        gcb.setMMSA( 8 );
        gcb.setVSync( true );
        gcb.setClassLoadersPackages( null );

        BeyondTimeStudio studio = new BeyondTimeStudio( gcb );
        StudioLauncher launcher = new StudioLauncher( studio );
        launcher.run();
    }

    @Override
    protected ApplicationListener createAdapter( GameBoot boot )
    {
        return new GameAdapter( boot );
    }
}
