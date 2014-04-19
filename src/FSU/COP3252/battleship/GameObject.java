/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

/**
 *
 * @author Connor
 */
public class GameObject {
    
    public static int HORIZONTAL = 0;
    public static int VERTICAL = 1;
    
    public static int GAMESIZE = 10;
    
    
    MyButton [] [][] game;
    ArrayList< Ship > [] playerShips;
    ArrayList< Integer > [] hits;
    ArrayList< Integer > [] misses;
    
    public GameObject( MyButton[][] team0Buttons, MyButton[][] team1Buttons )
    {
        game[0] = team0Buttons;
        game[1] = team1Buttons;
        
        playerShips[0] = new ArrayList< Ship >(0);
        playerShips[1] = new ArrayList< Ship >(0);
    }
    
    
    boolean placeShip( int x, int y, int orientation, Ship toPlace, int team )
    {
        //Check to see if the ship is already on the board
        
        
        toPlace.setRow( x );
        toPlace.setColumn( y );
        toPlace.setOrientation( orientation );
        
        //Try to create ship 
        if( orientation == HORIZONTAL )
        {
            if( ! validHorizontal(x, toPlace.getSize() ) )
                return false;
        }else if( orientation == VERTICAL )
        {
            if( ! validVertical( y, toPlace.getSize() ) )
                return false;
        }
        
        
        //Try to place 
        if( ! checkValid(  toPlace, team ) )
            return false;
        
        //If good placement add ship to team array
        playerShips[team].add(toPlace );
        
        //return true
        
        return true;
    }
    
    boolean validHorizontal( int x, int size )
    {
        return x + size > GAMESIZE;
    }
    
    boolean validVertical( int y, int size )
    {
        return y + size > GAMESIZE;
    }
    
    
    ArrayList< Ship > getShipsToDraw( int team )
    {
        return playerShips[ team ];
    }
    
    boolean spotOccupied( int x, int y, int team )
    {
        //Return whether or not a ship occupies that location
        return game[ team ][x][y].getOccupied();        
    }
    
    boolean spotHit( int x, int y, int team )
    {
        //Return whether or not a ship occupies that location
        //return game[ team ][x][y].getOccupied();
        return true;
    }
    
    boolean checkValid(  Ship toCheck , int team  )
    {
        if( toCheck.getOrientation() == HORIZONTAL )
        {
            for( int i = 0; i < toCheck.getSize(); i++ )
            {
                if( spotOccupied( toCheck.getRow(), toCheck.getColumn() + i, team ) )
                    return false;
            }
        }else
        {
           for( int i = 0; i < toCheck.getSize(); i++ )
            {
                if( spotOccupied( toCheck.getRow() + i, toCheck.getColumn(), team ) )
                    return false;
            } 
        }
        
        return true;
    }
    
}
