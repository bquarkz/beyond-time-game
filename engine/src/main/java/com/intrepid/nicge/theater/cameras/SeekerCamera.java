/**
 * Copyleft (C) 2016  Constantino, Nilton Rogerio <niltonrc@gmail.com>
 *
 * @author "Nilton R Constantino"
 * aKa bQUARKz <niltonrc@gmail, bquarkz@gmail.com>
 *
 * Everything about the respective software copyright can be found in the
 * "LICENSE" file included in the project source tree.
 *
 * The code was written based on study principles and can be enjoyed for
 * all comunity without problems.
 */
package com.intrepid.nicge.theater.cameras;

import com.badlogic.gdx.math.Vector2;
import com.intrepid.nicge.entities.Entity;

public class SeekerCamera extends Camera
{
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************
	private static final float hookEffect = 1.0f / 22.0f;

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private Vector2 v2Camera;
	private Vector2 v2Pivot;
	private Vector2 v2Translate;
	private float x0;
	private float y0;
	private float xf;
	private float yf;
	private Entity pivot;
	private boolean avoidHookEffect;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public SeekerCamera( int nativeResolutionWidth, int nativeResolutionHeight )
	{
		super( nativeResolutionWidth, nativeResolutionHeight );
		
		v2Camera = new Vector2();
		v2Pivot = new Vector2();

		// Cria uma ZONA MORTA, lugar onde a camera nao segue o pivot,
		// com um valor de 1/3 das extremidades da tela. 
		x0 = nativeResolutionWidth / 3;
		y0 = nativeResolutionHeight / 3;
		xf = nativeResolutionWidth - x0;
		yf = nativeResolutionHeight - y0;
		
		avoidHookEffect = false;
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	public final void setMapSize( float sizex, float sizey )
	{
		if( sizex < x0 ) sizex = x0;
		if( sizey < y0 ) sizey = y0;
		
		// Para o caso do mapa ter uma tamanho menor que x0 ou y0
		// toda a tela sera ZONA MORTA devido ao xf e yf serem ZERO. 
		xf = sizex - x0;
		yf = sizey - y0;
	}
	
	public final void setPivotSeeker( Entity pivot )
	{
		this.pivot = pivot;
		
		float xPivot = pivot.getPosition().x;
		float yPivot = pivot.getPosition().y;
		
		if( xPivot < x0 ) this.position.x = x0;
		if( xPivot > xf ) this.position.x = xf;

		if( yPivot < y0 ) this.position.y = y0;
		if( yPivot > yf ) this.position.y = yf;
	}

	public final void resetPosition( Entity pivot )
	{
		setPivotSeeker( pivot );
		this.position.x = pivot.getPosition().x;
		this.position.y = pivot.getPosition().y;
		
		avoidHookEffect = true;
	}

	private float getHookEffect()
	{
		return hookEffect;
	}
	
	@Override
	public final void update()
	{
		if( pivot != null )
		{
			// A camera ira seguir o pivot
			// Encontra o vetor translacao para mover a camera.
			v2Camera.set( -this.position.x, -this.position.y );
			v2Pivot.set( pivot.getPosition() );
			v2Translate = v2Pivot.add( v2Camera );
			if( avoidHookEffect ) {
				avoidHookEffect = false;
			} else {
				// aplica o hook effect
				// faz a translacao ser mais suave e com um efeito temporal
				v2Translate.scl( getHookEffect() );
			}
			
			// Casos em que a camera nao deve se movimentar
			float xPivot = pivot.getPosition().x;
			float yPivot = pivot.getPosition().y;
			boolean transx = ( ( ( xPivot < x0 )&&( this.position.x < x0 ) ) ||
							   ( ( xPivot > xf )&&( this.position.x > xf ) ) );
			boolean transy = ( ( ( yPivot < y0 )&&( this.position.y < x0 ) ) ||
							   ( ( yPivot > yf )&&( this.position.y > xf ) ) );
			if( transx ) v2Translate.x = 0;
			if( transy ) v2Translate.y = 0;
			
			this.translate( v2Translate );
		}
		
		super.update();
	}
	
	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
}