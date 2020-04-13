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
package com.intrepid.game.scenes;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.intrepid.game.Resources;
import com.intrepid.game.curtains.AllCurtains;
import com.intrepid.nicge.content.Dependency;
import com.intrepid.nicge.content.DependencyResource;
import com.intrepid.nicge.content.IResource;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.theater.cameras.Camera;
import com.intrepid.nicge.theater.scene.GameScene;
import com.intrepid.nicge.theater.scene.IScene;
import com.intrepid.nicge.utils.TriggerOnce;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;

import java.util.Set;

@GameScene
public class MainScene implements IScene
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private final MainSceneInputProcessor inputProcessor;
    private Camera camera;

    private Texture background;
    private boolean anyButtonPressed;
    private final TriggerOnce< Void > triggerOnce;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public MainScene()
    {
        inputProcessor = new MainSceneInputProcessor();
        triggerOnce = new TriggerOnce<Void>()
                .when( () -> anyButtonPressed )
                .theAction( () -> {
                    System.out.println( "PRESS" );
                    Game.scene.change( GameplayScene.class, AllCurtains.OPENING_FADE );
                } );
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************

    @Override
    public void start()
    {
        Game.common.addInputProcessor( inputProcessor );
        background = Resources.Textures.MAIN_BACKGROUND.getAsset();
        anyButtonPressed = false;
    }

    @Override
    public void update()
    {
        triggerOnce.exec();
    }

    @Override
    public void display( GraphicsBatch batch )
    {
        batch.begin();
        batch.draw( background, 0, 0 );
        batch.end();
    }

    @Override
    public void configureGraphicsBatch( GraphicsBatch stageBatch )
    {
        camera = Game.graphics.newNativeCamera();
        stageBatch.setProjectionMatrix( camera.combined );
    }

    @Override
    public void stop()
    {
        Game.common.removeInputProcessor( inputProcessor );
    }

    @Override
    public void hibernate()
    {
    }

    @Override
    public void wakeup()
    {
    }

    @Override
    public void inputControlLogic()
    {

    }

    @Override
    public void simulation()
    {
    }

    @Override
    public void prepareEnvironment()
    {
    }

    @Override
    public void bindAssets()
    {
    }

    @Override
    public void unBindAssets()
    {
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
    class MainSceneInputProcessor implements InputProcessor
    {
        @Override
        public boolean keyDown( int keycode )
        {
            anyButtonPressed = true;
            return false;
        }

        @Override
        public boolean keyUp( int keycode )
        {
            return false;
        }

        @Override
        public boolean keyTyped( char character )
        {
            return false;
        }

        @Override
        public boolean touchDown(
                int screenX,
                int screenY,
                int pointer,
                int button )
        {
            anyButtonPressed = true;
            return false;
        }

        @Override
        public boolean touchUp(
                int screenX,
                int screenY,
                int pointer,
                int button )
        {
            return false;
        }

        @Override
        public boolean touchDragged(
                int screenX,
                int screenY,
                int pointer )
        {
            return false;
        }

        @Override
        public boolean mouseMoved(
                int screenX,
                int screenY )
        {
            return false;
        }

        @Override
        public boolean scrolled( int amount )
        {
            return false;
        }
    }

    @Dependency( MainScene.class )
    public static class DependencyMainScene extends DependencyResource
    {
        @Override
        protected void setDependencies( Set< IResource< ? > > resources )
        {
            resources.add( Resources.Textures.MAIN_BACKGROUND );
        }
    }
}
