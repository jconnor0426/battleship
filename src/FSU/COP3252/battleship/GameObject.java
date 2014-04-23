/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import java.util.Stack;

/**
 *
 * @author Connor
 */
public class GameObject {
    
    public static int HORIZONTAL = Ship.HORIZONTAL;
    public static int VERTICAL = Ship.VERTICAL;
    public static int GAMESIZE = 10;
    private static int MAXHITS = 17;
    private static int NUMPLAYERS = 2;
    
    
    MyButton [] [][] game = new MyButton[2][10][10];
    ArrayList< ArrayList < Ship > > playerShips;
    private int [] hits;
    private int [] misses;
    private int [] shipsSunk;
    
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
        
        hits =  new int[2];
        hits[0] =0;
        hits[1] =0;
        
        misses = new int[2];
        misses[0] =0;
        misses[1] =0;

        shipsSunk = new int[2];
        shipsSunk[0] = 0;
        shipsSunk[1] = 0;
    }
    
    //ONLY USE IN COMPUTER VS COMPUTER!
    public void start()
    {
        
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
        //System.out.println("BEFORE CHECKS: " + x + ":" + y);
        
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

        //System.out.println("x: " + x + "\ty:" + y);
        
        
        //Try to place 
        if( ! checkValid(  toPlace, team ) )
            return false;

        //If good placement add ship to team array
        playerShips.get(team).add(toPlace );
        
        //Set all spots to become occupied:
        if (toPlace.getOrientation() == Ship.HORIZONTAL){
            toPlace.clearButtonList();
            for (int i = toPlace.getRow(); i < toPlace.getRow() + toPlace.getSize(); i++){
                    game[team][toPlace.getColumn()][i].setOccupied(true);
                    toPlace.addButton(game[team][toPlace.getColumn()][i]);
            }
        } else if (toPlace.getOrientation() == Ship.VERTICAL){
            toPlace.clearButtonList();
                for (int i = toPlace.getColumn(); i < toPlace.getColumn() + toPlace.getSize(); i++){
                    game[team][i][toPlace.getRow()].setOccupied(true);
                    toPlace.addButton(game[team][i][toPlace.getRow()]);
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
        //System.out.println(x + ":" + y);
        //Return whether or not a ship occupies that location
        return game[ team ][y][x].getOccupied();        
    }
    
    boolean spotHit( int x, int y, int team )
    {
        //Return whether or not a ship occupies that location
        //System.out.println("SPOT HIT? "+x + ":" + y + game[ team ][y][x].getHit());
        return game[ team ][y][x].getHit();
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
                //System.out.println( "DEBUGGING REMOVE: " +toRemove.getRow()+i +":"+toRemove.getColumn());
                game[team][ toRemove.getColumn()][toRemove.getRow()+i].setOccupied(false);
            }
        }else
        {
           for( int i = 0; i < toRemove.getSize(); i++ )
            {
                //System.out.println( "DEBUGGING REMOVE: " +toRemove.getRow() +":"+toRemove.getColumn()+i);
                game[team][ toRemove.getColumn()+i][toRemove.getRow()].setOccupied(false);
            } 
        } 
    }
    
    public void cpuPlaceShips( int team)
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
    
    public void cpuInitialize( int team )
    {
        cpuPlaceShips(team);
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
    
    //Returns true for successful attack
    //Returns false if it was a duplicate value
    public boolean takeTurn(int x, int y, int team, int teamToAttack )
    {
        //!Occupided + !Hit = noColor
        //!Occupied + Hit = Miss Color
        //Occupied + Hit = Red  
        
        //See if that spot has been hit already
        if( spotHit( x, y, teamToAttack ) )
        {
            return false;
        }else
        {
            //System.out.println("DEBUG AM I ON?");
            //If occupied 
            if( spotOccupied(x,y, teamToAttack) )
            {
                game[teamToAttack][y][x].setHit(true);
                game[teamToAttack][y][x].setBackground(Color.RED);
                game[teamToAttack][y][x].setOpaque(true);
                game[teamToAttack][y][x].setBorderPainted(false);
                hits[team]++;

                // Checking if ship is sunk
                checkShipSunk(x, y, teamToAttack);
                
                //Check to see if the ship at that location is sunk
                return true;
            }
            //else it was a miss
            else
            {
                game[teamToAttack][y][x].setHit(true);
                game[teamToAttack][y][x].setBackground(Color.BLUE);
                game[teamToAttack][y][x].setOpaque(true);
                game[teamToAttack][y][x].setBorderPainted(false);
                return true;
            }

        }
        
    }
    
    //Returns -1 if not over
    //otherwise returns the integer of the team that won
    public int checkGameOver()
    {
        for( int i = 0; i < NUMPLAYERS ; i++ )
        {
            if( hits[i] == MAXHITS )
                return i;
        }
        
        return -1;
    }

    public void checkShipSunk(int x, int y, int team){
        ArrayList < Ship > tempShipsList;
        if (team == 1){
            tempShipsList = getShipsToDraw(1);
        } else{
            tempShipsList = getShipsToDraw(0);
        }

        for (int i = 0; i < tempShipsList.size(); i++){
            ArrayList < MyButton > tempButtons = tempShipsList.get(i).getButtonLocations();

            for (int j = 0; j < tempButtons.size(); j++){

                if (tempButtons.get(j).getRow() == x && tempButtons.get(j).getColumn() == y){
                    tempShipsList.get(i).increaseHits();
                    if (tempShipsList.get(i).getSunk()){
                        // Increment player count so player1 = left board and player2 = right board
                        shipsSunk[team] += 1;
                    }
                }
            }
        }
    }

    public int getNumberOfShipsSunk(int team){
        return shipsSunk[team];
    }
}

class HumanVsComputerEasy extends GameObject
{

    public HumanVsComputerEasy(MyButton[][] team0Buttons, MyButton[][] team1Buttons) {
        super(team0Buttons, team1Buttons);
        //Human is always team 0 
        //CPU is always team 1
        
        cpuInitialize(1);
    }
    
    public boolean takeTurn(int x, int y, int team, int teamToAttack )
    {
        boolean userResult = super.takeTurn(x, y, team, teamToAttack);
        if( userResult ) //If the user made a valid move
        {
            //If Game is not over, CPU takes a turn
            if( checkGameOver() == -1 )
            {
               Random gen = new Random();
               super.takeTurn(gen.nextInt(10), gen.nextInt(10), teamToAttack, team);
            }
            //CPU decision: basic mode will be just random choice
        }
        return userResult;
    }
    
}

class HumanVsComputerMedium extends GameObject
{
    
    
    protected Stack< Couple > computerTarget;

    public HumanVsComputerMedium(MyButton[][] team0Buttons, MyButton[][] team1Buttons) {
        super(team0Buttons, team1Buttons);
        //Human is always team 0 
        //CPU is always team 1
        
        cpuInitialize(1);
    }
    
    public boolean takeTurn(int x, int y, int team, int teamToAttack )
    {
        boolean userResult = super.takeTurn(x, y, team, teamToAttack);
        if( userResult ) //If the user made a valid move
        {
            //If Game is not over, CPU takes a turn
            if( checkGameOver() == -1 )
            {
               cpuTurn( cpuGetTarget(), teamToAttack, team );
            }
            //CPU decision: basic mode will be just random choice
        }
        return userResult;
    }
    
    public void cpuInitialize( int team )
    {
        super.cpuInitialize(team);
        
        computerTarget = new Stack< Couple >();
    }
    
    private Couple cpuGetTarget()
    {
        Couple target;
        Random gen = new Random();
        //If stack is empty
        if( computerTarget.empty())
        {
            target = new Couple( gen.nextInt(10), gen.nextInt(10) );
        }else
        {
            target = computerTarget.pop();
        }
        
        return target;
    }
    
    private boolean cpuTurn(Couple target, int team, int teamToAttack)
    {
        
        while( ! super.takeTurn(target.x, target.y, team, teamToAttack ) )
        {
            target = cpuGetTarget();
        };
        
        //If shot hit, add  N, S, E, W, to stack
        if( spotHit( target.x, target.y, teamToAttack) && spotOccupied(target.x, target.y, teamToAttack) )
        {
            //Add x+1, y
            if( target.x +1 < 10 )
                computerTarget.push( new Couple( target.x +1 , target.y ) );
            //Add x-1, y
            if( target.x -1 >= 0 )
                computerTarget.push( new Couple( target.x -1 , target.y ) );
            //Add x, y+1
            if( target.y +1 < 10 )
                computerTarget.push( new Couple( target.x  , target.y+1 ) );
            //Add x, y-1
            if( target.y - 1 >= 0 )
                computerTarget.push( new Couple( target.x , target.y -1 ) );
        }
        
        
        
        return true;
    }
    
}

class HumanVsComputerHard extends GameObject
{

    protected Stack< Couple > computerTarget;
    
    public HumanVsComputerHard(MyButton[][] team0Buttons, MyButton[][] team1Buttons) {
        super(team0Buttons, team1Buttons);
        cpuInitialize(1);
    }

    private Couple cpuGetTarget()
    {
        
        Couple target;
        Random gen = new Random();
        //If stack is empty
        if( computerTarget.empty())
        {
            //Select from only half the board
            int x = gen.nextInt(10);
            int y = (x%2==0)?   gen.nextInt(5)*2+1 //if even, select odd
                            :   gen.nextInt(5)*2;  //else odd, select even
            target = new Couple( x, y );
        }else
        {
            target = computerTarget.pop();
        }
        
        return target;
    }
    
    public boolean takeTurn(int x, int y, int team, int teamToAttack )
    {
        boolean userResult = super.takeTurn(x, y, team, teamToAttack);
        if( userResult ) //If the user made a valid move
        {
            //If Game is not over, CPU takes a turn
            if( checkGameOver() == -1 )
            {
               cpuTurn( cpuGetTarget(), teamToAttack, team );
            }
            //CPU decision: basic mode will be just random choice
        }
        return userResult;
    }
    
    private boolean cpuTurn(Couple target, int team, int teamToAttack)
    {
        
        while( ! super.takeTurn(target.x, target.y, team, teamToAttack ) )
        {
            target = cpuGetTarget();
        };
        
        //If shot hit, add  N, S, E, W, to stack
        if( spotHit( target.x, target.y, teamToAttack) && spotOccupied(target.x, target.y, teamToAttack) )
        {
            //Add x+1, y
            if( target.x +1 < 10 )
                computerTarget.push( new Couple( target.x +1 , target.y ) );
            //Add x-1, y
            if( target.x -1 >= 0 )
                computerTarget.push( new Couple( target.x -1 , target.y ) );
            //Add x, y+1
            if( target.y +1 < 10 )
                computerTarget.push( new Couple( target.x  , target.y+1 ) );
            //Add x, y-1
            if( target.y - 1 >= 0 )
                computerTarget.push( new Couple( target.x , target.y -1 ) );
        }
        
        
        
        return true;
    }
    
    public void cpuInitialize( int team )
    {
        super.cpuInitialize(team);
        
        computerTarget = new Stack< Couple >();
    }
    
    
}

class Couple{
        public int x;
        public int y;
        public Couple( int x, int y )
        {
            this.x = x;
            this.y = y;
        }
        
    }


class ComputerVsComputer extends GameObject
{
    private ArrayList< Stack< Couple > > cpuTargets;

    public ComputerVsComputer(MyButton[][] team0Buttons, MyButton[][] team1Buttons) {
        super(team0Buttons, team1Buttons);
        cpuTargets = new ArrayList<Stack<Couple>>();
        cpuTargets.add(new Stack<Couple>() );
        cpuTargets.add(new Stack<Couple>() );
        
        //Set the both to be a computer
        cpuInitialize(0);
        cpuInitialize(1);
    }
    
    
    public void start() //throws InterruptedException
    {
        int turn = 0;
        while( checkGameOver() == -1 ) //Game not over
        {
            cpuTurn( cpuGetTarget(turn%2), turn%2, (turn+1)%2 );
            turn++;
            //wait(10);
        }
    }
    
    private Couple cpuGetTarget(int team)
    {
        
        Couple target;
        Random gen = new Random();
        //If stack is empty
        if( cpuTargets.get(team).empty())
        {
            //Select from only half the board
            int x = gen.nextInt(10);
            int y = (x%2==0)?   gen.nextInt(5)*2+1 //if even, select odd
                            :   gen.nextInt(5)*2;  //else odd, select even
            target = new Couple( x, y );
        }else
        {
            target = cpuTargets.get(team).pop();
        }
        
        return target;
    }
    
    private boolean cpuTurn(Couple target, int team, int teamToAttack)
    {
        
        while( ! super.takeTurn(target.x, target.y, team, teamToAttack ) )
        {
            target = cpuGetTarget(team);
        };
        
        //If shot hit, add  N, S, E, W, to stack
        if( spotHit( target.x, target.y, teamToAttack) && spotOccupied(target.x, target.y, teamToAttack) )
        {
            //Add x+1, y
            if( target.x +1 < 10 )
                cpuTargets.get(team).push( new Couple( target.x +1 , target.y ) );
            //Add x-1, y
            if( target.x -1 >= 0 )
                cpuTargets.get(team).push( new Couple( target.x -1 , target.y ) );
            //Add x, y+1
            if( target.y +1 < 10 )
                cpuTargets.get(team).push( new Couple( target.x  , target.y+1 ) );
            //Add x, y-1
            if( target.y - 1 >= 0 )
                cpuTargets.get(team).push( new Couple( target.x , target.y -1 ) );
        }
        
        
        
        return true;
    }
    
   
}
