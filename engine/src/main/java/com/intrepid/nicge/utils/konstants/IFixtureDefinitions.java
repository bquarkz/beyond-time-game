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

public interface IFixtureDefinitions
{
    interface ICategoryBits
    {
        //*****************************************************************************
        // CATEGORY
        //***************************************************************************** 
        short NONE          = 0x0000;
        short ALL           = -1;
        
        short SCENARY       = 0x0001;
        short PLAYER        = 0x0002;
        short ENEMY         = 0x0004;
        short NPCS          = 0x0008;

        short PLAYER_ATTACK = 0x0010;
        short ENEMY_ATTACK  = 0x0020;
        short EXPLOSION     = 0x0040;
        short TRAP          = 0x0080;
        
        short PROSPECTOR    = 0x0100;
        short ORNAMENTS     = 0x0200;
        short BUTTON        = 0x0400;
        //short = 0x0800;

        //short = 0x1000;
        //short = 0x2000;
        //short = 0x4000;
        //short = 0x8000;
    }

    interface IMaskBits
    {
        //*****************************************************************************
        // MASKS
        //***************************************************************************** 
        short PLAYER        = //Category.PLAYER
                                                  ICategoryBits.NPCS
                                                | ICategoryBits.ENEMY
                                                | ICategoryBits.ORNAMENTS
                                                | ICategoryBits.SCENARY
                                                | ICategoryBits.TRAP
                                                | ICategoryBits.PROSPECTOR
                                                | ICategoryBits.EXPLOSION
                                                | ICategoryBits.ENEMY_ATTACK
                                                ;
                                                
        short NPCS          = ICategoryBits.PLAYER
                                                | ICategoryBits.NPCS
                                                | ICategoryBits.ENEMY
                                                | ICategoryBits.ORNAMENTS
                                                | ICategoryBits.SCENARY
                                                | ICategoryBits.TRAP
                                                | ICategoryBits.PROSPECTOR
                                                | ICategoryBits.PLAYER_ATTACK
                                                | ICategoryBits.ENEMY_ATTACK
                                                | ICategoryBits.EXPLOSION
                                                ;
                                                
        short ENEMY         = ICategoryBits.PLAYER
                                                | ICategoryBits.NPCS
                                                | ICategoryBits.ENEMY
                                                | ICategoryBits.ORNAMENTS
                                                | ICategoryBits.SCENARY
                                                | ICategoryBits.TRAP
                                                | ICategoryBits.PROSPECTOR
                                                | ICategoryBits.PLAYER_ATTACK
                                                | ICategoryBits.EXPLOSION
                                                ;
                                                
        short ORNAMENTS     = ICategoryBits.PLAYER
                                                | ICategoryBits.NPCS
                                                | ICategoryBits.ENEMY
                                                //| Category.ORNAMENTS
                                                //| Category.SCENARY
                                                //| Category.TRAP
                                                | ICategoryBits.PROSPECTOR
                                                | ICategoryBits.PLAYER_ATTACK
                                                | ICategoryBits.ENEMY_ATTACK
                                                | ICategoryBits.EXPLOSION
                                                ;
        
        short SCENARY       = ICategoryBits.PLAYER
                                                | ICategoryBits.NPCS
                                                | ICategoryBits.ENEMY
                                                | ICategoryBits.ORNAMENTS
                                                //| Category.SCENARY
                                                //| Category.TRAP
                                                //| Category.DOOR
                                                //| Category.PLAYER_ATTACK
                                                //| Category.ENEMY_ATTACK
                                                ;
                                              
        short TRAP          = ICategoryBits.PLAYER
                                                | ICategoryBits.NPCS
                                                | ICategoryBits.ENEMY
                                                | ICategoryBits.ORNAMENTS
                                                //| Category.SCENARY
                                                //| Category.TRAP
                                                | ICategoryBits.PROSPECTOR
                                                //| Category.PLAYER_ATTACK
                                                //| Category.ENEMY_ATTACK
                                                ;

        short PROSPECTOR    = ICategoryBits.PLAYER
                                                | ICategoryBits.NPCS
                                                | ICategoryBits.ENEMY
                                                | ICategoryBits.ORNAMENTS
                                                //| Category.SCENARY
                                                | ICategoryBits.TRAP
                                                //| Category.PLAYER_ATTACK
                                                //| Category.ENEMY_ATTACK
                                                ;
                                              
        short PLAYER_ATTACK = //Category.PLAYER
                                                  ICategoryBits.NPCS
                                                | ICategoryBits.ENEMY
                                                | ICategoryBits.ORNAMENTS
                                                //| Category.SCENARY
                                                //| Category.TRAP
                                                //| Category.DOOR
                                                //| Category.PLAYER_ATTACK
                                                | ICategoryBits.ENEMY_ATTACK
                                                ;

        short ENEMY_ATTACK  = ICategoryBits.PLAYER
                                                | ICategoryBits.NPCS
                                                //| Category.ENEMY
                                                | ICategoryBits.ORNAMENTS
                                                //| Category.SCENARY
                                                //| Category.TRAP
                                                //| Category.DOOR
                                                | ICategoryBits.PLAYER_ATTACK
                                                //| Category.ENEMY_ATTACK
                                                ;
        
        short BUTTON        = ICategoryBits.PLAYER
                                                //| Category.NPCS
                                                //| Category.ENEMY
                                                //| Category.ORNAMENTS
                                                //| Category.SCENARY
                                                //| Category.TRAP
                                                | ICategoryBits.PROSPECTOR
                                                //| Category.EXPLOSION
                                                //| Category.ENEMY_ATTACK
                                                ;
    }
}
