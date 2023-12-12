package application;

import chess.ChessMatch;

public class Program {

	public static void main(String[] args) {
		
		ChessMatch Match = new ChessMatch();
		UI.printBoard(Match.getPieces());
		

	}

}
