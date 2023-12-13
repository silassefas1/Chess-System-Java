package chess.pieces;

import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{

	public King(boradGame.Board board, Color color) {
		super(board, color);
		
	}
	
	@Override
	public String toString() {
		return "K";
		
	}
	

}
