/* ************************************************************************* *\
*                Programmierung 1 HS 2020 - Serie 5-1                         * 
\* ************************************************************************* */


// Pascal Zürcher, Matrikelnummer: 22-111-314
// Leandro Lüthi. Matrikelnummer: 22-105-035


package game;

import java.util.Arrays;
import java.util.Scanner;


public class VierGewinnt
{

	public static final int COLS = 7;
	public static final int ROWS = 6;

	private Token[][] board = new Token[ COLS ][ ROWS ]; // 7 columns with 6 fields each
	private IPlayer[] players = new IPlayer[ 2 ]; // two players

	/** initialize board and players and start the game */
	public void play()
	{
		// initialize the board
		for ( Token[] column : this.board ) {
			Arrays.fill( column, Token.empty );
		}

		/* initialize players */
		players[ 0 ] = new HumanPlayer();
		System.out.print( "Play against a human opponent? (y / n) " );
		String opponent = new Scanner( System.in ).nextLine().toLowerCase();
		while ( ( 1 != opponent.length() ) || ( -1 == ( "yn".indexOf ( opponent ) ) ) ) {
			System.out.print( "Can't understand your answer. Play against a human opponent? (y / n) " );
			opponent = new Scanner( System.in ).nextLine().toLowerCase();
		}
		if ( opponent.equals( "y" ) ) {
			players[ 1 ] = new HumanPlayer();
		} else {
			players[ 1 ] = new ComputerPlayer();
		}
		players[ 0 ].setToken( Token.player1 );
		players[ 1 ].setToken( Token.player2 );

		/* play... */
		boolean solved = false;
		int currentPlayer = ( new java.util.Random() ).nextInt( 2 );  //choose randomly who begins
		System.out.println( "current player: " + currentPlayer );
		int insertCol, insertRow; // starting from 0
		while ( !solved && !this.isBoardFull() ) {
			// get player's next "move"
			// note that we pass only a copy of the board as an argument,
			// otherwise the player would be able to manipulate the board and cheat!
			insertCol = players[ currentPlayer ].getNextColumn( getCopyOfBoard() );
			// insert the token and get the row where it landed
			insertRow = this.insertToken( insertCol, players[ currentPlayer ].getToken() );
			// check if the game is over
			solved = this.checkVierGewinnt( insertCol, insertRow );
			//switch to other player
			if ( !solved )
				currentPlayer = ( currentPlayer + 1 ) % 2;
		}
		System.out.println( displayBoard( this.board ) );
		if ( solved )
			System.out.println( "Player " + players[ currentPlayer ].getToken() + " wins!" );
		else
			System.out.println( "Draw! Game over." );
	}


	/**
	 * Inserts the token at the specified column (if possible)
	 * @param column the column to insert the token
	 * @param tok the players token
	 * @return the row where the token landed 
	 */
	private int insertToken(int column, Token tok ){

		if(column>COLS || column<0){
			System.exit(1);
		}
		for(int i=0; i<this.board[column].length; i++){
			if(this.board[column][i]==Token.empty){
				this.board[column][i] = tok;
				return i;
			}
		}
		System.exit(1);
		return -1; //Man soll dieses ersetzen, doch es wird ein Rückgabewert verlangt.
	}


	/**
	 * Checks if every position is occupied 
	 * @returns true, iff the board is full.
	 */
	private boolean isBoardFull(){
		for(int i=0; i<COLS; i++){
			for(int j=0; j<ROWS; j++){
				if(board[i][j] == Token.empty){
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Checks for at least four equal tokens in a row in
	 * either direction, starting from the given position. 
	 */
	private boolean checkVierGewinnt( int col, int row ){
		if(board[col][row]==Token.empty){
			return false;
		}

		if(row-3>=0 && board[col][row]==board[col][row-1] && //nach unten
				board[col][row]==board[col][row-2] &&
				board[col][row]==board[col][row-3]){
			return true;
		}
		else if((col+3<COLS 						&&	// rechts und links
				board[col][row]==board[col+1][row] 	&&
				board[col][row]==board[col+2][row] 	&&
				board[col][row]==board[col+3][row])		||
				(col-1>=0 && col+2<COLS 			&&
				board[col][row]==board[col-1][row] 	&&
				board[col][row]==board[col+1][row] 	&&
				board[col][row]==board[col+2][row]) 	||
				(col-2>=0 && col+1<COLS 			&&
				board[col][row]==board[col-2][row] 	&&
				board[col][row]==board[col-1][row] 	&&
				board[col][row]==board[col+1][row])		||
				(col-3>=0 							&&
				board[col][row]==board[col-1][row] 	&&
				board[col][row]==board[col-2][row] 	&&
				board[col][row]==board[col-3][row])){
			return true;
		}
		else if((col-3>=0 && row-3>=0 					&&
				board[col][row]==board[col-1][row-1]	&& 	//von unten-links nach oben-rechts
				board[col][row]==board[col-2][row-2] 	&&
				board[col][row]==board[col-3][row-3])		||
				(col-2>=0 && row-2>=0 					&&
				col+1<COLS && row+1<ROWS				&&
				board[col][row]==board[col-2][row-2]	&&
				board[col][row]==board[col-1][row-1] 	&&
				board[col][row]==board[col+1][row+1])		||
				(col-1>=0 && row-1>=0 					&&
				col+2<COLS && row+2<ROWS				&&
				board[col][row]==board[col-1][row-1]	&&
				board[col][row]==board[col+1][row+1] 	&&
				board[col][row]==board[col+2][row+2])		||
				(col+3<COLS && row+3<ROWS				&&
				board[col][row]==board[col+1][row+1]	&&
				board[col][row]==board[col+2][row+2] 	&&
				board[col][row]==board[col+3][row+3])){
			return true;
		}
		else if((col+3<COLS && row-3>=0 				&& 		//von oben-links nach unten-rechts
				board[col][row]==board[col+1][row-1] 	&&
				board[col][row]==board[col+2][row-2] 	&&
				board[col][row]==board[col+3][row-3])		||
				(col+2<COLS && row-2>=0				&&
				col-1>=0 && row+1<ROWS					&&
				board[col][row]==board[col+1][row-1] 	&&
				board[col][row]==board[col+2][row-2] 	&&
				board[col][row]==board[col-1][row+1])		||
				(col+1<COLS && row-1>=0					&&
				col-2>=0 && row+2<ROWS					&&
				board[col][row]==board[col+1][row-1] 	&&
				board[col][row]==board[col-1][row+1] 	&&
				board[col][row]==board[col-2][row+2])		||
				(col-3>=0 && row+3<ROWS					&&
				board[col][row]==board[col-1][row+1] 	&&
				board[col][row]==board[col-2][row+2] 	&&
				board[col][row]==board[col-3][row+3])){
			return true;
		}
		else{
			return false;
		}
	}


	/** Returns a (deep) copy of the board array */
	private Token[][] getCopyOfBoard()
	{
		Token[][] copiedBoard = new Token[ COLS ][ ROWS ];
		for ( int i = 0; i < copiedBoard.length; i++ ) {
			for ( int j = 0; j < copiedBoard[ i ].length; j++ ) {
				copiedBoard[ i ][ j ] = this.board[ i ][ j ];
			}
		}
		return copiedBoard;
	}


	/** returns a graphical representation of the board */
	public static String displayBoard( Token[][] myBoard )
	{
		String rowDelimiter = "+";
		String rowNumbering = " ";
		for ( int col = 0; col < myBoard.length; col++ ) {
			rowDelimiter += "---+";
			rowNumbering += " " + ( col + 1 ) + "  ";
		}
		rowDelimiter += "\n";

		String rowStr;
		String presentation = rowDelimiter;
		for ( int row = myBoard[ 0 ].length - 1; row >= 0; row-- ) {
			rowStr = "| ";
			for ( int col = 0; col < myBoard.length; col++ ) {
				rowStr += myBoard[ col ][ row ].toString() + " | ";
			}
			presentation += rowStr + "\n" + rowDelimiter;
		}
		presentation += rowNumbering;
		return presentation;
	}



	/** main method, starts the program */
	public static void main( String args[] )
	{
		VierGewinnt game = new VierGewinnt();
		game.play();
	}
}
