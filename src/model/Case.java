package model;


/**
 * Classe repr�sentant une case d'un plateau de jeu d'�checs.
 */
public class Case {

    private Piece currentPiece; // la pi�ce actuellement sur la case
    private boolean onSelect = false; // un drapeau indiquant si la case est s�lectionn�e
    private boolean isPlayable = false; // un drapeau indiquant si la case est jouable

    /**
     * Constructeur qui permet de cr�er une case avec une pi�ce.
     * @param currentPiece la pi�ce � placer sur la case
     */
    public Case(Piece currentPiece) {
        this.setCurrentPiece(currentPiece);
    }

    /**
     * Constructeur qui permet de cr�er une case vide.
     */
    public Case() {
        this(null);
    }

    /**
     * Renvoie la pi�ce actuellement sur la case.
     * @return la pi�ce actuellement sur la case, ou null si la case est vide
     */
    public Piece getCurrentPiece() {
        return currentPiece;
    }

    /**
     * Modifie la pi�ce actuellement sur la case.
     * @param currentPiece la nouvelle pi�ce � placer sur la case
     */
    public void setCurrentPiece(Piece currentPiece) {
        this.currentPiece = currentPiece;
    }

    /**
     * Indique si la case est occup�e par une pi�ce.
     * @return true si la case est occup�e, false sinon
     */
    public boolean isOccuped() {
        return currentPiece != null;
    }

    /**
     * Indique si la case est s�lectionn�e.
     * @return true si la case est s�lectionn�e, false sinon
     */
    public boolean isOnSelect() {
        return onSelect;
    }

    /**
     * Modifie le drapeau indiquant si la case est s�lectionn�e.
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
     * Enl�ve la pi�ce de la case.
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
     * Cr�e une copie de la case.
     * @return une nouvelle instance de la case avec les m�mes valeurs que la case originale
     */
    public Case copy() {
        Case cp = new Case(this.currentPiece);
        cp.isPlayable = this.isPlayable;
        cp.onSelect = this.onSelect;
        return cp;
    }
}

