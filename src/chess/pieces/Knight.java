package chess.pieces;


import boradGame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece{

	public Knight(boradGame.Board board, Color color) {
		super(board, color);
		
	}
	
	@Override
	//retorna a string k para o rei
	public String toString() {
		return "N";
		
	}
	
	private boolean canMove(Position position) {
		ChessPiece piece = (ChessPiece)getBoard().piece(position);
		return piece == null || piece.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		// movimento possiveis do rei
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColunms()];
		
		Position p = new Position(0,0);
		//above
		p.setValues(position.getRow() - 1, position.getColunm() -2);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColunm()] = true;
		}
		
	
		p.setValues(position.getRow() - 2, position.getColunm()-1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColunm()] = true;
		}

		
		p.setValues(position.getRow()-2, position.getColunm() + 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColunm()] = true;
		}
		

		p.setValues(position.getRow()-1, position.getColunm() + 2);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColunm()] = true;
		}
		
		
		p.setValues(position.getRow() + 1, position.getColunm() + 2);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColunm()] = true;
		}
		

		p.setValues(position.getRow() + 2, position.getColunm() + 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColunm()] = true;
		}

		p.setValues(position.getRow() + 2, position.getColunm() - 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColunm()] = true;
		}
		
	
		p.setValues(position.getRow() + 1, position.getColunm() - 2);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColunm()] = true;
		}
		
		
		
		return mat;
	}
	

}
