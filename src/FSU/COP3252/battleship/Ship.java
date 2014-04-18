package FSU.COP3252.battleship;

class Ship {
    
    public static int HORIZONTAL = 0;
    public static int VERTICAL = 1;
    
    private boolean [] hits;
    private int [] location;
    private int orientation;
    private int size;
    
    public Ship( int location, int orient, int sizeOfShip )
    {
        size = sizeOfShip;
        orientation = orient;
        
        //Initialize to un-hit 
        hits = new boolean[size];
        for( int i =0; i < size; i++ )
            hits[i] = false;
        
        /*Set position of the ship */
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
            
    }
    
    private boolean validHorizontal( int location )
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
    }
}

class Destroyer extends Ship
{
    public Destroyer( int location, int orientation )
    {
        super( location, orientation, 3);
    }

}

class Carrier extends Ship
{

    public Carrier( int location, int orientation )
    {
        super( location, orientation, 5);
    }
}

class PatrolBoat extends Ship
{
    public PatrolBoat( int location, int orientation )
    {
        super( location, orientation, 2);
    }

}

class Submarine extends Ship
{

    public Submarine( int location, int orientation )
    {
        super( location, orientation, 3);
    }
}

class Battleship extends Ship
{

    public Battleship( int location, int orientation )
    {
        super( location, orientation, 4);
    }
}