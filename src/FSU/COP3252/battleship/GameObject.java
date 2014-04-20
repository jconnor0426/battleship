/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Random;

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
        for( int i = 0; i < playerShips.get(team).size(); i++ )
        {
            
            if( toPlace.getName().equals( playerShips.get(team).get(i).getName() ) )
            {
                removeShip(playerShips.get(team).get(i), team );
            }
        }
        
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
        
        //Set all spots to become occupied:
        if (toPlace.getOrientation() == Ship.HORIZONTAL){
            for (int i = toPlace.getRow(); i < toPlace.getRow() + toPlace.getSize(); i++){
                    game[team][toPlace.getColumn()][i].setOccupied(true);
            }
        } else if (toPlace.getOrientation() == Ship.VERTICAL){
                for (int i = toPlace.getColumn(); i < toPlace.getColumn() + toPlace.getSize(); i++){
                    game[team][i][toPlace.getRow()].setOccupied(true);
                }
        }
        
        //return true
        
        return true;
    }
    
    boolean validHorizontal( int x, int size )
    {
        return x + size <= GAMESIZE;
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
        System.out.println(x + ":" + y);
        //Return whether or not a ship occupies that location
        return game[ team ][y][x].getOccupied();        
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
    
    public MyButton [][] getButtonForTeam( int team )
    {
        return game[team];
    }
    
    //Remove a ship from the game object 
    private void removeShip( Ship toRemove, int team )
    {
        //Remove it from the list
         playerShips.get(team).remove( toRemove );
         
        //Set all of its buttons to become not occupied
        if( toRemove.getOrientation() == HORIZONTAL )
        {
            for( int i = 0; i < toRemove.getSize(); i++ )
            {
                System.out.println( "DEBUGGING REMOVE: " +toRemove.getRow()+i +":"+toRemove.getColumn());
                game[team][ toRemove.getColumn()][toRemove.getRow()+i].setOccupied(false);
            }
        }else
        {
           for( int i = 0; i < toRemove.getSize(); i++ )
            {
                System.out.println( "DEBUGGING REMOVE: " +toRemove.getRow() +":"+toRemove.getColumn()+i);
                game[team][ toRemove.getColumn()+i][toRemove.getRow()].setOccupied(false);
            } 
        } 
    }
    
    public void cpuInitialize( int team )
    {
        Random generator = new Random();
        
        //Place a Carrier
        Carrier cpuCarrier = new Carrier();
        while( ! randomPlace( generator, team, cpuCarrier) )
        {
            System.out.println("DEBUGGING CPU Carrier");
        }
        //Place a Battleship
        bs cpuBattleship = new bs();
        while( ! randomPlace( generator, team, cpuBattleship) )
        {
            System.out.println("DEBUGGING CPU bs");
        }
        //Place a destroyer
        Destroyer cpuDestroyer = new Destroyer();
        while( ! randomPlace( generator, team, cpuDestroyer) )
        {
            System.out.println("DEBUGGING CPU destr");
        }
        //Place a Submarine
        Submarine cpuSubmarine = new Submarine();
        while( ! randomPlace( generator, team, cpuSubmarine) )
        {
            System.out.println("DEBUGGING CPU sub");
        }
        //Place a PatrolBoat
        PatrolBoat cpuPatrolBoat = new PatrolBoat();
        while( ! randomPlace( generator, team, cpuPatrolBoat) )
        {
            System.out.println("DEBUGGING CPU Patrol Boat");
        }
    }
    
    //Used to place ships for the CPU player
    private boolean randomPlace( Random generator, int team, Ship toPlace )
    {
        return placeShip(generator.nextInt(10),
                          generator.nextInt(10),
                          generator.nextInt(2),
                          toPlace,
                          team);
    }
}
