package chess;

import boradGame.Piece;

public abstract class ChessPiece extends Piece {
	
	private Color color;

	public ChessPiece(boradGame.Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	
	

}
