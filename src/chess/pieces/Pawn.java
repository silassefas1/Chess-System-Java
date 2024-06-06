package chess.pieces;

import boradGame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece{
	
	private ChessMatch chessMatch;

	public Pawn(boradGame.Board board, Color color,ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
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
			
			//#Especial move en Passant withe
			if(position.getRow() == 3) {
				Position left = new Position(position.getRow(), position.getColunm()-1);
				if(getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable() ) {
					mat[left.getRow()-1][left.getColunm()] = true;
				}
				Position right = new Position(position.getRow(), position.getColunm()+1);
				if(getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					mat[right.getRow()-1][right.getColunm()] = true;
				}
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
			
			//#Especial move en Passant Black
			if(position.getRow() == 4) {
				Position left = new Position(position.getRow(), position.getColunm()-1);
				if(getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable() ) {
					mat[left.getRow()+1][left.getColunm()] = true;
				}
				Position right = new Position(position.getRow(), position.getColunm()+1);
				if(getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					mat[right.getRow()+1][right.getColunm()] = true;
				}
			}
			
		}
		
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}

}
