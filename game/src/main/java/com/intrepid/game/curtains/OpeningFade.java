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
package com.intrepid.game.curtains;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.intrepid.game.Resources;
import com.intrepid.nicge.content.IResource;
import com.intrepid.nicge.theater.curtain.AbstractCurtain;
import com.intrepid.nicge.theater.curtain.CurtainCondition;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;
import com.intrepid.nicge.utils.timer.Timer;

import java.util.Random;
import java.util.Set;

class OpeningFade
        extends AbstractCurtain
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private float alpha;
    private final Timer timer;

    private Texture fadeInBlack;
    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public OpeningFade()
    {
        this.timer = new Timer();
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    @Override
    public void initRunBeforeCloseCommand()
    {
        alpha = 0.0f;
        timer.start();
        fadeInBlack = Resources.Textures.FULL_BLACK.getAsset();
    }

    @Override
    protected void openedUpdate()
    {
        alpha = 0.0f;
        timer.stop();
    }

    @Override
    protected void openingUpdate()
    {
        timer.update();

        if( timer.getTotalTime_ms() > 20 )
        {
            timer.reset();
            alpha -= 0.05f;
        }

        if( alpha <= 0.0f )
		{
			setStatus( CurtainCondition.OPENED );
		}
    }

    @Override
    protected void closedUpdate()
    {
        alpha = 1.0f;
        timer.reset();
    }

    @Override
    protected void closingUpdate()
    {
        timer.update();

        if( timer.getTotalTime_ms() > 20 )
        {
            timer.reset();
            alpha += 0.05f;
        }

		if( alpha >= 1.0f )
		{
            setStatus( CurtainCondition.CLOSED );
		}
    }

    private void showCurtain( GraphicsBatch batch )
    {
        Color c = batch.getColor();
        batch.setColor( c.r, c.g, c.b, alpha );
        batch.draw( fadeInBlack, 0, 0 );
        batch.setColor( c );
    }

    @Override
    protected void openedDisplay( GraphicsBatch batch )
    {
    }

    @Override
    protected void closedDisplay( GraphicsBatch batch )
    {
        batch.draw( fadeInBlack, 0, 0 );
    }

    @Override
    protected void openingDisplay( GraphicsBatch batch )
    {
        showCurtain( batch );
    }

    @Override
    protected void closingDisplay( GraphicsBatch batch )
    {
        showCurtain( batch );
    }

    @Override
    public final void injectResourcesOn( Set< IResource< ? > > resources )
    {
        resources.add( Resources.Textures.FULL_BLACK );
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ***************************************************************************************
}