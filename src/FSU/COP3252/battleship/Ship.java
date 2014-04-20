import java.awt.Color;

class Ship {
    
    protected Color shipColor;
    
    public static int HORIZONTAL = 0;
    public static int VERTICAL = 1;
    
    protected String name;
    
    protected boolean [] hits;
    protected int orientation;
    protected int size;
    private MyButton[] buttonLocations;
    private int x;
    private int y;
    
    public Ship()
    {

    }
    
    public String toString()
    {
        return name + " " + size ;
    }

    public int getSize(){
        return size;
    }

    public void setRow(int row){
        x = row;
    }

    public void setColumn(int column){
        y = column;
    }

    public int getRow(){
        return x;
    }

    public int getColumn(){
        return y;
    }

    public void setOrientation(int o){
        orientation = o;
    }

    public int getOrientation(){
        return orientation ;
    }
    
    public String getName()
    {
        return name;
    }
    
    public Color getColor()
    {
        return shipColor;
    }
}

class Destroyer extends Ship
{

    public Destroyer ()
    {
        name = "Destroyer";
        size = 3;
        hits = new boolean[size];
        shipColor = Color.BLACK;
    }
}

class Carrier extends Ship
{
    public Carrier ()
    {
        name = "Carrier";
        size = 5;
        hits = new boolean[size];
        shipColor = Color.BLUE;
    }
}

class PatrolBoat extends Ship
{
    public PatrolBoat ()
    {
        name = "PatrolBoat";
        size = 2;
        hits = new boolean[size];
        shipColor = Color.GREEN;
    }

}

class Submarine extends Ship
{

    public Submarine ()
    {
        name = "Submarine";
        size = 3;
        hits = new boolean[size];
        shipColor = Color.MAGENTA;
    }
}

class bs extends Ship
{
    public bs ()
    {
        name = "Battle Ship";
        size = 4;
        hits = new boolean[size];
        shipColor = Color.PINK;
    }
}
