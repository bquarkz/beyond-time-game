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
package com.intrepid.studio.enviroment;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.intrepid.nicge.ui.IComponent;
import com.intrepid.studio.animation.GenerateAnimationPackInfo;
import com.intrepid.studio.component.ButtonFactory;
import com.intrepid.studio.tilemap.GenerateMapPackInfo;
import com.intrepid.studio.tilemap.TileSetGutter;

public class ButtonEnviroment extends Enviroment {
    // ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	@Override
	public void addComponents( List<IComponent> list ) {
		int x = 775;
		int y = 470;
		int width = 180;
		int height = 60;
		int space = 5;
		
		list.add( new ButtonFactory().create( x, y, width, height, "ANIMATION PACK",
		        new GenerateAnimationPackInfo( Gdx.files ) ) );
		
		y -= height + space;
		list.add( new ButtonFactory().create( x, y, width, height, "TILESET GUTTER",
		        new TileSetGutter( Gdx.files ) ) );

		y -= height + space;
		list.add( new ButtonFactory().create( x, y, width, height, "MAP PACK", 
		        new GenerateMapPackInfo( Gdx.files ) ) );
		
		y -= height + space;
		list.add( new ButtonFactory().create( x, y, width, height, "TESTE 03" ) );
		
		y -= height + space;
		list.add( new ButtonFactory().create( x, y, width, height, "TESTE 04" ) );
		
		y -= height + space;
		list.add( new ButtonFactory().create( x, y, width, height, "TESTE 05" ) );
		
		y -= height + space;
		list.add( new ButtonFactory().create( x, y, width, height, "TESTE 06" ) );

		y -= height + space;
		list.add( new ButtonFactory().create( x, y, width, height, "BUILD ALL",
		                                                new GenerateAnimationPackInfo( Gdx.files ),
		                                                new GenerateMapPackInfo( Gdx.files ),
		                                                new TileSetGutter( Gdx.files )
		                                                ) );
	}

	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
}
