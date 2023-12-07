package model;

import java.awt.Point;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * Cette classe représente un fou dans un jeu d'échecs.
 */
public class Bishop extends Piece{

	/**
     * Constructeur de la classe Bishop.
     * @param j1 true si le fou appartient au joueur 1, false sinon.
     * @param p position initiale du fou.
     * @param i image représentant le fou.
     */
	public Bishop(boolean j1, Point p, ImageIcon i) {
		super(j1, p, i);
	}


	/**
     * Calcule les déplacements possibles du fou.
     * @param p l'échiquier actuel.
     * @return une liste de points représentant les cases où le fou peut aller.
     */
	@Override
	public List<Point> deplacementPossible(Board p) {
		return this.diagonalMooves(p);
	}

	/**
	 * Crée une copie de ce fou.
	 * @return un nouveau fou identique à celui-ci.
	 */
	@Override
	public Piece copy() {
		return new Bishop(this.j1, this.position, this.image);
	}

}
