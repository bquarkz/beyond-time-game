package com.intrepid.game.scenes;

import com.intrepid.game.Resources;
import com.intrepid.nicge.content.Dependency;
import com.intrepid.nicge.content.DependencyResource;
import com.intrepid.nicge.content.IResource;

import java.util.Set;

@Dependency( {
        OpeningGreenScene.class,
        OpeningBlueScene.class,
        OpeningPurpleScene.class,
        OpeningRedScene.class
} )
public class DependencyOpening extends DependencyResource
{
    @Override
    protected void setDependencies( Set< IResource< ? > > resources )
    {
        resources.add( Resources.Textures.BLUE_CURTAIN );
        resources.add( Resources.Textures.GREEN_CURTAIN );
        resources.add( Resources.Textures.PURPLE_CURTAIN );
        resources.add( Resources.Textures.RED_CURTAIN );
    }
}
