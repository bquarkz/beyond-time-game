package com.intrepid.studio.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.intrepid.nicge.gui.Bundle;
import com.intrepid.nicge.gui.Window;
import com.intrepid.nicge.gui.WindowsManager;
import com.intrepid.studio.gui.styles.Styles;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.theater.cameras.Camera;
import com.intrepid.nicge.theater.scene.GameScene;
import com.intrepid.nicge.theater.scene.IScene;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;
import com.intrepid.nicge.utils.graphics.TextureWorks;
import com.intrepid.studio.Resources;
import com.intrepid.studio.gui.WindowActions;

@GameScene
public class MainScene
    implements IScene
{
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constants
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final Color BACKGROUND_COLOR = new Color( 0xaabfbaff );

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Special Fields And Injections
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Fields
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Camera camera;

    private final Texture background;
    private final WindowsManager windowsManager;

    private Bundle< Window > windowAction;

    private BitmapFont font;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public MainScene()
    {
        background = TextureWorks.createTexture( 2, 2, BACKGROUND_COLOR );
        windowsManager = WindowsManager.create( Styles.GREEN_ORANGE );
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
    public void configureGraphicsBatch( GraphicsBatch stageBatch )
    {
        camera = Game.graphics.newNativeCamera();
        stageBatch.setProjectionMatrix( camera.combined );
    }

    @Override
    public void prepareEnvironment()
    {
        windowAction = windowsManager.addWindow( new WindowActions() );
    }

    @Override
    public void bindAssets()
    {
        windowsManager.bindAssets();

        font = Game.common.getAsset( Resources.Fonts.FIRST_TEST );
        font.setColor( Color.BLACK );
    }

    @Override
    public void unBindAssets()
    {
        windowsManager.unBindAssets();
        windowsManager.clear();
        background.dispose();
    }

    @Override
    public void inputControlLogic()
    {

    }

    @Override
    public void update()
    {
        camera.update();
        windowsManager.update();
    }

    @Override
    public void display( GraphicsBatch batch )
    {
        batch.begin();
        batch.draw( background, 0, 0,
                Game.common.getGameConfiguration().getNativeResolutionWidth(),
                Game.common.getGameConfiguration().getNativeResolutionHeight() );

        font.draw( batch, "TESTE 123", 50, 680 );

        batch.end();
        this.windowsManager.display();
    }

    @Override
    public void simulation()
    {

    }

    @Override
    public void start()
    {
        windowsManager.openWindow( windowAction );
    }

    @Override
    public void stop()
    {

    }

    @Override
    public void hibernate()
    {

    }

    @Override
    public void wakeup()
    {

    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Inner Classes And Patterns
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
