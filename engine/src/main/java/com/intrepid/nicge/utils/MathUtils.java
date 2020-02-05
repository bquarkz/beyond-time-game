package com.intrepid.nicge.utils;

import com.intrepid.nicge.kernel.game.Game;

public class MathUtils
{
    // ****************************************************************************************
    // Const Fields
    // ****************************************************************************************
    public static final float PI = 3.1415926535897932384626433832795f;
    public static final float COS_45 = 0.70710678f;

    // ****************************************************************************************
    // Common Fields
    // ****************************************************************************************

    // ****************************************************************************************
    // Constructors
    // ****************************************************************************************

    // ****************************************************************************************
    // Methods
    // ****************************************************************************************
    public static class operator
    {
        public static final float unitary( float number )
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

        public static final float mod( float number )
        {
			if( number < 0 )
			{
				number *= -1.0f;
			}
            return number;
        }
    }

    public static class convertion
    {
        public static final float toPhisycsSpace( float number )
        {
            return number * Game.common.getGameConfiguration().getPIXEL_TO_METER_RATIO();
        }

        public static final float toPixelSpace( float number )
        {
            return number * Game.common.getGameConfiguration().getMETER_TO_PIXEL_RATIO();
        }
    }

    public static class logic
    {
        // when a and b are equals then return false
        public static boolean XOR(
                boolean a,
                boolean b )
        {
            return ( ( !a & b ) | ( a & !b ) );
        }

        // when a and b are equals then return true
        public static boolean NXOR(
                boolean a,
                boolean b )
        {
            return !XOR( a, b );
        }
    }

    // ****************************************************************************************
    // Getters And Setters Methods
    // ****************************************************************************************

    // ****************************************************************************************
    // Patterns
    // ****************************************************************************************
}
