package com.intrepid.nicge.utils.light;


public class LE_Circle extends LightEmiter
{
	public LE_Circle( int posx, int posy, int strenght )
	{
		super( posx, posy, strenght );
	}
	
	@Override
	public void corrections( int mapSizeX, int mapSizeY )
	{
		super.corrections( mapSizeX, mapSizeY );
		strenght++;
	}
	
	@Override
	public float[][] make( float[][] mat )
	{
		float[][] bkp = new float[ mat.length ][ mat[ 0 ].length ];
		
		for( int i = 0; i < getStrenght(); i++ )
		{
			for( int j = 0; j < i; j++ )
			{
				for( int x = base_posx + i,
					     y = base_posy;
						
					y <= ( base_posy + i );
					
					y++, x-- )
				{
					if( ( x < mat.length ) && ( y < mat[ 0 ].length ) )
						bkp[ x ][ y ] = mat[ x ][ y ];
				}

				for( int x = base_posx + i,
					     y = base_posy;
						
					y >= ( base_posy - i );
					
					y--, x-- )
				{
					if( ( x < mat.length ) && ( y > 0 ) )
						bkp[ x ][ y ] = mat[ x ][ y ];
				}

				for( int x = base_posx - i,
					     y = base_posy;
						
					y <= ( base_posy + i );
					
					y++, x++ )
				{
					if( ( x > 0 ) && ( y < mat[ 0 ].length ) )
						bkp[ x ][ y ] = mat[ x ][ y ];
				}
				
				for( int x = base_posx - i,
					     y = base_posy;
						
					y >= ( base_posy - i );
					
					y--, x++ )
				{
					if( ( x > 0 ) && ( y > 0 ) )
						bkp[ x ][ y ] = mat[ x ][ y ];
				}
			}
		}

		/*
		for( int i = 0; i < bkp.length; i++ )
			for( int j = 0; j < bkp[ 0 ].length; j++ )
				bkp[ i ][ j ] = mat[ i ][ j ]; 
		*/
		
		mat[ base_posx ][ base_posy ] = calcLight( bkp[ base_posx ][ base_posy ], 0 );

		for( int i = 1; i < getStrenght(); i++ )
		{
			float shadow = returnShadowInc( i );
			
			for( int j = 0; j < i; j++ )
			{
				for( int x = base_posx + i,
					     y = base_posy;
						
					y <= ( base_posy + i );
					
					y++, x-- )
				{
					if( ( x < mat.length ) && ( y < mat[ 0 ].length ) )
						mat[ x ][ y ] = calcLight( bkp[ x ][ y ], shadow );
				}

				for( int x = base_posx + i,
					     y = base_posy;
						
					y >= ( base_posy - i );
					
					y--, x-- )
				{
					if( ( x < mat.length ) && ( y > 0 ) )
						mat[ x ][ y ] = calcLight( bkp[ x ][ y ], shadow );
				}

				for( int x = base_posx - i,
					     y = base_posy;
						
					y <= ( base_posy + i );
					
					y++, x++ )
				{
					if( ( x > 0 ) && ( y < mat[ 0 ].length ) )
						mat[ x ][ y ] = calcLight( bkp[ x ][ y ], shadow );
				}
				
				for( int x = base_posx - i,
					     y = base_posy;
						
					y >= ( base_posy - i );
					
					y--, x++ )
				{
					if( ( x > 0 ) && ( y > 0 ) )
						mat[ x ][ y ] = calcLight( bkp[ x ][ y ], shadow );
				}
			}
		}
		return null;
	}
}
