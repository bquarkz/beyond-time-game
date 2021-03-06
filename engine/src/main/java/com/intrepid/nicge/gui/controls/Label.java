package com.intrepid.nicge.gui.controls;

import com.intrepid.nicge.gui.ComponentWrapper;
import com.intrepid.nicge.gui.IStyle;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;

public class Label
    extends AbstractControl
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
    private ComponentWrapper parent;
    private final IStyle style;
    private final String label;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Label( IStyle style, String label )
    {
        this.style = style;
        this.label = label;
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
    public void display( GraphicsBatch batch )
    {
    }

    @Override
    public boolean checkMouseOver(
            int screenX,
            int screenY )
    {
        return false;
    }

    @Override
    public boolean mouseButtonPressed(
            int screenX,
            int screenY,
            int button )
    {
        return false;
    }

    @Override
    public boolean mouseButtonUnPressed(
            int screenX,
            int screenY,
            int button )
    {
        return false;
    }

    @Override
    public boolean dragged(
            int screenX,
            int screenY,
            int button )
    {
        return false;
    }

    @Override
    public void update()
    {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Inner Classes And Patterns
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
