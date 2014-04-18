/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FSU.COP3252.battleship;

import java.util.ArrayList;

/**
 *
 * @author Connor
 */
public class GameObject {
    
    MyButton [] [][] game;
    ArrayList< Ship > [] playerShips;
    
    public GameObject( MyButton[][] team0Buttons, MyButton[][] team1Buttons )
    {
        game[0] = team0Buttons;
        game[1] = team1Buttons;
    }
    
    
    boolean placeShip( int x, int y, int orientation, Ship toPlace, int team )
    {
        
        //Try to create ship 
        
        //Try to place 
        
        //If bad placement return false
        
        //If good placement add ship to team array
        //return true
        
        return true;
    }
    
    ArrayList< Ship > getShipsToDraw( int team )
    {
        return playerShips[ team ];
    }
    
    boolean spotOccupied( int x, int y, int team )
    {
        //Return whether or not a ship occupies that location
        //return game[ team ][x][y].getOccupied();
        return true;
    }
    
    boolean spotHit( int x, int y, int team )
    {
        //Return whether or not a ship occupies that location
        //return game[ team ][x][y].getOccupied();
        return true;
    }
    
}
