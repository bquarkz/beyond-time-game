package com.intrepid.game.gui;

import com.badlogic.gdx.graphics.Color;
import com.intrepid.game.Resources;
import com.intrepid.game.curtains.AllCurtains;
import com.intrepid.game.scenes.RandomScene;
import com.intrepid.game.scenes.SimulationScene;
import com.intrepid.nicge.content.Dependency;
import com.intrepid.nicge.content.DependencyResource;
import com.intrepid.nicge.content.IResource;
import com.intrepid.nicge.gui.Bundle;
import com.intrepid.nicge.gui.Window;
import com.intrepid.nicge.gui.WindowParameters;
import com.intrepid.nicge.gui.controls.Button;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.utils.MathUtils;
import com.intrepid.nicge.utils.animation.Animation;
import com.intrepid.nicge.utils.animation.AnimationPack;

import java.util.Set;

public class RedWindow
    extends Window
{
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constants
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Special Fields And Injections
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Fields
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public RedWindow( int x, int y )
    {
        super( new WindowParameters( MathUtils.Vector.with( x, y ), 300, 300 ) );
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
    public void _bindAssets()
    {
    }

    @Override
    protected void _unBindAssets()
    {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Inner Classes And Patterns
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Dependency( SimulationScene.class )
    public static class FreakWindowDependency extends DependencyResource
    {
        @Override
        protected void setDependencies( Set< IResource< ? > > resources )
        {
            resources.add( Resources.Animations.SENTINEL );
        }
    }
}
