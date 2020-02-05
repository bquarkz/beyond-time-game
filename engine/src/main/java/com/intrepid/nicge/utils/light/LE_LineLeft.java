package com.intrepid.nicge.utils.light;

public class LE_LineLeft extends LightEmiter
{
	public LE_LineLeft(int posx, int posy, int strenght)
	{
		super(posx, posy, strenght);
	}
	
	@Override
	public float[][] make(float[][] mat)
	{
		for( int i = 0; i < getStrenght(); i++ )
		{
			int p = base_posx + i;
			if( p < mat[ 0 ].length )
				mat[ p ][ base_posy ] = calcLight( mat[ p ][ base_posy ], i ); 
			else i = getStrenght();
		}
		
		return null;
	}
}
