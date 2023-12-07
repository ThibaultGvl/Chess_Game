package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * Cette classe repr�sente un pion dans un jeu d'�checs.
 */
public class Pawn extends Piece{

	/**
     * Boolean pour savoir si la pi�ce a d�j� �t� d�plac� ou pas
     */
	private boolean firstMoove = true; 
	
	/**
     * Constructeur de la classe Pawn.
     * @param j1 true si le pion appartient au joueur 1, false sinon.
     * @param p position initiale du pion.
     * @param i image repr�sentant le pion.
     */
	public Pawn(boolean j1, Point p, ImageIcon i) {
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
		this.setPosition(new Point(x, y));
		this.firstMoove = false;
	}
	
	/**
     * Calcule les d�placements possibles en diagonale de la pi�ce.
     * @param p l'�chiquier actuel.
     * @return une liste de points repr�sentant les cases o� la pi�ce peut aller en diagonale.
     */
	@Override
	public List<Point> deplacementPossible(Board p) {
		List<Point> usable = new ArrayList<>();
		Case[][] cases = p.getCases();
		if (j1) {
			if (firstMoove && !cases[this.getPosition().x-2][this.getPosition().y].isOccuped() && !cases[this.getX()-1][this.getY()].isOccuped()) {
				usable.add(new Point(this.getPosition().x-2, this.getPosition().y));
			}
			if (this.getX() > 0 && !cases[this.getX()-1][this.getY()].isOccuped()) {
				usable.add(new Point(this.getX()-1, this.getY()));
			}
			if (this.getY()>0 && this.getX() > 0 && cases[this.getX()-1][this.getY()-1].isOccuped() && !cases[this.getX()-1][this.getY()-1].getCurrentPiece().isJ1()) {
				usable.add(new Point(this.getX()-1, this.getY()-1));
			}
			if (this.getY()<p.getNumberCases()-1 && this.getX() > 0 && cases[this.getX()-1][this.getY()+1].isOccuped() && !cases[this.getX()-1][this.getY()+1].getCurrentPiece().isJ1()) {
				usable.add(new Point(this.getX()-1, this.getY()+1));
			}
		}
		else {
			if (firstMoove && !cases[this.getPosition().x+2][this.getPosition().y].isOccuped() && !cases[this.getX()+1][this.getY()].isOccuped()) {
				usable.add(new Point(this.getPosition().x+2, this.getPosition().y));
			}
			if (this.getX() < p.getNumberCases()-1 && !cases[this.getX()+1][this.getY()].isOccuped()) {
				usable.add(new Point(this.getX()+1, this.getY()));
			}
			if (this.getY()>0 && this.getX() < p.getNumberCases()-1 && cases[this.getX()+1][this.getY()-1].isOccuped() && cases[this.getX()+1][this.getY()-1].getCurrentPiece().isJ1()) {
				usable.add(new Point(this.getX()+1, this.getY()-1));
			}
			if (this.getY()<p.getNumberCases()-1 && this.getX() < p.getNumberCases()-1 && cases[this.getX()+1][this.getY()+1].isOccuped() && cases[this.getX()+1][this.getY()+1].getCurrentPiece().isJ1()) {
				usable.add(new Point(this.getX()+1, this.getY()+1));
			}
		}
		return usable;
	}

	/**
	 * Cr�e une copie de ce pion.
	 * @return un nouveau pion identique � celui-ci.
	 */
	@Override
	public Piece copy() {
		Pawn cp = new Pawn(this.j1, this.position, this.image);
		cp.firstMoove = firstMoove;
		return cp;
	}
	
	/**
     * D�finit si le pion a d�j� �t� d�plac�.
     * @param b mise � jour de firstMove.
     */
	public void setFirstMove(boolean b) {
		this.firstMoove = b;
	}

}
