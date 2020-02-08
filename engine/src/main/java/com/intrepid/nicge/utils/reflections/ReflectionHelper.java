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
package com.intrepid.nicge.utils.reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.intrepid.nicge.content.Dependency;
import com.intrepid.nicge.content.DependencyResource;
import com.intrepid.nicge.content.ILoadable;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.theater.scene.GameScene;
import com.intrepid.nicge.theater.scene.IScene;
import com.intrepid.nicge.utils.fsmachine.IFSMachineDefinition;
import com.intrepid.nicge.utils.fsmachine.HashFSMachineDefinition;
import com.intrepid.nicge.utils.logger.Log;

public class ReflectionHelper {
	public static IFSMachineDefinition< IScene > autoSearchSceneDefinitionsByAnnotations() {
		IFSMachineDefinition< IScene > definitions = new HashFSMachineDefinition<>();
		Set< Class< ? extends IScene > > scenes = findScenes();
		for( Class< ? extends IScene > scene : scenes ) {
			boolean isStartScene = false;
			// check if "GameScene" annotation is present then add to machine
			if( scene.isAnnotationPresent( GameScene.class ) ) {
				GameScene annotation = scene.getAnnotation( GameScene.class );
				isStartScene = annotation.start();
				definitions.registerState( scene, isStartScene );
			} else {
				isStartScene = false;
			}
		}
		
		return definitions;
	}
	
    public static Map< Class< ? >, ILoadable > createDependencyContainer() {
        Map< Class< ? >, ILoadable > depedencyContainer = new HashMap<>();
        
        Reflection reflections = Game.util.getReflections();
        Set< Class< ? > > dependencyClasses = reflections.get().getTypesAnnotatedWith( Dependency.class );
        for( Class< ? > dependencyClass : dependencyClasses ) {
            Dependency annotation = dependencyClass.getAnnotation( Dependency.class );
            Class< ? > mainClass = annotation.value();
            
            boolean needsIntanciation = dependencyClass.getSuperclass() == DependencyResource.class;
            if( needsIntanciation ) {
                try {
                    ILoadable loadable = (ILoadable) dependencyClass.newInstance();
                    Log.from( Game.class ).info( "loading dependency class:[ " + dependencyClass.getName() + " ]; to main class:[ " + mainClass.getName() +" ]" );
                    depedencyContainer.put( mainClass, loadable );
                } catch ( InstantiationException | IllegalAccessException e ) {
                    throw new RuntimeException( "> Class: " + dependencyClass.getName() + "; don't have a default Constructor <", e );
                }
            } else {
                throw new RuntimeException( "> Class: " + dependencyClass.getName() + "; is not well done. Check about: " + DependencyResource.class.getName() );
            }         
        }
        
        return depedencyContainer;
    }

	private static Set< Class< ? extends IScene > > findScenes() {
		Reflection reflections = Game.util.getReflections();
		Set< Class< ? extends IScene > > scenes = reflections.get().getSubTypesOf( IScene.class );
		return scenes;
	}
}
