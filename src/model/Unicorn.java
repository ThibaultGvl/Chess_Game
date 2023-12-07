package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * Cette classe représente une licorne dans la variante du Grant Acedrex.
 */
public class Unicorn extends Piece {

	/**
     * Constructeur de la classe Unicorn.
     * @param j1 true si la licorne appartient au joueur 1, false sinon.
     * @param p position initiale de la licorne.
     * @param i image représentant la licorne.
     */
	public Unicorn(boolean j1, Point p, ImageIcon i) {
		super(j1, p, i);
		// TODO Auto-generated constructor stub
	}

	/**
     * Calcule les déplacements possibles de la pièce.
     * @param p l'échiquier actuel.
     * @return une liste de points représentant les cases où la pièce peut aller.
     */
	@Override
	public List<Point> deplacementPossible(Board p) {
		// TODO Auto-generated method stub
		Point[] moves = {new Point(this.getX()-2, this.getY()-1), new Point(this.getX()-2, this.getY()+1), new Point(this.getX()-1, this.getY()-2), new Point(this.getX()-1, this.getY()+2), new Point(this.getX()+2, this.getY()-1), new Point(this.getX()+2, this.getY()+1), new Point(this.getX()+1, this.getY()-2), new Point(this.getX()+1, this.getY()+2) };
		List<Point> possibleMoves = new ArrayList<>();
		for (Point move : moves) {
			Case cur = (move.x < p.getNumberCases() && move.x >= 0 && move.y < p.getNumberCases() && move.y >= 0)?p.getCases()[move.x][move.y]:null;
			if (!((cur == null) || (cur.isOccuped() && ((cur.getCurrentPiece().j1 && this.j1) || (!cur.getCurrentPiece().j1 && !this.j1))))) {
				possibleMoves.add(move);
			}
		}
		int tmp = 1;
		Point tmpoint = moves[0];
		Case cur = (moves[0].x < p.getNumberCases() && moves[0].x >= 0 && moves[0].y < p.getNumberCases() && moves[0].y >= 0)?p.getCases()[moves[0].x][moves[0].y]:null;
		while ((cur != null && !cur.isOccuped()) && tmpoint.x-tmp >= 0 && tmpoint.y-tmp >= 0 && tmp >= 1) {
			cur = (tmpoint.x-tmp < p.getNumberCases() && tmpoint.x-tmp >= 0 && tmpoint.y-tmp < p.getNumberCases() && tmpoint.y-tmp >= 0)?p.getCases()[tmpoint.x-tmp][tmpoint.y-tmp]:null;
			if (cur != null && (!cur.isOccuped() || (!cur.getCurrentPiece().j1 && this.j1) || (cur.getCurrentPiece().j1 && !this.j1))) {
				possibleMoves.add(new Point(tmpoint.x-tmp, tmpoint.y-tmp));
				if (cur.isOccuped()) {
					tmp = -1;
				}
			}
			else {
				tmp = -1;
			}
			tmp++;
		}
		tmp = 1;
		tmpoint = moves[1];
		cur = (moves[1].x < p.getNumberCases() && moves[1].x >= 0 && moves[1].y < p.getNumberCases() && moves[1].y >= 0)?p.getCases()[moves[1].x][moves[1].y]:null;
		while ((cur != null && !cur.isOccuped()) && tmpoint.x-tmp >= 0 && tmpoint.y+tmp < p.getNumberCases() && tmp >= 1) {
			cur = (tmpoint.x-tmp < p.getNumberCases() && tmpoint.x-tmp >= 0 && tmpoint.y+tmp < p.getNumberCases() && tmpoint.y+tmp >= 0)?p.getCases()[tmpoint.x-tmp][tmpoint.y+tmp]:null;
			if (cur != null && (!cur.isOccuped() || (!cur.getCurrentPiece().j1 && this.j1) || (cur.getCurrentPiece().j1 && !this.j1))) {
				possibleMoves.add(new Point(tmpoint.x-tmp, tmpoint.y+tmp));
				if (cur.isOccuped()) {
					tmp = -1;
				}
			}
			else {
				tmp = -1;
			}
			tmp++;
		}
		tmp = 1;
		tmpoint = moves[2];
		cur = (moves[2].x < p.getNumberCases() && moves[2].x >= 0 && moves[2].y < p.getNumberCases() && moves[2].y >= 0)?p.getCases()[moves[2].x][moves[2].y]:null;
		while ((cur != null && !cur.isOccuped()) && tmpoint.x-tmp >= 0 && tmpoint.y-tmp >= 0 && tmp >= 1) {
			cur = (tmpoint.x-tmp < p.getNumberCases() && tmpoint.x-tmp >= 0 && tmpoint.y-tmp < p.getNumberCases() && tmpoint.y-tmp >= 0)?p.getCases()[tmpoint.x-tmp][tmpoint.y-tmp]:null;
			if (cur != null && (!cur.isOccuped() || (!cur.getCurrentPiece().j1 && this.j1) || (cur.getCurrentPiece().j1 && !this.j1))) {
				possibleMoves.add(new Point(tmpoint.x-tmp, tmpoint.y-tmp));
				if (cur.isOccuped()) {
					tmp = -1;
				}
			}
			else {
				tmp = -1;
			}
			tmp++;
		}
		tmp = 1;
		tmpoint = moves[3];
		cur = (moves[3].x < p.getNumberCases() && moves[3].x >= 0 && moves[3].y < p.getNumberCases() && moves[3].y >= 0)?p.getCases()[moves[3].x][moves[3].y]:null;
		while ((cur != null && !cur.isOccuped()) && tmpoint.x-tmp >= 0 && tmpoint.y+tmp < p.getNumberCases() && tmp >= 1) {
			cur = (tmpoint.x-tmp < p.getNumberCases() && tmpoint.x-tmp >= 0 && tmpoint.y+tmp < p.getNumberCases() && tmpoint.y+tmp >= 0)?p.getCases()[tmpoint.x-tmp][tmpoint.y+tmp]:null;
			if (cur != null && (!cur.isOccuped() || (!cur.getCurrentPiece().j1 && this.j1) || (cur.getCurrentPiece().j1 && !this.j1))) {
				possibleMoves.add(new Point(tmpoint.x-tmp, tmpoint.y+tmp));
				if (cur.isOccuped()) {
					tmp = -1;
				}
			}
			else {
				tmp = -1;
			}
			tmp++;
		}
		tmp = 1;
		tmpoint = moves[4];
		cur = (moves[4].x < p.getNumberCases() && moves[4].x >= 0 && moves[4].y < p.getNumberCases() && moves[4].y >= 0)?p.getCases()[moves[4].x][moves[4].y]:null;
		while ((cur != null && !cur.isOccuped()) && tmpoint.x+tmp >= 0 && tmpoint.y-tmp >= 0 && tmp >= 1) {
			cur = (tmpoint.x+tmp < p.getNumberCases() && tmpoint.x+tmp >= 0 && tmpoint.y-tmp < p.getNumberCases() && tmpoint.y-tmp >= 0)?p.getCases()[tmpoint.x+tmp][tmpoint.y-tmp]:null;
			if (cur != null && (!cur.isOccuped() || (!cur.getCurrentPiece().j1 && this.j1) || (cur.getCurrentPiece().j1 && !this.j1))) {
				possibleMoves.add(new Point(tmpoint.x+tmp, tmpoint.y-tmp));
				if (cur.isOccuped()) {
					tmp = -1;
				}
			}
			else {
				tmp = -1;
			}
			tmp++;
		}
		tmp = 1;
		tmpoint = moves[5];
		cur = (moves[5].x < p.getNumberCases() && moves[5].x >= 0 && moves[5].y < p.getNumberCases() && moves[5].y >= 0)?p.getCases()[moves[5].x][moves[5].y]:null;
		while ((cur != null && !cur.isOccuped()) && tmpoint.x+tmp >= 0 && tmpoint.y+tmp >= 0 && tmp >= 1) {
			cur = (tmpoint.x+tmp < p.getNumberCases() && tmpoint.x+tmp >= 0 && tmpoint.y+tmp < p.getNumberCases() && tmpoint.y+tmp >= 0)?p.getCases()[tmpoint.x+tmp][tmpoint.y+tmp]:null;
			if (cur != null && (!cur.isOccuped() || (!cur.getCurrentPiece().j1 && this.j1) || (cur.getCurrentPiece().j1 && !this.j1))) {
				possibleMoves.add(new Point(tmpoint.x+tmp, tmpoint.y+tmp));
				if (cur.isOccuped()) {
					tmp = -1;
				}
			}
			else {
				tmp = -1;
			}
			tmp++;
		}
		tmp = 1;
		tmpoint = moves[6];
		cur = (moves[6].x < p.getNumberCases() && moves[6].x >= 0 && moves[6].y < p.getNumberCases() && moves[6].y >= 0)?p.getCases()[moves[6].x][moves[6].y]:null;
		while ((cur != null && !cur.isOccuped()) && tmpoint.x+tmp >= 0 && tmpoint.y-tmp >= 0 && tmp >= 1) {
			cur = (tmpoint.x+tmp < p.getNumberCases() && tmpoint.x+tmp >= 0 && tmpoint.y-tmp < p.getNumberCases() && tmpoint.y-tmp >= 0)?p.getCases()[tmpoint.x+tmp][tmpoint.y-tmp]:null;
			if (cur != null && (!cur.isOccuped() || (!cur.getCurrentPiece().j1 && this.j1) || (cur.getCurrentPiece().j1 && !this.j1))) {
				possibleMoves.add(new Point(tmpoint.x+tmp, tmpoint.y-tmp));
				if (cur.isOccuped()) {
					tmp = -1;
				}
			}
			else {
				tmp = -1;
			}
			tmp++;
		}
		tmp = 1;
		tmpoint = moves[7];
		cur = (moves[7].x < p.getNumberCases() && moves[7].x >= 0 && moves[7].y < p.getNumberCases() && moves[7].y >= 0)?p.getCases()[moves[7].x][moves[7].y]:null;
		while ((cur != null && !cur.isOccuped()) && tmpoint.x+tmp < p.getNumberCases() && tmpoint.y+tmp < p.getNumberCases() && tmp >= 1) {
			cur = (tmpoint.x+tmp < p.getNumberCases() && tmpoint.x+tmp >= 0 && tmpoint.y+tmp < p.getNumberCases() && tmpoint.y+tmp >= 0)?p.getCases()[tmpoint.x+tmp][tmpoint.y+tmp]:null;
			if (cur != null && (!cur.isOccuped() || (!cur.getCurrentPiece().j1 && this.j1) || (cur.getCurrentPiece().j1 && !this.j1))) {
				possibleMoves.add(new Point(tmpoint.x+tmp, tmpoint.y+tmp));
				if (cur.isOccuped()) {
					tmp = -1;
				}
			}
			else {
				tmp = -1;
			}
			tmp++;
		}
 		return possibleMoves;
	}

	/**
	 * Crée une copie de cette pièce.
	 * @return une nouvelle pièce identique à celle-ci.
	 */
	@Override
	public Piece copy() {
		// TODO Auto-generated method stub
		return new Unicorn(this.j1, this.position, this.image);
	}

}
