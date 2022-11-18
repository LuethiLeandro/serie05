package game;/* ************************************************************************* *\
*                Programmierung 1 HS 2020 - Serie 5-1                         * 
\* ************************************************************************* */


// Pascal Zürcher, Matrikelnummer: 22-111-314
// Leandro Lüthi. Matrikelnummer: 22-105-035

//package habe ich reingemacht


/**
 * Basis enum to provide player tokens
 */
public enum Token {
       empty( " " ),
       player1( "O" ),
       player2( "X" );
       private String representation; // string representation of value
       Token( String s ) { this.representation = s; }
       public String toString() { return this.representation; }
}
