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

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.intrepid.nicge.content.AssetManager;
import com.intrepid.nicge.content.Loadable;
import com.intrepid.nicge.content.Resource;
import com.intrepid.nicge.kernel.Application;
import com.intrepid.nicge.kernel.KernelScreenVariables;
import com.intrepid.nicge.kernel.ScreenInfo;
import com.intrepid.nicge.theater.Theater;
import com.intrepid.nicge.theater.courtain.Curtain;
import com.intrepid.nicge.theater.courtain.CurtainLayer;
import com.intrepid.nicge.theater.courtain.CurtainManager;
import com.intrepid.nicge.theater.scene.Scene;
import com.intrepid.nicge.utils.IProcessExecution;
import com.intrepid.nicge.utils.audio.AudioControl;
import com.intrepid.nicge.utils.audio.MusicBoard;
import com.intrepid.nicge.utils.fsmachine.FSMachineDefinition;
import com.intrepid.nicge.utils.graphics.TextureWorks;
import com.intrepid.nicge.utils.logger.Log;
import com.intrepid.nicge.utils.reflections.Reflection;
import com.intrepid.nicge.utils.reflections.ReflectionHelper;
import com.intrepid.nicge.utils.threads.ThreadExecutor;
import com.intrepid.nicge.utils.timer.TimerManager;

public final class Game implements Application
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private static TextureWorks textureWorks;
    private static AssetManager assetManager;
    private static ScreenInfo info;

    private static GameBoot boot;
    private static GameConfiguration gameConfiguration;
    private static double elapsedTime;
    private static Theater theater;
    private static double lastLoopTime;
    private static double allProcessTime;
    private static AudioControl audioControl;
    private static Reflection reflection;
    private static TimerManager timerManager;
    private static IProcessExecution[] process;
    private static Map< Class< ? >, Loadable > dependencyContainer;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public Game( GameBoot boot )
    {
        Game.boot = boot;
        Game.gameConfiguration = boot.getConfig();
        Game.process = getProcess();
        Game.elapsedTime = 0.0000001;
        Game.lastLoopTime = 0.0000001;
        Game.allProcessTime = 0.0000001;
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    protected IProcessExecution[] getProcess()
    {
        return GameProcess.values();
    }

    protected void throwUpDebugInfo()
    {
        double control = usage.getProcessExecution( GameProcess.CONTROL );
        double simulation = usage.getProcessExecution( GameProcess.SIMULATION );
        double update = usage.getProcessExecution( GameProcess.UPDATE );
        double display = usage.getProcessExecution( GameProcess.DISPLAY );
        double audio = usage.getProcessExecution( GameProcess.AUDIO );

        Game.info.addSystemInfo( "Control Capture:..(%s)", (int)control );
        Game.info.addSystemInfo( "Simulation:.......(%s)", (int)simulation );
        Game.info.addSystemInfo( "Update:...........(%s)", (int)update );
        Game.info.addSystemInfo( "Display:..........(%s)", (int)display );
        Game.info.addSystemInfo( "Audio Update:.....(%s)", (int)audio );

        Game.info.addSystemInfo( "FPS: " + Game.graphics.getFPS() );
        Game.info.throwUp();
    }

    @Override
    public void boot()
    {
        // mouse configuration
        final boolean isMiceHere = Game.common.getGameConfiguration().isMouseLockedAndInvisible();
        Gdx.input.setCursorCatched( isMiceHere );

        // Game instantiation and some configuration
        Game.textureWorks = new TextureWorks();
        Game.audioControl = new AudioControl();
        Game.assetManager = new AssetManager();
        Game.timerManager = new TimerManager();
        Game.info = new ScreenInfo( gameConfiguration.getWindowResolutionWidth(),
                gameConfiguration.getWindowResolutionHeight(),
                true );
        // configure reflections, no sure if it will be useful
        // we have to be careful about android environment
        final Set< String > classLoadersPackages = Game.gameConfiguration.getClassLoadersPackages();
        Game.reflection = new Reflection( classLoadersPackages );

        // boot instantiation
        final FSMachineDefinition< Scene > bootDefinitions = Optional
                .ofNullable( Game.boot.instantiation() )
                .orElseGet( ReflectionHelper::autoSearchSceneDefinitionsByAnnotations );
        Game.theater = new Theater( bootDefinitions, Game.assetManager );

        // create a new dependency container
        dependencyContainer = ReflectionHelper.createDependencyContainer();

        // game load assets
        // engine load needs to run before
        final Set< Resource< ? > > resources = new HashSet<>();
        Game.boot.loader( resources );
        Game.assetManager.load( resources );
		while( !Game.assetManager.update() );

        // initialization all game variable and its stuff
        Game.elapsedTime = Gdx.graphics.getDeltaTime();
        Game.lastLoopTime = 0;
        Game.allProcessTime = 0;

        // initialize the game
        Game.boot.initialization();
    }

    @Override
    public void loop()
    {
        // clean the screen before drawing every frame
        Game.graphics.clearScreen();

        // it gets the time who last loop took to be executed
        Game.elapsedTime += Game.time.getRawDeltaTime();

        // execute all process and calculate the time to execute sounds
        double procGeral_i = Game.time.getSystemMicroTime();
        executeAllProcess( procGeral_i );
        double procGeral_f = Game.time.getSystemMicroTime();
        Game.lastLoopTime = procGeral_f - procGeral_i;

        // well, that is it... and throw up all debug info into screen
        throwUpDebugInfo();
    }

    private void executeAllProcess( double procGeral_i )
    {
        for( IProcessExecution processExecution : process )
        {
            processExecution.execute();
        }
        allProcessTime = Game.time.getSystemMicroTime() - procGeral_i;

        // calculate how many time was gone since last update (targeting the FPS)
        double soundPlayLeftOverTime = ( Game.time.getRawDeltaTime() * 1_000_000 ) - allProcessTime;
        // take just 90% of that time
        soundPlayLeftOverTime *= 0.9f;
        // throw up the sound stack
        Game.audioControl.getSoundManager().execute( soundPlayLeftOverTime );
    }

    @Override
    public void resize(
            int width,
            int height )
    {
        if( Game.gameConfiguration.isResizable() )
        {
            if( Game.gameConfiguration.isFullscreen() )
            {
                final KernelScreenVariables screen = new KernelScreenVariables( Game.gameConfiguration );
                final DisplayMode mode = screen.selectDisplayMode( width, height );
                Gdx.graphics.setFullscreenMode( mode );
            }
            else
            {
                // DANGEROUZ! DANGEROUZ! DANGEROUZ! because gameConfiguration is not updated to resize screen
                Gdx.graphics.setWindowedMode( width, height );
            }
        }
    }

    @Override
    public void pause()
    {
        // do nothing
    }

    @Override
    public void resume()
    {
        // do nothing
    }

    @Override
    public void dispose()
    {
        Game.info.dispose();
        Game.textureWorks.dispose();
        Game.assetManager.dispose();
        new ThreadExecutor().shutdown();
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public static final class scene
    {
        public static void change(
                Class< ? extends Scene > sceneClass,
                Curtain curtain )
        {
            Game.theater.changeScene( sceneClass, curtain );
        }

        public static CurtainManager getCurtainManager()
        {
            return Game.theater.getCurtainManager();
        }

        public static void setMainCurtain( Curtain curtain )
        {
            Game.theater.getCurtainManager().setCurtain( CurtainLayer.MAIN, curtain );
        }

        public static void setHUD( Curtain curtain )
        {
            Game.theater.getCurtainManager().setCurtain( CurtainLayer.HUD_INTERFACE, curtain );
        }
    }

    public static final class util
    {
        private static final GameDebugVariables DEBUG_KERNEL_VARIABLES = new GameDebugVariables();

        public static Reflection getReflections()
        {
            return Game.reflection;
        }

        public static void addDebugMessage(
                String process,
                float timeProcess,
                List< String > messages )
        {
            Game.info.addProcessMessages( process, timeProcess, messages );
        }

        public static void addDebugMessage(
                String process,
                List< String > messages )
        {
            Game.info.addProcessMessages( process, messages );
        }

        public static void addDebugMessage(
                String process,
                String... messages )
        {
            final List< String > allMessages = Stream
                    .of( messages )
                    .flatMap( s -> Stream.of( s.split( "\n" ) ) )
                    .collect( Collectors.toList() );
            Game.info.addProcessMessages( process, allMessages );
        }

        public static final GameDebugVariables debug()
        {
            return DEBUG_KERNEL_VARIABLES;
        }

        public static final FileHandle getInternalFileHandle( String path )
        {
            return Gdx.files.internal( path );
        }
    }

    public static final class common
    {
        public static GameConfiguration getGameConfiguration()
        {
            return Game.gameConfiguration;
        }

//		public static DynamicDependency getDynamicDependency( String filename )
//		{
//		    Object object = Game.assetManager.get( ResourcesPath.dynamicDependencyPack + filename );
//		    if( object != null && object instanceof DynamicDependency )
//			{
//		        return (DynamicDependency) object;
//		    } else {
//		        throw new RuntimeException( " ## problems to load the dynamic dependency: " + filename );
//		    }
//		}

        public static < T > T getAsset( Resource< T > resource )
        {
            return Game.assetManager.get( resource );
        }

        public static Loadable getDependencies( Class< ? > clazz )
        {
            final Loadable dependencies = dependencyContainer.get( clazz );
            if( dependencies == null )
            {
                final String message = "dependencies for: " + clazz.getName() + "; was NOT loaded";
                Log.from( Game.class ).failure( message );
                throw new RuntimeException( "[ FATAL ] >> " + message + " <<" );
            }
            return dependencies;
        }
    }

    public static final class audio
    {
        public static void playSound( Resource< Sound > sound )
        {
            Game.audioControl.playSound( sound, Game.assetManager );
        }

        public static void setMusicBoardFrom( Resource< Music > music )
        {
            Game.audioControl.setMusicBoardFrom( music, Game.assetManager );
        }

        public static MusicBoard getMusicBoard()
        {
            return Game.audioControl.getMusicBoard();
        }
    }

    public static final class graphics
    {
        public static TextureWorks getTextureWork()
        {
            return Game.textureWorks;
        }

        public static void clearScreen()
        {
            Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
            Gdx.gl.glEnable( GL20.GL_DEPTH_TEST );
            Gdx.gl.glDepthFunc( GL20.GL_LESS );
            Gdx.gl.glClearColor( 0, 0, 0, 1 );
        }

        public static int getFPS()
        {
            return Gdx.graphics.getFramesPerSecond();
        }
    }

    public static final class time
    {
        public static double getSystemMicroTime()
        {
            return ( System.nanoTime() / 1000000.0 );
        }

        public static double getSystemNanoTime()
        {
            return System.nanoTime();
        }

        /**
         * @return the time span between the current frame and the last frame in seconds, without smoothing
         */
        public static float getRawDeltaTime()
        {
            return Gdx.graphics.getRawDeltaTime();
        }

        public static double getElapsedTime()
        {
            return Game.elapsedTime;
        }

        public static TimerManager getTimers()
        {
            return Game.timerManager;
        }

        public static double getLastLoopTime()
        {
            return lastLoopTime;
        }

        public static double getAllProcessTime()
        {
            return allProcessTime;
        }
    }

    public static final class usage
    {
        public static double getProcessExecution( GameProcess gp )
        {
            return ( gp.getExecutionTime() / lastLoopTime ) * 100.0;
        }
    }

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
    static class AudioUpdate implements IProcessExecution
    {
        @Override
        public void execute()
        {
            Game.audioControl.update();
        }
    }
}