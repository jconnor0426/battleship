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
    
    public static int HORIZONTAL = Ship.HORIZONTAL;
    public static int VERTICAL = Ship.VERTICAL;
    
    public static int GAMESIZE = 10;
    
    
    MyButton [] [][] game = new MyButton[2][10][10];
    ArrayList< ArrayList < Ship > > playerShips;
    ArrayList< ArrayList< Integer > > hits;
    ArrayList< ArrayList< Integer > > misses;
    
    public GameObject( MyButton[][] team0Buttons, MyButton[][] team1Buttons )
    {
        game[0] = team0Buttons;
        game[1] = team1Buttons;

        /*for (int k = 0; k < 1; k++){
            for (int i= 0; i < 10; i++){
                for (int j = 0; j < 10; j++){
                    System.out.println(game[k][i][j].getRow() + ":" + game[k][i][j].getColumn());
                }
            }
        }*/
        
        playerShips = new ArrayList< ArrayList < Ship > >() ;
        playerShips.add( new ArrayList< Ship >(0) );
        playerShips.add( new ArrayList< Ship >(0) );
        
        hits = new ArrayList< ArrayList< Integer > >();
        hits.add( new ArrayList< Integer > (0 ) );
        hits.add( new ArrayList< Integer > (0 ) );
        
        misses = new ArrayList< ArrayList< Integer > >();
        misses.add( new ArrayList< Integer > (0 ) );
        misses.add( new ArrayList< Integer > (0 ) );
    }
    
    
    boolean placeShip( int x, int y, int orientation, Ship toPlace, int team )
    {
        //Check to see if the ship is already on the board
        
        
        toPlace.setRow( x );
        toPlace.setColumn( y );
        toPlace.setOrientation( orientation );
        System.out.println("BEFORE CHECKS: " + x + ":" + y);
        
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

        System.out.println("x: " + x + "\ty:" + y);
        
        
        //Try to place 
        if( ! checkValid(  toPlace, team ) )
            return false;

        //If good placement add ship to team array
        playerShips.get(team).add(toPlace );
        
        //return true
        
        return true;
    }
    
    boolean validHorizontal( int x, int size )
    {
        return x + size <= GAMESIZE;
<<<<<<< HEAD
        
=======
>>>>>>> 8827cb482f1eb8d4b4fd90e3023962c51759b1d9
    }
    
    boolean validVertical( int y, int size )
    {
        return y + size <= GAMESIZE;
    }
    
    
    ArrayList< Ship > getShipsToDraw( int team )
    {
        return playerShips.get( team );
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
                if( spotOccupied( toCheck.getRow()+i, toCheck.getColumn() , team ) )
                    return false;
            }
        }else
        {
           for( int i = 0; i < toCheck.getSize(); i++ )
            {
                if( spotOccupied( toCheck.getRow() , toCheck.getColumn() + i, team ) )
                    return false;
            } 
        }
        
        return true;
    }
    
}
