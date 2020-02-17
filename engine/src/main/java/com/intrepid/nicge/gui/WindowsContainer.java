package com.intrepid.nicge.gui;

import com.intrepid.nicge.utils.containers.TopTailList;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;

import java.util.Iterator;

class WindowsContainer
        extends ComponentContainer
        implements Iterable< Window >
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
    private final TopTailList< Window > windows;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public WindowsContainer()
    {
        this.windows = new TopTailList<>();
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
    protected void enable( ComponentWrapper wrapper )
    {
        if( wrapper == null ) return;
        wrapper.enable();
        ( (Window)wrapper.getComponent() ).enable();
    }

    @Override
    protected void disable( ComponentWrapper wrapper )
    {
        if( wrapper == null ) return;
        wrapper.disable();
        ( (Window)wrapper.getComponent() ).disable();
    }

    public TopTailList< Window > getWindows()
    {
        return windows;
    }

    public Iterator< Window > iterator()
    {
        return windows.iterator();
    }

    @Override
    public void display( GraphicsBatch batch )
    {
        windows.forEach( window -> window.display( batch ) );
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Inner Classes And Patterns
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}