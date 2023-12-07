package model;

import java.awt.Point;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * Cette classe repr�sente un fou dans un jeu d'�checs.
 */
public class Bishop extends Piece{

	/**
     * Constructeur de la classe Bishop.
     * @param j1 true si le fou appartient au joueur 1, false sinon.
     * @param p position initiale du fou.
     * @param i image repr�sentant le fou.
     */
	public Bishop(boolean j1, Point p, ImageIcon i) {
		super(j1, p, i);
	}


	/**
     * Calcule les d�placements possibles du fou.
     * @param p l'�chiquier actuel.
     * @return une liste de points repr�sentant les cases o� le fou peut aller.
     */
	@Override
	public List<Point> deplacementPossible(Board p) {
		return this.diagonalMooves(p);
	}

	/**
	 * Cr�e une copie de ce fou.
	 * @return un nouveau fou identique � celui-ci.
	 */
	@Override
	public Piece copy() {
		return new Bishop(this.j1, this.position, this.image);
	}

}
