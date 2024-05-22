package chess;

import boradGame.BoardException;

public class ChessException extends BoardException {

	private static final long serialVersionUID = 1L;
	
	public ChessException(String msg) {
		/*super class das excessoes personalizadas do xadres*/
		super(msg);
	}

}
