package model;

import java.awt.Point;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * Cette classe représente un fou dans un jeu d'échecs.
 */
public class Rook extends Piece{

	/**
     * Boolean pour savoir si la pièce a déjà été déplacé ou pas
     */
	private boolean firstMove = true;
	
	/**
     * Constructeur de la classe Rook.
     * @param j1 true si la tour appartient au joueur 1, false sinon.
     * @param p position initiale de la tour.
     * @param i image représentant la tour.
     */
	public Rook(boolean j1, Point p, ImageIcon i) {
		super(j1, p, i);
	}

	/**
     * Déplace la pièce sur la case spécifiée.
     * @param x coordonnée x de la case où la pièce doit être déplacée.
     * @param y coordonnée y de la case où la pièce doit être déplacée.
     * @param p l'échiquier actuel.
     */
	@Override
	public void deplacement(int x, int y, Board p) {
		// TODO Auto-generated method stub
		this.setPosition(new Point(x, y));
		if (firstMove) firstMove = false;
	}
	
	/**
     * Calcule les déplacements possibles de la tour.
     * @param p l'échiquier actuel.
     * @return une liste de points représentant les cases où la tour peut aller.
     */
	@Override
	public List<Point> deplacementPossible(Board p) {
		// TODO Auto-generated method stub
		return this.linearMooves(p);
		
	}

	/**
	 * Crée une copie de cette tour.
	 * @return une nouvelle tour identique à celle-ci.
	 */
	@Override
	public Piece copy() {
		// TODO Auto-generated method stub
		return new Rook(this.j1, this.position, this.image);
	}
	
	/**
     * Renvoie si la tour a déjà été déplacé.
     * @return renvoie true si la tour a déjà été déplacé sinon false.
     */
	public boolean getFirstMove() {
		return firstMove;
	}

}
