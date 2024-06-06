package boradGame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces;

	public Board(int rows, int columns) {
		/**/
		if (rows < 1 || columns < 1) {
			throw new BoardException("Erro creating board: there must be at least 1 row and column ");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];

	}

	public int getRows() {
		return rows;
	}

	public int getColunms() {
		return columns;
	}

	public Piece piece(int row, int column) {
		if (!positionExists(row, column)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[row][column];

	}

	public Piece piece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[position.getRow()][position.getColunm()];
	}

	public void placePiece(Piece piece, Position position) {
		/*Adiciona uma peça ao tabuleiro*/
		if (thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position " + position);
		}
		pieces[position.getRow()][position.getColunm()] = piece;
		piece.position = position;
	}

	public Piece removePiece(Position position) {
		/*Remove uma peça do tabuleiro*/
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		if (piece(position) == null) {
			return null;
		}
		Piece aux = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getColunm()] = null;
		return aux;
	}

	private boolean positionExists(int row, int column) {
		/*Verifica se a posição é existente*/
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}

	public boolean positionExists(Position position) {
		/*sobrecarga de operador positionExists*/
		return positionExists(position.getRow(), position.getColunm());
	}

	public boolean thereIsAPiece(Position position) {
		// metodo verifica se a posição contem uma peça
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return piece(position) != null;
	}
}
