package com.intrepid.game.curtains;

import java.util.Set;

import com.intrepid.nicge.content.IResource;
import com.intrepid.nicge.theater.curtain.Curtain;
import com.intrepid.nicge.theater.curtain.CurtainCondition;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;

public enum AllCurtains implements Curtain
{
    IMAGE_FADE( new ImageFade() ),
    OPENING_FADE( new OpeningFade() ),
    ;

    private Curtain curtain;

    AllCurtains( Curtain curtain )
    {
        this.curtain = curtain;
    }

    @Override
    public void initRunBeforeCloseCommand()
    {
        curtain.initRunBeforeCloseCommand();
    }

    @Override
    public void update()
    {
        curtain.update();
    }

    @Override
    public void display( GraphicsBatch batch )
    {
        curtain.display( batch );
    }

    @Override
    public void closeCommand()
    {
        curtain.closeCommand();
    }

    @Override
    public void openCommand()
    {
        curtain.openCommand();
    }

    @Override
    public boolean isTransitionFinished()
    {
        return curtain.isTransitionFinished();
    }

    @Override
    public CurtainCondition getStatus()
    {
        return curtain.getStatus();
    }

    @Override
    public final void injectResourcesOn( Set< IResource< ? > > resources )
    {
        curtain.injectResourcesOn( resources );
    }
}
