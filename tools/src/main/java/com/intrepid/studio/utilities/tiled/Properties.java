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
package com.intrepid.studio.utilities.tiled;

public class Properties
{
    private int stageID;
    private int sceneID;
    private String sceneName;
    private String stageName;

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append( "stage ID: " ).append( stageID ).append( "\n" );
        sb.append( "scene ID: " ).append( sceneID ).append( "\n" );
        sb.append( "Scene Name: " ).append( sceneName ).append( "\n" );
        sb.append( "Stage Name: " ).append( stageName ).append( "\n" );
        return sb.toString();
    }

    public int getStageID()
    {
        return stageID;
    }

    public int getSceneID()
    {
        return sceneID;
    }

    public String getSceneName()
    {
        return sceneName;
    }

    public String getStageName()
    {
        return stageName;
    }
}
