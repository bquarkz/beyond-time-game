package com.intrepid.nicge.utils.light;

public class LE_LineDown extends LightEmiter
{
	public LE_LineDown(int posx, int posy, int strenght)
	{
		super(posx, posy, strenght);
	}
	
	@Override
	public float[][] make(float[][] mat)
	{
		for( int i = 0; i < getStrenght(); i++ )
		{
			int p = base_posy - i;
			if( p >= 0 )
				mat[ base_posx ][ p ] = calcLight( mat[ base_posx ][ p ], i ); 
			else i = getStrenght();
		}
		
		return null;
	}
}
