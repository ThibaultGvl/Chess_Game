package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Bishop;
import model.Knight;
import model.Lion;
import model.Pawn;
import model.Piece;
import model.Board;
import model.Giraffe;
import model.Gryphon;
import model.Queen;
import model.Rook;
import model.Unicorn;

/**
 * La classe ChessBoard étend JFrame et implémente Serializable.
 * Représentation graphique d'un plateau d'échec
 */
public class ChessBoard extends JFrame implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/*
	 * Model du plateau qu'on va représenter graphiquement
	 */
	private Board p;
	/*
	 * Panneau représentant le contenu de la fenêtre
	 */
    private JPanel boardPanel;
    /*
	 * Grille des cases graphiques
	 */
    private Cases[][] boardSquares;
    
    /**
     * Constructeur de la classe ChessBoard. Il initialise la fenêtre principale et ajoute un MouseListener à son JPanel.
     */
    public ChessBoard() {
    	boardPanel = new JPanel();
    	boardPanel.addMouseListener((MouseListener) new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	getOnClickModifications(e);
            }
    	});
    }
    
    /**
     * Méthode qui est appelée lorsqu'un utilisateur clique sur une case du plateau. Elle permet de mettre à jour le plateau en conséquence.
     * 
     * @param e L'événement MouseEvent qui est déclenché lorsqu'un utilisateur clique sur une case du plateau.
     */
    public void getOnClickModifications(MouseEvent e) {
    	Cases cur = getCasesByPoint(new Point((int)e.getY()/75,(int) e.getX()/75));
    	if (p.getFocusedCase() == null || p.getFocusedCase() == cur.getCase() || (p.getFocusedCase() != null && p.getFocusedCase().getCurrentPiece() != null && p.getFocusedCase().getCurrentPiece().isJ1() && cur.getCase().getCurrentPiece() != null && cur.getCase().getCurrentPiece().isJ1()) || (!p.getFocusedCase().getCurrentPiece().isJ1() && cur.getCase().getCurrentPiece() != null && !cur.getCase().getCurrentPiece().isJ1())) {
    		if (p.getFocusedCase() != null && p.getFocusedCase().getCurrentPiece() != null && ((p.getFocusedCase().getCurrentPiece().isJ1() && cur.getCase().getCurrentPiece() != null && cur.getCase().getCurrentPiece().isJ1()) || (!p.getFocusedCase().getCurrentPiece().isJ1() && cur.getCase().getCurrentPiece() != null && !cur.getCase().getCurrentPiece().isJ1())) && !(p.getFocusedCase().getCurrentPiece().getPosition() == cur.getCase().getCurrentPiece().getPosition())) {
        		for (Point moove : p.getFocusedCase().getCurrentPiece().updateDeplacementWithKingCheck(p)) {
            		getCasesByPoint(moove).onZoom();
            	}
        		getCasesByPoint(new Point(p.getFocusedCase().getCurrentPiece().getX(), p.getFocusedCase().getCurrentPiece().getY())).onSelectedColor(false);
        	}
    		List<Point> mooves = new ArrayList<>();
    		if (cur.getCase().isOccuped() && cur.getCase().getCurrentPiece().isJ1() && p.getCurrentPlayer() == 1 && !(cur.getPoint().equals(p.getKingJ1().getPosition()) && p.getKingJ1().updateDeplacementWithKingCheck(p).size() == 0)) {
    			boolean isKing = cur.getPoint().equals(p.getKingJ1().getPosition()) && p.getKingJ1().isOnCheck(p, p.getKingJ1().getPosition());
    			getCasesByPoint(new Point((int)e.getY()/75,(int) e.getX()/75)).onSelectedColor(isKing);
    	    	mooves.addAll(cur.getCase().getCurrentPiece().updateDeplacementWithKingCheck(p));
    		}
    		else if (cur.getCase().isOccuped() && !cur.getCase().getCurrentPiece().isJ1() && p.getCurrentPlayer() == 2 && !(cur.getPoint().equals(p.getKingJ2().getPosition()) && p.getKingJ2().updateDeplacementWithKingCheck(p).size() == 0)) {
    			boolean isKing = cur.getPoint().equals(p.getKingJ2().getPosition()) && p.getKingJ2().isOnCheck(p, p.getKingJ2().getPosition());
    			getCasesByPoint(new Point((int)e.getY()/75,(int) e.getX()/75)).onSelectedColor(isKing);
    			mooves.addAll(cur.getCase().getCurrentPiece().updateDeplacementWithKingCheck(p));
    		}
    		for (Point moove :mooves) {
        		getCasesByPoint(moove).onZoom();
        	}
    	}
    	else if (p.getFocusedCase().getCurrentPiece().updateDeplacementWithKingCheck(p).contains(cur.getPoint())) {
    		Cases caseToUpdate = cur;
    		Cases caseToDel = getCasesByPoint(p.getFocusedCase().getCurrentPiece().getPosition());
	    	for (Point moove :caseToDel.getCase().getCurrentPiece().updateDeplacementWithKingCheck(p)) {
	    		getCasesByPoint(moove).onZoom();
	        }
	    	caseToUpdate.getCase().setCurrentPiece(caseToDel.getCase().getCurrentPiece());
	    	p.getFocusedCase().getCurrentPiece().deplacement(cur.getPoint().x, cur.getPoint().y, p);
	    	if (p.getFocusedCase().getCurrentPiece()!=null && ((p.getFocusedCase().getCurrentPiece().getPosition().equals(p.getKingJ1().getPosition()) && p.getKingJ1().getFirstMove())  || (p.getFocusedCase().getCurrentPiece().getPosition().equals(p.getKingJ2().getPosition()) && p.getKingJ2().getFirstMove())) && caseToUpdate.getCase().getCurrentPiece().getY()-2 == caseToDel.getPoint().y) {
	    		getCasesByPoint(new Point((int)e.getY()/75,(int) (e.getX()/75)+1)).removePiece(true);
	    		getCasesByPoint(new Point((int)e.getY()/75,(int) (e.getX()/75)-1)).paintNewPiece();
	    		if (p.getCurrentPlayer() == 1) {
	    			p.getKingJ1().setFirstMove(false);
	    		}
	    		else {
	    			p.getKingJ2().setFirstMove(false);
	    		}
	    	}
	    	if (p.getFocusedCase().getCurrentPiece()!=null && ((p.getFocusedCase().getCurrentPiece().getPosition().equals(p.getKingJ1().getPosition()) && p.getKingJ1().getFirstMove())  || (p.getFocusedCase().getCurrentPiece().getPosition().equals(p.getKingJ2().getPosition()) && p.getKingJ2().getFirstMove())) && caseToUpdate.getCase().getCurrentPiece().getY()+2 == caseToDel.getPoint().y) {
	    		getCasesByPoint(new Point((int)e.getY()/75,(int) (e.getX()/75)-2)).removePiece(true);
	    		getCasesByPoint(new Point((int)e.getY()/75,(int) (e.getX()/75)+1)).paintNewPiece();
	    		if (p.getCurrentPlayer() == 1) {
	    			p.getKingJ1().setFirstMove(false);
	    		}
	    		else {
	    			p.getKingJ2().setFirstMove(false);
	    		}
	    	}
	    	caseToDel.removePiece(false);
	    	caseToUpdate.paintNewPiece();
	    	p.setCurrentPlayer(((p.getCurrentPlayer())%2)+1);
	    	promotion(new Point((int)e.getY()/75,(int) e.getX()/75));
	    	getCasesByPoint(p.getKingJ1().getPosition()).inCheck(p);
	    	getCasesByPoint(p.getKingJ2().getPosition()).inCheck(p);
    	}
    	if (p.getKingJ1().isCheckMate(p)) {
    		int choice = JOptionPane.showOptionDialog(boardPanel, "Echec et mat ! Le joueur 2 gagne !", "Rejouer ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Oui", "Non"}, "Non");
    		if (choice == JOptionPane.YES_OPTION) {
    			this.start();
    		} else {
    			this.dispose();
    		}
    	}
    	else if (p.getKingJ2().isCheckMate(p)) {
    		int choice = JOptionPane.showOptionDialog(boardPanel, "Echec et mat ! Le joueur 1 gagne !", "Rejouer ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Oui", "Non"}, "Non");
    		if (choice == JOptionPane.YES_OPTION) {
    			this.start();
    		} else {
    		    this.dispose();
    		}
    	}
    	else if (p.pat()) {
    		int choice = JOptionPane.showOptionDialog(boardPanel, "Echec et pat ! Pas de gangnant !", "Rejouer ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Oui", "Non"}, "Non");
    		if (choice == JOptionPane.YES_OPTION) {
    			this.start();
    		} else {
    		    this.dispose();
    		}
    	}
    }
    
    /**
     * Cette méthode permet de démarrer l'application en initialisant la fenêtre avec deux boutons, pour le mode "Echec" et "Grant Acedrex".
     * Lorsqu'un bouton est cliqué, la méthode "drawBoard" est appelée avec le nombre de cases correspondant au mode choisi.
    */
    public void start() {
    	this.boardPanel.removeAll();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        JLabel welcomeGame = new JLabel("Bienvenue ! Choississez un mode de jeu !");
        welcomeGame.setHorizontalAlignment(JLabel.CENTER);
        JButton normalModeButton = new JButton("Echec");
        JButton grantAcedrexModeButton = new JButton("Grant Acedrex");
        setTitle("Accueil");
        setIconImage(new ImageIcon(getClass().getResource("/res/blackPawn.png")).getImage());
        boardPanel.setLayout(new GridLayout(3, 1));
        boardPanel.add(welcomeGame);
        boardPanel.add(normalModeButton);
        boardPanel.add(grantAcedrexModeButton);
        getContentPane().add(boardPanel, BorderLayout.CENTER);
        setVisible(true);
        normalModeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	setTitle("Echec");
            	drawBoard(8);
            }
         });

         grantAcedrexModeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               setVisible(false);
               setTitle("Grant Acedrex");
               drawBoard(12);
            }
         });
    }
    
    /**
     * Cette méthode permet de dessiner le plateau "Echec" ou "Grant Acedrex" selon le nombre de cases passées en paramètre.
     * @param numberCase nombre de cases pour la création du plateau
    */
    public void drawBoard(int numberCase) {
    	p = new Board(numberCase);
    	this.boardSquares = new Cases[p.getNumberCases()][p.getNumberCases()];
    	p.initBoard();
    	boardPanel.removeAll();
        setSize(numberCase*75, numberCase*75);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        boardPanel.setLayout(new GridLayout(p.getNumberCases(), p.getNumberCases()));
        getContentPane().add(boardPanel);
    	for (int row = 0; row < p.getNumberCases(); row++) {
            for (int col = 0; col < p.getNumberCases(); col++) {
                boardSquares[row][col] = new Cases(p.getCases()[row][col], new Point(row,col));
                if (boardSquares[row][col].getCase().getCurrentPiece() != null) {
                    JLabel label = new JLabel();
                    label.setIcon(boardSquares[row][col].getCase().getCurrentPiece().getImage());
                    label.setHorizontalAlignment(JLabel.CENTER);
                    boardSquares[row][col].add(label);
                }
                boardPanel.add(boardSquares[row][col]);
                boardSquares[row][col].setBackground(boardSquares[row][col].getInitialColor());
            }
        }
    	setVisible(true);
    }
    
    
    /**
     * Renvoie la case correspondante au point donné en paramètre.
     * @param point le point correspondant à la case recherchée.
     * @return la case correspondante au point donné.
    */
    public Cases getCasesByPoint(Point point) {
    	Cases c = null;
    	for (int i = 0; i< p.getNumberCases(); i++) {
    		for (int j = 0; j< p.getNumberCases(); j++) {
    			if (boardSquares[i][j].getPoint().equals(point)) {
    				c = boardSquares[i][j];
    			}
    		}
    	}
    	return c;
    }
    
    /**
     * Permet la promotion d'un pion en une autre pièce lorsqu'il atteint la dernière ligne du plateau.
     * @param point le point correspondant à la case où le pion a atteint la dernière ligne.
    */
    public void promotion(Point point) {
    	Piece piece = getCasesByPoint(point).getCase().getCurrentPiece();
    	if ((piece instanceof Pawn && point.x == 0 && piece.isJ1()) || (piece instanceof Pawn && point.x == p.getNumberCases()-1 && !piece.isJ1())) {
    		Piece newPiece = null;
    		if (this.p.getNumberCases() == 8) {
                ImageIcon[] options = {piece.isJ1()?new ImageIcon(getClass().getResource("/res/whiteBishop.png")):new ImageIcon(getClass().getResource("/res/blackBishop.png")), piece.isJ1()?new ImageIcon(getClass().getResource("/res/whiteRook.png")):new ImageIcon(getClass().getResource("/res/blackRook.png")), piece.isJ1()?new ImageIcon(getClass().getResource("/res/whiteKnight.png")):new ImageIcon(getClass().getResource("/res/blackKnight.png")), piece.isJ1()?new ImageIcon(getClass().getResource("/res/whiteQueen.png")):new ImageIcon(getClass().getResource("/res/blackQueen.png"))};
                int choice = JOptionPane.showOptionDialog(this,
                        "Choisissez la pièce pour promouvoir le pion:",
                        "Promotion de pion",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null, options, options[0]);
                switch (choice) {
                	case 0:
                		newPiece = new Bishop(piece.isJ1(), point, piece.isJ1()?new ImageIcon(getClass().getResource("/res/whiteBishop.png")):new ImageIcon(getClass().getResource("/res/blackBishop.png")));
                		break;
                    case 1:
                        newPiece = new Rook(piece.isJ1(), point, piece.isJ1()?new ImageIcon(getClass().getResource("/res/whiteRook.png")):new ImageIcon(getClass().getResource("/res/blackRook.png")));
                        break;
                    case 2:
                        newPiece = new Knight(piece.isJ1(), point, piece.isJ1()?new ImageIcon(getClass().getResource("/res/whiteKnight.png")):new ImageIcon(getClass().getResource("/res/blackKnight.png")));
                        break;
                    default :
                    	newPiece = new Queen(piece.isJ1(), point, piece.isJ1()?new ImageIcon(getClass().getResource("/res/whiteQueen.png")):new ImageIcon(getClass().getResource("/res/blackQueen.png")));
                        break;
                }
            }
            else {
            	switch(point.y) {
            		case 0, 11:
            			newPiece = new Rook(piece.isJ1(), point, piece.isJ1()?new ImageIcon(getClass().getResource("/res/whiteRook.png")):new ImageIcon(getClass().getResource("/res/blackRook.png")));
                    	break;
            		case 1, 10:
            			newPiece = new Lion(piece.isJ1(), point, piece.isJ1()?new ImageIcon(getClass().getResource("/res/wlion.png")):new ImageIcon(getClass().getResource("/res/blion.png")));
                		break;
            		case 2, 9:
            			newPiece = new Unicorn(piece.isJ1(), point, piece.isJ1()?new ImageIcon(getClass().getResource("/res/WUnicorn.png")):new ImageIcon(getClass().getResource("/res/BUnicorn.png")));
                		break;
            		case 3, 8:
            			newPiece = new Giraffe(piece.isJ1(), point, piece.isJ1()?new ImageIcon(getClass().getResource("/res/WGiraffe.png")):new ImageIcon(getClass().getResource("/res/BGiraffe.png")));
                		break;
            		case 4, 7:
            			newPiece = new Bishop(piece.isJ1(), point, piece.isJ1()?new ImageIcon(getClass().getResource("/res/WCrocodile.png")):new ImageIcon(getClass().getResource("/res/BCrocodile.png")));
                		break;
            		case 5, 6:
            			newPiece = new Gryphon(piece.isJ1(), point, piece.isJ1()?new ImageIcon(getClass().getResource("/res/wgryphon.png")):new ImageIcon(getClass().getResource("/res/bgryphon.png")));
                		break;
            	}
            }
    		p.getCases()[point.x][point.y].setCurrentPiece(newPiece);
            getCasesByPoint(point).paintNewPiece();
        }
    }
}
