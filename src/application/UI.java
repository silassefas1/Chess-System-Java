package application;

import java.util.InputMismatchException;
import java.util.Scanner;
import chess.ChessPiece;
import chess.ChessPosition;

public class UI {
	
	public static void printBoard(ChessPiece[][] pieces) {
		for(int i = 0; i < pieces.length;i++) {
			System.out.print(8 - i + " ");
			for (int j=0; j <pieces.length;j++) {
				printePiece(pieces[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
		
		
	}
	public static ChessPosition readChessPosition(Scanner scan) {
		try {
			String position = scan.nextLine();
			char column = position.charAt(0);
			int row = Integer.parseInt(position.substring(1));
			return new ChessPosition(column,row);
		}
		catch(RuntimeException e){
			throw new InputMismatchException("Erro reading ChessPosition. Valid Value are from a1 to h8.");
		}
	}
	private static void printePiece(ChessPiece piece) {
		if(piece == null) {
			System.out.print("-");
		}else {
			System.out.print(piece);
		}
		System.out.print(" ");
	}
}
