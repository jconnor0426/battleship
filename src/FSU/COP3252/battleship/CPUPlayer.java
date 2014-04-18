

import java.util.Random;


public class CPUPlayer extends Player
{
	private char opponentMark;
	private int freeCounter = 0;
	private int[] freeList = new int [9] ;

	public CPUPlayer( int teamNumber )
	{
		teamNum = teamNumber +1;
		if( teamNum == 2 )
		{
			teamChar = 'O';
			opponentMark = 'X';
		}
		else
		{
			teamChar = 'X';
			opponentMark = 'O';
		}
	}

	public void takeTurn(GameBoard board)
	{
//		GameBoard test;
//		//Check for winning Moves
//		for( int i = 0; i < board.boardSize; i++)
//		{
//			for ( int j = 0; j < board.boardSize ; j++ ) 
//			{
//				if( board.getSpace( i, j ) == ' ' )
//				{
//					//Check if that would win
//					test = new GameBoard( board);
//					test.makeMove( i, j, teamChar );
//					if(  test.checkWin( teamChar )  )
//					{
//						board.makeMove( i, j, teamChar );
//						return;
//					}
//				}
//			}
//		}
//
//		for( int i = 0; i < board.boardSize; i++)
//		{
//			for ( int j = 0; j < board.boardSize ; j++ ) 
//			{
//				if( board.getSpace( i, j ) == ' ' )
//				{
//					//Check if that would win
//					test = new GameBoard( board);
//					test.makeMove( i, j, opponentMark  );
//					if(  test.checkWin( opponentMark )  )
//					{
//						board.makeMove( i, j, teamChar );
//						return;
//					}
//				}
//			}
//		}
//
//		//Check center
//		if( board.getSpace( 1,1 ) == ' ' )
//		{		
//			board.makeMove( 1, 1, teamChar );
//			return;
//		}
//
//
//		//Update FreeList
//		freeCounter = 0;
//		for( int x = 0; x < 9; x++)
//		{
//			if( board.getSpace(  (x)/3 , (x)%3  ) == ' ')
//				freeList[ freeCounter++] = x; 
//		}
//
//		Random generator = new Random();
//               	int pick;
//        		pick = generator.nextInt( freeCounter ) ;
//
//        		board.makeMove( freeList[pick]/3, freeList[pick]%3, teamChar);
//	


	}

        @Override
        public void placeShips( GameBoard board)
        {
            int randomLocation = 0;
            int randomOrientation = 0;
            //Place Carrier
//            board.placeShip( teamNum, 
//                            new Carrier( randomLocation, randomOrientation )
//                            );
            //Place Battleship
            
            //Place Destroyer
            
            //Place Sub
            
            //Place PatrolBoat
        }

}