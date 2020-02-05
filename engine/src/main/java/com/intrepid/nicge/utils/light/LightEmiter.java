package com.intrepid.nicge.utils.light;

public abstract class LightEmiter
{
	//pensar em conceitos de distancia e intensidade.
	//private LightControl control;
	
	private float inc_step;
	private float deltaValue;

	protected int strenght;
	protected int base_posx;
	protected int base_posy;
	
	public LightEmiter( int posx, int posy, int strenght )
	{
		update( posx, posy, strenght );
	}
	
	public void setLightControl( LightControl control )
	{
		//this.control = control;
	}
	
	public void update( float x_screen, float y_screen )
	{
		/*
		base_posx = (int) ( x_screen / LightControl.minimalBlockSize )
				* LightControl.ShadowResolution;
		base_posy = (int) ( y_screen / LightControl.minimalBlockSize )
				* LightControl.ShadowResolution;
		*/		
		base_posx = (int) ( x_screen / LightControl.blockSize );
		base_posy = (int) ( y_screen / LightControl.blockSize );
	}
	
	public void update( float x_screen, float y_screen, int strenght )
	{
		this.strenght = strenght * LightControl.ShadowResolution;
		/*
		base_posx = (int) ( x_screen / LightControl.minimalBlockSize )
		 		* LightControl.ShadowResolution;
		base_posy = (int) ( y_screen / LightControl.minimalBlockSize )
				* LightControl.ShadowResolution;
		*/
		base_posx = (int) ( x_screen / LightControl.blockSize );
		base_posy = (int) ( y_screen / LightControl.blockSize );
	}
	
	public void update( int x, int y )
	{
		base_posx = x * LightControl.ShadowResolution;
		base_posy = y * LightControl.ShadowResolution;
	}
	
	public void update( int x, int y, int strenght )
	{
		this.strenght = strenght * LightControl.ShadowResolution;

		base_posx = x * LightControl.ShadowResolution;
		base_posy = y * LightControl.ShadowResolution;
	}

	
	public void corrections( int mapSizeX, int mapSizeY )
	{
		base_posy = mapSizeY - base_posy - 1;
	}
	
	public int getStrenght() { return strenght; }
	
	public void setIncrementalStep( float deltaValue )
	{
		this.deltaValue = deltaValue;
		inc_step = (float) deltaValue / (float) strenght; 
	}
	
	protected float getIncStep() { return inc_step; }
	protected float getDeltaValue() { return deltaValue; }
	
	protected float returnShadowInc( int step )
	{
		return getDeltaValue() - ( getIncStep() * step );
	}
	
	protected float calcLight( float tmp, float shadow )
	{
		tmp -= shadow;
		if( tmp < 0.0f ) tmp = 0.0f;
		
		return tmp;
	}
	
	protected float calcLight( float tmp, int step )
	{
		tmp -= getDeltaValue() - ( getIncStep() * step );
		if( tmp < 0.0f ) tmp = 0.0f;
		
		return tmp;
	}
	
	public abstract float[][] make( float[][] mat );
}
