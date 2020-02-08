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
package com.intrepid.studio.kernel;

import java.util.Set;

import com.intrepid.nicge.content.IResource;
import com.intrepid.nicge.kernel.Boot;
import com.intrepid.nicge.kernel.game.GameConfiguration;

public class StudioBoot implements Boot< GameConfiguration > {
	private GameConfiguration config;

	public StudioBoot( GameConfiguration config ) {
		this.config = config;
	}

	@Override
	public void loader( Set< IResource< ? > > resources ) {
		System.out.println("carregando os assets");
	}

	@Override
	public void initialization() {
		System.out.println("inicializando os objetos");
	}

	@Override
	public GameConfiguration getConfig() {
		return this.config;
	}
}