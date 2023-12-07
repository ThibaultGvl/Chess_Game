package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * Cette classe repr�sente un Roi dans un jeu d'�chec et h�rite de la classe Piece.
 */
public class King extends Piece {
	
	/**
     * Boolean pour savoir si la pi�ce a d�j� �t� d�plac� ou pas
     */
	private boolean firstMove = true;

	/**
     * Constructeur de la classe King.
     * @param j1 true si la pi�ce appartient au joueur 1, false sinon.
     * @param p position initiale de la pi�ce.
     * @param i image repr�sentant la pi�ce.
     */
	public King(boolean j1, Point p, ImageIcon i) {
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
     * Calcule les d�placements possibles du roi avant v�rification d'�chec.
     * @param p l'�chiquier actuel.
     * @return une liste de points repr�sentant les cases o� la roi peut aller avant v�rification d'�chec.
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
	 * V�rifie si la position sp�cifi�e est en �chec pour la pi�ce courante.
	 * @param p le plateau de jeu
	 * @param position la position � v�rifier
	 * @return true si la position est en �chec, sinon false
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
	 * Met � jour la liste des d�placements possibles pour ce roi pour ne pas se mettre en �chec.
	 * @param board le plateau de jeu.
	 * @return une liste de points repr�sentant les d�placements possibles sans se mettre en �chec.
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
	 * V�rifie si le roi est echec et mat.
	 * @param p le plateau de jeu
	 * @return true si le roi est �chec et mat, sinon false
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
	 * Cr�e une copie de ce roi.
	 * @return une nouvelle pi�ce identique � celle-ci.
	 */
	@Override
	public Piece copy() {
		return new King(this.j1, this.position, this.image);
	}

	/**
     * Renvoie si le roi a d�j� �t� d�plac�.
     * @return renvoie true si le roi a d�j� �t� d�plac� sinon false.
     */
	public boolean getFirstMove() {
		return firstMove;
	}
	
	/**
     * D�finit si le roi a d�j� �t� d�plac�.
     * @param first Mise � jour de firstMove.
     */
	public void setFirstMove(boolean first) {
		this.firstMove = first;
	}
}
