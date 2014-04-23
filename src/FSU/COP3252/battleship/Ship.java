import java.awt.Color;
import java.util.ArrayList;

class Ship {
    
    protected Color shipColor;
    
    public static int HORIZONTAL = 0;
    public static int VERTICAL = 1;
    
    protected String name;
    
    protected boolean [] hits;
    protected int orientation;
    protected int size;
    protected ArrayList < MyButton > buttonLocations;
    protected int numberOfHits;
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

    public void addButton(MyButton b){
        buttonLocations.add(b);
    }

    public void clearButtonList(){
        buttonLocations.clear();
    }

    public ArrayList < MyButton > getButtonLocations(){
        return buttonLocations;
    }

    public void increaseHits(){
        numberOfHits += 1;
    }

    public int getNumberOfHits(){
        return numberOfHits;
    }

    public boolean getSunk(){
        return (numberOfHits == size);
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
        buttonLocations = new ArrayList < MyButton > (size);
        numberOfHits = 0;
    }
}

class Carrier extends Ship
{
    public Carrier ()
    {
        name = "Carrier";
        size = 5;
        hits = new boolean[size];
        shipColor = Color.YELLOW;
        buttonLocations = new ArrayList < MyButton > (size);
        numberOfHits = 0;
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
        buttonLocations = new ArrayList < MyButton > (size);
        numberOfHits = 0;
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
        buttonLocations = new ArrayList < MyButton > (size);
        numberOfHits = 0;
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
        buttonLocations = new ArrayList < MyButton > (size);
        numberOfHits = 0;
    }
}
