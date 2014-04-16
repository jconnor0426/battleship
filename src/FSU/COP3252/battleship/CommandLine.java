/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FSU.COP3252.battleship;

/**
 *
 * @author Connor
 */
public class CommandLine {
    
    public static void main(String[] args) {
		
		//Initialization of game board and players
		GameBoard board  = new GameBoard();
		int maxPlayers = 2;
		Player [] players = new Player[ maxPlayers ];
		
                players[0] = new HumanPlayer( 0 );
                players[1] = new CPUPlayer( 1 );

                /*Let the player build his ships */
                for( int i = 0; i < maxPlayers; i++ )
                    players[i].placeShips( board );
                
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
