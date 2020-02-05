package com.intrepid.game.courtains;

import java.util.Set;

import com.intrepid.nicge.content.Resource;
import com.intrepid.nicge.theater.courtain.Curtain;
import com.intrepid.nicge.theater.courtain.CurtainCondition;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;

public enum AllCurtains implements Curtain
{
    IMAGE_FADE( new ImageFade() ),
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
    public final void captureResources( Set< Resource< ? > > resources )
    {
        curtain.captureResources( resources );
    }
}
