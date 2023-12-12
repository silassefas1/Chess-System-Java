package boradGame;

public class Piece {
	
	protected Position position;
	private Board Board;
	
	public Piece(boradGame.Board board) {
		Board = board;
		position = null;
	}

	protected Board getBoard() {
		return Board;
	}


	

}
