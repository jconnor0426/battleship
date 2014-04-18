package FSU.COP3252.battleship;

class Ship {
    
    public static int HORIZONTAL = 0;
    public static int VERTICAL = 1;
    
    protected String name;
    
    private boolean [] hits;
    private int [] location;
    private int orientation;
    protected int size;
    private MyButton[] buttonLocations;
    
    public Ship()
    {
        
    }
    public Ship(int row, int column, String name, int sizeOfShip, MyButton[][] board){
        // set buttonlocations to the buttons on the board

    }
    
    public String toString()
    {
        return name + " " + size ;
    }

    /*public Ship( int location, int orient, int sizeOfShip )
    {
        size = sizeOfShip;
        orientation = orient;
        
        //Initialize to un-hit 
        hits = new boolean[size];
        for( int i =0; i < size; i++ )
            hits[i] = false;
        
        //Set position of the ship
        if(orientation == HORIZONTAL )
        {
            if( validHorizontal( location )  )
                setHorizontalPosition(location);
            else
                setHorizontalPosition(0);
        }else if( orientation == VERTICAL)
        {
            if( validVertical(location) )
                setVerticalPosition(location);
            else
                setVerticalPosition(0);
        }else
            setHorizontalPosition(0);
            
    }*/
    
    /*private boolean validHorizontal( int location )
    {   
        return ((location + size ) / 10 ) == (location / 10 ) ;  
    }
    
    private void setHorizontalPosition( int start )
    {
        for( int i = 0; i < size; i++ )
            location[i] = start + i;
    }
    
    private boolean validVertical( int location )
    {
        return  ( (location + size ) %10) == (location % 10 )  ;
    }
    
    private void setVerticalPosition( int start )
    {
        for( int i = 0; i < size; i++ )
            location[i] = start + (10 * i);
    }*/
}

class Destroyer extends Ship
{
    public Destroyer ()
    {
        name = "Destroyer";
        size = 3;
    }
}

class Carrier extends Ship
{
    public Carrier ()
    {
        name = "Carrier";
        size = 5;
    }
}

class PatrolBoat extends Ship
{
    public PatrolBoat ()
    {
        name = "PatrolBoat";
        size = 2;
    }

}

class Submarine extends Ship
{

    public Submarine ()
    {
        name = "Submarine";
        size = 3;
    }
}

class bs extends Ship
{
    public bs ()
    {
        name = "Battle Ship";
        size = 3;
    }
}
