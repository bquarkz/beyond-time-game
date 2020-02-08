/**
 * Copyleft (C) 2016  Constantino, Nilton Rogerio <niltonrc@gmail.com>
 *
 * @author "Nilton R Constantino"
 * aKa bQUARKz <niltonrc@gmail, bquarkz@gmail.com>
 *
 * Everything about the respective software copyright can be found in the
 * "LICENSE" file included in the project source tree.
 *
 * The code was written based on study principles and can be enjoyed for
 * all comunity without problems.
 */
package com.intrepid.studio.kernel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.intrepid.nicge.content.AssetManager;
import com.intrepid.nicge.content.IResource;
import com.intrepid.nicge.kernel.Application;
import com.intrepid.nicge.kernel.Boot;
import com.intrepid.nicge.kernel.ScreenInfo;
import com.intrepid.nicge.kernel.game.GameConfiguration;
import com.intrepid.nicge.utils.IProcessExecution;
import com.intrepid.nicge.utils.audio.AudioControl;
import com.intrepid.nicge.utils.audio.IMusicBoard;
import com.intrepid.nicge.utils.graphics.TextureWorks;
import com.intrepid.nicge.utils.reflections.Reflection;
import com.intrepid.nicge.utils.threads.ThreadExecutor;
import com.intrepid.nicge.utils.timer.TimerManager;

public final class Studio implements Application {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private static Boot< GameConfiguration > boot;
	private static GameConfiguration config;
	private static double elapsedTime;
	private static TextureWorks textureWorks;
	private static double loopTime;
	private static double allProcessTime;
	private static AudioControl audioControl;
	private static Reflection reflectz;
	private static AssetManager assetManager;
	private static TimerManager timerManager;
	private static ScreenInfo info;
	
	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public Studio( Boot< GameConfiguration > boot ) {
		Studio.boot = boot;
		Studio.config = boot.getConfig();
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	protected void throwUpDebugInfo() {
		double control = usage.getProcessExecution( StudioProcess.CONTROL );
		double audio = usage.getProcessExecution( StudioProcess.AUDIO );
		
		Studio.info.addSystemInfo( "Capture Control: " + (int)control );
		Studio.info.addSystemInfo( "Audio Update:    " + (int)audio );
		
		Studio.info.addSystemInfo( "FPS: " + Studio.graphics.getFPS() );
		Studio.info.throwUp();
	}
	
	@Override
	public void boot() {
		Gdx.input.setCursorCatched( config.isMouseLockedAndInvisible() );
		
		// Game instanciation and some configuration.
	   	Studio.textureWorks = new TextureWorks();
	   	Studio.audioControl = new AudioControl();
	   	Studio.assetManager = new AssetManager();
	   	Studio.timerManager = new TimerManager();
	   	Studio.info = new ScreenInfo( config.getWindowResolutionWidth(), config.getWindowResolutionHeight(), true );
	   	
	   	// Configure Reflections   	
	   	Set< String > classLoadersPackages = Studio.config.getClassLoadersPackages();
	   	Studio.reflectz = new Reflection( classLoadersPackages );
	   	
		// Game load assets
		// Engine load needs to run before
		Set< IResource< ? > > resources = new HashSet<>();
	   	Studio.boot.loader( resources );
   		Studio.assetManager.load( resources );
	   	while( !Studio.assetManager.update() );
	   	
	   	// Inicialization all game variable and its stuff 
		Studio.elapsedTime = Gdx.graphics.getDeltaTime();
		Studio.loopTime = 0;
		Studio.allProcessTime = 0;
		Studio.boot.initialization();
	}

	@Override
	public void loop() {
		Studio.graphics.clear_screen();
		
		Studio.elapsedTime += (double) Studio.time.getDeltaTime();

		double procGeral_i = Studio.time.getSystemMicroTime();
		for( StudioProcess process : StudioProcess.values() ) {
			process.execute();
		}
		allProcessTime = Studio.time.getSystemMicroTime() - procGeral_i;
		
		// calcular quanto tempo resta para o tempo previsto para o FPS atingido no ultimo update
		double soundPlayLeftOverTime = ( Studio.time.getDeltaTime() * 1000 ) - allProcessTime;
		// aplicar o atenuador de 90%, por seguranca.
		soundPlayLeftOverTime *= 0.9f;
		// descarrega a fila de sons ate o tempo faltante;
		Studio.audioControl.getSoundManager().execute( soundPlayLeftOverTime );
		
		double procGeral_f = Studio.time.getSystemMicroTime();
		
		loopTime = procGeral_f - procGeral_i;
		
		this.throwUpDebugInfo();
	}
	
	@Override
	public void dispose () {
		Studio.textureWorks.dispose();
		Studio.assetManager.dispose();
		
		new ThreadExecutor().shutdown();
	}

	@Override
	public void resize( int width, int height ) {
		if( Studio.config.isResizable() ) {
			Gdx.graphics.setWindowedMode( width, height );
		}
	}

	@Override
	public void pause() {
		// do nothing
	}

	@Override
	public void resume() {
		// do nothing
	}
	
	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************
	public static final class util {
		public static Reflection getReflections() {
			return Studio.reflectz;
		}
		
		public static void addDebugMessage( String process, float timeProcess, List< String > messages ) {
			Studio.info.addProcessMessages( process, timeProcess, messages );
		}
		
		public static void addDebugMessage( String process, List< String > messages ) {
			Studio.info.addProcessMessages( process, messages );
		}
		
		public static void addDebugMessage( String process, String... messages ) {
			List< String > msg = new ArrayList<String>();
			for( String s : messages ) {
				for( String ss : s.split( "\n" ) )
					msg.add( ss );
			}
			Studio.info.addProcessMessages( process, msg );
		}
	}
	
	public static final class common {
		public static GameConfiguration getConfiguration() {
			return Studio.config;
		}
		
		public static < T > T getAsset( IResource< T > resource ) {
			return Studio.assetManager.get( resource );
		}
	}
	
	public static final class audio {
		public static void playSound( IResource< Sound > sound ) {
			Studio.audioControl.playSound( sound, Studio.assetManager );
		}
		
		public static void setMusicBoardFrom( IResource< Music > music ) {
			Studio.audioControl.setMusicBoardFrom( music, Studio.assetManager );
		}
		
		public static IMusicBoard getMusicBoard() {
			return Studio.audioControl.getMusicBoard();
		}
	}
	
	public static final class graphics {
		public static TextureWorks getTextureWork() {
			return Studio.textureWorks;
		}
		
		public static void clear_screen() {
			Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
			Gdx.gl.glEnable( GL20.GL_DEPTH_TEST );
			Gdx.gl.glDepthFunc( GL20.GL_LESS );
			Gdx.gl.glClearColor( 0, 0, 0, 1 );
		}
		
		public static int getFPS() {
			return Gdx.graphics.getFramesPerSecond();
		}
	}
	
	public static final class time {
		public static double getSystemMicroTime() {
			return ( ( System.nanoTime() ) / 1000000.0 );
		}

		public static float getDeltaTime() {
			return Gdx.graphics.getRawDeltaTime();
		}
		
		public static double getElapsedTime() {
			return Studio.elapsedTime;
		}
		
		public static TimerManager getTimers() {
			return Studio.timerManager;
		}
		
		public static double getTotalLoopTime() {
			return loopTime;
		}
		
		public static double getAllProcessTime() {
			return allProcessTime;
		}
	}
	
	public static final class usage {
		public static double getProcessExecution( StudioProcess gp ) {
			return ( gp.getExecutionTime() / loopTime ) * 100.0;
		}
	}

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
	static class AudioUpdate implements IProcessExecution
    {
		@Override
		public void execute() {
			Studio.audioControl.update();
		}
	}
}