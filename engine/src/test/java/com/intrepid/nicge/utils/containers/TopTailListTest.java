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

    @Test
    public void test()
    {
        final TopTailList< String > list = new TopTailList<>();
        TopTailList.Node< String > n1 = new TopTailList.Node<>( "1" );
        TopTailList.Node< String > n2 = new TopTailList.Node<>( "2" );
        TopTailList.Node< String > n3 = new TopTailList.Node<>( "3" );
        list.push( n3 );
        list.push( n2 );
        list.push( n1 ); // 1 was last so it is on the top
        list.sendToTop( n2 );
        Iterator< String > iterator = list.topTailIterator();
        Assert.assertEquals( "2", iterator.next() );
        Assert.assertEquals( "1", iterator.next() );
        Assert.assertEquals( "3", iterator.next() );
    }

}
