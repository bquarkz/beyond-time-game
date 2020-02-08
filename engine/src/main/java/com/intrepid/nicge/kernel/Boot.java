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
package com.intrepid.nicge.kernel;

import java.util.Set;

import com.intrepid.nicge.content.IResource;

public interface Boot< T extends AppConfiguration > {
	void loader( Set< IResource< ? > > resources );
	void initialization();
	T getConfig();
}
