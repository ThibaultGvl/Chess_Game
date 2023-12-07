package model;

import java.awt.Point;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * Cette classe repr�sente un fou dans un jeu d'�checs.
 */
public class Rook extends Piece{

	/**
     * Boolean pour savoir si la pi�ce a d�j� �t� d�plac� ou pas
     */
	private boolean firstMove = true;
	
	/**
     * Constructeur de la classe Rook.
     * @param j1 true si la tour appartient au joueur 1, false sinon.
     * @param p position initiale de la tour.
     * @param i image repr�sentant la tour.
     */
	public Rook(boolean j1, Point p, ImageIcon i) {
		super(j1, p, i);
	}

	/**
     * D�place la pi�ce sur la case sp�cifi�e.
     * @param x coordonn�e x de la case o� la pi�ce doit �tre d�plac�e.
     * @param y coordonn�e y de la case o� la pi�ce doit �tre d�plac�e.
     * @param p l'�chiquier actuel.
     */
	@Override
	public void deplacement(int x, int y, Board p) {
		// TODO Auto-generated method stub
		this.setPosition(new Point(x, y));
		if (firstMove) firstMove = false;
	}
	
	/**
     * Calcule les d�placements possibles de la tour.
     * @param p l'�chiquier actuel.
     * @return une liste de points repr�sentant les cases o� la tour peut aller.
     */
	@Override
	public List<Point> deplacementPossible(Board p) {
		// TODO Auto-generated method stub
		return this.linearMooves(p);
		
	}

	/**
	 * Cr�e une copie de cette tour.
	 * @return une nouvelle tour identique � celle-ci.
	 */
	@Override
	public Piece copy() {
		// TODO Auto-generated method stub
		return new Rook(this.j1, this.position, this.image);
	}
	
	/**
     * Renvoie si la tour a d�j� �t� d�plac�.
     * @return renvoie true si la tour a d�j� �t� d�plac� sinon false.
     */
	public boolean getFirstMove() {
		return firstMove;
	}

}
