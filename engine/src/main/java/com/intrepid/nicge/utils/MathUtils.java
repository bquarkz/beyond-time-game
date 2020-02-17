package com.intrepid.nicge.utils;

import com.intrepid.nicge.kernel.game.Game;

public interface MathUtils
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************
    float PI = 3.1415926535897932384626433832795f;
    float COS_45 = 0.70710678f;

    // ****************************************************************************************
    // Internal Interfaces
    // ****************************************************************************************
    interface Vector
    {
        Vector ZERO = new Vector()
        {
            @Override
            public int getX()
            {
                return 0;
            }

            @Override
            public int getY()
            {
                return 0;
            }
        };

        static Vector with( Vector vector )
        {
            return with( vector.getX(), vector.getY() );
        }

        static Vector sum( Vector v1, Vector v2 )
        {
            return Vector.with( v1.getX() + v2.getX(), v1.getY() + v2.getY() );
        }

        static Vector subtraction( Vector v1, Vector v2 )
        {
            return Vector.with( v1.getX() - v2.getX(), v1.getY() - v2.getY() );
        }


        static Vector with( int x, int y )
        {
            return new Vector()
            {
                @Override
                public int getX()
                {
                    return x;
                }

                @Override
                public int getY()
                {
                    return y;
                }

                @Override
                public String toString()
                {
                    return "x: " + getX() + ", y: " + getY();
                }
            };
        }

        int getX();
        int getY();
    }

    interface gdx
    {
        static boolean checkInside( Vector topLeftCorner, Vector bottomRightCorner, Vector toCheck )
        {
            if( toCheck.getY() > topLeftCorner.getY()  ) return false;
            if( toCheck.getX() < topLeftCorner.getX() ) return false;
            if( toCheck.getY() < bottomRightCorner.getY() ) return false;
            if( toCheck.getX() > bottomRightCorner.getX() ) return false;
            return true;
        }
    }

    interface operator
    {
        static float unitary( float number )
        {
			if( number > 0 )
			{
				number = 1.0f;
			}
			else if( number < 0 )
			{
				number = -1.0f;
			}

            return number;
        }

        static float mod( float number )
        {
			if( number < 0 )
			{
				number *= -1.0f;
			}
            return number;
        }
    }

    interface conversion
    {
        static Vector gdxCoordinates( Vector vector )
        {
            return gdxCoordinates( vector.getX(), vector.getY() );
        }

        static Vector gdxCoordinates( int x, int y )
        {
            return Vector.with( x, Game.common.getGameConfiguration().getNativeResolutionHeight() - y );
        }

        static float toPhysicsSpace( float number )
        {
            return number * Game.common.getGameConfiguration().getPIXEL_TO_METER_RATIO();
        }

        static float toPixelSpace( float number )
        {
            return number * Game.common.getGameConfiguration().getMETER_TO_PIXEL_RATIO();
        }
    }

    interface logic
    {
        // when a and b are equals then return false
        static boolean XOR(
                boolean a,
                boolean b )
        {
            return ( ( !a & b ) | ( a & !b ) );
        }

        // when a and b are equals then return true
        static boolean NXOR(
                boolean a,
                boolean b )
        {
            return !XOR( a, b );
        }
    }
}
