package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * Cette classe représente un lion dans la variante du Grant Acedrex.
 */
public class Lion extends Piece {

	/**
     * Constructeur de la classe Lion.
     * @param j1 true si la giraffe appartient au joueur 1, false sinon.
     * @param p position initiale du lion.
     * @param i image représentant le lion.
     */
	public Lion(boolean j1, Point p, ImageIcon i) {
		super(j1, p, i);
	}

	/**
     * Calcule les déplacements possibles de la pièce.
     * @param p l'échiquier actuel.
     * @return une liste de points représentant les cases où la pièce peut aller.
     */
	@Override
	public List<Point> deplacementPossible(Board p) {
		List<Point> usable = new ArrayList<>();
		Point[] depPotentiel = {new Point(position.x-3, position.y-1), new Point(position.x-3, position.y), new Point(position.x-3, position.y+1), new Point(position.x-1, position.y+3), new Point(position.x, position.y+3), new Point(position.x+1, position.y+3), new Point(position.x+3, position.y-1), new Point(position.x+3, position.y), new Point(position.x+3, position.y+1), new Point(position.x-1, position.y-3), new Point(position.x, position.y-3), new Point(position.x+1, position.y-3)};
		for (Point move : depPotentiel) {
			Case cur = (move.x < p.getNumberCases() && move.x >= 0 && move.y < p.getNumberCases() && move.y >= 0)?p.getCases()[move.x][move.y]:null;
			if (!((cur == null) || (cur.isOccuped() && ((cur.getCurrentPiece().j1 && this.j1) || (!cur.getCurrentPiece().j1 && !this.j1))))) {
				usable.add(move);
			}
		}
		return usable;
	}
	
	/**
	 * Crée une copie de cette pièce.
	 * @return une nouvelle pièce identique à celle-ci.
	 */
	@Override
	public Piece copy() {
		// TODO Auto-generated method stub
		return new Lion(j1, position, image);
	}

}
