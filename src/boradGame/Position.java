package boradGame;

public class Position {
	
	private int row;
	private int colunm;
	
	public Position(int row, int colunm) {
		this.row = row;
		this.colunm = colunm;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColunm() {
		return colunm;
	}

	public void setColunm(int colunm) {
		this.colunm = colunm;
	}
	
	public void setValues(int row, int column) {
		this.row = row;
		this.colunm = column;
	}
	
	@Override
	public String toString() {
		return row + ", " + colunm;
	}

}
