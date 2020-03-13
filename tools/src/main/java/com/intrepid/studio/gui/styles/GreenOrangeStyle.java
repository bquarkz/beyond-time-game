package com.intrepid.studio.gui.styles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.intrepid.nicge.content.Dependency;
import com.intrepid.nicge.content.DependencyResource;
import com.intrepid.nicge.content.IResource;
import com.intrepid.nicge.gui.IStyle;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.studio.Resources;
import com.intrepid.studio.scenes.MainScene;

import java.util.Set;

class GreenOrangeStyle
    implements IStyle
{
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constants
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final Color WINDOW_TITLE_BAR_COLOR = new Color( 0xdc5b21ff );
    private static final Color WINDOW_TITLE_TEXT_COLOR = new Color( 0x000000ff );
    private static final Color WINDOW_BODY_BACKGROUND_COLOR = new Color( 0x70ab8faf );
    private static final Color WINDOW_CLOSE_BUTTON_IDLE = new Color( 0x999999af );
    private static final Color WINDOW_CLOSE_BUTTON_MOUSE_OVER = new Color( 0xccccccff );
    private static final Color WINDOW_CLOSE_BUTTON_ACTION = new Color( 0x00bb55ff );
    private static final Color WINDOW_CLOSE_BUTTON_SUPPORT = WINDOW_CLOSE_BUTTON_MOUSE_OVER;

    private static final Color GENERAL_BUTTON_IDLE = new Color( 0x999999af );
    private static final Color GENERAL_BUTTON_MOUSE_OVER = new Color( 0xccccccff );
    private static final Color GENERAL_BUTTON_ACTION = new Color( 0xdd0055ff );
    private static final Color GENERAL_BUTTON_SUPPORT = GENERAL_BUTTON_MOUSE_OVER;
    private static final Color GENERAL_BUTTON_TEXT_COLOR = new Color( 0x00afafff );

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Special Fields And Injections
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Fields
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public GreenOrangeStyle()
    {
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
    public IButtonSchema getGeneralButtonSchema()
    {
        return new IButtonSchema()
        {
            @Override
            public BitmapFont getFont()
            {
                return Game.common.getAsset( Resources.Fonts.FIRST_TEST );
            }

            @Override
            public int getPadding()
            {
                return 10;
            }

            @Override
            public Color getTextColor()
            {
                return GENERAL_BUTTON_TEXT_COLOR;
            }

            @Override
            public Color getIDLE()
            {
                return GENERAL_BUTTON_IDLE;
            }

            @Override
            public Color getMouseOverMe()
            {
                return GENERAL_BUTTON_MOUSE_OVER;
            }

            @Override
            public Color getActionClicked()
            {
                return GENERAL_BUTTON_ACTION;
            }

            @Override
            public Color getSupportClicked()
            {
                return GENERAL_BUTTON_SUPPORT;
            }
        };
    }

    @Override
    public IWindowSchema getWindowSchema()
    {
        return new IWindowSchema()
        {
            @Override
            public Color getTitleBarColor()
            {
                return WINDOW_TITLE_BAR_COLOR;
            }

            @Override
            public Color getBodyBackgroundColor()
            {
                return WINDOW_BODY_BACKGROUND_COLOR;
            }

            @Override
            public Color getTitleTextColor()
            {
                return WINDOW_TITLE_TEXT_COLOR;
            }

            @Override
            public IButtonSchema getCloseButtonSchema()
            {
                return new IButtonSchema()
                {
                    @Override
                    public BitmapFont getFont()
                    {
                        return null;
                    }

                    @Override
                    public int getPadding()
                    {
                        return 0;
                    }

                    @Override
                    public Color getTextColor()
                    {
                        return Color.BLACK;
                    }

                    @Override
                    public Color getIDLE()
                    {
                        return WINDOW_CLOSE_BUTTON_IDLE;
                    }

                    @Override
                    public Color getMouseOverMe()
                    {
                        return WINDOW_CLOSE_BUTTON_MOUSE_OVER;
                    }

                    @Override
                    public Color getActionClicked()
                    {
                        return WINDOW_CLOSE_BUTTON_ACTION;
                    }

                    @Override
                    public Color getSupportClicked()
                    {
                        return WINDOW_CLOSE_BUTTON_SUPPORT;
                    }
                };
            }
        };
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Inner Classes And Patterns
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Dependency( MainScene.class )
    public static class GreenOrangeStyleDependencies extends DependencyResource
    {
        @Override
        protected void setDependencies( Set< IResource< ? > > resources )
        {
            resources.add( Resources.Fonts.FIRST_TEST );
        }
    }
}
