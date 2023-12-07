package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * Cette classe représente une reine dans un jeu d'échecs.
 */
public class Queen extends Piece {

	/**
     * Constructeur de la classe Queen.
     * @param j1 true si la reine appartient au joueur 1, false sinon.
     * @param p position initiale de la reine.
     * @param i image représentant la reine.
     */
	public Queen(boolean j1, Point p, ImageIcon i) {
		super(j1, p, i);
	}

	/**
     * Calcule les déplacements possibles de la reine.
     * @param p l'échiquier actuel.
     * @return une liste de points représentant les cases où la reine peut aller.
     */
	@Override
	public List<Point> deplacementPossible(Board p) {
		ArrayList<Point> moves = (ArrayList<Point>) this.diagonalMooves(p);
		moves.addAll(this.linearMooves(p));
		return moves;
	}

	/**
	 * Crée une copie de cette reine.
	 * @return une nouvelle reine identique à celle-ci.
	 */
	@Override
	public Piece copy() {
		return new Queen(this.j1, this.position, this.image);
	}

}
