/**
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
package com.intrepid.game.scenes;

import java.util.Set;

import com.intrepid.game.Resources;
import com.intrepid.nicge.content.Dependency;
import com.intrepid.nicge.content.DependencyResource;
import com.intrepid.nicge.content.Resource;

@Dependency( CombatScene.class )
public class CombatSceneDependency extends DependencyResource
{
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
    protected void setDependencies( Set< Resource< ? > > resources )
    {
        resources.add( Resources.Textures.BADLOGIC_LOGO );
        resources.add( Resources.Animations.SENTINEL );

        resources.add( Resources.Textures.TESTE_CARGA_01 );
        resources.add( Resources.Textures.TESTE_CARGA_02 );
        resources.add( Resources.Textures.TESTE_CARGA_03 );
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
