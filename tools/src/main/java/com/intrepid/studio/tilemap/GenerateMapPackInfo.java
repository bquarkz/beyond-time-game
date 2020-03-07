/*
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
package com.intrepid.studio.tilemap;

import static com.intrepid.studio.Constants.ABSOLUTE_RESOURCES_MPINFO;
import static com.intrepid.studio.Constants.RESOURCES_TEXTURE;
import static com.intrepid.studio.Constants.WORKDIR_TILEMAP;
import static com.intrepid.studio.Constants.WORKDIR_TILEMAP_JSON_MAP;
import static com.intrepid.studio.Constants.WORKDIR_TILEMAP_TILESET;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.intrepid.nicge.content.DynamicDependencyPack;
import com.intrepid.nicge.entities.Entity;
import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.utils.JsonHelper;
import com.intrepid.nicge.utils.RootContent;
import com.intrepid.nicge.utils.map.EntityPosition;
import com.intrepid.nicge.utils.map.MapPackInfo;
import com.intrepid.nicge.utils.map.ShapeCollisionType;
import com.intrepid.nicge.utils.map.SoloType;
import com.intrepid.nicge.utils.threads.ITask;
import com.intrepid.studio.Constants;
import com.intrepid.studio.OutputDesigns;
import com.intrepid.studio.utilities.tiled.TiledReader;

public class GenerateMapPackInfo implements ITask
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************
    private static final boolean SHOW_STATISTICS = Constants.SHOW_TILEMAP_STATISTICS;

    private static final int PAD_SIZE = Game.common.getGameConfiguration().getPadSize();
    private static final int TILE_SIZE = Game.common.getGameConfiguration().getTileSize();
    private static final int TILESET_SIZE = Game.common.getGameConfiguration().getTilesetSize();

    private static final int TILE_SIZE_PADDED = TILE_SIZE + PAD_SIZE + PAD_SIZE; // tile size aplicado o pad

    private static final int __MAX_COLUNS = TILESET_SIZE / TILE_SIZE;  // numero de tiles no tileset
    private static final int __COLUNS_DISPOSE = TILESET_SIZE / TILE_SIZE_PADDED; // o numero de colunas "inteiras" disponiveis
    private static final int __REST = __MAX_COLUNS - __COLUNS_DISPOSE; // resto das colunas

    private static final int __N_SHAPE_COLLISION_TYPE = 256;  // numero total de tiles no shape collision tileset
    private static final int __N_SOLO_TYPE = 16;   // numero total de tiles no solo type tileset
    private static final int __N_MONSTER_TYPE = 256;  // numero total de tiles no monster tileset
    private static final int __N_ORNAMENT_TYPE = 256;  // numero total de tiles no ornament tileset

    // no Tiled o shape collision tileset vem em primeiro
    private static final int __SHAPE_COLLISION_OFFSET = 0;
    // no Tiled o solo type tileset vem apos o shape collision tileset
    private static final int __SOLO_TYPE_OFFSET = __N_SHAPE_COLLISION_TYPE;
    // no Tiled o monster tileset vem apos o solo type tileset
    private static final int __MONSTER_OFFSET = __SOLO_TYPE_OFFSET + __N_SOLO_TYPE;
    // no Tiled o ornaments tileset vem apos o monster tileset
    private static final int __ORNAMENT_OFFSET = __MONSTER_OFFSET + __N_MONSTER_TYPE;
    // no Tiled o tile id tileset (tileset do mapa propriamente dito) vem sempre por ultimo
    private static final int __TILE_ID_OFFSET = __ORNAMENT_OFFSET + __N_ORNAMENT_TYPE;

    private final Files files;

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private RootContent rootContent;
    private int oldP;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public GenerateMapPackInfo( Files files )
    {
        this.files = files;
        this.oldP = 0;
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    private FileHandle getFileHandle( String path )
    {
        return files.absolute( path );
    }

    @Override
    public void taskStart()
    {
        rootContent = new RootContent( getFileHandle( WORKDIR_TILEMAP ) );

        System.out.println( OutputDesigns.createTagForService( "GENERATE MAP PACK INFO SERVICE" ) );
        System.out.println( "# Service to generate map pack infos" );
        System.out.println( "# parameters:" );
        System.out.println( "## pad size: " + PAD_SIZE );
        System.out.println( "## tile size: " + TILE_SIZE );
        System.out.println( "## tileset size: " + TILESET_SIZE );
        System.out.println( "## tilse size padded: " + TILE_SIZE_PADDED ); // tile size aplicado o pad

        System.out.println( "## max number of columns on the tileset: " + __MAX_COLUNS );
        System.out.println( "## max number of usefull columns on the tileset: " + __COLUNS_DISPOSE );
        System.out.println( "## the diference between those: " + __REST );

        System.out.println( "## tiles in shape collision type: " + __N_SHAPE_COLLISION_TYPE );
        System.out.println( "## tiles in solo type: " + __N_SOLO_TYPE );
        System.out.println( "## tiles in monster: " + __N_MONSTER_TYPE );
        System.out.println( "## tiles in ornament: " + __N_ORNAMENT_TYPE );

        System.out.println( "## The job will begins..." );
    }

    @Override
    public boolean taskRun()
    {
        final Json json = JsonHelper.getJson();

        rootContent.foreachFileInRootDirectory( WORKDIR_TILEMAP_JSON_MAP, fHandle -> {
            final String filename = fHandle.name();
            System.out.println( OutputDesigns.createTagForWorkingFile( filename ) );
            final String content = fHandle.readString();

            final MapPackInfo mapPackInfo;
            System.out.println( "### Creating MapPackInfo " );
            try
            {
                mapPackInfo = createMapPackInfoFrom( content, json );
            }
            catch( Exception e )
            {
                System.out.println( OutputDesigns.createErrorCode( " >>>> build fail, error message:\n" + e.getMessage() ) );
                return;
            }
            System.out.println( "### MapPackInfo done" );

            final String output = ABSOLUTE_RESOURCES_MPINFO + "/" + filename;
            JsonHelper.writeJson( getFileHandle( output ), mapPackInfo, MapPackInfo.class );
            System.out.println( "### MapPackInfo Stored on: " + output );
        } );

        return true;
    }

    @Override
    public void taskShutdown()
    {
    }

    @Override
    public float taskCompleted()
    {
        return 0;
    }

    public MapPackInfo createMapPackInfoFrom(
            String content,
            Json json )
    {
        System.out.println( "### Parsing the content" );
        TiledReader tiledReader = null;
        try
        {
            // nao usar o JsonHelper para fugir da encriptacao (quando houver)
            tiledReader = json.fromJson( TiledReader.class, content );
        }
        catch( Exception e )
        {
            String msg = " >>>> the json file can not be parsed <<<< \n" + e.getMessage() + "\n";

            throw new RuntimeException( msg );
        }

        System.out.println( "### init first values" );
        int[] tileIdIndexer = null;
        int[] shapeCollisionIndexerTmp = null;
        int[] soloIndexerTmp = null;
        int[] monstersIndexerTmp = null;
        int[] ornamentsIndexerTmp = null;
        for( int i = 0; i < tiledReader.getLayers().length; i++ )
        {
            if( tiledReader.getLayers()[ i ].getName().equals( "MapIndexer" ) )
            {
                tileIdIndexer = tiledReader.getLayers()[ i ].getData();
            }

            if( tiledReader.getLayers()[ i ].getName().equals( "Phisycs" ) )
            {
                shapeCollisionIndexerTmp = tiledReader.getLayers()[ i ].getData();
            }

            if( tiledReader.getLayers()[ i ].getName().equals( "SoloType" ) )
            {
                soloIndexerTmp = tiledReader.getLayers()[ i ].getData();
            }

            if( tiledReader.getLayers()[ i ].getName().equals( "Monsters" ) )
            {
                monstersIndexerTmp = tiledReader.getLayers()[ i ].getData();
            }

            if( tiledReader.getLayers()[ i ].getName().equals( "Ornaments" ) )
            {
                ornamentsIndexerTmp = tiledReader.getLayers()[ i ].getData();
            }
        }
        System.out.println( "### checking init values" );
        if( tileIdIndexer == null )
        {
            exit( "MAP", tiledReader );
        }
        if( shapeCollisionIndexerTmp == null )
        {
            exit( "PHISYCS", tiledReader );
        }
        if( soloIndexerTmp == null )
        {
            exit( "SOLO TYPE", tiledReader );
        }
        if( monstersIndexerTmp == null )
        {
            exit( "MONSTERS", tiledReader );
        }
        if( ornamentsIndexerTmp == null )
        {
            exit( "ORNAMENTS", tiledReader );
        }

        if( tileIdIndexer.length != shapeCollisionIndexerTmp.length ||
                tileIdIndexer.length != soloIndexerTmp.length ||
                tileIdIndexer.length != monstersIndexerTmp.length ||
                tileIdIndexer.length != ornamentsIndexerTmp.length )
        {

            throw new RuntimeException( " >>>> the lenght of the indexers are different <<<< " );
        }

        // inicializacoes
        SoloType[] soloTypeIndexer = new SoloType[ soloIndexerTmp.length ];
        ShapeCollisionType[] shapeCollisionTypeIndexer = new ShapeCollisionType[ shapeCollisionIndexerTmp.length ];
        Set< Class< ? > > classes = new HashSet<>();
        List< EntityPosition > monsterPositionsList = new LinkedList<>();
        List< EntityPosition > ornamentPositionsList = new LinkedList<>();
        int passableTileCounter = 0;

        System.out.print( "### starting map computus for every tile" );
        for( int i = 0; i < tileIdIndexer.length; i++ )
        {
            printLoadingBar( i, tileIdIndexer.length );

            // acerta a posicao de cada tile nos mapas.
            int xPos = i % tiledReader.getWidth();
            int yPos = i / tiledReader.getWidth();

            // o Tiled inicia a contagem com 1. Corrigir para o indexador zero. Ou para -1 quando for vazio.
            tileIdIndexer[ i ]--;
            shapeCollisionIndexerTmp[ i ]--;
            soloIndexerTmp[ i ]--;
            monstersIndexerTmp[ i ]--;
            ornamentsIndexerTmp[ i ]--;

            // correcoes para casar com os tilesets funcionais, pois o Tiled indexa em uma unica contagem todos os tiles.
            // para a correcao devemos subtrair os respectivos offsets, que sao o numero de tiles em cada tileset funcional.
            tileIdIndexer[ i ] -= __TILE_ID_OFFSET;
            shapeCollisionIndexerTmp[ i ] -= __SHAPE_COLLISION_OFFSET;
            soloIndexerTmp[ i ] -= __SOLO_TYPE_OFFSET;
            monstersIndexerTmp[ i ] -= __MONSTER_OFFSET;
            ornamentsIndexerTmp[ i ] -= __ORNAMENT_OFFSET;

            ////////////////////////////////////////////////////////////////////////////////
            // TILE INDEXER
            ////////////////////////////////////////////////////////////////////////////////
            // Logica necessaria para fazer casar o gutter com o Tiled.

            // 32 colunas de 16px cada, em um arquivo de tileset de 512px.
            // O total de colunas que podem ser utilizados � de 25,
            // 7 eh o residual de colunas que sobram e devem ser descartadas.
            // PS: Os calculos sao feitos "sempre" para um tileset de tamanho
            // 512x512. Outros tamanhos necessitam de outros calculos.

            // os comentarios acima ficaram como historico, hoje a correcao do gutter eh feito de forma
            // dinamica atraves do calculo abaixo.  
            if( tileIdIndexer[ i ] >= 0 )
            {
                tileIdIndexer[ i ] -= ( tileIdIndexer[ i ] / __MAX_COLUNS ) * ( __REST );
            }

            ////////////////////////////////////////////////////////////////////////////////
            // PHISYCS INDEXER
            ////////////////////////////////////////////////////////////////////////////////
            shapeCollisionTypeIndexer[ i ] = ShapeCollisionType.getShapeCollisionType( shapeCollisionIndexerTmp[ i ] );

            ////////////////////////////////////////////////////////////////////////////////
            // SOLO TYPE INDEXER
            ////////////////////////////////////////////////////////////////////////////////            
            soloTypeIndexer[ i ] = SoloType.getSoloType( soloIndexerTmp[ i ] );
            // Colocar aqui todas as possibilidades de tiles N�O passables
            //if( soloType[ i ] != SoloType.??? )
            if( soloTypeIndexer[ i ] != SoloType.FALL )
            {
                passableTileCounter++;
            }

            ////////////////////////////////////////////////////////////////////////////////
            // MONSTER INDEXER
            ////////////////////////////////////////////////////////////////////////////////
            if( monstersIndexerTmp[ i ] >= 0 )
            {
                Class< ? extends Entity > clazz = getMonsterClassById( monstersIndexerTmp[ i ] );
                monsterPositionsList.add( new EntityPosition( xPos, yPos, clazz ) );
                classes.add( clazz );
            }

            ////////////////////////////////////////////////////////////////////////////////
            // ORNAMENTS INDEXER
            ////////////////////////////////////////////////////////////////////////////////
            if( ornamentsIndexerTmp[ i ] >= 0 )
            {
                Class< ? extends Entity > clazz = getOrnamentClassById( ornamentsIndexerTmp[ i ] );
                ornamentPositionsList.add( new EntityPosition( xPos, yPos, clazz ) );
                classes.add( clazz );
            }
        }
        System.out.print( "\n" );

        EntityPosition[] monsterPositions = monsterPositionsList.toArray( new EntityPosition[ monsterPositionsList.size() ] );
        EntityPosition[] ornamentPositions = ornamentPositionsList.toArray( new EntityPosition[ ornamentPositionsList.size() ] );
        DynamicDependencyPack dynamicDependenyPack = new DynamicDependencyPack( classes );

        showStatistics( monsterPositions, ornamentPositions );

        String tilesetAssetSourceFilename = getTilesetAssetSourceFilename( tiledReader );
        System.out.println( "### tileset asset source filename: [ " + tilesetAssetSourceFilename + " ]" );

        return new MapPackInfo( tiledReader.getProperties().getSceneName(),
                tiledReader.getProperties().getSceneID(),
                tiledReader.getProperties().getStageName(),
                tiledReader.getProperties().getStageID(),
                tilesetAssetSourceFilename,
                tiledReader.getWidth(),
                tiledReader.getHeight(),
                tileIdIndexer,
                soloTypeIndexer,
                shapeCollisionTypeIndexer,
                monsterPositions,
                ornamentPositions,
                passableTileCounter,
                dynamicDependenyPack );
    }

    private void showStatistics(
            EntityPosition[] monsterPositions,
            EntityPosition[] ornamentPositions )
    {
        if( !SHOW_STATISTICS )
        {
            return;
        }

        System.out.println( "< STATISTICS >" );

        Map< Class< ? extends Entity >, Integer > monsterMap = createMap( monsterPositions );
        System.out.println( "### Monsters: " );
        printStatisticsFromMap( monsterMap );

        Map< Class< ? extends Entity >, Integer > ornamentMap = createMap( ornamentPositions );
        System.out.println( "### Ornament List: " );
        printStatisticsFromMap( ornamentMap );

        System.out.println( "<>" );
    }

    private void printStatisticsFromMap( Map< Class< ? extends Entity >, Integer > map )
    {
        for( Class< ? extends Entity > entity : map.keySet() )
        {
            System.out.println( " + " + entity.getName() + " [ " + map.get( entity ) + " ]" );
        }
    }

    private Map< Class< ? extends Entity >, Integer > createMap( EntityPosition[] entities )
    {
        Map< Class< ? extends Entity >, Integer > map = new HashMap<>();
        for( EntityPosition entity : entities )
        {
            Integer counter = map.get( entity.type );
            if( counter == null )
            {
                counter = 0;
            }
            else
            {
                counter++;
            }
            map.put( entity.type, counter );
        }

        return map;
    }

    private String getTilesetAssetSourceFilename( TiledReader tiledReader )
    {
        int lastOne = tiledReader.getTilesets().length - 1;
        File file = new File( WORKDIR_TILEMAP_TILESET + "/" + new File( tiledReader.getTilesets()[ lastOne ].getImage() ).getName() );
        if( !file.exists() )
        {
            System.out.println( " >>>> filename: " + file.getAbsolutePath() );
            System.out.println( " >>>> CAUTION: the tileset is not in the right place <<<<" );
        }
        String tilesetAssetSourceFilename = RESOURCES_TEXTURE + file.getName();
        return tilesetAssetSourceFilename;
    }

    private void printLoadingBar(
            int i,
            int max )
    {
        int p = (int)( ( (float)i / (float)max ) * 100.0f );
        if( p == 0 )
        {
            return;
        }

        if( oldP == p )
        {
            return;
        }

        //if( p % 2 == 0 ) System.out.print( "||" );
        if( p % 10 == 0 )
        {
            System.out.print( "." );
        }

        oldP = p;
    }

    // tabela "de para" conversao de ornamentos
    private Class< ? extends Entity > getOrnamentClassById( int ornamentId )
    {
        // alterar isso no futuro claro
        return Entity.class;
    }

    // tabela "de para" conversao de monstros   
    private Class< ? extends Entity > getMonsterClassById( int monsterId )
    {
        // alterar isso no futuro claro
        return Entity.class;
    }

    private void exit(
            String type,
            TiledReader tiledReader )
    {
        String sceneName = tiledReader.getProperties().getSceneName();
        int sceneId = tiledReader.getProperties().getSceneID();

        String stageName = tiledReader.getProperties().getStageName();
        int stageId = tiledReader.getProperties().getStageID();

        String msg = " **** " + type + " Indexers NULL, Tiled is broken. ****\n" +
                " >> Please check: " +
                "Scene name: " + sceneName + "( " + sceneId + " ) @ " +
                "Stage name: " + stageName + "( " + stageId + " )";

        throw new RuntimeException( msg );
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
