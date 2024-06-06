package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boradGame.Board;
import boradGame.Piece;
import boradGame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;


public class ChessMatch {
	
	private Board board;
	private int turn;
	private Color currentPlayer;
	private boolean check;
	private boolean checkMate;
	private ChessPiece enPassantVulnerable;
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	
	
	public ChessMatch() {
		/*Configurações iniciais e incrementos da partida*/
		board = new Board(8,8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
		
	}
	
	public int getTurn() {
		/*Retorna o turno atual */
		return turn;
	}
	
	public Color getCurrentPlayer() {
		/*Retorna o jogador da vez*/
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
	}
	
	public ChessPiece getEnPassantVulnerable() {
		return enPassantVulnerable;
	}
	
	
	
	public ChessPiece[][] getPieces(){
		/*Gera a matriz com as peças para o jogo no console*/
		ChessPiece [][] matriz = new ChessPiece[board.getRows()][board.getColunms()];
		for(int i=0; i<board.getRows();i++) {
			for(int j = 0; j< board.getColunms();j++) {
				matriz[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return matriz;
	}
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		/*Verifica se o movimento é possivel para a peça apartir da origen*/
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		
		return board.piece(position).possibleMoves();
		
		
		}
	
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		/*Executa o movimento completo do xadres*/
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturePiece = makeMove(source, target);
		
		if(testCheck(currentPlayer)) {
			undoMove(source, target, capturePiece);
			throw new ChessException("You Can't Put Yourself in Check");
		}
		
		ChessPiece movedPiece = (ChessPiece)board.piece(target);
		
		check = (testCheck(opponent(currentPlayer)));
		
		if(testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		}else {
			nextTurn();
		}
		
		//Especialmove en passant
		if(movedPiece instanceof Pawn && target.getRow() == source.getRow() - 2 || target.getRow() == source.getRow() + 2 ) {
			enPassantVulnerable = movedPiece;
		}else {
			enPassantVulnerable = null;
		}

		return (ChessPiece)capturePiece;
	}
	
	private Piece makeMove(Position source, Position target) {
		/*Remova a peça na origem e a posiciona no destino */
		ChessPiece p = (ChessPiece)board.removePiece(source);
		p.increaseMoveCount();
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		//# especialmove castling kingside rook
		if(p instanceof King && target.getColunm() == source.getColunm() +2) {
			Position sourceRook = new Position(source.getRow(), source.getColunm()+3);
			Position targetRook = new Position(source.getRow(), source.getColunm()+1);
			ChessPiece rook = (ChessPiece)board.removePiece(sourceRook);
			board.placePiece(rook, targetRook);
			rook.increaseMoveCount();
		}
		
		//# especialmove castling Queenside rook
		if(p instanceof King && target.getColunm() == source.getColunm() -2) {
			Position sourceRook = new Position(source.getRow(), source.getColunm()-4);
			Position targetRook = new Position(source.getRow(), source.getColunm()-1);
			ChessPiece rook = (ChessPiece)board.removePiece(sourceRook);
			board.placePiece(rook, targetRook);
			rook.increaseMoveCount();
		}
		
		return capturedPiece;
	}
	
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		/*Metodo para desfazer um movimento*/
		ChessPiece p = (ChessPiece)board.removePiece(target);
		board.placePiece(p, source);
		p.decreaseMoveCount();
		if(capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
		//# especialmove castling kingside rook
		if(p instanceof King && target.getColunm() == source.getColunm() +2) {
			Position sourceRook = new Position(source.getRow(), source.getColunm()+3);
			Position targetRook = new Position(source.getRow(), source.getColunm()+1);
			ChessPiece rook = (ChessPiece)board.removePiece(targetRook);
			board.placePiece(rook, sourceRook);
			rook.decreaseMoveCount();
		}
		
		//# especialmove castling Queenside rook
		if(p instanceof King && target.getColunm() == source.getColunm() -2) {
			Position sourceRook = new Position(source.getRow(), source.getColunm()-4);
			Position targetRook = new Position(source.getRow(), source.getColunm()-1);
			ChessPiece rook = (ChessPiece)board.removePiece(targetRook);
			board.placePiece(rook, sourceRook);
			rook.decreaseMoveCount();
		}
		
	}
	
	private void validateSourcePosition(Position position) {
		/*Valida as regras para executar um movimento*/
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
		if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece is not yours");
		}
		
		
		if(!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible move for the chosen piece.");
		}
	
	}
	

	
	private void validateTargetPosition(Position source, Position target) {
		/*Valida a posição de movimento no tabuleiro*/
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can't move to target position.");
		}
	}
	
	private Color opponent(Color color) {
		/*Pega a cor do jogador e retorna a cor do oponente*/
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	
		// nota: os operadores "?" e ":" funcionão como operadores de condição. Ex: Condição ? Caso VERDADE : Caso FALSO
	}
	
	private ChessPiece king(Color color) {
		/*Verifica se o Rei é uma peça no tabuleiro, parte da logica do check*/
		List<Piece> list = piecesOnTheBoard.stream().filter(x ->((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece p : list) {
			if(p instanceof King) {
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("There is no King " +color+ " on the board ");
		
	}
	
	private boolean testCheck(Color color) {
		/*metodo que verifica se o rei esta em cheque varendo uma lista de peças do oponente e
		 *  verificando se elas tem um movimento que caia na posição atual do rei*/
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x ->((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		for (Piece p: opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			if(mat[kingPosition.getRow()][kingPosition.getColunm()]) {
				return true;
			}
		}
		return false;
		
	}
	
	
	private boolean testCheckMate(Color color) {
		if (!testCheck(color)) {
			return false;
		}
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece p : list) {
			boolean[][] mat = p.possibleMoves();
			for (int i=0; i<board.getRows(); i++) {
				for (int j=0; j<board.getColunms(); j++) {
					if (mat[i][j]) {
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i, j);
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = testCheck(color);
						undoMove(source, target, capturedPiece);
						if (!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}
	
	private void nextTurn() {
		/*Muda o oponente e incrementa o turno*/
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	
	
	public void initialSetup() {
		/*Posiciona as peças inicialmente no tabuleiro*/
		placeNewPiece('a', 1, new Rook(board, Color.WHITE));
		placeNewPiece('b', 1, new Knight(board, Color.WHITE));
		placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('d', 1, new Queen(board, Color.WHITE));
		placeNewPiece('e', 1, new King(board, Color.WHITE, this));
		placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('g', 1, new Knight(board, Color.WHITE));
		placeNewPiece('h', 1, new Rook(board, Color.WHITE));
		placeNewPiece('a', 2, new Pawn(board, Color.WHITE,this));
		placeNewPiece('b', 2, new Pawn(board, Color.WHITE,this));
		placeNewPiece('c', 2, new Pawn(board, Color.WHITE,this));
		placeNewPiece('d', 2, new Pawn(board, Color.WHITE,this));
		placeNewPiece('e', 2, new Pawn(board, Color.WHITE,this));
		placeNewPiece('f', 2, new Pawn(board, Color.WHITE,this));
		placeNewPiece('g', 2, new Pawn(board, Color.WHITE,this));
		placeNewPiece('h', 2, new Pawn(board, Color.WHITE,this));
		
		placeNewPiece('a', 8, new Rook(board, Color.BLACK));
		placeNewPiece('b', 8, new Knight(board, Color.BLACK));
		placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('d', 8, new Queen(board, Color.BLACK));
		placeNewPiece('e', 8, new King(board, Color.BLACK, this));
		placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('g', 8, new Knight(board, Color.BLACK));
		placeNewPiece('h', 8, new Rook(board, Color.BLACK));
		placeNewPiece('a', 7, new Pawn(board, Color.BLACK,this));
		placeNewPiece('b', 7, new Pawn(board, Color.BLACK,this));
		placeNewPiece('c', 7, new Pawn(board, Color.BLACK,this));
		placeNewPiece('d', 7, new Pawn(board, Color.BLACK,this));
		placeNewPiece('e', 7, new Pawn(board, Color.BLACK,this));
		placeNewPiece('f', 7, new Pawn(board, Color.BLACK,this));
		placeNewPiece('g', 7, new Pawn(board, Color.BLACK,this));
		placeNewPiece('h', 7, new Pawn(board, Color.BLACK,this));



	
	/*	//Teste de logica de xeque mate
		
		placeNewPiece('h', 7, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new King(board, Color.WHITE));
		
		placeNewPiece('b', 8, new Rook(board, Color.BLACK));
		placeNewPiece('a', 8, new King(board, Color.BLACK));
		*/
	}
	
	
	
	
}
