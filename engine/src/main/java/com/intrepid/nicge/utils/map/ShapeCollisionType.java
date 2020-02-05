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

import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.intrepid.nicge.kernel.Configurations;

// MASCARA DE SHAPES:
//  1.00f  *       *       *       *       *
//  
//  
//  0.75f  *       *       *       *       *
// 
// 
//  0.50f  *       *       *       *       *
// 
// 
//  0.25f  *       *       *       *       *
// 
// 
//  0.00f  *       *       *       *       *
//  
//       0.00f   0.25f   0.50f   0.75f   1.00f
//  
//  EXEMPLO:
//  1.00f  O-------O-------O-------O-------O
//         |                               |
//         |                               |
//  0.75f  O       *       *       *       O
//         |                               |
//         |                               |
//  0.50f  O       *       O-------O-------O
//         |               |
//         |               |
//  0.25f  O       *       O       *       *
//         |               |
//         |               |
//  0.00f  O-------O-------O       *       *
//  
//       0.00f   0.25f   0.50f   0.75f   1.00f
//  
//  TILE: No exemplo, somam-se um total de 06 vertices, que sao descritos abaixo:
//                X      Y
//           1) 0.00f, 0.00f
//           2) 0.50f, 0.00f
//           3) 0.50f, 0.50f
//           4) 1.00f, 0.50f
//           5) 1.00f, 1.00f
//           6) 0.00f, 1.00f
// OBS: Eh bom lembrar que a entrada dos vertices deve ser no sentido anti-horario.
// E que o maximo de vertices aceito para tratamento pelo Box2d sao oito, ou seja,
// a figura com o maior numero de lados que podemos criar eh um octogono.
public enum ShapeCollisionType {
    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
    //Shape vazio, nao sera criado nenhum corpo fisico.  // 0
    EMPTY_SQUARE( null ),
    //Um quadrado em toda a sua plenitude :) // 1
    FULL_SQUARE( new float[]{ 0.00f, 0.00f, 1.00f, 0.00f, 1.00f, 1.00f, 0.00f, 1.00f } ),
    //Um triangulo com o angulo reto para direita-baixo // 2
    TRIANGLE_RIGHT_DOWN( new float[]{ 0.00f, 0.00f, 1.00f, 0.00f, 1.00f, 1.00f } ), 
    //Um triangulo com o angulo reto para esquerda-baixo // 3
    TRIANGLE_LEFT_DOWN( new float[]{ 0.00f, 0.00f, 1.00f, 0.00f, 0.00f, 1.00f } ), 
    //Um triangulo com o angulo reto para direita-cima // 4
    TRIANGLE_RIGHT_UP( new float[]{ 0.00f, 1.00f, 1.00f, 0.00f, 1.00f, 1.00f } ), 
    //Um triangulo com o angulo reto para direita-baixo. // 5
    TRIANGLE_LEFT_UP( new float[]{ 0.00f, 0.00f, 1.00f, 1.00f, 0.00f, 1.00f } ),
    //Um retangulo na metade de baixo de um quadrado completo. // 6
    MIDDLE_SQUARE_DOWN( new float[]{ 0.00f, 0.00f, 1.00f, 0.00f, 1.00f, 0.50f, 0.00f, 0.50f } ),
    //Um retangulo na metade de cima de um quadrado completo. // 7
    MIDDLE_SQUARE_UP( new float[]{ 0.00f, 0.50f, 1.00f, 0.50f, 1.00f, 1.00f, 0.00f, 1.00f } ),
    //Um retangulo na metade da esquerda de um quadrado completo. // 8
    MIDDLE_SQUARE_LEFT( new float[]{ 0.00f, 0.00f, 0.50f, 0.00f, 0.50f, 1.00f, 0.00f, 1.00f } ),
    //Um retangulo na metade da direita de um quadrado completo. // 9
    MIDDLE_SQUARE_RIGHT( new float[]{ 0.50f, 0.00f, 1.00f, 0.00f, 1.00f, 1.00f, 0.50f, 1.00f } ),
    //Um retangulo de tres quartos de comprimento de cima para baixo. // 10
    TRES_QUARTOS_UP( new float[]{ 0.00f, 1.00f, 1.00f, 1.00f, 1.00f, 0.25f, 0.00f, 0.25f } ),
    ;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    private ShapeCollisionType( float[] vertices ) {
        this.id = counter.index++;
        if( vertices != null ) {
            float pixelToMeterRatio = 1.0f / Configurations.get().pixelsIsOneMeter;
            for( int i = 0; i < vertices.length; i++ ) {
                //Para adequar ao tamanho dos tiles
                vertices[ i ] *= Configurations.get().tileSize;
                        //Game.common.getGameConfiguration().getTileSize();
                //Para adequar ao RATIO da simulacao
                vertices[ i ] *= pixelToMeterRatio;
                        //Game.common.getGameConfiguration().getPIXEL_TO_METER_RATIO();
            }
        }
        this.vertices = vertices;
    }
    
    // ****************************************************************************************
    // Fields & Ks
    // ****************************************************************************************
    private final float[] vertices;
    private final int id;
    
    // Somente para contagem dos termos da estrutura.
    protected static class counter {
        public static int index = 0;
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public static ShapeCollisionType getShapeCollisionType( int index ) {
        if( ( index < 0 ) || ( index > values().length ) ) {
            throw new RuntimeException( " >>>> index: " + index + "; not found on: " + ShapeCollisionType.class.getName() + " <<<<" );
        } else {
            return values()[ index ];
        }
    }

    /**
     * Isso deve ser feito assim devido ao comportamento lazy do Box2D dentro do libgdx.
     * PolygonShapes somente poderao ser criados apos ocorrer a instacia de um "world" dentro do box2d.
     * @return
     */
    public PolygonShape getPolygonShape() {
        PolygonShape shape = new PolygonShape();
        shape.set( vertices );
        return shape;
    }
    
//    public PolygonShape getPolygonShape( float xCorrection, float yCorrection ) {
//        PolygonShape shape = null;
//        if( vertices != null ) {
//            float[] v = new float[ vertices.length ];
//            
//            float tileSize = (float) Game.common.getGameConfiguration().getTileSize();
//            float pixelToMeterRatio = Game.common.getGameConfiguration().getPIXEL_TO_METER_RATIO();
//            
//            for( int i = 0; i < vertices.length; i++ ) {
//                v[ i ] = vertices[ i ];
//                
//                //Para adequar ao tamanho dos tiles
//                v[ i ] *= tileSize;
//                
//                if( ( i % 2) == 0 )     v[ i ] += xCorrection;
//                else                    v[ i ] += yCorrection;
//                
//                //Para adequar ao RATIO da simulacao
//                v[ i ] *= pixelToMeterRatio;
//            }
//            
//            shape = new PolygonShape();
//            shape.set( v );
//        }
//
//        return shape;
//    }

    public int id() {
        return id;
    }
}