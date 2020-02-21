package com.intrepid.nicge.utils.containers;

import java.util.Iterator;

public class TopTailList< C >
    implements Iterable< C >
{
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constants
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Special Fields And Injections
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Fields
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Node< C > top;
    private Node< C > tail;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public TopTailList()
    {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Factories
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Getters And Setters
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Iterator< C > iterator()
    {
        return tailTopIterator();
    }

    public Iterator< C > topTailIterator()
    {
        return new TopTailIterator( top );
    }

    public Iterator< C > tailTopIterator()
    {
        return new TailTopIterator( tail );
    }

    public C getTop()
    {
        return top.getContent();
    }

    public C getTail()
    {
        return tail.getContent();
    }

    public C pop()
    {
        final Node< C > temp = top;
        if( top.after != null )
        {
            top.after.before = null;
            top = top.after; // move down
        }
        else if( top == tail )
        {
            top = null;
            tail = null;
        }
        return temp.getContent();
    }

    public void push( C content )
    {
        if( content == null ) return;
        push( new Node<>( content ) );
    }

    public void push( Node< C > node )
    {
        if( node == null ) return;
        if( top == null )
        {
            node.before = null;
            node.after = null;
            top = node;
            tail = node;
        }
        else
        {
            node.after = top;
            top.before = node;
            top = node;
        }
    }

    public void sendToTop( Node< C > node )
    {
        if( node.before == null ) return; // it is already top

        if( node.after == null ) // it is last
        {
            node.before.after = null;
            tail = node.before;
        }
        else
        {
            node.before.after = node.after;
            node.after.before = node.before;
        }

        top.before = node;
        node.after = top;
        node.before = null;
        top = node;
    }

    public void clear()
    {
        if( top == null ) return;

        Node< C > current = top;
        while( current.after != null )
        {
            current.before = null;
            current.content = null;
            current = current.after;
        }
        current.before = null;
        current.content = null;

        top = null;
        tail = null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Inner Classes And Patterns
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public class TailTopIterator
            implements Iterator< C >
    {
        private TopTailList.Node< C > current;

        public TailTopIterator( TopTailList.Node< C > startPoint )
        {
            this.current = startPoint;
        }

        @Override
        public boolean hasNext()
        {
            return current != null;
        }

        @Override
        public C next()
        {
            final C content = current.getContent();
            current = current.getBefore();
            return content;
        }
    }

    public class TopTailIterator
            implements Iterator< C >
    {
        private TopTailList.Node< C > current;

        public TopTailIterator( TopTailList.Node< C > current )
        {
            this.current = current;
        }

        @Override
        public boolean hasNext()
        {
            return current != null;
        }

        @Override
        public C next()
        {
            final C content = current.getContent();
            current = current.getAfter();
            return content;
        }
    }

    public final static class Node< C >
    {
        private C content;

        private Node< C > before;
        private Node< C > after;

        public Node( C content )
        {
            this.content = content;
        }

        public Node< C > getBefore()
        {
            return before;
        }

        public Node< C > getAfter()
        {
            return after;
        }

        public C getContent()
        {
            return content;
        }
    }
}
