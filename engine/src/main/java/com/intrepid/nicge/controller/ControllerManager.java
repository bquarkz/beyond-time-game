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
package com.intrepid.nicge.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.utils.Array;
import com.intrepid.nicge.controller.basics.AbstractPad;
import com.intrepid.nicge.controller.basics.Pad;
import com.intrepid.nicge.controller.basics.UnitControl;
import com.intrepid.nicge.controller.joystick.JoystickMap;
import com.intrepid.nicge.controller.joystick.JoystickPad;
import com.intrepid.nicge.controller.keyboard.KeyboardMap;
import com.intrepid.nicge.controller.keyboard.KeyboardPad;
import com.intrepid.nicge.theater.Controlable;
import com.intrepid.nicge.theater.Updatable;

public class ControllerManager implements Updatable, Controlable
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************
//	private static final int MAX_JOYSTICKS = 4;
    private static final KeyboardMap MAP_KEYBOARD = new KeyboardMapping();
    private static final JoystickMap MAP_XBOX360 = new Xbox360Mapping();
    private static final JoystickMap MAP_PS4 = new Xbox360Mapping(); // for while

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private int numbersOfControllers;
    private Pad usedPad;
    private UnitControl unitControl;
    private final AbstractPad keyboardPad;
    private final Array< JoystickPad > joystickPads;
    private JoystickPad lastPad;
    private boolean hasControllers;
    private boolean isControllerDisconnected;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public ControllerManager()
    {
        joystickPads = new Array<>();
        keyboardPad = new KeyboardPad( Gdx.input, MAP_KEYBOARD );
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    public void verifyControlsConditions()
    {
        Array< Controller > controllers = Controllers.getControllers();
		if( numbersOfControllers == controllers.size )
		{
			return;
		}

        int controllersSize = controllers.size;

        if( controllersSize == 0 )
        {
            hasControllers = false;
            setPad( keyboardPad );
            return;
        }

        if( controllersSize > numbersOfControllers )
        {
            addControllers( controllers );
        }
        else
        { //controllersSize < numbersOfControllers
            removeControllers( controllers );
        }

        if( joystickPads.size > 0 )
        {
            if( ( lastPad != null ) && ( lastPad != joystickPads.get( 0 ) ) )
            {
                isControllerDisconnected = true;
            }
            else
            {
                isControllerDisconnected = false;
            }

            lastPad = joystickPads.get( 0 );
            setPad( lastPad );
        }
    }

    private boolean checkXBox360( String controllerName )
    {
        return ( controllerName.contains( "xbox" ) & controllerName.contains( "360" ) );
    }

    private boolean checkPS4( String controllerName )
    {
        return ( controllerName.contains( "wireless" ) );
    }

    private JoystickPad checkControllersType( Controller controller )
    {
        String controllerName = controller.getName().toLowerCase();
        if( checkXBox360( controllerName ) )
        {
            return new JoystickPad( controller, MAP_XBOX360 );
        }
        else if( checkPS4( controllerName ) )
        {
            return new JoystickPad( controller, MAP_PS4 );
        }

        return null;
    }

    private void addControllers( Array< Controller > controllers )
    {
        for( Controller controller : controllers )
        {
            boolean isIncluded = false;
            for( JoystickPad joystickPad : joystickPads )
            {
                if( controller == joystickPad.getController() )
                {
                    isIncluded = true;
                }
            }

            if( !isIncluded )
            {
                JoystickPad pad = checkControllersType( controller );
                // controller accept
                if( pad != null )
                {
                    hasControllers = true;
                    joystickPads.add( pad );
                }
            }
        }
    }

    private void removeControllers( Array< Controller > controllers )
    {
        Array< JoystickPad > removes = new Array<>();

        for( JoystickPad joystickPad : joystickPads )
        {
            if( !controllers.contains( joystickPad.getController(), true ) )
            {
                removes.add( joystickPad );
            }
        }

        joystickPads.removeAll( removes, true );
    }

    @Override
    public void captureControl()
    {
        unitControl.captureControl();
    }

    @Override
    public void update()
    {
        unitControl.update();
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public boolean isControllerDisconnected()
    {
        return isControllerDisconnected;
    }

    public boolean hasController()
    {
        return hasControllers;
    }

    protected void setPad( AbstractPad pad )
    {
        usedPad = pad;
        unitControl = pad;
    }

    public Pad getPad()
    {
        return usedPad;
    }

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
