/* ************************************************************************* *\
*                Programmierung 1 HS 2020 - Serie 5-1                         * 
\* ************************************************************************* */
package game;

/** A very stupid computer player */
public class ComputerPlayer implements IPlayer
{
	private Token token;

	/**
	 * Strategy is to chose a random column and select 
	 * the next valid column to the right (the chosen included)
	 */
	public int getNextColumn( Token[][] board ){
		//Was der ComputerPlayer spielen kann
		if(findBestColumn(board)!=-2){
			return findBestColumn(board);
		} else {
			java.util.Random generator = new java.util.Random();
			int col = generator.nextInt(board.length);
			while (isColFull(col, board)) {
				col = (col + 1) % board.length;
			}
			return col;
		}
	}

	public int findBestColumn(Token[][] board ){


		//Was der ComputerPlayer spielen kann
		int[] possibilities = new int[7];
		for(int i=0; i<board.length; i++){
			for (int j = 0; j < board[0].length; j++){
				if(board[i][j]==Token.empty){
					possibilities[i]=j;
					break;
				}
			}
		}
		//rund um jeden Spot wird gezählt, wieviele von seinen Tokens in einer Linie liegen
		//die längste Linie wird in den Array strengh gespeichert
		int[] strengh = new int[7];
		int deny=-1;

		for(int i=0; i<board.length;i++) {
			int c0= 0, c1 = 0, c2 = 0, c3 = 0, c4 = 0, c5 = 0, c6 = 0;
			for (int j=1; j <= 3; j++) {
				if (j == c0 + 1 && possibilities[i] - j >= 0 && board[i][possibilities[i] - j] == Token.player2) {//nach unten
					c0++;
				} else if (j == c1 + 1 && i - j >= 0 && board[i - j][possibilities[i]] == Token.player2) {//nach links
					c1++;
				} else if (j == c2 + 1 && i + j < board.length && board[i + j][possibilities[i]] == Token.player2) {//nach rechts
					c2++;
				} else if (j == c3 + 1 && i - j >= 0 && possibilities[i] - j >= 0 && board[i - j][possibilities[i] - j] == Token.player2) {//nach unten links
					c3++;
				} else if (j == c4 + 1 && i + j < board.length && possibilities[i] - j >= 0 && board[i + j][possibilities[i] - j] == Token.player2) {//nach unten rechts
					c4++;
				} else if (j == c5 + 1 && i - j >= 0 && possibilities[i] + j < board[0].length && board[i - j][possibilities[i] + j] == Token.player2) {//nach oben links
					c5++;
				} else if (j == c6 + 1 && i + j < board.length && possibilities[i] + j < board[0].length && board[i + j][possibilities[i] + j] == Token.player2) {//nach oben rechts
					c6++;
				}

				//Gegner am Sieg hindern
				if( 	(possibilities[i]-3>=0 							&&		//nach unten
						board[i][possibilities[i]-1] == Token.player1 	&&
						board[i][possibilities[i]-2] == Token.player1 	&&
						board[i][possibilities[i]-3] == Token.player1) 		||	//nach links
						(i-3>=0 										&&
						board[i-1][possibilities[i]] == Token.player1 	&&
						board[i-2][possibilities[i]] == Token.player1 	&&
						board[i-3][possibilities[i]] == Token.player1)		||	//nach unten links
						(i-3>=0 && possibilities[i]-3>=0				&&
						board[i-1][possibilities[i]-1]== Token.player1 	&&
						board[i-2][possibilities[i]-2]== Token.player1 	&&
						board[i-3][possibilities[i]-3]== Token.player1)		||	//nach rechts
						(i+3<=board.length 								&&
						board[i+1][possibilities[i]] == Token.player1 	&&
						board[i+2][possibilities[i]] == Token.player1 	&&
						board[i+3][possibilities[i]] == Token.player1)		|| 	//nach unten rechts
						(i+3<=board.length 								&&
						possibilities[i]-3>=0 							&&
						board[i+1][possibilities[i]-1]== Token.player1 	&&
						board[i+2][possibilities[i]-2]== Token.player1	&&
						board[i+3][possibilities[i]-3]== Token.player1) 	||	//nach oben links
						(i-3>=0 && possibilities[i]+3<=board[0].length 	&&
						board[i-1][possibilities[i]+1]== Token.player1	&&
						board[i-2][possibilities[i]+2]== Token.player1	&&
						board[i-3][possibilities[i]+3]== Token.player1)		|| //nach oben rechts
						(i+3<= board.length  							&&
						possibilities[i]+3<=board[0].length				&&
						board[i+1][possibilities[i]+1]== Token.player1 	&&
						board[i+2][possibilities[i]+2]== Token.player1	&&
						board[i+3][possibilities[i]+3]== Token.player1)){
					deny=i;
				}

			}
			if (c0 < c1) c0 = c1;
			if (c0 < c2) c0 = c2;
			if (c0 < c3) c0 = c3;
			if (c0 < c4) c0 = c4;
			if (c0 < c5) c0 = c5;
			if (c0 < c6) c0 = c6;
			strengh[i] = c0;
		}



		//die längste linie Aller Spots wird in int strongest gespeichert
		//die Position von der Zahl strongest im Array wird zurückgegeben
		//Falls es ein Fehler gäbe gibt es -1 zurück und der Computer spielt nach zufall
		int strongest=0;
		int count=0;
		for(int i=0; i<strengh.length; i++){
			if(strongest<strengh[i]) strongest = strengh[i];
			else if(strengh[i]==0){
				count++;
			}
		}
		if(count==7){
			return -2;
		}

		//Vermassel Sieg von Mensch, wenn Computer nicht selbst direkt gewinnen kann
		if(strongest<3 && deny!=-1){
			return deny;
		}

		for(int i=0; i<strengh.length;i++) {
			if (strongest == strengh[i]) {
				return i;
			}
		}
		return -1;
	}


	/**
	 * @return true if the column col is already full and false otherwise. 
	 */
	private boolean isColFull( int col, Token[][] board )
	{
		int topRow = board[ col ].length - 1;
		return ( board[ col ][ topRow ] != Token.empty );
	}


	public void setToken( Token token )
	{
		this.token = token;
	}

	public Token getToken()
	{
		return this.token;
	}

	public String getPlayersName()
	{
		return "Random Player";
	}
}
