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
package com.intrepid.nicge.kernel;

import java.util.Set;

public interface AppConfiguration
{
    boolean isMouseLockedAndInvisible();

    String getTitle();

    boolean isResizable();

    int getMSAA();

    boolean isGL20();

    int getRefreshRate();

    public int getWindowResolutionWidth();

    public int getWindowResolutionHeight();

    public boolean isFullscreen();

    public int getNativeResolutionWidth();

    public int getNativeResolutionHeight();

    public boolean getVSync();

    public int getBitsPerPixel();

    Set< String > getClassLoadersPackages();
}
