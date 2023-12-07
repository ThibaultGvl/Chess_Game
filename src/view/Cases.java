package view;

import java.awt.Color;

import java.awt.Component;
import java.awt.Point;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Case;
import model.King;
import model.Board;

/**
 * Une classe pour représenter graphiquement une case du plateau de jeu.
*/
public class Cases extends JPanel implements Serializable {
	
	private static final long serialVersionUID = -6429905708651035507L;

	/*
	 * Position de la case
	 */
	private Point p;
	
	/*
	 * Case modele qu'on représente graphiquement
	 */
	private Case c;
	
	/*
	 * Couleur initiale de la case
	 */
	private Color initialColor;
	
	/**
	 * Crée un objet Cases représentant une case sur le plateau.
	 * @param c La case représentée.
	 * @param p La position de la case sur le plateau.
	*/
	public Cases(Case c, Point p) {
		super();
		this.c = c;
		this.p = p;
		this.initialColor = (p.x + p.y) % 2==0?new Color(207, 184, 135):new Color(101, 67, 33);
	}
	
	/**
	 * Renvoie la position de la case.
	 *
	 * @return un objet Point représentant la position de la case
	 */
	public Point getPoint() {
	    return this.p;
	}

	/**
	 * Renvoie la case modèle associée à l'objet Cases.
	 *
	 * @return l'objet Case associé à l'objet Cases
	 */
	public Case getCase() {
	    return c;
	}

	/**
	 * Change la couleur de fond de la case sélectionnée.
	 *
	 * @param isKing un booléen indiquant si le joueur courant a le roi sélectionné
	 */
	public void onSelectedColor(boolean isKing) {
	    if (c.isOnSelect()) {
	        setBackground(initialColor);
	    } else {
	        setBackground(initialColor.brighter());
	    }
	    if (isKing) {
	        setBackground(Color.red);
	    }
	    c.setOnSelect(!c.isOnSelect());
	}

	/**
	 * Modifie la représentation graphique d'une case si on peut se déplacer dessus
	 */
	public void onZoom() {
	    if (c.isPlayable() && !c.isOccuped()) {
	        this.removeAll();
	        setBackground(initialColor);
	    } else if (c.isPlayable()) {
	        setBackground(initialColor);
	    } else {
	        JLabel label = new JLabel();
	        label.setAlignmentX(Component.CENTER_ALIGNMENT);
	        label.setAlignmentY(Component.CENTER_ALIGNMENT);
	        this.add(label);
	        this.setBackground(initialColor.darker());
	    }
	    this.validate();
	    c.setPlayable(!c.isPlayable());
	}

	/**
	 * Renvoie la couleur de fond initiale de la case.
	 *
	 * @return la couleur de fond initiale de la case
	 */
	public Color getInitialColor() {
	    return initialColor;
	}

	/**
	 * Change la couleur de fond initiale de la case.
	 *
	 * @param color la nouvelle couleur de fond initiale de la case
	 */
	public void setInitialColor(Color color) {
	    this.initialColor = color;
	}

	/**
	 * Remplace le contenu de la case avec une nouvelle pièce.
	 */
	public void paintNewPiece() {
	    this.removeAll();
	    JLabel label = new JLabel();
	    label.setIcon(c.getCurrentPiece().getImage());
	    label.setVerticalAlignment(JLabel.CENTER);
	    label.setHorizontalAlignment(JLabel.CENTER);
	    this.add(label);
	    this.validate();
	}

	/**
	 * Retire le contenu de la case et met à jour sa taille.
	 *
	 * @param roque un booléen indiquant si le mouvement effectué est un roque
	 */
	public void removePiece(boolean roque) {
	    onSelectedColor(false);
	    if (roque) {
	        onSelectedColor(false);
	    }
	    c.removePiece();
	    this.removeAll();
	    this.validate();
	}

	/**
	 * Met en evidence la case du roi s'il est en échec.
	 *
	 * @param board le plateau de jeu courant
	 */
	public void inCheck(Board board) {
	    King k = board.getCurrentPlayer() == 1 ? board.getKingJ1() : board.getKingJ2();
	    boolean isInCheck = c.isOccuped() && ((King) c.getCurrentPiece()).isOnCheck(board, k.getPosition());
	    setBackground(Color.red);
	    if (!isInCheck) {
	        setBackground(this.getInitialColor());
	    }
	    this.validate();
	}

}
