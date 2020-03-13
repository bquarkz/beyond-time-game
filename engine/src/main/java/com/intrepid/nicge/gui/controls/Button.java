/*
 * Copyleft (C) 2016  Constantino, Nilton Rogerio <niltonrc@gmail.com>
 *
 * @author "Nilton R Constantino"
 * aKa bQUARKz <niltonrc@gmail, bquarkz@gmail.com>
 * <p>
 * Everything about the respective software copyright can be found in the
 * "LICENSE" file included in the project source tree.
 * <p>
 * The code was written based on study principles and can be enjoyed for
 * all community without problems.
 */
package com.intrepid.nicge.gui.controls;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.intrepid.nicge.gui.ComponentParameters;
import com.intrepid.nicge.gui.IStyle;
import com.intrepid.nicge.gui.WindowParameters;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.utils.MathUtils;
import com.intrepid.nicge.utils.MathUtils.Vector;
import com.intrepid.nicge.utils.animation.Animation;
import com.intrepid.nicge.utils.graphics.GraphicsBatch;

import static com.intrepid.nicge.utils.graphics.TextureWorks.createTexture;

public class Button
        extends AbstractControl
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************
    private static final int BUTTON_ACTION = MOUSE_BUTTON_LEFT;
    private static final int BUTTON_SUPPORT = MOUSE_BUTTON_RIGHT;

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private boolean isMouseOverMe;
    private boolean isActionClicked;
    private boolean isActionActive;
    private boolean isSupportClicked;
    private boolean isSupportActive;
    private IButtonAction supportRunner;
    private IButtonAction actionRunner;

    private float elapsedTime_s;

    private Animation idle;
    private Animation mouserOverMe;
    private Animation actionClicked;
    private Animation supportClicked;
    private String label;
    private BitmapFont font;
    private int padding;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    protected Button()
    {
        this( null );
    }

    protected Button( String label )
    {
        this.label = label;
        this.isMouseOverMe = false;
        this.isActionClicked = false;
        this.isActionActive = false;
        this.isSupportClicked = false;
        this.isSupportActive = false;
        this.actionRunner = () -> {};
        this.supportRunner = () -> {};
    }

    // ****************************************************************************************
    // Factories
    // ****************************************************************************************
    public static Button create( String label )
    {
        return new Button( label );
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    @Override
    public boolean checkMouseOver(
            int screenX,
            int screenY )
    {
        float x = getParameters().getPosition().getX();
        float y = getParameters().getPosition().getY();
        float width = getParameters().getWidth();
        float height = getParameters().getHeight();

        boolean cx = ( ( screenX >= x ) && ( screenX <= ( x + width ) ) );
        boolean cy = ( ( screenY >= y ) && ( screenY <= ( y + height ) ) );

        isMouseOverMe = cx && cy;

        return isMouseOverMe;
    }

    @Override
    public boolean mouseButtonPressed(
            int screenX,
            int screenY,
            int button )
    {
        checkMouseOver( screenX, screenY );

        if( isMouseOverMe )
        {
            elapsedTime_s = 0;
            if( button == BUTTON_ACTION )
            {
                isActionClicked = true;
            }
            else if( button == BUTTON_SUPPORT )
            {
                isSupportClicked = true;
            }
        }

        return isActionActive || isSupportActive;
    }

    @Override
    public boolean mouseButtonUnPressed(
            int screenX,
            int screenY,
            int button )
    {
        checkMouseOver( screenX, screenY );

        // the component will be active for just the current cycle
        if( button == BUTTON_ACTION )
        {
            if( isMouseOverMe && isActionClicked )
            {
                elapsedTime_s = 0;
                isActionActive = true;
            }
            else
            {
                isActionActive = false;
            }

            isActionClicked = false;
        }

        if( button == BUTTON_SUPPORT )
        {
            if( isMouseOverMe && isSupportActive )
            {
                elapsedTime_s = 0;
                isSupportActive = true;
            }
            else
            {
                isSupportActive = false;
            }

            isSupportClicked = false;
        }

        return !isActionActive && !isSupportActive;
    }

    @Override
    public void dragged(
            int screenX,
            int screenY,
            int button )
    {
    }

    @Override
    public void update()
    {
        final Vector parentPosition = getParentPosition();
        getParameters().setPosition( MathUtils
                .conversion
                .gdxCoordinates( Vector.sum( parentPosition, getRelativePosition() ) ) );

        elapsedTime_s += Game.time.getGdxRawDeltaTime_s();

        if( isActionActive )
        {
            isActionActive = false;
            actionRunner.takeAction();
        }

        if( isSupportActive )
        {
            isSupportActive = false;
            supportRunner.takeAction();
        }
    }

    @Override
    public void display( GraphicsBatch batch )
    {
        TextureRegion tr = idle != null
                ? idle.getKeyFrame( elapsedTime_s )
                : null;

        if( mouserOverMe != null && isMouseOverMe )
        {
            tr = mouserOverMe.getKeyFrame( elapsedTime_s );
        }

        if( actionClicked != null && isActionClicked )
        {
            tr = actionClicked.getKeyFrame( elapsedTime_s );
        }
        else if( supportClicked != null && isSupportClicked )
        {
            tr = supportClicked.getKeyFrame( elapsedTime_s );
        }

        if( tr != null )
        {
            float x = getParameters().getPosition().getX();
            float y = getParameters().getPosition().getY();
            batch.draw( tr, x, y, getParameters().getWidth(), getParameters().getHeight() );
            if( font != null && label != null )
            {
                float xx = x + ( getParameters().getWidth() - label.length() * padding ) / 2.0f;
                float yy = y + ( ( getParameters().getHeight() - font.getCapHeight() ) / 2.0f ) + padding;
                font.draw( batch, label, xx, yy );
            }
        }
    }

    public void mouseIsNotOverMe()
    {
        isMouseOverMe = false;
    }

    @Override
    public void bindAssets()
    {
        getParent().ifPresent( parent -> {
            final ComponentParameters parameters = parent.getParameters();
            if( parameters == null ) return;
            if( !( parameters instanceof WindowParameters ) ) return;
            IStyle style = ( (WindowParameters)parameters ).getStyle();
            IStyle.IButtonSchema schema = style.getGeneralButtonSchema();
            font = style.getGeneralButtonSchema().getFont();
            if( font != null )
            {
                padding = style.getGeneralButtonSchema().getPadding();
                final Color textColor = style.getGeneralButtonSchema().getTextColor();
                if( textColor != null )
                {
                    font.setColor( textColor );
                }
            }
            idle = new Animation( createTexture( 2, 2, schema.getIDLE() ) );
            mouserOverMe = new Animation( createTexture( 2, 2, schema.getMouseOverMe() ) );
            actionClicked = new Animation( createTexture( 2, 2, schema.getActionClicked() ) );
            supportClicked = new Animation( createTexture( 2, 2, schema.getSupportClicked() ) );
            setIdle( idle );
            setMouserOverMe( mouserOverMe );
            setActionClicked( actionClicked );
            setSupportClicked( supportClicked );
        } );
    }

    @Override
    public void unBindAssets()
    {
        getParent().ifPresent( parent -> {
//            idle.dispose();
//            mouserOverMe.dispose();
//            actionClicked.dispose();
//            supportClicked.dispose();
        } );
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    public String getLabel()
    {
        return label;
    }

    public void setLabel( String label )
    {
        this.label = label;
    }

    final public void setSupportRun( IButtonAction supportRunner )
    {
        this.supportRunner = supportRunner;
    }

    final public void setActionRun( IButtonAction actionRunner )
    {
        this.actionRunner = actionRunner;
    }

    final public void setIdle( Animation idle )
    {
        this.idle = idle;
    }

    final public void setActionClicked( Animation actionClicked )
    {
        this.actionClicked = actionClicked;
    }

    final public void setSupportClicked( Animation supportClicked )
    {
        this.supportClicked = supportClicked;
    }

    final public void setMouserOverMe( Animation mouserOverMe )
    {
        this.mouserOverMe = mouserOverMe;
    }

    final public void setIdle( Texture idle )
    {
        this.idle = new Animation( idle );
    }

    final public void setActionClicked( Texture actionClicked )
    {
        this.actionClicked = new Animation( actionClicked );
    }

    final public void setSupportClicked( Texture supportClicked )
    {
        this.supportClicked = new Animation( supportClicked );
    }

    final public void setMouserOverMe( Texture mouserOverMe )
    {
        this.mouserOverMe = new Animation( mouserOverMe );
    }

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
    @FunctionalInterface
    public interface IButtonAction
    {
        void takeAction();
    }
}
