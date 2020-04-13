package com.intrepid.game.scenes;

import com.badlogic.gdx.graphics.Texture;
import com.intrepid.game.Resources;
import com.intrepid.nicge.content.Dependency;
import com.intrepid.nicge.content.DependencyResource;
import com.intrepid.nicge.content.IResource;
import com.intrepid.nicge.theater.scene.GameScene;
import com.intrepid.nicge.theater.scene.IScene;

import java.util.Set;

@GameScene
public class OpeningRedScene
    extends AbstractOpeningScene
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
    public OpeningRedScene()
    {
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
    protected IResource< Texture > getOpeningTexture()
    {
        return Resources.Textures.RED_CURTAIN;
    }

    @Override
    protected Class< ? extends IScene > getNextScene()
    {
        return MainScene.class;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Inner Classes And Patterns
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
