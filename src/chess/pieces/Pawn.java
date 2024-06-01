package chess.pieces;

import boradGame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece{

	public Pawn(boradGame.Board board, Color color) {
		super(board, color);
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColunms()];
		
		Position auxPosition = new Position(0,0);
		
		if(getColor() == Color.WHITE) {
			auxPosition.setValues(position.getRow()-1, position.getColunm());
			if(getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
				mat[auxPosition.getRow()][auxPosition.getColunm()] = true;
			}
			auxPosition.setValues(position.getRow()-2, position.getColunm());
			Position position2 = new Position(position.getRow()-1, position.getColunm());
			if(getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition) && !getBoard().thereIsAPiece(position2) && getMoveCount() == 0) {
				mat[auxPosition.getRow()][auxPosition.getColunm()] = true;
			}
			auxPosition.setValues(position.getRow()-1, position.getColunm()-1);
			if(getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)) {
				mat[auxPosition.getRow()][auxPosition.getColunm()] = true;
			}
			auxPosition.setValues(position.getRow()-1, position.getColunm()+1);
			if(getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)) {
				mat[auxPosition.getRow()][auxPosition.getColunm()] = true;
			}
		}
		else {
			auxPosition.setValues(position.getRow()+1, position.getColunm());
			if(getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
				mat[auxPosition.getRow()][auxPosition.getColunm()] = true;
			}
			auxPosition.setValues(position.getRow()+2, position.getColunm());
			Position position2 = new Position(position.getRow()+1, position.getColunm());
			if(getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition) && !getBoard().thereIsAPiece(position2) && getMoveCount() == 0) {
				mat[auxPosition.getRow()][auxPosition.getColunm()] = true;
			}
			auxPosition.setValues(position.getRow()+1, position.getColunm()-1);
			if(getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)) {
				mat[auxPosition.getRow()][auxPosition.getColunm()] = true;
			}
			auxPosition.setValues(position.getRow()+1, position.getColunm()+1);
			if(getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)) {
				mat[auxPosition.getRow()][auxPosition.getColunm()] = true;
			}
			
		}
		
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}

}
