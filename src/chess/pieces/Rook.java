package chess.pieces;

import boradGame.Position;
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

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColunms()];
		
		Position auxPosition = new Position(0,0);
		//above
		auxPosition.setValues(position.getRow()-1, position.getColunm());
		while(getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColunm()] = true;
			auxPosition.setRow(auxPosition.getRow()-1);
		}
		if(getBoard().positionExists(auxPosition)&& isThereOpponentPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColunm()] = true;
		}
		
		//left
		auxPosition.setValues(position.getRow(), position.getColunm()-1);
		while(getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColunm()] = true;
			auxPosition.setColunm(auxPosition.getColunm()-1);
		}
		if(getBoard().positionExists(auxPosition)&& isThereOpponentPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColunm()] = true;
		}
		
		
		//right
		auxPosition.setValues(position.getRow(), position.getColunm()+1);
		while(getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColunm()] = true;
			auxPosition.setColunm(auxPosition.getColunm()+1);
		}
		if(getBoard().positionExists(auxPosition)&& isThereOpponentPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColunm()] = true;
		}
		
		//below
		auxPosition.setValues(position.getRow()+1, position.getColunm());
		while(getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColunm()] = true;
			auxPosition.setRow(auxPosition.getRow()+1);
		}
		if(getBoard().positionExists(auxPosition)&& isThereOpponentPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColunm()] = true;
		}
		
		return mat;
	}

}
