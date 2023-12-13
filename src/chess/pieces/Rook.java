package chess.pieces;

import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece{

	public Rook(boradGame.Board board, Color color) {
		super(board, color);
		
	}
	
	@Override
	public String toString() {
		return "R";
	}

}
