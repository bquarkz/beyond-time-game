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
 * all community without problems.
 */
package com.intrepid.nicge.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.utils.animation.Animation;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;

public class Button implements IComponent
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************
    private static final int BUTTON_ACTION = MOUSE_BUTTON_LEFT;
    private static final int BUTTON_SUPPORT = MOUSE_BUTTON_RIGHT;

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private float x;
    private float y;
    private int width;
    private int height;
    private boolean isMouseOverMe;
    private boolean isActionClicked;
    private boolean isActionActive;
    private boolean isSupportClicked;
    private boolean isSupportActive;
    private Runnable supportRunner;
    private Runnable actionRunner;

    private float elapsedTime;

    private Animation idle;
    private Animation mouserOverMe;
    private Animation actionClicked;
    private Animation supportClicked;
    private ComponentControl componentControl;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    protected Button()
    {
        this.isMouseOverMe = false;
        this.isActionClicked = false;
        this.isActionActive = false;
        this.isSupportClicked = false;
        this.isSupportActive = false;
    }

    // ****************************************************************************************
    // Factories
    // ****************************************************************************************
    public static Button create()
    {
        Button o = new Button();

        return o;
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    @Override
    public void checkMouseOver(
            int screenX,
            int screenY )
    {
        boolean cx = ( ( screenX >= x ) && ( screenX <= ( x + width ) ) );
        boolean cy = ( ( screenY >= y ) && ( screenY <= ( y + height ) ) );

        if( cx && cy )
        {
            isMouseOverMe = true;
        }
        else
        {
            isMouseOverMe = false;
        }
    }

    @Override
    public void mouseButtonPressed(
            int screenX,
            int screenY,
            int button )
    {
        checkMouseOver( screenX, screenY );

        if( isMouseOverMe )
        {
            elapsedTime = 0;
            if( button == BUTTON_ACTION )
            {
                isActionClicked = true;
            }
            else if( button == BUTTON_SUPPORT )
            {
                isSupportClicked = true;
            }
        }
    }

    @Override
    public void mouseButtonUnPressed(
            int screenX,
            int screenY,
            int button )
    {
        checkMouseOver( screenX, screenY );

        // the component will be active for just the current cycle

        if( button == BUTTON_ACTION )
        {
            if( isMouseOverMe && isActionClicked )
            {
                elapsedTime = 0;
                isActionActive = true;
            }
            else
            {
                isActionActive = false;
            }

            isActionClicked = false;
        }

        if( button == BUTTON_SUPPORT )
        {
            if( isMouseOverMe && isSupportActive )
            {
                elapsedTime = 0;
                isSupportActive = true;
            }
            else
            {
                isSupportActive = false;
            }

            isSupportClicked = false;
        }
    }

    @Override
    public void update()
    {
        elapsedTime += Game.time.getRawDeltaTime();

        if( isActionActive )
        {
            if( actionRunner != null )
            {
                actionRunner.run();
            }
        }

        if( isSupportActive )
        {
            if( supportRunner != null )
            {
                supportRunner.run();
            }
        }
    }

    @Override
    public void display( GraphicsBatch batch )
    {
        TextureRegion tr = ( idle != null ? idle.getKeyFrame( elapsedTime ) : null );
        if( mouserOverMe != null && isMouseOverMe )
        {
            tr = mouserOverMe.getKeyFrame( elapsedTime );
        }

        if( actionClicked != null && isActionClicked )
        {
            tr = actionClicked.getKeyFrame( elapsedTime );
        }
        else if( supportClicked != null && isSupportClicked )
        {
            tr = supportClicked.getKeyFrame( elapsedTime );
        }

        if( tr != null )
        {
            batch.draw( tr, x, y );
        }
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    final public void setScreenPosition(
            float x,
            float y )
    {
        this.x = x;
        this.y = y;
    }

    final public void setSize(
            int width,
            int height )
    {
        this.width = width;
        this.height = height;
    }

    final public void setSupportRun( Runnable supportRunner )
    {
        this.supportRunner = supportRunner;
    }

    final public void setActionRun( Runnable actionRunner )
    {
        this.actionRunner = actionRunner;
    }

    final public void setIdle( Animation idle )
    {
        this.idle = idle;
    }

    final public void setActionClicked( Animation actionClicked )
    {
        this.actionClicked = actionClicked;
    }

    final public void setSupportClicked( Animation supportClicked )
    {
        this.supportClicked = supportClicked;
    }

    final public void setMouserOverMe( Animation mouserOverMe )
    {
        this.mouserOverMe = mouserOverMe;
    }

    @Override
    final public void setComponentControl( ComponentControl componentControl )
    {
        this.componentControl = componentControl;
    }

    @Override
    public ComponentControl getComponentControl()
    {
        return componentControl;
    }

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
