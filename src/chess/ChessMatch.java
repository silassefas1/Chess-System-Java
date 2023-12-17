package chess;

import boradGame.Board;
import chess.pieces.*;


public class ChessMatch {
	
	private Board board;
	
	public ChessMatch() {
		board = new Board(8,8);
		initialSetup();
		
	}
	
	public ChessPiece[][] getPieces(){
		ChessPiece [][] matriz = new ChessPiece[board.getRows()][board.getColunms()];
		for(int i=0; i<board.getRows();i++) {
			for(int j = 0; j< board.getColunms();j++) {
				matriz[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return matriz;
	}
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	public void initialSetup() {
		placeNewPiece('b', 6, new Rook(board, Color.WHITE));
		placeNewPiece('e', 8, new King(board, Color.WHITE));
		placeNewPiece('e', 1, new King(board, Color.WHITE));
	}
	
	
	
	
}
