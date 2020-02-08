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
package com.intrepid.nicge.controller.joystick;

import com.intrepid.nicge.controller.basics.GamePadButton;
import com.intrepid.nicge.utils.timer.Timer;

public final class JoystickButton implements GamePadButton
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private final InspectJoystick inspect;

    private boolean isEdgeDown;
    private boolean isEdgeUp;
    private boolean isPressed;
    private Timer timer;
    private int code;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public JoystickButton( InspectJoystick inspect )
    {
        this.inspect = inspect;
        this.isEdgeDown = false;
        this.isEdgeUp = false;
        this.isPressed = false;
        this.timer = new Timer();
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    private void press()
    {
        this.isEdgeDown = true;
        timer.start();
    }

    private void release()
    {
        this.isEdgeUp = true;
        timer.stop();
    }

    @Override
    public boolean isEdgeDown()
    {
        return this.isEdgeDown;
    }

    @Override
    public boolean isEdgeUp()
    {
        return this.isEdgeUp;
    }

    @Override
    public boolean isPressed()
    {
        return this.isPressed;
    }

    @Override
    public boolean isPressedForMilliSecs( float time )
    {
        return timer.checkTime_ns( (long)( time * 1_000_000L ) );
    }

    @Override
    public float getHowMuchTimeInMilliSecsItIsPressed()
    {
        return timer.getTotalTime_ns() / 1_000_000.0f;
    }

    @Override
    public void captureControl()
    {
        if( inspect.getButton( code ) )
        {
            press();
        }
        else
        {
            release();
        }
    }

    @Override
    public void update()
    {
        if( isEdgeUp )
        {
            if( isPressed )
            {
                isPressed = false;
                return;
            }
            else
            {
                isEdgeUp = false;
                return;
            }
        }

        if( isEdgeDown )
        {
            if( isPressed )
            {
                isEdgeDown = false;
            }
            else
            {
                isPressed = true;
            }
        }

        if( isPressed )
        {
            timer.update();
        }
    }

    public void setCode( int code )
    {
        this.code = code;
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
