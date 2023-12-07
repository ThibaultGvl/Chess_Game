package controller;

import view.ChessBoard;

/**
 * La classe Game est la classe principale du jeu d'échecs.
 * Elle contient la méthode principale main() qui instancie un objet ChessBoard et lance le jeu.
*/
public class Game {

	/**
	 * La méthode principale main() instancie un objet ChessBoard et lance le jeu.
	 * @param args les arguments de la ligne de commande, non utilisés dans cette application
	*/
	public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        board.start();
    }

}
