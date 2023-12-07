package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * Cette classe repr�sente un lion dans la variante du Grant Acedrex.
 */
public class Lion extends Piece {

	/**
     * Constructeur de la classe Lion.
     * @param j1 true si la giraffe appartient au joueur 1, false sinon.
     * @param p position initiale du lion.
     * @param i image repr�sentant le lion.
     */
	public Lion(boolean j1, Point p, ImageIcon i) {
		super(j1, p, i);
	}

	/**
     * Calcule les d�placements possibles de la pi�ce.
     * @param p l'�chiquier actuel.
     * @return une liste de points repr�sentant les cases o� la pi�ce peut aller.
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
	 * Cr�e une copie de cette pi�ce.
	 * @return une nouvelle pi�ce identique � celle-ci.
	 */
	@Override
	public Piece copy() {
		// TODO Auto-generated method stub
		return new Lion(j1, position, image);
	}

}
