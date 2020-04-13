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
package com.intrepid.game;

import com.intrepid.game.scenes.OpeningGreenScene;
import com.intrepid.nicge.theater.scene.AbstractBootScene;
import com.intrepid.nicge.theater.scene.GameScene;
import com.intrepid.nicge.theater.scene.IScene;

@GameScene( start = true )
public class BootScene extends AbstractBootScene
{
    @Override
    public Class< ? extends IScene > getStartScene()
    {
        return OpeningGreenScene.class;
    }
}