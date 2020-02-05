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
package com.intrepid.nicge.controller.basics;

public interface Pad {
	GamePadButton get_BUTTON_X();
	GamePadButton get_BUTTON_Y();
	GamePadButton get_BUTTON_A();
	GamePadButton get_BUTTON_B();
	GamePadButton get_BUTTON_BACK();
	GamePadButton get_BUTTON_START();
	GamePadButton get_BUTTON_LB();
	GamePadButton get_BUTTON_L3();
	GamePadButton get_BUTTON_RB();
	GamePadButton get_BUTTON_R3();
	Axis get_AXIS_LEFT_X();
	Axis get_AXIS_LEFT_Y();
	Axis get_AXIS_LEFT_TRIGGER();
	Axis get_AXIS_RIGHT_X();
	Axis get_AXIS_RIGHT_Y();
	Axis get_AXIS_RIGHT_TRIGGER();
	POV get_DPAD();
}
