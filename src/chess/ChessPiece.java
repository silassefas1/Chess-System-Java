package chess;

import boradGame.Piece;
import boradGame.Position;

public abstract class ChessPiece extends Piece {
	
	private Color color;

	public ChessPiece(boradGame.Board board, Color color) {
		super(board);
		this.color = color;
	}
	
	public ChessPosition getChessPosition(){
		return ChessPosition.fromPosition(position);
	}
	
	public Color getColor() {
		return color;
	}
	
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece pieceAux = (ChessPiece)getBoard().piece(position);
		return pieceAux != null && pieceAux.getColor() != color;
	}

	
	

}
