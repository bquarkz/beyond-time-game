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

public class GreenWindow
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
    private final Bundle< Button > button;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public GreenWindow( int x, int y )
    {
        super( new WindowParameters( MathUtils.Vector.with( x, y ), 300, 300, false, Color.BLACK, Color.GREEN ) );
        button = addComponent( Button.create() );
        button.getComponent().setScreenPosition( 250, 150 );
        button.getComponent().setSize( 32, 32 );
        button.getComponent().setActionRun( () -> Game.scene.change( RandomScene.class, AllCurtains.IMAGE_FADE ) );
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
    public void loadAssets()
    {
        AnimationPack pack = Game.common.getAsset( Resources.Animations.SENTINEL );
        Animation idle = pack.get( "sentinel.move.down" );
        Animation over = pack.get( "sentinel.move.up" );
        Animation actionClick = pack.get( "sentinel.move.left" );
        Animation supportClick = pack.get( "sentinel.move.right" );
        button.getComponent().setIdle( idle );
        button.getComponent().setMouserOverMe( over );
        button.getComponent().setActionClicked( actionClick );
        button.getComponent().setSupportClicked( supportClick );
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
