package com.intrepid.launcher;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.intrepid.nicge.kernel.AppConfiguration;
import com.intrepid.nicge.kernel.Boot;

public abstract class AbstractLauncher< T extends Boot< ? extends AppConfiguration > >
{
    private T boot;
    private LwjglApplicationConfiguration lwjglConfig;

    public AbstractLauncher( T boot )
    {
        this.boot = boot;

        AppConfiguration config = boot.getConfig();
        lwjglConfig = new LwjglApplicationConfiguration();
        lwjglConfig.initialBackgroundColor = new Color( 0f, 0f, 0f, 1f );
        lwjglConfig.title = config.getTitle();
        lwjglConfig.fullscreen = config.isFullscreen();
        lwjglConfig.resizable = config.isResizable();
        lwjglConfig.width = config.getWindowResolutionWidth();
        lwjglConfig.height = config.getWindowResolutionHeight();
        lwjglConfig.samples = config.getMSAA();
        lwjglConfig.foregroundFPS = config.getRefreshRate();
        lwjglConfig.vSyncEnabled = config.getVSync();
    }

    public void run()
    {
        new LwjglApplication( createAdapter( boot ), lwjglConfig );
    }

    protected abstract ApplicationListener createAdapter( T boot );
}
