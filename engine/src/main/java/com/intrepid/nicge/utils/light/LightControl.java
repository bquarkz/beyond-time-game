package com.intrepid.nicge.utils.light;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class LightControl
{
	public static final int		ShadowResolution			= 2;
	
	public static final Vector3 ambientColorForNight = new Vector3( .3f, .3f, .3f );

	//ESTE DEVE SER SEMPRE POWER_OF_TWO
	private static final int RESOLUTION = ShadowResolution;
	public static final int blockSize = 10;//(int) EngineConfigs.tile_size / RESOLUTION;
	
	private float[][] fmat_StaticLight;
	private float[][] fmat_DinamicLight;
	
	public final int shadowMapSizeX;
	public final int shadowMapSizeY;
	public final int shadowMapSizeTotal;
	public final float natuaralShadowIntense;
	
	private LinkedList< LightEmiter > staticLightEmiters;
	private LinkedList< LightEmiter > dinamicLightEmiters;

	public LightControl( int sizeX, int sizeY, float NaturalShadowIntense )
	{
		//Invertido somente por uma questao visual
		shadowMapSizeX = sizeX * RESOLUTION;
		shadowMapSizeY = sizeY * RESOLUTION;
		shadowMapSizeTotal = shadowMapSizeX * shadowMapSizeY;
		natuaralShadowIntense = NaturalShadowIntense;
		
		fmat_StaticLight = null;
		fmat_DinamicLight = null;
		
		staticLightEmiters = new LinkedList<LightEmiter>();
		dinamicLightEmiters = new LinkedList<LightEmiter>();
	}
	
	private void sets( LightEmiter lightEmiter )
	{
		lightEmiter.setIncrementalStep( natuaralShadowIntense );
		lightEmiter.corrections( shadowMapSizeX, shadowMapSizeY );
		lightEmiter.setLightControl( this );
	}
	
	public void addStaticLightEmiter( LightEmiter lightEmiter )
	{
		sets( lightEmiter );
		staticLightEmiters.add( lightEmiter );
	}
	
	public void addDinamicLightEmiter( LightEmiter lightEmiter )
	{
		sets( lightEmiter );
		dinamicLightEmiters.add( lightEmiter );
	}
	
	public void removeDinamicLightEmiter( LightEmiter lightEmiter )
	{
		dinamicLightEmiters.remove( lightEmiter );
	}
	
	private float[][] instanceMatrix()
	{
		float[][] R = new float[ shadowMapSizeX ][ shadowMapSizeY ];
		for( int i = 0; i < shadowMapSizeX; i++ )
			for( int j = 0; j < shadowMapSizeY; j++ )
				R[ i ][ j ] = natuaralShadowIntense;
		
		return R;
	}
	
	public void makeStaticalLight()
	{
		fmat_StaticLight = instanceMatrix();
		
		for( LightEmiter le : staticLightEmiters )
			le.make( fmat_StaticLight );
	}
	
	public void makeDinamicalLight()
	{
		if( fmat_StaticLight == null ) return;

		if( fmat_DinamicLight == null )
			fmat_DinamicLight = new float[ shadowMapSizeX ][ shadowMapSizeY ];
		
		for( int i = 0; i < shadowMapSizeX; i++ )
			for( int j = 0; j < shadowMapSizeY; j++ )
				fmat_DinamicLight[ i ][ j ] = fmat_StaticLight[ i ][ j ];

		for( LightEmiter le : dinamicLightEmiters )
			le.make( fmat_DinamicLight );
	}
	
	public void displayShadowMaps( SpriteBatch batch, Texture mask )
	{
    	Color color_bkp = batch.getColor();
    	
    	int srcBlendFunc = batch.getBlendSrcFunc();
    	int dstBlendFunc = batch.getBlendDstFunc();
    	
    	batch.setBlendFunction( GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA );
    	
    	boolean c = batch.isBlendingEnabled();
    	
    	float[][] fmat;
    	if( fmat_DinamicLight != null ) fmat = fmat_DinamicLight;
    	else fmat = fmat_StaticLight;

    	float x;
    	float y;
    	
		for( int i = 0; i < shadowMapSizeX; i++ )
			for( int j = 0; j < shadowMapSizeY; j++ )
			{
				//rever se vai ficar invertido
				x = i * blockSize;
				y = j * blockSize;
				
				batch.setColor( ambientColorForNight.x,
								ambientColorForNight.y,
								ambientColorForNight.z,
								fmat[ i ][ j ] );
				batch.draw( mask, x, y );
			}
		
    	if( !c ) batch.disableBlending();
    	
    	batch.setBlendFunction( srcBlendFunc, dstBlendFunc );
    	
    	batch.setColor( color_bkp );
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		sb.append( "STATIC LIGHT\n" );
		for( int i = 0; i < shadowMapSizeX; i++ )
		{
			for( int j = 0; j < shadowMapSizeY; j++ )
			{
				sb.append( "[ " + String.format( "%3.0f", fmat_StaticLight[ i ][ j ] * 100 ) +  " ] " );
			}
			sb.append( '\n' );
		}
		
		sb.append( "DINAMIC LIGHT\n" );
		for( int i = 0; i < shadowMapSizeX; i++ )
		{
			for( int j = 0; j < shadowMapSizeY; j++ )
			{
				sb.append( "[ " + String.format( "%3.0f", fmat_DinamicLight[ i ][ j ] * 100 ) +  " ] " );
			}
			sb.append( '\n' );
		}
		
		return sb.toString();
	}
}
