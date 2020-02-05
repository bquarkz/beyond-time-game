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
 * all community without problems.
 */
package com.intrepid.nicge.content;

import java.util.Set;

public final class DynamicDependencyPack {
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private Class< ? >[] dynamicDependencies;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public DynamicDependencyPack( Set< Class< ? > > dynamicDependencies ) {
        this.dynamicDependencies = dynamicDependencies.toArray( new Class< ? >[ dynamicDependencies.size() ] );
    }

    public DynamicDependencyPack() {
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    public Class< ? >[] getDynamicDependencies() {
        return dynamicDependencies;
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
