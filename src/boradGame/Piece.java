package boradGame;

public abstract class Piece {
	
	protected Position position;
	private Board Board;
	
	public Piece(boradGame.Board board) {
		Board = board;
		position = null;
	}

	protected Board getBoard() {
		return Board;
	}

	public abstract boolean[][] possibleMoves();
		
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColunm()];
	}
	
	public boolean isThereAnyPossibleMove() {
		boolean[][] matrizAux = possibleMoves();
		for(int i =0; i< matrizAux.length;i++) {
			for(int j =0; j< matrizAux.length;j++) {
				if(matrizAux[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}
