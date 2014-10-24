package game;

public class SuperBoard {
	private static int[][] InitialBoard ;

	public SuperBoard() {

	}
	public void DrawBoard() {
	}

	public void InitialPlace() {
	}
	public int getPieceCell(int row, int column) {

		return InitialBoard[row][column];
	}

	public void setPieceCell(int row, int column, int piece) {
		InitialBoard[row][column] = piece;
	}

	public String calculateWinner(String[] strPlayerName) {
		return null;
	}

}
