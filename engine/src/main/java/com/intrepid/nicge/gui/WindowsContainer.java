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
    WindowsContainer()
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

    @Override
    public boolean mouseButtonPressed(
            int screenX,
            int screenY,
            int button )
    {
        final Iterator< Window > iterator = getWindows().topTailIterator();
        if( iterator.hasNext() ) // check focus first
        {
            final Window window = iterator.next();
            if( window.checkMouseOver( screenX, screenY ) )
            {
                return window.mouseButtonPressed( screenX, screenY, button );
            }
        }

        while( iterator.hasNext() )
        {
            final Window window = iterator.next();
            if( window.checkMouseOver( screenX, screenY ) )
            {
                getWindows().sendToTop( window.getNode() );
                return window.mouseButtonPressed( screenX, screenY, button );
            }
        }

        return false;
    }

    @Override
    public boolean checkMouseOver(
            int screenX,
            int screenY )
    {
        return runAndQuitWhen( windows.topTailIterator(),
                        w -> w.checkMouseOver( screenX, screenY ),
                        Window::failSafe );
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

    @Override
    public void clear()
    {
        windows.clear();
        super.clear();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Inner Classes And Patterns
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
