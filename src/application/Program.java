package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		
		ChessMatch chessMatch = new ChessMatch();
		Scanner scan = new Scanner(System.in);
		List<ChessPiece> captured = new ArrayList<>();
		
		while(!chessMatch.getCheckMate()){
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(scan);
				
				boolean [][] possibleMoves = chessMatch.possibleMoves(source);
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				UI.clearScreen();
				
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(scan);
				
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				
				if(capturedPiece != null) {
					captured.add(capturedPiece);
				}
			
				if(chessMatch.getPromoted() != null) {
					System.out.print("Enter piece for promotion (B/N/R/Q): ");
					String type = scan.nextLine();
					while(!type.equals("B") && !type.equals("N") &&!type.equals("R") && !type.equals("Q")) {
						System.out.print("Invalid Value! Enter piece for promotion (B/N/R/Q): ");
					}
					chessMatch.replacePromotedPiece(type.toUpperCase());
				}
				
			}
		
			catch(ChessException e) {
				System.out.println(e.getMessage());
				System.out.println("press ENTER to continue:");
				scan.nextLine();	
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				System.out.println("press ENTER to continue:");
				scan.nextLine();
				UI.clearScreen();
			}

		}
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);
		

	}

}
