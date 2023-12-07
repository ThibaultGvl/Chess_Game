package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * La classe Board représente le plateau du jeu d'échecs.
 */
public class Board {
	
	/**
	 * Le nombre de cases du plateau.
	 */
	private int numberCases;
	
	/**
	 * Les cases du plateau.
	 */
	private Case[][] cases;
	
	/**
	 * Le joueur courant.
	 */
	private int currentPlayer = 1;
	
	/**
	 * Le roi du joueur 1.
	 */
	private King kingJ1;
	
	/**
	 * Le roi du joueur 2.
	 */
	private King kingJ2;
	
	/**
	 * Initialise un nouveau plateau avec un nombre de cases donné.
	 * @param numberCases Le nombre de cases du plateau.
	 */
	public Board(int numberCases) {
		this.numberCases = numberCases;
		this.cases = new Case[numberCases][numberCases];
	}

	/**
	 * Renvoie le nombre de cases du plateau.
	 * @return Le nombre de cases.
	 */
	public int getNumberCases() {
		return numberCases;
	}

	/**
	 * Renvoie les cases du plateau.
	 * @return Les cases.
	 */
	public Case[][] getCases() {
		return cases;
	}
	
	/**
	 * Renvoie les cases libres du plateau.
	 * @return Les cases libres.
	 */
	public List<Point> getFreeCases() {
		List<Point> free = new ArrayList<>();
		for (int i = 0; i<numberCases; i++) {
			for (int j = 0; j<numberCases; j++) {
				if (!cases[i][j].isOccuped()) {
					free.add(new Point(i,j));
				}
			}
		}
		return free;
	}
	
	/**
	 * Renvoie les pièces du joueur 1.
	 * @return Les pièces du joueur 1.
	 */
	public List<Case> getJ1Piece() {
		List<Case> pieceJ1 = new ArrayList<>();
		for (int i = 0; i<numberCases; i++) {
			for (int j = 0; j<numberCases; j++) {
				if (cases[i][j].isOccuped() && cases[i][j].getCurrentPiece().isJ1()) {
					pieceJ1.add(cases[i][j]);
				}
			}
		}
		return pieceJ1;
	}
	
	/**
	 * Renvoie les pièces du joueur 2.
	 * @return Les pièces du joueur 2.
	 */
	public List<Case> getJ2Piece() {
		List<Case> pieceJ2 = new ArrayList<>();
		for (int i = 0; i<numberCases; i++) {
			for (int j = 0; j<numberCases; j++) {
				if (cases[i][j].isOccuped() && !cases[i][j].getCurrentPiece().isJ1()) {
					pieceJ2.add(cases[i][j]);
				}
			}
		}
		return pieceJ2;
	}
	
	/**
	 * Renvoie les positions des pièces du j2 qui mettent en échec le roi j1
	 * @return Les positions de ces pièces.
	 */
	public List<Point> j2CheckJ1() {
		List<Point> checker = new ArrayList<>();
		for (Case foe : getJ2Piece()) {
			if (foe.getCurrentPiece().deplacementPossible(this).contains(kingJ1.getPosition())) {
				checker.add(foe.getCurrentPiece().getPosition());
			}
		}
		return checker;
	}
	
	/**
	 * Renvoie les positions des pièces du j1 qui mettent en échec le roi j2
	 * @return Les positions de ces pièces.
	 */
	public List<Point> j1CheckJ2() {
		List<Point> checker = new ArrayList<>();
		for (Case foe : getJ1Piece()) {
			if (foe.getCurrentPiece().deplacementPossible(this).contains(kingJ2.getPosition())) {
				checker.add(foe.getCurrentPiece().getPosition());
			}
		}
		return checker;
	}
	
	/**
	 * Initialise le plateau de jeu avec des pièces aux positions de départ.
	 */
	public void initBoard() {
		if (numberCases == 8) {
			for(int i = 0; i<numberCases; i++) {
				for (int j = 0; j<numberCases; j++) {
					cases[i][j] = new Case();
				}
			}
			for (int i = 0; i<numberCases; i++) {
				cases[1][i].setCurrentPiece(new Pawn(false, new Point(1,i), new ImageIcon(getClass().getResource("/res/blackPawn.png"))));
				cases[6][i].setCurrentPiece(new Pawn(true, new Point(6,i), new ImageIcon(getClass().getResource("/res/whitePawn.png"))));
			}
			cases[7][7].setCurrentPiece(new Rook(true, new Point(7,7), new ImageIcon(getClass().getResource("/res/whiteRook.png"))));
			cases[7][0].setCurrentPiece(new Rook(true, new Point(7,0), new ImageIcon(getClass().getResource("/res/whiteRook.png"))));
			cases[0][0].setCurrentPiece(new Rook(false, new Point(0,0), new ImageIcon(getClass().getResource("/res/blackRook.png"))));
			cases[0][7].setCurrentPiece(new Rook(false, new Point(0,7), new ImageIcon(getClass().getResource("/res/blackRook.png"))));
			cases[7][1].setCurrentPiece(new Knight(true, new Point(7,1), new ImageIcon(getClass().getResource("/res/whiteKnight.png"))));
			cases[7][6].setCurrentPiece(new Knight(true, new Point(7,6), new ImageIcon(getClass().getResource("/res/whiteKnight.png"))));
			cases[0][1].setCurrentPiece(new Knight(false, new Point(0,1), new ImageIcon(getClass().getResource("/res/blackKnight.png"))));
			cases[0][6].setCurrentPiece(new Knight(false,new Point(0,6), new ImageIcon(getClass().getResource("/res/blackKnight.png"))));
			cases[7][2].setCurrentPiece(new Bishop(true, new Point(7,2), new ImageIcon(getClass().getResource("/res/whiteBishop.png"))));
			cases[7][5].setCurrentPiece(new Bishop(true, new Point(7,5), new ImageIcon(getClass().getResource("/res/whiteBishop.png"))));
			cases[0][2].setCurrentPiece(new Bishop(false, new Point(0,2), new ImageIcon(getClass().getResource("/res/blackBishop.png"))));
			cases[0][5].setCurrentPiece(new Bishop(false, new Point(0,5), new ImageIcon(getClass().getResource("/res/blackBishop.png"))));
			cases[7][3].setCurrentPiece(new Queen(true, new Point(7,3), new ImageIcon(getClass().getResource("/res/whiteQueen.png"))));
			cases[7][4].setCurrentPiece(new King(true, new Point(7,4), new ImageIcon(getClass().getResource("/res/whiteKing.png"))));
			cases[0][3].setCurrentPiece(new Queen(false, new Point(0,3), new ImageIcon(getClass().getResource("/res/blackQueen.png"))));
			cases[0][4].setCurrentPiece(new King(false, new Point(0,4), new ImageIcon(getClass().getResource("/res/blackKing.png"))));
			this.kingJ1 = (King) cases[7][4].getCurrentPiece();
			this.kingJ2 = (King) cases[0][4].getCurrentPiece();
		}
		else {
			for(int i = 0; i<numberCases; i++) {
				for (int j = 0; j<numberCases; j++) {
					cases[i][j] = new Case();
				}
			}
			for (int i = 0; i<numberCases; i++) {
				cases[3][i].setCurrentPiece(new Pawn(false, new Point(3,i), new ImageIcon(getClass().getResource("/res/blackPawn.png"))));
				cases[8][i].setCurrentPiece(new Pawn(true, new Point(8,i), new ImageIcon(getClass().getResource("/res/whitePawn.png"))));
				((Pawn) cases[3][i].getCurrentPiece()).setFirstMove(false);
				((Pawn) cases[8][i].getCurrentPiece()).setFirstMove(false);
			}
			cases[11][11].setCurrentPiece(new Rook(true, new Point(11,11), new ImageIcon(getClass().getResource("/res/whiteRook.png"))));
			cases[11][0].setCurrentPiece(new Rook(true, new Point(11,0), new ImageIcon(getClass().getResource("/res/whiteRook.png"))));
			cases[0][0].setCurrentPiece(new Rook(false, new Point(0,0), new ImageIcon(getClass().getResource("/res/blackRook.png"))));
			cases[0][11].setCurrentPiece(new Rook(false, new Point(0,11), new ImageIcon(getClass().getResource("/res/blackRook.png"))));
			cases[11][4].setCurrentPiece(new Bishop(true, new Point(11,4), new ImageIcon(getClass().getResource("/res/WCrocodile.png"))));
			cases[11][7].setCurrentPiece(new Bishop(true, new Point(11,7), new ImageIcon(getClass().getResource("/res/WCrocodile.png"))));
			cases[0][4].setCurrentPiece(new Bishop(false, new Point(0,4), new ImageIcon(getClass().getResource("/res/BCrocodile.png"))));
			cases[0][7].setCurrentPiece(new Bishop(false, new Point(0,7), new ImageIcon(getClass().getResource("/res/BCrocodile.png"))));
			cases[11][5].setCurrentPiece(new Gryphon(true, new Point(11,5), new ImageIcon(getClass().getResource("/res/wgryphon.png"))));
			cases[0][5].setCurrentPiece(new Gryphon(false, new Point(0,5), new ImageIcon(getClass().getResource("/res/bgryphon.png"))));
			cases[0][1].setCurrentPiece(new Lion(false, new Point(0,1), new ImageIcon(getClass().getResource("/res/blion.png"))));
			cases[0][10].setCurrentPiece(new Lion(false, new Point(0,10), new ImageIcon(getClass().getResource("/res/blion.png"))));
			cases[11][1].setCurrentPiece(new Lion(true, new Point(11,1), new ImageIcon(getClass().getResource("/res/wlion.png"))));
			cases[11][10].setCurrentPiece(new Lion(true, new Point(11,10), new ImageIcon(getClass().getResource("/res/wlion.png"))));
			cases[0][3].setCurrentPiece(new Giraffe(false, new Point(0,3), new ImageIcon(getClass().getResource("/res/BGiraffe.png"))));
			cases[0][8].setCurrentPiece(new Giraffe(false, new Point(0,8), new ImageIcon(getClass().getResource("/res/BGiraffe.png"))));
			cases[11][8].setCurrentPiece(new Giraffe(true, new Point(11,8), new ImageIcon(getClass().getResource("/res/WGiraffe.png"))));
			cases[11][3].setCurrentPiece(new Giraffe(true, new Point(11,3), new ImageIcon(getClass().getResource("/res/WGiraffe.png"))));
			cases[0][2].setCurrentPiece(new Unicorn(false, new Point(0,2), new ImageIcon(getClass().getResource("/res/BUnicorn.png"))));
			cases[0][9].setCurrentPiece(new Unicorn(false, new Point(0,9), new ImageIcon(getClass().getResource("/res/BUnicorn.png"))));
			cases[11][2].setCurrentPiece(new Unicorn(true, new Point(11,2), new ImageIcon(getClass().getResource("/res/WUnicorn.png"))));
			cases[11][9].setCurrentPiece(new Unicorn(true, new Point(11,9), new ImageIcon(getClass().getResource("/res/WUnicorn.png"))));
			cases[11][6].setCurrentPiece(new King(true, new Point(11,6), new ImageIcon(getClass().getResource("/res/whiteKing.png"))));
			cases[0][6].setCurrentPiece(new King(false, new Point(0,6), new ImageIcon(getClass().getResource("/res/blackKing.png"))));
			((King) cases[0][6].getCurrentPiece()).setFirstMove(false);
			((King) cases[11][6].getCurrentPiece()).setFirstMove(false);
			this.kingJ1 = (King) cases[11][6].getCurrentPiece();
			this.kingJ2 = (King) cases[0][6].getCurrentPiece();
		}
	}

	/**
	 * Renvoie le joueur en cours.
	 *
	 * @return le joueur en cours
	 */
	public int getCurrentPlayer() {
	    return currentPlayer;
	}

	/**
	 * Définit le joueur en cours.
	 *
	 * @param currentPlayer le joueur en cours à définir
	 */
	public void setCurrentPlayer(int currentPlayer) {
	    this.currentPlayer = currentPlayer;
	}

	/**
	 * Renvoie la case sélectionnée.
	 *
	 * @return la case sélectionnée ou null si aucune case n'est sélectionnée
	 */
	public Case getFocusedCase() {
	    Case caseFocused = null;
	    for (int i = 0; i<numberCases; i++) {
	        for (int j = 0; j<numberCases; j++) {
	            if (cases[i][j].isOnSelect()) {
	                caseFocused = cases[i][j];
	            }
	        }
	    }
	    return caseFocused;
	}

	/**
	 * Renvoie le roi du joueur 1.
	 *
	 * @return le roi du joueur 1
	 */
	public King getKingJ1() {
	    return kingJ1;
	}

	/**
	 * Définit le roi du joueur 1.
	 *
	 * @param kingJ1 le roi du joueur 1 à définir
	 */
	public void setKingJ1(King kingJ1) {
	    this.kingJ1 = kingJ1;
	}

	/**
	 * Renvoie le roi du joueur 2.
	 *
	 * @return le roi du joueur 2
	 */
	public King getKingJ2() {
	    return kingJ2;
	}

	/**
	 * Définit le roi du joueur 2.
	 *
	 * @param kingJ2 le roi du joueur 2 à définir
	 */
	public void setKingJ2(King kingJ2) {
	    this.kingJ2 = kingJ2;
	}

	/**
	 * Crée une copie du plateau de jeu actuel.
	 *
	 * @return une copie du plateau de jeu actuel
	 */
	public Board copy() {
	    Board copy = new Board(this.getNumberCases());
	    for (int i = 0; i < cases.length; i++) {
	        for (int j = 0; j < cases[i].length; j++) {
	            copy.cases[i][j] = cases[i][j].copy();
	        }
	    }
	    copy.kingJ1 = (King) kingJ1.copy();
	    copy.kingJ2 = (King) kingJ2.copy();
	    copy.currentPlayer = currentPlayer;
	    return copy;
	}

	/**
	 * Vérifie si la partie est en pat.
	 *
	 * @return true si la partie est en pat, false sinon
	 */
	public boolean pat() {
		boolean pat = !kingJ1.isCheckMate(this)&&!kingJ2.isCheckMate(this);
		for (Case c : getJ1Piece()) {
			if (c.getCurrentPiece().updateDeplacementWithKingCheck(this).size() != 0) {
				pat = false;
			}
		}
		for (Case c : getJ2Piece()) {
			if (c.getCurrentPiece().updateDeplacementWithKingCheck(this).size() != 0) {
				pat = false;
			}
		}
		return pat;
	}

}
