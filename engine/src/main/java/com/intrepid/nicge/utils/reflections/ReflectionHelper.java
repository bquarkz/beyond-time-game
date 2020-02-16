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
 * all comunity without problems.
 */
package com.intrepid.nicge.utils.reflections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.intrepid.nicge.content.Dependency;
import com.intrepid.nicge.content.DependencyResource;
import com.intrepid.nicge.content.ILoadable;
import com.intrepid.nicge.content.IResource;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.theater.scene.GameScene;
import com.intrepid.nicge.theater.scene.IScene;
import com.intrepid.nicge.utils.fsmachine.IFSMachineDefinition;
import com.intrepid.nicge.utils.fsmachine.HashFSMachineDefinition;
import com.intrepid.nicge.utils.logger.Log;

public class ReflectionHelper
{
    public static IFSMachineDefinition< IScene > autoSearchSceneDefinitionsByAnnotations()
    {
        final IFSMachineDefinition< IScene > definitions = new HashFSMachineDefinition<>();
        final Set< Class< ? extends IScene > > scenes = findScenes();
        for( Class< ? extends IScene > scene : scenes )
        {
            // check if "GameScene" annotation is present then add to machine
            if( scene.isAnnotationPresent( GameScene.class ) )
            {
                final GameScene annotation = scene.getAnnotation( GameScene.class );
                final boolean isStartScene = annotation.start();
                definitions.registerState( scene, isStartScene );
            }
        }

        return definitions;
    }

    public static Map< Class< ? >, ILoadable > createDependencyContainer()
    {
        final Map< Class< ? >, ILoadable > dependencyContainer = new HashMap<>();

        final Reflection reflections = Game.util.getReflections();
        final Set< Class< ? > > dependencyClasses = reflections.get().getTypesAnnotatedWith( Dependency.class );
        for( Class< ? > dependencyClass : dependencyClasses )
        {
            for( Class< ? > sceneClass : dependencyClass.getAnnotation( Dependency.class ).value() )
            {
                boolean shouldInstantiation = dependencyClass.getSuperclass() == DependencyResource.class;
                if( shouldInstantiation )
                {
                    try
                    {
                        final ILoadable partialDependencies = (ILoadable)dependencyClass.newInstance();
                        Log.from( Game.class )
                           .info( "loading dependency class:[ " + dependencyClass.getName() + " ]; to main class:[ " + sceneClass.getName() + " ]" );
                        if( dependencyContainer.containsKey( sceneClass ) )
                        {
                            final ILoadable moreDependencies = dependencyContainer.get( sceneClass );
                            final Set< IResource< ? > > fullResources = new HashSet<>();
                            moreDependencies.injectResourcesOn( fullResources );
                            partialDependencies.injectResourcesOn( fullResources );
                            dependencyContainer.replace( sceneClass, ILoadable.with( fullResources ) );
                        }
                        else
                        {
                            dependencyContainer.put( sceneClass, partialDependencies );
                        }

                    }
                    catch( InstantiationException | IllegalAccessException e )
                    {
                        throw new RuntimeException( "> Class: " + dependencyClass.getName() + "; don't have a default Constructor <", e );
                    }
                }
                else
                {
                    throw new RuntimeException( "> Class: " + dependencyClass.getName() + "; is not well done. Check about: " + DependencyResource.class.getName() );
                }
            }
        }

        return dependencyContainer;
    }

    private static Set< Class< ? extends IScene > > findScenes()
    {
        Reflection reflections = Game.util.getReflections();
        Set< Class< ? extends IScene > > scenes = reflections.get().getSubTypesOf( IScene.class );
        return scenes;
    }
}
