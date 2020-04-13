/*
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
package com.intrepid.studio.curtains;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.intrepid.nicge.content.IResource;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.theater.curtain.AbstractCurtain;
import com.intrepid.nicge.theater.curtain.CurtainCondition;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;

import java.util.Set;

class ImageFade
        extends AbstractCurtain
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private float alpha;
    private Texture texture;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public ImageFade()
    {
        this.texture = Game.graphics.getTextureWork().getPixel_2x2();
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    @Override
    public void initRunBeforeCloseCommand()
    {
        alpha = 0.0f;
    }

    @Override
    protected void openedUpdate()
    {
        alpha = 0.0f;
    }

    @Override
    protected void openingUpdate()
    {
        alpha -= 0.05f;
		if( alpha <= 0.0f )
		{
			setStatus( CurtainCondition.OPENED );
		}
    }

    @Override
    protected void closedUpdate()
    {
        alpha = 1.0f;
    }

    @Override
    protected void closingUpdate()
    {
        alpha += 0.05f;
		if( alpha >= 1.0f )
		{
			setStatus( CurtainCondition.CLOSED );
		}
    }

    @Override
    protected void openedDisplay( GraphicsBatch batch )
    {
    }

    private void showCurtain( GraphicsBatch batch )
    {
        Color c = batch.getColor();
        batch.setColor( c.r, c.g, c.b, alpha );
        batch.draw( texture, 0, 0,
                Game.common.getGameConfiguration().getNativeResolutionWidth(),
                Game.common.getGameConfiguration().getNativeResolutionHeight() );
        batch.setColor( c );
    }

    @Override
    protected void closedDisplay( GraphicsBatch batch )
    {
        batch.draw( texture, 0, 0 );
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
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ***************************************************************************************
}