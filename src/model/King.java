package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * Cette classe représente un Roi dans un jeu d'échec et hérite de la classe Piece.
 */
public class King extends Piece {
	
	/**
     * Boolean pour savoir si la pièce a déjà été déplacé ou pas
     */
	private boolean firstMove = true;

	/**
     * Constructeur de la classe King.
     * @param j1 true si la pièce appartient au joueur 1, false sinon.
     * @param p position initiale de la pièce.
     * @param i image représentant la pièce.
     */
	public King(boolean j1, Point p, ImageIcon i) {
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
		if (position.x == x && position.y+2 == y) {
			p.getCases()[x][y-1].setCurrentPiece(p.getCases()[position.x][position.y+3].getCurrentPiece());
			p.getCases()[x][y-1].getCurrentPiece().setPosition(new Point(x, y-1));
			p.getCases()[x][position.y+3].setCurrentPiece(null);
		}
		if (position.x == x && position.y-2 == y) {
			p.getCases()[x][y+1].setCurrentPiece(p.getCases()[position.x][position.y-4].getCurrentPiece());
			p.getCases()[x][y+1].getCurrentPiece().setPosition(new Point(x, y+1));
			p.getCases()[x][position.y-4].setCurrentPiece(null);
		}
		this.setPosition(new Point(x, y));
	}

	/**
     * Calcule les déplacements possibles du roi avant vérification d'échec.
     * @param p l'échiquier actuel.
     * @return une liste de points représentant les cases où la roi peut aller avant vérification d'échec.
     */
	@Override
	public List<Point> deplacementPossible(Board p) {
		Point[] moves = {new Point(this.getX()+1, this.getY()), new Point(this.getX()-1, this.getY()), new Point(this.getX(), this.getY()+1), new Point(this.getX(), this.getY()-1), new Point(this.getX()-1, this.getY()-1), new Point(this.getX()+1, this.getY()-1), new Point(this.getX()+1, this.getY()+1), new Point(this.getX()-1, this.getY()+1)};
		List<Point> ds = new ArrayList<>();
		for (Point move : moves) {
			if (move.x < p.getNumberCases() && move.x >= 0 && move.y < p.getNumberCases() && move.y >= 0 && (!p.getCases()[move.x][move.y].isOccuped() || (p.getCases()[move.x][move.y].getCurrentPiece().j1 && !j1 || !p.getCases()[move.x][move.y].getCurrentPiece().j1 && j1))) {
				ds.add(move);
			}
		}
		if (firstMove && position.y+3 < p.getNumberCases() && !p.getCases()[position.x][position.y+2].isOccuped() && !p.getCases()[position.x][position.y+1].isOccuped() && p.getCases()[position.x][position.y+3].getCurrentPiece() instanceof Rook) {
			ds.add(new Point(position.x, position.y+2));
		}
		if (firstMove && position.y-4 >= 0 && !p.getCases()[position.x][position.y-3].isOccuped() && !p.getCases()[position.x][position.y-2].isOccuped() && !p.getCases()[position.x][position.y-1].isOccuped() && p.getCases()[position.x][position.y-4].getCurrentPiece() instanceof Rook) {
			ds.add(new Point(position.x, position.y-2));
		}
		return ds;
	}
	
	/**
	 * Vérifie si la position spécifiée est en échec pour la pièce courante.
	 * @param p le plateau de jeu
	 * @param position la position à vérifier
	 * @return true si la position est en échec, sinon false
	*/
	public boolean isOnCheck(Board p, Point position) {
		List<Case> foes = this.j1?p.getJ2Piece():p.getJ1Piece();
		boolean check = false;
		for (Case foe : foes) {
			if (foe.getCurrentPiece().deplacementPossible(p).contains(position)) {
				check = true;
			}
		}
		return check;
	}
	
	/**
	 * Met à jour la liste des déplacements possibles pour ce roi pour ne pas se mettre en échec.
	 * @param board le plateau de jeu.
	 * @return une liste de points représentant les déplacements possibles sans se mettre en échec.
	 */
	@Override
	public List<Point> updateDeplacementWithKingCheck(Board board) {
	    List<Point> deplacementPossible = deplacementPossible(board);
	    List<Point> safeMoves = new ArrayList<>();
	        for (Point p : deplacementPossible) {
	            Board newBoard = board.copy();
	            newBoard.getCases()[p.x][p.y].setCurrentPiece(this);
	            newBoard.getCases()[getPosition().x][getPosition().y].setCurrentPiece(null);
	            if (!isOnCheck(newBoard, p)) {
	                safeMoves.add(p);
	            }
	        }
	        return safeMoves;
	}
	

	/**
	 * Vérifie si le roi est echec et mat.
	 * @param p le plateau de jeu
	 * @return true si le roi est échec et mat, sinon false
	*/
	public boolean isCheckMate(Board p) {
		List<Point> deplacementPossible = new ArrayList<>();
		List<Case> friends = j1?p.getJ1Piece():p.getJ2Piece();
		for (Case c : friends) {
			deplacementPossible.addAll(c.getCurrentPiece().updateDeplacementWithKingCheck(p));
		}
		return deplacementPossible.isEmpty() && this.isOnCheck(p, position);
	}
	
	
	/**
	 * Crée une copie de ce roi.
	 * @return une nouvelle pièce identique à celle-ci.
	 */
	@Override
	public Piece copy() {
		return new King(this.j1, this.position, this.image);
	}

	/**
     * Renvoie si le roi a déjà été déplacé.
     * @return renvoie true si le roi a déjà été déplacé sinon false.
     */
	public boolean getFirstMove() {
		return firstMove;
	}
	
	/**
     * Définit si le roi a déjà été déplacé.
     * @param first Mise à jour de firstMove.
     */
	public void setFirstMove(boolean first) {
		this.firstMove = first;
	}
}
