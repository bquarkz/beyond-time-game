package com.intrepid.launchers;

import com.badlogic.gdx.ApplicationListener;
import com.intrepid.launcher.AbstractLauncher;
import com.intrepid.nicge.kernel.Boot;
import com.intrepid.nicge.kernel.game.GameConfiguration;
import com.intrepid.nicge.kernel.game.GameConfigurationBuilder;
import com.intrepid.studio.kernel.StudioAdapter;
import com.intrepid.studio.kernel.StudioBoot;

public class StudioLauncher extends AbstractLauncher< Boot< GameConfiguration > > {
	
	public StudioLauncher( Boot< GameConfiguration > boot ) {
		super(boot);
	}

	public static void main(String[] args) {
		int width = 1280 * 3 / 4;
		int height = 720 * 3 / 4;
		
		GameConfigurationBuilder gcb = new GameConfigurationBuilder();
		gcb.setMouseLockedAndInvisible( false );
		gcb.setTitle( "Sparking Heaven - Game Studio" );
		gcb.setFullscreenMode( false );
		gcb.setResisable( false );
		gcb.setWindowResolutionWidth( width );
		gcb.setWindowResolutionHeight( height );
		gcb.setNativeResolutionWidth( width );
		gcb.setNativeResolutionHeight( height );
		gcb.setBitsPerPixel( 24 );
		gcb.setFPS( 30 );
		gcb.setMMSA( 0 );
		gcb.setVSync( false );
		gcb.setClassLoadersPackages( null );

		StudioBoot boot = new StudioBoot( gcb );
		StudioLauncher launcher = new StudioLauncher( boot );
		launcher.run();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected ApplicationListener createAdapter( Boot boot ) {
	    return new StudioAdapter( (Boot< GameConfiguration >) boot );
	}
}
