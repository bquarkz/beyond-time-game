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
 * all comunity without problems.
 */
package com.intrepid.nicge.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.files.FileHandle;
import com.intrepid.nicge.utils.logger.Log;

public final class RootContent
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private Map< String, ContentSet > rootContentMap;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public RootContent( FileHandle fHandle )
    {
        if( !fHandle.isDirectory() )
        {
            throw new RuntimeException( "> the argument: " + fHandle.name() + "; isn't a directory <" );
        }

        final Set< FileHandle > fHandles = new HashSet<>();
        for( FileHandle f : fHandle.list() )
        {
            fHandles.add( f );
        }

        seekForRootContent( fHandles );
    }

    public RootContent( Set< FileHandle > fHandles )
    {
        seekForRootContent( fHandles );
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    protected void seekForRootContent( Set< FileHandle > fHandles )
    {
        rootContentMap = new HashMap<>();

        for( FileHandle root : fHandles )
        {
            if( root.isDirectory() )
            {

                Set< FileHandle > directories = new HashSet<>();
                Set< FileHandle > files = new HashSet<>();
                for( FileHandle f : root.list() )
                {
                    if( f.isDirectory() )
                    {
                        directories.add( f );
                    }
                    else
                    {
                        files.add( f );
                    }
                }

                String path = root.path();
                if( !path.endsWith( "/" ) )
                {
                    path += "/";
                }
                rootContentMap.put( path, new ContentSet( directories, files ) );
            }
        }
    }

    public void foreachFileInRootDirectory(
            String[] rootPaths,
            IFileExec fileExec )
    {
        for( String rootPath : rootPaths )
        {
            foreachFileInRootDirectory( rootPath, fileExec );
        }
    }

    public void foreachFileInRootDirectory(
            String rootDirectoryPath,
            IFileExec fileExec )
    {
        final ContentSet contentSet = rootContentMap.get( rootDirectoryPath );
        if( contentSet == null )
        {
            Log.from( this ).failure( "root path: [ " + rootDirectoryPath + " ]; cannot be found" );
            return;
        }
        foreachFileInRootDirectory( contentSet, fileExec );
    }

    protected void foreachFileInRootDirectory(
            ContentSet contentSet,
            IFileExec fileExec )
    {
        for( FileHandle contentDirectories : contentSet.getDirectories() )
        {
            for( FileHandle f : contentDirectories.list() )
            {
                if( !f.isDirectory() )
                {
                    // All Files on Content (first level) Directories
                    fileExec.runOver( f );
                }
            }
        }

        for( FileHandle f : contentSet.getFiles() )
        {
            // All files on the root
            fileExec.runOver( f );
        }
    }

    public void foreachFilesInAllRootDirectories( IFileExec fileExec )
    {
        for( ContentSet cSet : rootContentMap.values() )
        {
            foreachFileInRootDirectory( cSet, fileExec );
        }
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        for( String root : getRoots() )
        {
            sb.append( "root: " ).append( root ).append( "\n" );
            for( FileHandle f : getDirectoriesFromRoot( root ) )
            {
                sb.append( " - directory: " ).append( f.name() ).append( "\n" );
            }

            for( FileHandle f : getFilesFromRoot( root ) )
            {
                sb.append( " - file: " ).append( f.name() ).append( "\n" );
            }
        }

        return sb.toString();
    }


    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public Set< String > getRoots()
    {
        return rootContentMap.keySet();
    }

    public Set< FileHandle > getDirectoriesFromRoot( String rootPath )
    {
        return rootContentMap.get( rootPath ).getDirectories();
    }

    public Set< FileHandle > getFilesFromRoot( String rootPath )
    {
        return rootContentMap.get( rootPath ).getFiles();
    }

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
    public class ContentSet
    {
        private Set< FileHandle > directories;
        private Set< FileHandle > files;

        public ContentSet(
                Set< FileHandle > directories,
                Set< FileHandle > files )
        {
            this.directories = directories;
            this.files = files;
        }

        public Set< FileHandle > getFiles()
        {
            return files;
        }

        public Set< FileHandle > getDirectories()
        {
            return directories;
        }
    }
}
