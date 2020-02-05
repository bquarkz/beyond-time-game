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
package com.intrepid.nicge.kernel;

import com.badlogic.gdx.ApplicationAdapter;

public abstract class AbstractAdapter extends ApplicationAdapter
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private final Application app;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public < T extends Boot< ? extends AppConfiguration > > AbstractAdapter( T boot )
    {
        app = getApplication( boot );
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    @SuppressWarnings( "rawtypes" )
    protected abstract < T extends Boot > Application getApplication( T boot );

    @Override
    public final void create()
    {
        app.boot();
    }

    @Override
    public final void resize(
            int width,
            int height )
    {
        app.resize( width, height );
    }

    @Override
    public final void render()
    {
        app.loop();
    }

    @Override
    public final void pause()
    {
        app.pause();
    }

    @Override
    public final void resume()
    {
        app.resume();
    }

    @Override
    public final void dispose()
    {
        app.dispose();
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ***************************************************************************************
}