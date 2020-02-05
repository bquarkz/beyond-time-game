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
package com.intrepid.nicge.utils.audio;

import com.intrepid.nicge.kernel.game.Game;
import com.intrepid.nicge.utils.containers.Queue;

public class SoundManager
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************
    private static final int MAX_QUEUE_SIZE_DEFAULT = 20;

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************
    private Queue< SoundPack > highPrior_Queue;
    private Queue< SoundPack > middlePrior_Queue;
    private Queue< SoundPack > lowPrior_Queue;

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************
    public SoundManager()
    {
        this( MAX_QUEUE_SIZE_DEFAULT );
    }

    public SoundManager( int queueSize )
    {
        highPrior_Queue = new Queue<>( queueSize );
        middlePrior_Queue = new Queue<>( queueSize );
        lowPrior_Queue = new Queue<>( queueSize );
    }

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    public Queue< SoundPack > access()
    {
        return access( SoundPriority.MIDDLE );
    }

    public Queue< SoundPack > access( SoundPriority sp )
    {
        switch( sp )
        {
            case LOW:
                return lowPrior_Queue;
            case MIDDLE:
                return middlePrior_Queue;
            case HIGH:
                return highPrior_Queue;
            default:
                throw new RuntimeException( "sound priority is null" );
        }
    }

    public void execute( double timeToExecute )
    {
        double consumedTime = 0, t_i, t_f;

        while( timeToExecute > consumedTime )
        {
            t_i = Game.time.getSystemMicroTime();

            boolean hQisEmpty = highPrior_Queue.isEmpty();
            boolean mQisEmpty = middlePrior_Queue.isEmpty();
            boolean lQisEmpty = lowPrior_Queue.isEmpty();
            if( hQisEmpty && mQisEmpty && lQisEmpty )
            {
                return;
            }

            // it will play one sound per time and obey the priority scale
            if( !hQisEmpty )
            {
                highPrior_Queue.pop().play();
            }
            else if( !mQisEmpty )
            {
                middlePrior_Queue.pop().play();
            }
            else if( !lQisEmpty )
            {
                lowPrior_Queue.pop().play();
            }

            t_f = Game.time.getSystemMicroTime();

            consumedTime += t_f - t_i;
        }
    }


    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************
    public int getSoundQueueSize()
    {
        return highPrior_Queue.size();
    }

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
