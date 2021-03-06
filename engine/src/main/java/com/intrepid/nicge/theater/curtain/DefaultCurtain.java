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
package com.intrepid.nicge.theater.curtain;

import java.util.Set;

import com.intrepid.nicge.content.IResource;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;

public class DefaultCurtain
        extends AbstractCurtain
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public DefaultCurtain()
    {
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    @Override
    public void initRunBeforeCloseCommand()
    {
    }

    @Override
    protected void openedUpdate()
    {
    }

    @Override
    protected void openingUpdate()
    {
        setStatus( CurtainCondition.OPENED );
    }

    @Override
    protected void closedUpdate()
    {
    }

    @Override
    protected void closingUpdate()
    {
        setStatus( CurtainCondition.CLOSED );
    }

    @Override
    protected void openedDisplay( GraphicsBatch batch )
    {
    }

    @Override
    protected void openingDisplay( GraphicsBatch batch )
    {
    }

    @Override
    protected void closedDisplay( GraphicsBatch batch )
    {
    }

    @Override
    protected void closingDisplay( GraphicsBatch batch )
    {
    }

    @Override
    public void injectResourcesOn( Set< IResource< ? > > resources )
    {
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
