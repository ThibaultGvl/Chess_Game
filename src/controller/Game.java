package controller;

import view.ChessBoard;

/**
 * La classe Game est la classe principale du jeu d'�checs.
 * Elle contient la m�thode principale main() qui instancie un objet ChessBoard et lance le jeu.
*/
public class Game {

	/**
	 * La m�thode principale main() instancie un objet ChessBoard et lance le jeu.
	 * @param args les arguments de la ligne de commande, non utilis�s dans cette application
	*/
	public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        board.start();
    }

}
