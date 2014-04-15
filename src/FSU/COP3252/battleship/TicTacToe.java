

public class TicTacToe 
{

	public static void main(String[] args) {
		
		//Initialization of game board and players
		GameBoard board  = new GameBoard();
		int maxPlayers = 2;
		Player [] players = new Player[ maxPlayers ];
		

		//Get command Line arguments
		/*
			java TicTacToe [-c [1|2]]
			
			java TicTacToe	// two human players
			java TicTacToe -c	// two computer players
			java TicTacToe -c 1	// computer is player 1, human player 2
			java TicTacToe -c 2	// human player 1, computer player 2
		*/

		if( args.length ==0 )
		{
			for( int i = 0; i < maxPlayers; i++ )
				players[i] = new HumanPlayer( i );
		}
		else  // Parse the  input
		{
			
			if ( !args[0].equals("-c" ) )
			{
				System.out.println( "Arguments must be in the form: java TicTacToe [-c [1|2]]");
				return;
			}

			int computerPlayer;

			if( args.length == 1)
			{
				for( int i = 0; i < maxPlayers; i++ )
					players[i] = new CPUPlayer( i );
			} else
			{
				try 
				{
					computerPlayer = Integer.parseInt(args[1])  ; 
				} catch (NumberFormatException e) {
					System.out.println( "Arguments must be in the form: java TicTacToe [-c [1|2]]");
					return;
				}

				if( computerPlayer != 1 && computerPlayer != 2 )
				{
					System.out.println( "Arguments must be in the form: java TicTacToe [-c [1|2]]");
					return;
				}
				computerPlayer--; // -1 to account for 0 indexed arrays
				players[ computerPlayer ] = new CPUPlayer( computerPlayer ); 
				for( int i = 0; i < maxPlayers; i++ )
					if( i != computerPlayer )
						players[i] = new HumanPlayer( i );
			}

		}

			

		int turn = 0;

		while( !board.gameover() )
		{
			System.out.println( board );
			players[  turn % maxPlayers ].takeTurn(  board );
			turn += 1;
		}

		System.out.println( board);

		if( !board.fullBoard() )
		{
			System.out.println( "Player " + ( ( turn - 1)  % 2 + 1) + " won!");
		}else
		{
			System.out.println( "Cat's Game!");
		}


	}
}