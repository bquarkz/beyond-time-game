package com.intrepid.studio.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.intrepid.nicge.gui.Bundle;
import com.intrepid.nicge.gui.Window;
import com.intrepid.nicge.gui.WindowsManager;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.theater.cameras.Camera;
import com.intrepid.nicge.theater.scene.GameScene;
import com.intrepid.nicge.theater.scene.IScene;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;
import com.intrepid.nicge.utils.graphics.TextureWorks;
import com.intrepid.nicge.utils.threads.TaskOverview;
import com.intrepid.nicge.utils.threads.ThreadExecutor;
import com.intrepid.studio.gui.WindowActions;
import com.intrepid.studio.gui.styles.Styles;

import java.util.List;
import java.util.stream.Collectors;

@GameScene
public class MainScene
    implements IScene
{
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constants
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final Color BACKGROUND_COLOR = new Color( 0xaabfbaff );
    private static final int TASK_BAR_WIDTH = 250;
    private static final int TASK_BAR_HEIGHT = 20;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Special Fields And Injections
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Fields
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Camera camera;

    private final Texture background;
    private final Texture taskBar;
    private final Texture taskBarBackground;
    private final BitmapFont taskFont;
    private final WindowsManager windowsManager;

    private Bundle< Window > windowAction;
    private final ThreadExecutor threadExecutor;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public MainScene()
    {
        background = TextureWorks.createTexture( 2, 2, BACKGROUND_COLOR );
        taskBar = TextureWorks.createTexture( TASK_BAR_WIDTH, TASK_BAR_HEIGHT, Color.BLUE );
        taskBarBackground = TextureWorks.createTexture( TASK_BAR_WIDTH, TASK_BAR_HEIGHT, Color.GRAY );
        taskFont = new BitmapFont(  );
        windowsManager = WindowsManager.create( Styles.GREEN_ORANGE );
        threadExecutor = new ThreadExecutor();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Factories
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Getters And Setters
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void configureGraphicsBatch( GraphicsBatch stageBatch )
    {
        camera = Game.graphics.newNativeCamera();
        stageBatch.setProjectionMatrix( camera.combined );
    }

    @Override
    public void prepareEnvironment()
    {
        windowAction = windowsManager.addWindow( new WindowActions( this ) );
    }

    @Override
    public void bindAssets()
    {
        windowsManager.bindAssets();
    }

    @Override
    public void unBindAssets()
    {
        windowsManager.unBindAssets();
        windowsManager.clear();
        background.dispose();
        taskBar.dispose();
        taskBarBackground.dispose();
        taskFont.dispose();
    }

    @Override
    public void inputControlLogic()
    {

    }

    @Override
    public void update()
    {
        camera.update();
        windowsManager.update();
    }

    @Override
    public void display( GraphicsBatch batch )
    {
        batch.begin();
        batch.draw( background, 0, 0,
                Game.common.getGameConfiguration().getNativeResolutionWidth(),
                Game.common.getGameConfiguration().getNativeResolutionHeight() );

//        Game.debug.addDebugMessage( "TASK OVERVIEW", threadExecutor
//                .getTasksOverview()
//                .stream()
//                .map( taskOverview -> taskOverview.getTaskName() + " :: " + taskOverview.getTaskCompletion()  )
//                .collect( Collectors.toList() ) );

        final List< TaskOverview > tasksOverview = threadExecutor.getTasksOverview();
        for( int i = 0; i < tasksOverview.size(); i++ )
        {
            final TaskOverview taskOverview = tasksOverview.get( i );
            int x = 10;
            int y = Game.common.getGameConfiguration().getNativeResolutionHeight() - 10 - ( ( TASK_BAR_HEIGHT + 2 ) * ( i + 1 ) );
            float w = TASK_BAR_WIDTH * taskOverview.getTaskCompletion();
            batch.draw( taskBarBackground, x, y, TASK_BAR_WIDTH, TASK_BAR_HEIGHT );
            batch.draw( taskBar, x, y, w, TASK_BAR_HEIGHT );
            taskFont.draw( batch, taskOverview.getTaskName(),15, y + 15 );
        }

        batch.end();
        this.windowsManager.display();
    }

    @Override
    public void simulation()
    {

    }

    @Override
    public void start()
    {
        Game.common.addOnTopInputProcessor( windowsManager );
        windowsManager.openWindow( windowAction );
    }

    @Override
    public void stop()
    {
        Game.common.removeInputProcessor( windowsManager );
        threadExecutor.shutdown();
    }

    @Override
    public void hibernate()
    {

    }

    @Override
    public void wakeup()
    {

    }

    public ThreadExecutor getThreadExecutor()
    {
        return threadExecutor;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Inner Classes And Patterns
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
