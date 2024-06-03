package chess.pieces;

import boradGame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece{

	public Bishop(boradGame.Board board, Color color) {
		super(board, color);
		
	}
	
	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColunms()];
		
		Position auxPosition = new Position(0,0);
		//NorthWest
		auxPosition.setValues(position.getRow()-1, position.getColunm()-1);
		while(getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColunm()] = true;
			auxPosition.setValues(auxPosition.getRow()-1, auxPosition.getColunm()-1);
		}
		if(getBoard().positionExists(auxPosition)&& isThereOpponentPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColunm()] = true;
		}
		
		//NorthEast
		auxPosition.setValues(position.getRow()-1, position.getColunm()+1);
		while(getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColunm()] = true;
			auxPosition.setValues(auxPosition.getRow()-1, auxPosition.getColunm()+1);
		}
		if(getBoard().positionExists(auxPosition)&& isThereOpponentPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColunm()] = true;
		}
		
		
		//SouthEast
		auxPosition.setValues(position.getRow()+1, position.getColunm()+1);
		while(getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColunm()] = true;
			auxPosition.setValues(auxPosition.getRow()+1, auxPosition.getColunm()+1);
		}
		if(getBoard().positionExists(auxPosition)&& isThereOpponentPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColunm()] = true;
		}
		
		//SouthWest
		auxPosition.setValues(position.getRow() +1, position.getColunm() -1);
		while(getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColunm()] = true;
			auxPosition.setValues(auxPosition.getRow()-1, auxPosition.getColunm()-1);
		}
		if(getBoard().positionExists(auxPosition)&& isThereOpponentPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColunm()] = true;
		}
		
		return mat;
	}

}
