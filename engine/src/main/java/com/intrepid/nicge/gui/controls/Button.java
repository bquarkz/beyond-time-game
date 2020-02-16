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
package com.intrepid.nicge.gui.controls;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.utils.animation.Animation;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;

public class Button
        extends AbstractControl
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************
    private static final int BUTTON_ACTION = MOUSE_BUTTON_LEFT;
    private static final int BUTTON_SUPPORT = MOUSE_BUTTON_RIGHT;

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isMouseOverMe;
    private boolean isActionClicked;
    private boolean isActionActive;
    private boolean isSupportClicked;
    private boolean isSupportActive;
    private IButtonAction supportRunner;
    private IButtonAction actionRunner;

    private float elapsedTime_s;

    private Animation idle;
    private Animation mouserOverMe;
    private Animation actionClicked;
    private Animation supportClicked;

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
        this.actionRunner = () -> {};
        this.supportRunner = () -> {};
    }

    // ****************************************************************************************
    // Factories
    // ****************************************************************************************
    public static Button create()
    {
        return new Button();
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
            elapsedTime_s = 0;
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
                elapsedTime_s = 0;
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
                elapsedTime_s = 0;
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
        elapsedTime_s += Game.time.getGdxRawDeltaTime_s();

        if( isActionActive )
        {
            isActionActive = false;
            actionRunner.takeAction();
        }

        if( isSupportActive )
        {
            isSupportActive = false;
            supportRunner.takeAction();
        }
    }

    @Override
    public void display( GraphicsBatch batch )
    {
        TextureRegion tr = idle != null
                ? idle.getKeyFrame( elapsedTime_s )
                : null;

        if( mouserOverMe != null && isMouseOverMe )
        {
            tr = mouserOverMe.getKeyFrame( elapsedTime_s );
        }

        if( actionClicked != null && isActionClicked )
        {
            tr = actionClicked.getKeyFrame( elapsedTime_s );
        }
        else if( supportClicked != null && isSupportClicked )
        {
            tr = supportClicked.getKeyFrame( elapsedTime_s );
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
            int x,
            int y )
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

    final public void setSupportRun( IButtonAction supportRunner )
    {
        this.supportRunner = supportRunner;
    }

    final public void setActionRun( IButtonAction actionRunner )
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

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
    @FunctionalInterface
    public interface IButtonAction
    {
        void takeAction();
    }
}
