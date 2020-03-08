package com.intrepid.studio.gui;

import com.intrepid.nicge.gui.Window;
import com.intrepid.nicge.gui.WindowParameters;
import com.intrepid.nicge.gui.layouts.Layouts;
import com.intrepid.nicge.gui.styles.Styles;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.utils.MathUtils;

public class WindowActions
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
    public WindowActions()
    {
        super( WindowParameters.createStaticWindow(
                MathUtils.Vector.with( 1120, 10 ),
                150,
                Game.common.getGameConfiguration().getNativeResolutionHeight() - 20,
                Layouts.DEFAULT ) );
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
    protected void _bindAssets()
    {

    }

    @Override
    protected void _unBindAssets()
    {

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Inner Classes And Patterns
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
