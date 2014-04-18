import java.util.ArrayList;
import java.util.Formatter;
import java.util.Locale;

public class GameBoard
{
        public static int players = 2;
	public static int boardSize = 10;
	private ArrayList< Ship > [] playerShips ;
        private ArrayList<Integer> [] hits ;
        private ArrayList<Integer> [] misses ;
	

	public GameBoard()
	{
            for( int i = 0; i < players; i++ )
            {
		playerShips[i] = new ArrayList< Ship >();
                hits[i] = new ArrayList<Integer>();
                misses[i] = new ArrayList<Integer>();
            }
	}

        public boolean placeShip( int team, Ship toPlace  )
        {
            if( validShipLocation( team, toPlace ) )
                    playerShips[team].add(toPlace);
            
            return true;
        }

	public boolean makeMove( int x, int y, char team)
	{
		if( x > boardSize || x < 0 )
			return false;
		if( y > boardSize || y < 0 )
			return false;
		if( board[x][y] != ' ') 
			return false;
			
		board[x][y] = team;
		return true;
	}

    private boolean validShipLocation(int team, Ship toPlace) {
        
        return true;
    }
}