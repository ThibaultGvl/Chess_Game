package model;


/**
 * Classe représentant une case d'un plateau de jeu d'échecs.
 */
public class Case {

    private Piece currentPiece; // la pièce actuellement sur la case
    private boolean onSelect = false; // un drapeau indiquant si la case est sélectionnée
    private boolean isPlayable = false; // un drapeau indiquant si la case est jouable

    /**
     * Constructeur qui permet de créer une case avec une pièce.
     * @param currentPiece la pièce à placer sur la case
     */
    public Case(Piece currentPiece) {
        this.setCurrentPiece(currentPiece);
    }

    /**
     * Constructeur qui permet de créer une case vide.
     */
    public Case() {
        this(null);
    }

    /**
     * Renvoie la pièce actuellement sur la case.
     * @return la pièce actuellement sur la case, ou null si la case est vide
     */
    public Piece getCurrentPiece() {
        return currentPiece;
    }

    /**
     * Modifie la pièce actuellement sur la case.
     * @param currentPiece la nouvelle pièce à placer sur la case
     */
    public void setCurrentPiece(Piece currentPiece) {
        this.currentPiece = currentPiece;
    }

    /**
     * Indique si la case est occupée par une pièce.
     * @return true si la case est occupée, false sinon
     */
    public boolean isOccuped() {
        return currentPiece != null;
    }

    /**
     * Indique si la case est sélectionnée.
     * @return true si la case est sélectionnée, false sinon
     */
    public boolean isOnSelect() {
        return onSelect;
    }

    /**
     * Modifie le drapeau indiquant si la case est sélectionnée.
     * @param onSelect la nouvelle valeur du drapeau
     */
    public void setOnSelect(boolean onSelect) {
        this.onSelect = onSelect;
    }

    /**
     * Indique si la case est jouable.
     * @return true si la case est jouable, false sinon
     */
    public boolean isPlayable() {
        return isPlayable;
    }

    /**
     * Enlève la pièce de la case.
     */
    public void removePiece() {
        setCurrentPiece(null);
        setPlayable(false);
        setOnSelect(false);
    }

    /**
     * Modifie le drapeau indiquant si la case est jouable.
     * @param isPlayable la nouvelle valeur du drapeau
     */
    public void setPlayable(boolean isPlayable) {
        this.isPlayable = isPlayable;
    }

    /**
     * Crée une copie de la case.
     * @return une nouvelle instance de la case avec les mêmes valeurs que la case originale
     */
    public Case copy() {
        Case cp = new Case(this.currentPiece);
        cp.isPlayable = this.isPlayable;
        cp.onSelect = this.onSelect;
        return cp;
    }
}

