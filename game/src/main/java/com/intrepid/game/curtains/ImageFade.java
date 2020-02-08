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

import java.util.Random;
import java.util.Set;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.intrepid.game.Resources;
import com.intrepid.nicge.content.IResource;
import com.intrepid.nicge.theater.curtain.AbstractCurtain;
import com.intrepid.nicge.theater.curtain.CurtainCondition;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;

class ImageFade extends AbstractCurtain
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************
    private static final Random RANDOM = new Random( System.currentTimeMillis() );
    private static final Resources.Textures[] TEXTURES = new Resources.Textures[]{
            Resources.Textures.GREEN_COUTAIN,
            Resources.Textures.RED_COUTAIN,
            Resources.Textures.BLUE_COUTAIN,
            Resources.Textures.PURPLE_COUTAIN
    };

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private float alpha;
    private Texture texture;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    @Override
    public void initRunBeforeCloseCommand()
    {
        alpha = 0.0f;
        int random = RANDOM.nextInt( 3 );
        texture = TEXTURES[ random ].getAsset();
    }

    @Override
    protected void openedUpdate()
    {
        alpha = 0.0f;
    }

    @Override
    protected void openingUpdate()
    {
        alpha -= 0.01;
		if( alpha <= 0.0f )
		{
			status = CurtainCondition.OPENED;
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
			status = CurtainCondition.CLOSED;
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
        batch.draw( texture, 0, 0 );
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
    public final void captureResources( Set< IResource< ? > > resources )
    {
        for( Resources.Textures texture : TEXTURES )
        {
            resources.add( texture );
        }
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ***************************************************************************************
}