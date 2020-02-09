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
import com.intrepid.nicge.content.ILoadable;
import com.intrepid.nicge.content.IResource;
import com.intrepid.nicge.kernel.Application;
import com.intrepid.nicge.kernel.KernelScreenVariables;
import com.intrepid.nicge.kernel.ScreenInfo;
import com.intrepid.nicge.theater.Theater;
import com.intrepid.nicge.theater.cameras.Camera;
import com.intrepid.nicge.theater.curtain.Curtain;
import com.intrepid.nicge.theater.curtain.CurtainLayer;
import com.intrepid.nicge.theater.curtain.CurtainManager;
import com.intrepid.nicge.theater.scene.IScene;
import com.intrepid.nicge.utils.IProcessExecution;
import com.intrepid.nicge.utils.audio.AudioControl;
import com.intrepid.nicge.utils.audio.IMusicBoard;
import com.intrepid.nicge.utils.fsmachine.IFSMachineDefinition;
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
    private static Theater theater;
    private static long elapsedTime;
    private static long lastLoopTime;
    private static long allProcessTime;
    private static AudioControl audioControl;
    private static Reflection reflection;
    private static TimerManager timerManager;
    private static IProcessExecution[] process;
    private static Map< Class< ? >, ILoadable > dependencyContainer;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public Game( GameBoot boot )
    {
        Game.boot = boot;
        Game.gameConfiguration = boot.getConfig();
        Game.process = getProcess();
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
        final IFSMachineDefinition< IScene > bootDefinitions = Optional
                .ofNullable( Game.boot.instantiation() )
                .orElseGet( ReflectionHelper::autoSearchSceneDefinitionsByAnnotations );
        Game.theater = new Theater( bootDefinitions, Game.assetManager );

        // create a new dependency container
        dependencyContainer = ReflectionHelper.createDependencyContainer();

        // game load assets
        // engine load needs to run before
        final Set< IResource< ? > > resources = new HashSet<>();
        Game.boot.loader( resources );
        Game.assetManager.load( resources );
		while( !Game.assetManager.update() );

        // initialization all game variable and its stuff
        Game.elapsedTime = Game.time.getRawDeltaTime_ns();
        Game.lastLoopTime = 0L;
        Game.allProcessTime = 0L;

        // initialize the game
        Game.boot.initialization();
    }

    @Override
    public void loop()
    {
        // clean the screen before drawing every frame
        Game.graphics.clearScreen();

        // it gets the time who last loop took to be executed
        Game.elapsedTime += Game.time.getRawDeltaTime_ns();

        // execute all process and calculate the time to execute sounds
        long procGeral_i = Game.time.getCurrentTime_ns();
        executeAllProcess( procGeral_i );

        // well, that is it... and throw up all debug info into screen
        throwUpDebugInfo();

        long procGeral_f = Game.time.getCurrentTime_ns();

        Game.lastLoopTime = procGeral_f - procGeral_i;
    }

    private void executeAllProcess( long procGeral_i )
    {
        for( IProcessExecution processExecution : process )
        {
            processExecution.execute();
        }
        allProcessTime = Game.time.getCurrentTime_ns() - procGeral_i;

        // calculate how many time was gone since last update (targeting the FPS)
        long leftoverTimeToPlaySounds = ( Game.time.getRawDeltaTime_ns() ) - allProcessTime;

        // throw up the sound stack
        Game.audioControl.getSoundManager().execute( leftoverTimeToPlaySounds );
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
                Class< ? extends IScene > sceneClass,
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

        public static < T > T getAsset( IResource< T > resource )
        {
            return Game.assetManager.get( resource );
        }

        public static ILoadable getDependencies( Class< ? > clazz )
        {
            final ILoadable dependencies = dependencyContainer.get( clazz );
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
        public static void playSound( IResource< Sound > sound )
        {
            Game.audioControl.playSound( sound, Game.assetManager );
        }

        public static void setMusicBoardFrom( IResource< Music > music )
        {
            Game.audioControl.setMusicBoardFrom( music, Game.assetManager );
        }

        public static IMusicBoard getMusicBoard()
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

        public static int getScreenWidth()
        {
            return Gdx.graphics.getWidth();
        }

        public static int getScreenHeight()
        {
            return Gdx.graphics.getHeight();
        }

        public static Camera newNativeCamera()
        {
            int nativeResolutionWidth = Game.common.getGameConfiguration().getNativeResolutionWidth();
            int nativeResolutionHeight = Game.common.getGameConfiguration().getNativeResolutionHeight();
            return new Camera( nativeResolutionWidth, nativeResolutionHeight );
        }
    }

    public static final class time
    {
        public static long getCurrentTime_ms()
        {
            return ( Game.time.getCurrentTime_ns() / 1_000_000 );
        }

        public static long getCurrentTime_ns()
        {
            return System.nanoTime();
        }

        /**
         * Get delta time in nano-seconds, without scale
         */
        public static long getRawDeltaTime_ns()
        {
            return (long)( Gdx.graphics.getRawDeltaTime() * 1_000_000_000L );
        }

        /**
         * Get delta time in seconds without scale
         */
        public static long getDeltaTime_ns()
        {
            return (long)( Gdx.graphics.getDeltaTime() * 1_000_000_000L );
        }

        /**
         * Get delta time in seconds, without scale
         */
        public static float getGdxRawDeltaTime_s()
        {
            return Gdx.graphics.getRawDeltaTime();
        }

        /**
         * Get delta time in seconds with scale
         */
        public static float getGdxDeltaTime_s()
        {
            return Gdx.graphics.getDeltaTime();
        }

        public static long getElapsedTime_ns()
        {
            return Game.elapsedTime;
        }

        public static TimerManager getTimers()
        {
            return Game.timerManager;
        }

        public static long getLastLoopTime_ns()
        {
            return lastLoopTime;
        }

        public static long getAllProcessTime_ns()
        {
            return allProcessTime;
        }
    }

    public static final class usage
    {
        public static double getProcessExecution( GameProcess gp )
        {
            return ( gp.getExecutionTime() / (double)Game.time.getLastLoopTime_ns() ) * 100.0;
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