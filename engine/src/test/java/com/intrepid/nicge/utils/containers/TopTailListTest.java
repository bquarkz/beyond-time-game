package com.intrepid.nicge.utils.containers;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

public class TopTailListTest
{
    @Test
    public void test_Basic()
    {
        final TopTailList< String > list = new TopTailList<>();
        list.push( "TESTE3" );
        list.push( "TESTE2" );
        list.push( "TESTE1" );
        String t1 = list.pop();
        Assert.assertNotNull( t1 );
        Assert.assertEquals( "TESTE1", t1 );
        String t2 = list.pop();
        Assert.assertNotNull( t2 );
        Assert.assertEquals( "TESTE2", t2 );
        String t3 = list.pop();
        Assert.assertNotNull( t3 );
        Assert.assertEquals( "TESTE3", t3 );
    }

    @Test
    public void test_TopTailIterator()
    {
        final TopTailList< String > list = new TopTailList<>();
        list.push( "S" );
        list.push( "S" );
        list.push( "S" );

        int c = 0;
        for( String s : list )
        {
            Assert.assertEquals( "S", s );
            c++;
        }
        Assert.assertEquals( 3, c );
    }

    @Test
    public void test_TailTopIterator()
    {
        final TopTailList< String > list = new TopTailList<>();
        list.push( "S" );
        list.push( "S" );
        list.push( "S" );

        Iterator< String > iterator = list.tailTopIterator();
        int c = 0;
        while( iterator.hasNext() )
        {
            String s = iterator.next();
            Assert.assertEquals( "S", s );
            c++;
        }
        Assert.assertEquals( 3, c );
    }

}
