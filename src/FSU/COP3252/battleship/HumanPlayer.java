import java.util.Scanner;

public class HumanPlayer extends Player
{

	public HumanPlayer( int teamNumber )
	{
		teamNum = teamNumber +1;
		if( teamNum == 2 )
			teamChar = 'O';
		else
			teamChar = 'X';
	}

	public void takeTurn( GameBoard board)
	{
		boolean validInput = false;
		int x;
		Scanner s = new Scanner( System.in );
		while( !validInput )
		{
			System.out.print( "Player " + teamNum + " please enter a move (1-9): " );
                		try
                		{
                			x = s.nextInt();
             			if ( x > 0 && x <= 9 )
             			{
             				if( board.getSpace(  (x-1)/3 , (x-1)%3  ) == ' ' )
             				{
             					board.makeMove(   ( x-1) / 3 , (x -1) % 3, teamChar );
             					validInput = true;
             				}
             				else
             					System.out.println( "Space is taken!"); 
             			}
             			else
             				System.out.println( "invalid number!" );
                		} catch (Exception e) {
       					System.out.println("enter a valid number" + " must be an integer");
        			}	
		}
        		
	}
        
        public void placeShips( GameBoard board)
        {
            
        }
}