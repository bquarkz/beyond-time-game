package com.intrepid.nicge.gui.layouts;

import com.intrepid.nicge.gui.ILayout;

public enum Layouts
    implements ILayout
{
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Definitions
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    DEFAULT( new DefaultLayout() )
    ;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Fields and Constants
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final ILayout layout;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    Layouts( ILayout layout )
    {
        this.layout = layout;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Inner Classes And Patterns
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
