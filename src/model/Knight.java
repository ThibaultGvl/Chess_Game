package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * Cette classe repr�sente un fou dans un jeu d'�checs.
 */
public class Knight extends Piece {

	/**
     * Constructeur de la classe Knight.
     * @param j1 true si le cavalier appartient au joueur 1, false sinon.
     * @param p position initiale du cavalier.
     * @param i image repr�sentant le cavalier.
     */
	public Knight(boolean j1, Point p, ImageIcon i) {
		super(j1, p, i);
	}

	/**
     * Calcule les d�placements possibles du cavalier.
     * @param p l'�chiquier actuel.
     * @return une liste de points repr�sentant les cases o� le cavalier peut aller.
     */
	@Override
	public List<Point> deplacementPossible(Board p) {
		Point[] moves = {new Point(this.getX()-2, this.getY()-1), new Point(this.getX()-2, this.getY()+1), new Point(this.getX()-1, this.getY()-2), new Point(this.getX()-1, this.getY()+2), new Point(this.getX()+2, this.getY()-1), new Point(this.getX()+2, this.getY()+1), new Point(this.getX()+1, this.getY()-2), new Point(this.getX()+1, this.getY()+2) };
		List<Point> possibleMoves = new ArrayList<>();
		for (Point move : moves) {
			Case cur = (move.x < 8 && move.x >= 0 && move.y < 8 && move.y >= 0)?p.getCases()[move.x][move.y]:null;
			if (!((cur == null) || (cur.isOccuped() && ((cur.getCurrentPiece().j1 && this.j1) || (!cur.getCurrentPiece().j1 && !this.j1))))) {
				possibleMoves.add(move);
			}
		}
		return possibleMoves;
	}

	/**
	 * Cr�e une copie de ce cavalier.
	 * @return un nouveau cavalier identique � celui-ci.
	 */
	@Override
	public Piece copy() {
		return new Knight(this.j1, this.position, this.image);
	}

}
