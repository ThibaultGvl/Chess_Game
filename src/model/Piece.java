package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * Cette classe représente une pièce dans un jeu d'échecs.
 */
public abstract class Piece {
	
	/**
     * Position de la pièce sur l'échiquier.
     */
	protected Point position;
	
	/**
     * Image représentant la pièce.
     */
	protected ImageIcon image;
	
	/**
     * Indique si la pièce appartient au joueur 1.
     */
	protected boolean j1;
	
	/**
     * Constructeur de la classe Piece.
     * @param j1 true si la pièce appartient au joueur 1, false sinon.
     * @param p position initiale de la pièce.
     * @param i image représentant la pièce.
     */
	public Piece(boolean j1, Point p, ImageIcon i) {
		this.j1 = j1;
		this.position = p;
		this.image = i;
	}
	
	/**
     * Calcule les déplacements possibles de la pièce.
     * @param p l'échiquier actuel.
     * @return une liste de points représentant les cases où la pièce peut aller.
     */
	public abstract List<Point> deplacementPossible(Board p);
	
	/**
     * Déplace la pièce sur la case spécifiée.
     * @param x coordonnée x de la case où la pièce doit être déplacée.
     * @param y coordonnée y de la case où la pièce doit être déplacée.
     * @param p l'échiquier actuel.
     */
	public void deplacement(int x, int y, Board p) {
		this.setPosition(new Point(x, y));
	}
	
	/**
     * Calcule les déplacements possibles en diagonale de la pièce.
     * @param p l'échiquier actuel.
     * @return une liste de points représentant les cases où la pièce peut aller en diagonale.
     */
	public List<Point> diagonalMooves(Board p) {
		List<Point> moves = new ArrayList<>();
		Case southEast =  position.x+1<p.getNumberCases() && position.x+1>=0 && position.y+1 >= 0 && position.y+1<p.getNumberCases()?p.getCases()[position.x+1][position.y+1]:null;
		Case northWest = position.x-1<p.getNumberCases() && position.x-1>=0 && position.y-1 >= 0 && position.y-1<p.getNumberCases()?p.getCases()[position.x-1][position.y-1]:null;
		Case northEast = position.x-1<p.getNumberCases() && position.x-1>=0 && position.y+1 >= 0 && position.y+1<p.getNumberCases()?p.getCases()[position.x-1][position.y+1]:null;
		Case southWest = position.x+1<p.getNumberCases() && position.x+1>=0 && position.y-1 >= 0 && position.y-1<p.getNumberCases()?p.getCases()[position.x+1][position.y-1]:null;
		int tmp = 1; 
		while (position.x-tmp >= 0 && position.y+tmp >= 0 && position.x-tmp < p.getNumberCases() && position.y+tmp < p.getNumberCases() && !northEast.isOccuped()) {
			moves.add(new Point(position.x-tmp, position.y+tmp));
			tmp++;
			northEast = position.x-tmp<p.getNumberCases() && position.x-tmp>=0 && position.y+tmp >= 0 && position.y+tmp<p.getNumberCases()?p.getCases()[position.x-tmp][position.y+tmp]:null;
		}
		if (northEast!= null && ((northEast.getCurrentPiece().j1 && !this.j1) || (!northEast.getCurrentPiece().j1 && this.j1))) {
			moves.add(new Point(position.x-tmp, position.y+tmp));
		}
		tmp = 1;
		while (position.x+tmp >= 0 && position.y+tmp >= 0 && position.x+tmp < p.getNumberCases() && position.y+tmp < p.getNumberCases() && !southEast.isOccuped()) {
			moves.add(new Point(position.x+tmp, position.y+tmp));
			tmp++;
			southEast = position.x+tmp<p.getNumberCases() && position.x+tmp>=0 && position.y+tmp >= 0 && position.y+tmp<p.getNumberCases()?p.getCases()[position.x+tmp][position.y+tmp]:null;
		}
		if (southEast != null && ((southEast.getCurrentPiece().j1 && !this.j1) || (!southEast.getCurrentPiece().j1 && this.j1))) {
			moves.add(new Point(position.x+tmp, position.y+tmp));
		}
		tmp = 1;
		while (position.x-tmp >= 0 && position.y-tmp >= 0 && position.x-tmp < p.getNumberCases() && position.y-tmp < p.getNumberCases() && !northWest.isOccuped()) {
			moves.add(new Point(position.x-tmp, position.y-tmp));
			tmp++;
			northWest = position.x-tmp<p.getNumberCases() && position.x-tmp>=0 && position.y-tmp >= 0 && position.y-tmp<p.getNumberCases()?p.getCases()[position.x-tmp][position.y-tmp]:null;
		}
		if (northWest != null && ((northWest.getCurrentPiece().j1 && !this.j1) || (!northWest.getCurrentPiece().j1 && this.j1))) {
			moves.add(new Point(position.x-tmp, position.y-tmp));
		}
		tmp = 1;
		while (position.x+tmp >= 0 && position.y-tmp >= 0 && position.x+tmp < p.getNumberCases() && position.y-tmp < p.getNumberCases() && !southWest.isOccuped()) {
			moves.add(new Point(position.x+tmp, position.y-tmp));
			tmp++;
			southWest = position.x+tmp<p.getNumberCases() && position.x+tmp>=0 && position.y-tmp >= 0 && position.y-tmp<p.getNumberCases()?p.getCases()[position.x+tmp][position.y-tmp]:null;
		}
		if (southWest != null && ((southWest.getCurrentPiece().j1 && !this.j1) || (!southWest.getCurrentPiece().j1 && this.j1))) {
			moves.add(new Point(position.x+tmp, position.y-tmp));
		}
		return moves;
	}

	/**
     * Calcule les déplacements possibles en ligne droite de la pièce.
     * @param p l'échiquier actuel.
     * @return une liste de points représentant les cases où la pièce peut aller en ligne droite.
     */
	public List<Point> linearMooves(Board p) {
		List<Point> moves = new ArrayList<>();
		Case top =  position.x-1<p.getNumberCases() && position.x-1>=0 && position.y >= 0 && position.y<p.getNumberCases()?p.getCases()[position.x-1][position.y]:null;
		Case right = position.x<p.getNumberCases() && position.x>=0 && position.y+1 >= 0 && position.y+1<p.getNumberCases()?p.getCases()[position.x][position.y+1]:null;
		Case left = position.x<p.getNumberCases() && position.x>=0 && position.y-1 >= 0 && position.y-1<p.getNumberCases()?p.getCases()[position.x][position.y-1]:null;
		Case bottom=  position.x+1<p.getNumberCases() && position.x+1>=0 && position.y >= 0 && position.y<p.getNumberCases()?p.getCases()[position.x+1][position.y]:null;
		int tmp = 1;
		while (position.x-tmp >= 0 && !top.isOccuped()) {
			moves.add(new Point(position.x-tmp, position.y));
			tmp++;
			top = position.x-tmp>=0?p.getCases()[position.x-tmp][position.y]:null;
		}
		if (top!= null && ((top.getCurrentPiece().j1 && !this.j1) || (!top.getCurrentPiece().j1 && this.j1))) {
			moves.add(new Point(position.x-tmp, position.y));
		}
		tmp = 1;
		while (position.y+tmp < p.getNumberCases() && !right.isOccuped()) {
			moves.add(new Point(position.x, position.y+tmp));
			tmp++;
			right = position.y+tmp<p.getNumberCases()?p.getCases()[position.x][position.y+tmp]:null;
		}
		if (right!= null && ((right.getCurrentPiece().j1 && !this.j1) || (!right.getCurrentPiece().j1 && this.j1))) {
			moves.add(new Point(position.x, position.y+tmp));
		}
		tmp = 1;
		while (position.y-tmp >= 0 && !left.isOccuped()) {
			moves.add(new Point(position.x, position.y-tmp));
			tmp++;
			left = position.y-tmp>=0?p.getCases()[position.x][position.y-tmp]:null;
		}
		if (left!= null && ((left.getCurrentPiece().j1 && !this.j1) || (!left.getCurrentPiece().j1 && this.j1))) {
			moves.add(new Point(position.x, position.y-tmp));
		}
		tmp = 1;
		while (position.x+tmp < p.getNumberCases() && !bottom.isOccuped()) {
			moves.add(new Point(position.x+tmp, position.y));
			tmp++;
			bottom = position.x+tmp<p.getNumberCases()?p.getCases()[position.x+tmp][position.y]:null;
		}
		if (bottom!= null && ((bottom.getCurrentPiece().j1 && !this.j1) || (!bottom.getCurrentPiece().j1 && this.j1))) {
			moves.add(new Point(position.x+tmp, position.y));
		}
		return moves;
	}
	
	/**
	 * Indique si cette pièce appartient au joueur 1 ou non.
	 * @return vrai si la pièce appartient au joueur 1, faux sinon.
	 */
	public boolean isJ1() {
		return j1;
	}

	/**
	 * Obtient la position de cette pièce sur le plateau de jeu.
	 * @return la position de la pièce.
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * Définit la position de cette pièce sur le plateau de jeu.
	 * @param p la nouvelle position de la pièce.
	 */
	public void setPosition(Point p) {
		this.position = p;
	}
	
	/**
	 * Obtient la coordonnée x de la position de cette pièce sur le plateau de jeu.
	 * @return la coordonnée x de la position de la pièce.
	 */
	public int getX() {
		return position.x;
	}
	
	/**
	 * Définit la coordonnée x de la position de cette pièce sur le plateau de jeu.
	 * @param x la nouvelle coordonnée x de la position de la pièce.
	 */
	public void setX(int x) {
		this.position.x = x;
	}
	
	/**
	 * Obtient la coordonnée y de la position de cette pièce sur le plateau de jeu.
	 * @return la coordonnée y de la position de la pièce.
	 */
	public int getY() {
		return position.y;
	}
	
	/**
	 * Définit la coordonnée y de la position de cette pièce sur le plateau de jeu.
	 * @param y la nouvelle coordonnée y de la position de la pièce.
	 */
	public void setY(int y) {
		this.position.y = y;
	}
	
	/**
	 * Obtient l'image associée à cette pièce.
	 * @return l'image associée à la pièce.
	 */
	public ImageIcon getImage() {
		return image;
	}

	/**
	 * Définit l'image associée à cette pièce.
	 * @param image la nouvelle image associée à la pièce.
	 */
	public void setImage(ImageIcon image) {
		this.image = image;
	}
	
	/**
	 * Crée une copie de cette pièce.
	 * @return une nouvelle pièce identique à celle-ci.
	 */
	public abstract Piece copy();
	
	/**
	 * Met à jour la liste des déplacements possibles pour cette pièce, en prenant en compte la position actuelle du roi.
	 * @param board le plateau de jeu.
	 * @return une liste de points représentant les déplacements possibles pour cette pièce sans mettre en échec le roi.
	 */
	public List<Point> updateDeplacementWithKingCheck(Board board) {
	    List<Point> deplacementPossible = deplacementPossible(board);
	    King king = j1 ? board.getKingJ1() : board.getKingJ2();
	    List<Point> safeMoves = new ArrayList<>();
	        for (Point p : deplacementPossible) {
	            Board newBoard = board.copy();
	            newBoard.getCases()[p.x][p.y].setCurrentPiece(this);
	            newBoard.getCases()[getPosition().x][getPosition().y].setCurrentPiece(null);
	            if (!king.isOnCheck(newBoard, king.position)) {
	                safeMoves.add(p);
	            }
	        }
	        return safeMoves;
	}

}
