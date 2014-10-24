package game;

import java.util.Scanner;

public class Controller {
	public static Board bd = new Board();
	public String name1, name2;
	private String[] strPlayerName=new String[2];
	private int Row, Column;
	private String strStatusMsg;
	private MoveCheck moveCheck = new MoveCheck();
	private int currentPlayer = 1; // The current coin placement
	private boolean firstTime = true; // The first play the game
	private boolean hasWon = false; // If the board is full
	private boolean coinPlaced = false;

	public void message() {
		if (hasWon) // If a game has been own
		{
			strStatusMsg = moveCheck.calculateWinner(strPlayerName);
		} else if (firstTime) // Displays which player is which color
		{
			strStatusMsg = "" + strPlayerName[0] + " you are Black(x), "
					+ strPlayerName[1] + " you are White(o)";
		} else {
			strStatusMsg = "";
		}
		System.out.println(strStatusMsg);

	}

	public void setname(String player1, String player2) {

		strPlayerName[0] = player1;
		strPlayerName[1] = player2;

		message();

		firstTime = false;
		bd.InitialPlace();
		bd.DrawBoard();
		inputController();

	}

	public void inputController() {
		boolean playerOneCanMove = moveCheck.playerOneCanMove();
		boolean playerTwoCanMove = moveCheck.playerTwoCanMove();

		if (!playerOneCanMove && !playerTwoCanMove) // If neither players can
													// move
		{

			hasWon = true;
			message();

		}

		else {

			String disc = null;
			boolean inputFlag = true;
			// get a valid position
			while (inputFlag) {
				try {
					if (currentPlayer == 1) {
						disc = "x";
					}
					if (currentPlayer == 2) {
						disc = "o";
					}
					System.out.print("It is "
							+ strPlayerName[currentPlayer - 1] + "(" + disc
							+ ")"
							+ "'s turn, please enter a valid location x,y:");
					Scanner sc = new Scanner(System.in);
					String st = sc.nextLine();
					String[] s = st.split(",");
					Row = Integer.parseInt(s[0]);
					 Column= Integer.parseInt(s[1]);
					if (moveCheck.getPieceCell(Row, Column) != 0) {
						System.out.println("This place already have a disk");
						inputFlag = true;

					} else {
						inputFlag = false;
					}

				} catch (Exception e) {
					// TODO: handle exception
					System.out
							.println("Input format wrong, input integer x,y!  "
									+ strPlayerName[currentPlayer - 1]
									+ " please  try again. ");

				}

			}
			coinPlaced = true;

			checkmove();
		}
	}

	public void checkmove() {

		if (coinPlaced) // If a coin has been placed
		{

			if (moveCheck.legalMove(Row, Column, currentPlayer, true)) // If the move
																// is legal (the
																// true says
																// this is an
																// actual move)
			{

				moveCheck.checkPossibleMoves();

				boolean playerOneCanMove = moveCheck.playerOneCanMove();
				boolean playerTwoCanMove = moveCheck.playerTwoCanMove();

				if (!playerOneCanMove && !playerTwoCanMove) // If neither
															// players can move
				{

					hasWon = true;
					message();

				} else // If at least one player can move
				{

					if (currentPlayer == 1 && playerTwoCanMove) // change to the
																// oposite
																// player,
																// providing
																// they can move
					{

						currentPlayer = 2;
						message();

					} else if (currentPlayer == 2 && playerOneCanMove) {

						currentPlayer = 1;
						message();

					} else {

						if (currentPlayer == 1) {
							System.out.println("" + strPlayerName[1]
									+ " can not move, " + strPlayerName[0]
									+ " move again");
						} else {
							System.out.println("" + strPlayerName[0]
									+ " can not move, " + strPlayerName[1]
									+ " move again");
						}

					}

				}
				coinPlaced = false;
				bd.DrawBoard();

				inputController();
			} else // move not legal
			{
				System.out
						.println("There are no possible over takes from that square, please choose another");
				inputController();
			}

		}

	}

}
