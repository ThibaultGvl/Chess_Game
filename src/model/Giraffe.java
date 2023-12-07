package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * Cette classe représente une giraffe dans la variante du Grant Acedrex.
 */
public class Giraffe extends Piece {

	/**
     * Constructeur de la classe Giraffe.
     * @param j1 true si la giraffe appartient au joueur 1, false sinon.
     * @param p position initiale de la giraffe.
     * @param i image représentant la giraffe.
     */
	public Giraffe(boolean j1, Point p, ImageIcon i) {
		super(j1, p, i);
	}

	/**
     * Calcule les déplacements possibles de la giraffe.
     * @param p l'échiquier actuel.
     * @return une liste de points représentant les cases où la giraffe peut aller.
     */
	@Override
	public List<Point> deplacementPossible(Board p) {
		List<Point> usable = new ArrayList<>();
		Point[] depPotentiel = {new Point(position.x-2, position.y-3), new Point(position.x-3, position.y-2), new Point(position.x-2, position.y+3), new Point(position.x-3, position.y+2), new Point(position.x+2, position.y-3), new Point(position.x+3, position.y-2), new Point(position.x+2, position.y+3), new Point(position.x+3, position.y+2)};
		for (Point move : depPotentiel) {
			Case cur = (move.x < p.getNumberCases() && move.x >= 0 && move.y < p.getNumberCases() && move.y >= 0)?p.getCases()[move.x][move.y]:null;
			if (!((cur == null) || (cur.isOccuped() && ((cur.getCurrentPiece().j1 && this.j1) || (!cur.getCurrentPiece().j1 && !this.j1))))) {
				usable.add(move);
			}
		}
		return usable;
	}

	/**
	 * Crée une copie de cette giraffe.
	 * @return un nouvelle piece identique à celle-ci.
	 */
	@Override
	public Piece copy() {
		return new Giraffe(j1, position, image);
	}

}
