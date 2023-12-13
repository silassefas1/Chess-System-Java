package chess;

import boradGame.Board;
import boradGame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

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
	
	public void initialSetup() {
		board.placePiece(new Rook(board, Color.WHITE), new Position (2,1));
		board.placePiece(new King(board, Color.BLACK), new Position (0,4));
		board.placePiece(new King(board, Color.BLACK), new Position (7,4));
	}
	
	
	
	
}
