package com.intrepid.nicge.utils;

import org.junit.Assert;
import org.junit.Test;

public class TriggerOnceTest
{
    @Test
    public void test() throws Exception
    {

        final TriggerOnce< String > triggerOnce = new TriggerOnce< String >()
                .usingDefaultReturn( "default" )
                .when( () -> true )
                .theAction( () -> "test" );

        final String s0 = triggerOnce.call();
        Assert.assertNotNull( s0 );
        Assert.assertEquals( "test", s0 );

        final String s1 = triggerOnce.call();
        Assert.assertNotNull( s1 );
        Assert.assertEquals( "default", s1 );
    }
}
