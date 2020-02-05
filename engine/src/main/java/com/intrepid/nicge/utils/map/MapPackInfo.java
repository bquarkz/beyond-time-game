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
package com.intrepid.nicge.utils.map;

import com.intrepid.nicge.content.DynamicDependencyPack;

public class MapPackInfo {
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private final String sceneName;
    private final int sceneId;
    private final String stageName;
    private final int stageId;
    private final String tilesetAssetSourceFilename;
    
    private final int mapWidth;
    private final int mapHeight;
    private final int mapSize;
    
    private final int[] tileIdIndexer;
    private final SoloType[] soloTypeIndexer;
    private final ShapeCollisionType[] shapeCollisionIndexer;
    private final EntityPosition[] monsterPositionIndexer;
    private final EntityPosition[] ornamentPositionIndexer;
    private final int passableTileCounter;
    private final DynamicDependencyPack dynamicDependenyPack;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public MapPackInfo(
            String sceneName,
            int sceneId,
            String stageName,
            int stageId,
            String tilesetAssetSourceFilename,
            int mapWidth,
            int mapHeight,
            int[] tileIdIndexer,
            SoloType[] soloTypeIndexer,
            ShapeCollisionType[] shapeCollisionIndexer,
            EntityPosition[] monsterPositions,
            EntityPosition[] ornamentPositions,
            int passableTileCounter,
            DynamicDependencyPack dynamicDependenyPack
            ) {
        
        this.sceneName = sceneName;
        this.sceneId = sceneId;
        this.stageName = stageName;
        this.stageId = stageId;
        this.tilesetAssetSourceFilename = tilesetAssetSourceFilename;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.mapSize = mapWidth * mapHeight;
        this.tileIdIndexer = tileIdIndexer;
        this.soloTypeIndexer = soloTypeIndexer;
        this.shapeCollisionIndexer = shapeCollisionIndexer;
        this.monsterPositionIndexer = monsterPositions;
        this.ornamentPositionIndexer = ornamentPositions;
        this.passableTileCounter = passableTileCounter;
        this.dynamicDependenyPack = dynamicDependenyPack;
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public String getSceneName() {
        return sceneName;
    }
    
    public String getStageName() {
        return stageName;
    }

    public String getTilesetAssetSourceFilename() {
        return tilesetAssetSourceFilename;
    }
    
    public int getMapSize() {
        return mapSize;
    }
    
    public int getSceneId() {
        return sceneId;
    }
    
    public int getStageId() {
        return stageId;
    }
    
    public int getMapWidth() {
        return mapWidth;
    }
    
    public int getMapHeight() {
        return mapHeight;
    }
    
    public int[] getTileIdIndexer() {
        return tileIdIndexer;
    }
    
    public SoloType[] getSoloTypeIndexer() {
        return soloTypeIndexer;
    }
    
    public ShapeCollisionType[] getShapeCollisionIndexer() {
        return shapeCollisionIndexer;
    }
    
    public EntityPosition[] getMonsterPositionIndexer() {
        return monsterPositionIndexer;
    }
    
    public EntityPosition[] getOrnamentPositionIndexer() {
        return ornamentPositionIndexer;
    }
    
    public int getPassableTileCounter() {
        return passableTileCounter;
    }

    public DynamicDependencyPack getDynamicDependenyPack() {
        return dynamicDependenyPack;
    }

    public final SoloType getSoloTypeOnPosition( MapPoint mapPoint ) {
        return this.getSoloTypeOnPosition( mapPoint.getX(), mapPoint.getY() );
    }
    
    public final SoloType getSoloTypeOnPosition( int x, int y ) {
        int index = getIndexFrom( x, y );
        if( ( index < 0 ) || ( index >= mapSize ) ) return null;
        else return soloTypeIndexer[ index ];
    }
    
    public final MapPoint getMapPointFrom( int index ) {
        return new MapPoint( index % mapWidth, index / mapWidth );
    }
    
    public final int getIndexFrom( MapPoint mapPoint ) {
        return this.getIndexFrom( mapPoint.getX(), mapPoint.getY() );
    }
    
    public final int getIndexFrom( int x, int y ) {
        return x + ( y * mapWidth );
    }

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
