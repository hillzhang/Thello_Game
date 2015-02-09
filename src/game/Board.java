package game;

public class Board {

	private static int[][] InitialBoard = new int[8][8];
	private InterruptInput SaveUndo= new InterruptInput();

	public Board() {

	}

	public void DrawBoard() {

		System.out.println("new board");
		System.out.println("  0 1 2 3 4 5 6 7");
		for (int i = 0; i < 8; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < 8; j++) {

				try {
					if (InitialBoard[i][j] == 2)
						System.out.print("o ");
					else if (InitialBoard[i][j] == 0)
						System.out.print("- ");
					else if (InitialBoard[i][j] == 1)
						System.out.print("x ");
					else {
						Exception e = new Exception("disk error");
						throw e;
					}
				} catch (Exception e) {
					System.out.println("disk error");
				}

			}
			System.out.println();
		}
	}

	public void InitialPlace() {

		for (int row = 0; row < 8; row++) {

			for (int column = 0; column < 8; column++) {

				if (row <= 2 || row >= 5) // row 0,1,2 or 5,6,7
				{
					InitialBoard[row][column] = 0;
				} else {

					if (column <= 2 || column >= 5) // column 0,1,2 or 5,6,7
					{
						InitialBoard[row][column] = 0;
					} else {

						if ((row == 3 && column == 3)
								|| (row == 4 && column == 4)) // NW and SE of
																// middle
						{
							InitialBoard[row][column] = 2;
						} else {
							InitialBoard[row][column] = 1;
						}

					}

				}

			}

		}

	}

	public int getPieceCell(int row, int column) {

		return InitialBoard[row][column];
	}
	
	
	public void setundoredo(int current){
		
		
		String undoredo ="";
		
		for(int row=0;row<8 ;row++){
			for(int column=0; column<8; column++){
				
				
				undoredo+= row+","+column+","+getPieceCell(row,column)+",";
				
			}
		}
	
		undoredo=undoredo+current;
//	System.out.println(undoredo);
		SaveUndo.savetoUndolist(undoredo);	
		
	}

	public void setPieceCell(int row, int column, int piece) {
		InitialBoard[row][column] = piece;
	}

	public String calculateWinner(String[] strPlayerName) {
		int Black = 0;
		int White = 0;
		int currentCell = 0;

		for (int row = 0; row < 8; row++) {

			for (int column = 0; column < 8; column++) {

				currentCell = InitialBoard[row][column];

				if (currentCell == 0) {
					continue;
				} else if (currentCell == 1) {
					Black++;
				} else {
					White++;
				}

			}

		}

		if (Black > White) {
			return strPlayerName[0] + " has won, with a score of " + Black
					+ ". " + strPlayerName[1] + " got " + White;
		} else if (White > Black) {
			return strPlayerName[1] + " has won, with a score of " + White
					+ ". " + strPlayerName[0] + " got " + Black;
		} else {
			return "this game was a draw with both players getting a score of "
					+ Black;
		}

	}
	
	
	
	
	
	

}
