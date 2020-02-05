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
package com.intrepid.nicge.utils.konstants;

public interface FixtureDefinitions {
    public interface CategoryBits {
        //*****************************************************************************
        // CATEGORY
        //***************************************************************************** 
        public static final short NONE          = 0x0000;
        public static final short ALL           = -1;
        
        public static final short SCENARY       = 0x0001;
        public static final short PLAYER        = 0x0002;
        public static final short ENEMY         = 0x0004;
        public static final short NPCS          = 0x0008;

        public static final short PLAYER_ATTACK = 0x0010;
        public static final short ENEMY_ATTACK  = 0x0020;
        public static final short EXPLOSION     = 0x0040;
        public static final short TRAP          = 0x0080;
        
        public static final short PROSPECTOR    = 0x0100;
        public static final short ORNAMENTS     = 0x0200;
        public static final short BUTTON        = 0x0400;
        //public static final short = 0x0800;

        //public static final short = 0x1000;
        //public static final short = 0x2000;
        //public static final short = 0x4000;
        //public static final short = 0x8000;
    }

    public interface MaskBits {
        //*****************************************************************************
        // MASKS
        //***************************************************************************** 
        public static final short PLAYER        = //Category.PLAYER
                                                  CategoryBits.NPCS
                                                | CategoryBits.ENEMY
                                                | CategoryBits.ORNAMENTS
                                                | CategoryBits.SCENARY
                                                | CategoryBits.TRAP
                                                | CategoryBits.PROSPECTOR
                                                | CategoryBits.EXPLOSION
                                                | CategoryBits.ENEMY_ATTACK
                                                ;
                                                
        public static final short NPCS          = CategoryBits.PLAYER
                                                | CategoryBits.NPCS
                                                | CategoryBits.ENEMY
                                                | CategoryBits.ORNAMENTS
                                                | CategoryBits.SCENARY
                                                | CategoryBits.TRAP
                                                | CategoryBits.PROSPECTOR
                                                | CategoryBits.PLAYER_ATTACK
                                                | CategoryBits.ENEMY_ATTACK
                                                | CategoryBits.EXPLOSION
                                                ;
                                                
        public static final short ENEMY         = CategoryBits.PLAYER
                                                | CategoryBits.NPCS
                                                | CategoryBits.ENEMY
                                                | CategoryBits.ORNAMENTS
                                                | CategoryBits.SCENARY
                                                | CategoryBits.TRAP
                                                | CategoryBits.PROSPECTOR
                                                | CategoryBits.PLAYER_ATTACK
                                                | CategoryBits.EXPLOSION
                                                ;
                                                
        public static final short ORNAMENTS     = CategoryBits.PLAYER
                                                | CategoryBits.NPCS
                                                | CategoryBits.ENEMY
                                                //| Category.ORNAMENTS
                                                //| Category.SCENARY
                                                //| Category.TRAP
                                                | CategoryBits.PROSPECTOR
                                                | CategoryBits.PLAYER_ATTACK
                                                | CategoryBits.ENEMY_ATTACK
                                                | CategoryBits.EXPLOSION
                                                ;
        
        public static final short SCENARY       = CategoryBits.PLAYER
                                                | CategoryBits.NPCS
                                                | CategoryBits.ENEMY
                                                | CategoryBits.ORNAMENTS
                                                //| Category.SCENARY
                                                //| Category.TRAP
                                                //| Category.DOOR
                                                //| Category.PLAYER_ATTACK
                                                //| Category.ENEMY_ATTACK
                                                ;
                                              
        public static final short TRAP          = CategoryBits.PLAYER
                                                | CategoryBits.NPCS
                                                | CategoryBits.ENEMY
                                                | CategoryBits.ORNAMENTS
                                                //| Category.SCENARY
                                                //| Category.TRAP
                                                | CategoryBits.PROSPECTOR
                                                //| Category.PLAYER_ATTACK
                                                //| Category.ENEMY_ATTACK
                                                ;

        public static final short PROSPECTOR    = CategoryBits.PLAYER
                                                | CategoryBits.NPCS
                                                | CategoryBits.ENEMY
                                                | CategoryBits.ORNAMENTS
                                                //| Category.SCENARY
                                                | CategoryBits.TRAP
                                                //| Category.PLAYER_ATTACK
                                                //| Category.ENEMY_ATTACK
                                                ;
                                              
        public static final short PLAYER_ATTACK = //Category.PLAYER
                                                  CategoryBits.NPCS
                                                | CategoryBits.ENEMY
                                                | CategoryBits.ORNAMENTS
                                                //| Category.SCENARY
                                                //| Category.TRAP
                                                //| Category.DOOR
                                                //| Category.PLAYER_ATTACK
                                                | CategoryBits.ENEMY_ATTACK
                                                ;

        public static final short ENEMY_ATTACK  = CategoryBits.PLAYER
                                                | CategoryBits.NPCS
                                                //| Category.ENEMY
                                                | CategoryBits.ORNAMENTS
                                                //| Category.SCENARY
                                                //| Category.TRAP
                                                //| Category.DOOR
                                                | CategoryBits.PLAYER_ATTACK
                                                //| Category.ENEMY_ATTACK
                                                ;
        
        public static final short BUTTON        = CategoryBits.PLAYER
                                                //| Category.NPCS
                                                //| Category.ENEMY
                                                //| Category.ORNAMENTS
                                                //| Category.SCENARY
                                                //| Category.TRAP
                                                | CategoryBits.PROSPECTOR
                                                //| Category.EXPLOSION
                                                //| Category.ENEMY_ATTACK
                                                ;
    }
}
