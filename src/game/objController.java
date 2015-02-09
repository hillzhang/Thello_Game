

package game;

import java.awt.Robot;
import java.awt.event.KeyEvent;



public class objController {
	public static Board board = new Board();
	public String name1, name2;
	private String[] strPlayerName=new String[2];
	private int Row, Column;
	private String strStatusMsg;
	private MoveCheck moveCheck = new MoveCheck();
	private static int currentPlayer = 1; // The current coin placement
	private boolean firstTime = true; // The first play the game
	private boolean hasWon = false; // If the board is full
	private boolean coinPlaced = false;
	private GameTimer gametimer;
	private InterruptInput InputCheck;
  
	
	public void message() {
		if (hasWon) // If a game has been own
		{
			strStatusMsg = moveCheck.calculateWinner(strPlayerName);
		} else if (firstTime) // Displays which player is which color
		{
			strStatusMsg = "" + strPlayerName[0] + " you are Black(x), "
					+ strPlayerName[1] + " you are White(o)\nInput format:\n" +
							"coord format:x,y     x and y must be integer\n"+"" +
						    "undo format:undo,moves    moves mean how many moves you want to undo!(eg. undo,1)\n"+"" +
						    "redo format:redo,moves    moves mean how many moves you want to redo!(eg. redo,1)";
			
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
		board.InitialPlace();
		board.DrawBoard();
		board.setundoredo(currentPlayer);
		inputController();

		
	}

	public void inputController() {
		gametimer = GameTimer.getInstance();
		 InputCheck=new InterruptInput();
		boolean playerOneCanMove = moveCheck.playerOneCanMove();
		boolean playerTwoCanMove = moveCheck.playerTwoCanMove();

		if (!playerOneCanMove && !playerTwoCanMove) // If neither players can
													// move
		{

			hasWon = true;
			message();

		}

		else {
            String tmpString;
			String disc = null;
			boolean inputFlag = true;
			// get a valid position
			while (inputFlag) {
				
				//System.out.print("current"+currentPlayer);
			    
				try {
					if (currentPlayer == 1) {
						disc = "x";
					}
					if (currentPlayer == 2) {
						disc = "o";
					}
					System.out.println("It is "
							+ strPlayerName[currentPlayer - 1] + "(" + disc
							+ ")"
							+ "'s turn, please enter a valid input (if there is no response, plese try again):");
					
				    tmpString=gametimer.GetTime(60*1000);
				
					
					if(tmpString==null || tmpString.equalsIgnoreCase("")) {
						
						tmpString="timeout";
					
						
					} else {
						//System.out.println(tmpString);
					
					}
					
					if(gametimer.checkTime(tmpString))
					{
						
						System.out.println("Move Time Out,Change to another player.....");
						if(currentPlayer == 1){currentPlayer=2;}
						else if(currentPlayer==2)currentPlayer=1;
						
					}
					
					else if(InputCheck.isundoredo(tmpString)){
						

//						System.out.println("undo/redo successfull");
						
					}
					
					
					else{
					
				String[] s = tmpString.split(",");
						
				 	
					Row = Integer.parseInt(s[0]);
					 Column= Integer.parseInt(s[1]);
					if (moveCheck.getPieceCell(Row, Column) != 0) {
						System.out.println("This place already have a disk");
						inputFlag = true;

					} 
					
					else {
						inputFlag = false;
					}

				} 
				}catch (Exception e) {
					// TODO: handle exception
					System.out
							.println("Input format wrong, input integer (x,y) or (undo,int) or (redo,int)!  "
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
				
				board.DrawBoard();
				board.setundoredo(currentPlayer);
				inputController();
			}
			else // move not legal
			{
				System.out
						.println("There are no possible over takes from that square, please choose another");
				inputController();
			}

		}

	}


	@SuppressWarnings("static-access")
	public void setplayer(int player){
		this.currentPlayer=player;
		
	}
	
	

}
