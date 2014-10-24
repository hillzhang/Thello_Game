package game;

import java.util.Scanner;

public class Othello {

	public static Controller Controller = new Controller();

	public static void main(String[] args) {

		System.out.print("enter player1's name with black disc(x):");
		Scanner black = new Scanner(System.in);
		String Black = black.nextLine();
		System.out.print("enter player2's name with white disc(o):");
		Scanner white = new Scanner(System.in);
		String White = white.nextLine();
		Controller.setname(Black, White);

	}

}
