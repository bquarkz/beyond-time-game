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

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.intrepid.game.GameConstants;
import com.intrepid.game.curtains.AllCurtains;
import com.intrepid.nicge.content.IResource;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.theater.cameras.Camera;
import com.intrepid.nicge.theater.scene.IScene;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;
import com.intrepid.nicge.utils.timer.Timer;

public abstract class AbstractOpeningScene implements IScene
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private final Timer timer;
    private Camera camera;

    private Texture openingTexture;
    private boolean couldTrigger;
    private boolean escapePressed;

    private final OpeningSceneInputProcessor inputProcessor;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public AbstractOpeningScene()
    {
        timer = new Timer();
        inputProcessor = new OpeningSceneInputProcessor();
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    protected abstract IResource< Texture > getOpeningTexture();
    protected abstract Class< ? extends IScene > getNextScene();

    @Override
    public void start()
    {
        Game.common.addInputProcessor( inputProcessor );

        escapePressed = false;
        couldTrigger = true;
        timer.start();
        openingTexture = Game.common.getAsset( getOpeningTexture() );
    }

    @Override
    public void update()
    {
        timer.update();

        final boolean timeConstraint = timer.getTotalTime_ms() > GameConstants.OPENING_MAX_MS;
        final boolean buttonConstraint = escapePressed && timer.getTotalTime_ms() > GameConstants.OPENING_MIN_MS;
        if( couldTrigger && ( timeConstraint || buttonConstraint ) )
        {
            couldTrigger = false;
            timer.reset();
            Game.scene.change( getNextScene(), AllCurtains.OPENING_FADE );
        }
    }

    @Override
    public void display( GraphicsBatch batch )
    {
        batch.begin();
        batch.draw( openingTexture, 0, 0 );
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
    class OpeningSceneInputProcessor implements InputProcessor
    {
        @Override
        public boolean keyDown( int keycode )
        {
            if( keycode == Input.Keys.ESCAPE )
            {
                escapePressed = true;
            }
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
}
