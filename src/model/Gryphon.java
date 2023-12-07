package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * Cette classe représente un gryphon dans la variante du Grant Acedrex.
 */
public class Gryphon extends Piece {

	/**
     * Constructeur de la classe Gryphon.
     * @param j1 true si le gryphon appartient au joueur 1, false sinon.
     * @param p position initiale du gryphon.
     * @param i image représentant le gryphon.
     */
	public Gryphon(boolean j1, Point p, ImageIcon i) {
		super(j1, p, i);
	}


	/**
     * Calcule les déplacements possibles de la pièce.
     * @param p l'échiquier actuel.
     * @return une liste de points représentant les cases où la pièce peut aller.
     */
	@Override
	public List<Point> deplacementPossible(Board p) {
		List<Point> moves = new ArrayList<>();
		Case top_left =  position.x-1<p.getNumberCases() && position.x-1>=0 && position.y-1 >= 0 && position.y-1<p.getNumberCases()?p.getCases()[position.x-1][position.y-1]:null;
		Case top_right = position.x-1<p.getNumberCases() && position.x-1>=0 && position.y+1 >= 0 && position.y+1<p.getNumberCases()?p.getCases()[position.x-1][position.y+1]:null;
		Case bottom_right = position.x+1<p.getNumberCases() && position.x+1>=0 && position.y+1 >= 0 && position.y+1<p.getNumberCases()?p.getCases()[position.x+1][position.y+1]:null;
		Case bottom_left =  position.x+1<p.getNumberCases() && position.x+1>=0 && position.y-1 >= 0 && position.y-1<p.getNumberCases()?p.getCases()[position.x+1][position.y-1]:null;
		int tmp = 2;
		boolean accessible = true;
		if (top_left != null && (!top_left.isOccuped() || (top_left.isOccuped() && top_left.getCurrentPiece().j1 && !this.isJ1()) || (top_left.isOccuped() && !top_left.getCurrentPiece().j1 && this.isJ1()))) {
			if (top_left.isOccuped()) {
				accessible = false;
			}
			moves.add(new Point(position.x-1, position.y-1));
			top_left =  position.x-tmp<p.getNumberCases() && position.x-tmp>=0 && position.y-1 >= 0 && position.y-1<p.getNumberCases()?p.getCases()[position.x-tmp][position.y-1]:null;
			while (accessible && !moves.contains(new Point(position.x-tmp, position.y-1)) && top_left != null && (!top_left.isOccuped() || (top_left.isOccuped() && top_left.getCurrentPiece().j1 && !this.isJ1()) || (top_left.isOccuped() && !top_left.getCurrentPiece().j1 && this.isJ1()))) {
				if (top_left.isOccuped()) {
					accessible = false;
				}
				moves.add(new Point(position.x-tmp, position.y-1));
				tmp++;
				top_left =  position.x-tmp<p.getNumberCases() && position.x-tmp>=0 && position.y-1 >= 0 && position.y-1<p.getNumberCases()?p.getCases()[position.x-tmp][position.y-1]:null;
			}
			top_left =  position.x-1<p.getNumberCases() && position.x-1>=0 && position.y-1 >= 0 && position.y-1<p.getNumberCases()?p.getCases()[position.x-1][position.y-1]:null;
			tmp = 2;
			accessible = true;
			if (top_left.isOccuped()) {
				accessible = false;
			}
			top_left =  position.x-1<p.getNumberCases() && position.x-1>=0 && position.y-tmp >= 0 && position.y-tmp<p.getNumberCases()?p.getCases()[position.x-1][position.y-tmp]:null;
			while (accessible && !moves.contains(new Point(position.x-1, position.y-tmp)) && top_left != null && (!top_left.isOccuped() || (top_left.isOccuped() && top_left.getCurrentPiece().j1 && !this.isJ1()) || (top_left.isOccuped() && !top_left.getCurrentPiece().j1 && this.isJ1()))) {
				if (top_left.isOccuped()) {
					accessible = false;
				}
				moves.add(new Point(position.x-1, position.y-tmp));
				tmp++;
				top_left =  position.x-1<p.getNumberCases() && position.x-1>=0 && position.y-tmp >= 0 && position.y-tmp<p.getNumberCases()?p.getCases()[position.x-1][position.y-tmp]:null;
			}
		}
		tmp = 2;
		accessible = true;
		if (top_right != null && (!top_right.isOccuped() || (top_right.isOccuped() && top_right.getCurrentPiece().j1 && !this.isJ1()) || (top_right.isOccuped() && !top_right.getCurrentPiece().j1 && this.isJ1()))) {
			if (top_right.isOccuped()) {
				accessible = false;
			}
			moves.add(new Point(position.x-1, position.y+1));
			top_right =  position.x-tmp<p.getNumberCases() && position.x-tmp>=0 && position.y+1 >= 0 && position.y+1<p.getNumberCases()?p.getCases()[position.x-tmp][position.y+1]:null;
			while (accessible && !moves.contains(new Point(position.x-tmp, position.y+1)) && top_right != null && (!top_right.isOccuped() || (top_right.isOccuped() && top_right.getCurrentPiece().j1 && !this.isJ1()) || (top_right.isOccuped() && !top_right.getCurrentPiece().j1 && this.isJ1()))) {
				if (top_right.isOccuped()) {
					accessible = false;
				}
				moves.add(new Point(position.x-tmp, position.y+1));
				tmp++;
				top_right =  position.x-tmp<p.getNumberCases() && position.x-tmp>=0 && position.y+1 >= 0 && position.y+1<p.getNumberCases()?p.getCases()[position.x-tmp][position.y+1]:null;
			}
			top_right =  position.x-1<p.getNumberCases() && position.x-1>=0 && position.y+1 >= 0 && position.y+1<p.getNumberCases()?p.getCases()[position.x-1][position.y+1]:null;
			tmp = 2;
			accessible = true;
			if (top_right.isOccuped()) {
				accessible = false;
			}
			top_right =  position.x-1<p.getNumberCases() && position.x-1>=0 && position.y+tmp >= 0 && position.y+tmp<p.getNumberCases()?p.getCases()[position.x-1][position.y+tmp]:null;
			while (accessible && !moves.contains(new Point(position.x-1, position.y+tmp)) && top_right != null && (!top_right.isOccuped() || (top_right.isOccuped() && top_right.getCurrentPiece().j1 && !this.isJ1()) || (top_right.isOccuped() && !top_right.getCurrentPiece().j1 && this.isJ1()))) {
				if (top_right.isOccuped()) {
					accessible = false;
				}
				moves.add(new Point(position.x-1, position.y+tmp));
				tmp++;
				top_right =  position.x-1<p.getNumberCases() && position.x-1>=0 && position.y+tmp >= 0 && position.y+tmp<p.getNumberCases()?p.getCases()[position.x-1][position.y+tmp]:null;
			}
		}
		tmp = 2;
		accessible = true;
		if (bottom_right != null && (!bottom_right.isOccuped() || (bottom_right.isOccuped() && bottom_right.getCurrentPiece().j1 && !this.isJ1()) || (bottom_right.isOccuped() && !bottom_right.getCurrentPiece().j1 && this.isJ1()))) {
			if (bottom_right.isOccuped()) {
				accessible = false;
			}
			moves.add(new Point(position.x+1, position.y+1));
			bottom_right =  position.x+tmp<p.getNumberCases() && position.x+tmp>=0 && position.y+1 >= 0 && position.y+1<p.getNumberCases()?p.getCases()[position.x+tmp][position.y+1]:null;
			while (accessible && !moves.contains(new Point(position.x+tmp, position.y+1)) && bottom_right != null && (!bottom_right.isOccuped() || (bottom_right.isOccuped() && bottom_right.getCurrentPiece().j1 && !this.isJ1()) || (bottom_right.isOccuped() && !bottom_right.getCurrentPiece().j1 && this.isJ1()))) {
				if (bottom_right.isOccuped()) {
					accessible = false;
				}
				moves.add(new Point(position.x+tmp, position.y+1));
				tmp++;
				bottom_right =  position.x+tmp<p.getNumberCases() && position.x+tmp>=0 && position.y+1 >= 0 && position.y+1<p.getNumberCases()?p.getCases()[position.x+tmp][position.y+1]:null;
			}
			bottom_right =  position.x+1<p.getNumberCases() && position.x+1>=0 && position.y+1 >= 0 && position.y+1<p.getNumberCases()?p.getCases()[position.x+1][position.y+1]:null;
			tmp = 2;
			accessible = true;
			if (bottom_right.isOccuped()) {
				accessible = false;
			}
			bottom_right =  position.x+1<p.getNumberCases() && position.x+1>=0 && position.y+tmp >= 0 && position.y+tmp<p.getNumberCases()?p.getCases()[position.x+1][position.y+tmp]:null;
			while (accessible && !moves.contains(new Point(position.x+1, position.y+tmp)) && bottom_right != null && (!bottom_right.isOccuped() || (bottom_right.isOccuped() && bottom_right.getCurrentPiece().j1 && !this.isJ1()) || (bottom_right.isOccuped() && !bottom_right.getCurrentPiece().j1 && this.isJ1()))) {
				if (bottom_right.isOccuped()) {
					accessible = false;
				}
				moves.add(new Point(position.x+1, position.y+tmp));
				tmp++;
				bottom_right =  position.x+1<p.getNumberCases() && position.x+1>=0 && position.y+tmp >= 0 && position.y+tmp<p.getNumberCases()?p.getCases()[position.x+1][position.y+tmp]:null;
			}
		}
		tmp = 2;
		accessible = true;
		if (bottom_left != null && (!bottom_left.isOccuped() || (bottom_left.isOccuped() && bottom_left.getCurrentPiece().j1 && !this.isJ1()) || (bottom_left.isOccuped() && !bottom_left.getCurrentPiece().j1 && this.isJ1()))) {
			if (bottom_left.isOccuped()) {
				accessible = false;
			}
			moves.add(new Point(position.x+1, position.y-1));
			bottom_left =  position.x+tmp<p.getNumberCases() && position.x+tmp>=0 && position.y-1 >= 0 && position.y-1<p.getNumberCases()?p.getCases()[position.x+tmp][position.y-1]:null;
			while (accessible && !moves.contains(new Point(position.x+tmp, position.y-1)) && bottom_left != null && (!bottom_left.isOccuped() || (bottom_left.isOccuped() && bottom_left.getCurrentPiece().j1 && !this.isJ1()) || (bottom_left.isOccuped() && !bottom_left.getCurrentPiece().j1 && this.isJ1()))) {
				if (bottom_left.isOccuped()) {
					accessible = false;
				}
				moves.add(new Point(position.x+tmp, position.y-1));
				tmp++;
				bottom_left =  position.x+tmp<p.getNumberCases() && position.x+tmp>=0 && position.y-1 >= 0 && position.y-1<p.getNumberCases()?p.getCases()[position.x+tmp][position.y-1]:null;
			}
			bottom_left =  position.x+1<p.getNumberCases() && position.x+1>=0 && position.y-1 >= 0 && position.y-1<p.getNumberCases()?p.getCases()[position.x+1][position.y-1]:null;
			tmp = 2;
			accessible = true;
			if (bottom_left.isOccuped()) {
				accessible = false;
			}
			bottom_left =  position.x+1<p.getNumberCases() && position.x+1>=0 && position.y-tmp >= 0 && position.y-tmp<p.getNumberCases()?p.getCases()[position.x+1][position.y-tmp]:null;
			while (accessible && !moves.contains(new Point(position.x+1, position.y-tmp)) && bottom_left != null && (!bottom_left.isOccuped() || (bottom_left.isOccuped() && bottom_left.getCurrentPiece().j1 && !this.isJ1()) || (bottom_left.isOccuped() && !bottom_left.getCurrentPiece().j1 && this.isJ1()))) {
				if (bottom_left.isOccuped()) {
					accessible = false;
				}
				moves.add(new Point(position.x+1, position.y-tmp));
				tmp++;
				bottom_left =  position.x+1<p.getNumberCases() && position.x+1>=0 && position.y-tmp >= 0 && position.y-tmp<p.getNumberCases()?p.getCases()[position.x+1][position.y-tmp]:null;
			}
		}
		return moves;
	}

	/**
	 * Crée une copie de cette pièce.
	 * @return une nouvelle pièce identique à celle-ci.
	 */
	@Override
	public Piece copy() {
		return new Gryphon(j1, position, image);
	}

}
