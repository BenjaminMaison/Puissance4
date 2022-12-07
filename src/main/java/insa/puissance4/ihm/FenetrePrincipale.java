package insa.puissance4.ihm;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import insa.puissance4.business.Arbitre;
import insa.puissance4.business.GrilleJeu;
import insa.puissance4.business.IJoueur;
import insa.puissance4.business.Partie;
import insa.puissance4.business.joueur.JoueurIA;
import insa.puissance4.business.joueur.JoueurManuel;

public class FenetrePrincipale extends JFrame implements MouseListener, ActionListener {
	public static final int HAUTEUR_FENETRE = 700;
	public static final int LARGEUR_PANNEAU_LATERAL = 250;

	public static final int LARGEUR_PANNEAU_JEU = 800;
	public static final int LARGEUR_JEU = 700;
	public static final int LARGEUR_AUTOUR_JEU = (LARGEUR_PANNEAU_JEU - LARGEUR_JEU) / 2;

	public static final int HAUTEUR_JEU = 600;
	public static final int HAUTEUR_AUTOUR_JEU = (HAUTEUR_FENETRE - HAUTEUR_JEU) / 2;

	public static final int X_DEBUT_JEU = FenetrePrincipale.LARGEUR_PANNEAU_LATERAL + LARGEUR_AUTOUR_JEU;
	private static final int X_FIN_JEU = X_DEBUT_JEU + FenetrePrincipale.LARGEUR_JEU;
	public static final int Y_DEBUT_JEU = FenetrePrincipale.HAUTEUR_AUTOUR_JEU;
	private static final int Y_FIN_JEU = Y_DEBUT_JEU + FenetrePrincipale.HAUTEUR_JEU;

	private static final int TEMPS_REPONSE_MAX = 10;

	PanneauLateral panLat;
	PanneauJeu panJeu;
	Partie partie;

	int partieFinie = -1;
	boolean interactionPossible = true;

	Timer timer;
	int tempsEcoule = 0;
	FenetreAccueil fenetreAccueil;

	public FenetrePrincipale(FenetreAccueil fenetreAccueil, IJoueur j1, IJoueur j2) {
		this.fenetreAccueil = fenetreAccueil;
		setTitle("");
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		partie = new Partie(j1, j2);

		setSize(LARGEUR_PANNEAU_LATERAL + LARGEUR_PANNEAU_JEU, HAUTEUR_FENETRE);
		setLayout(null);
		this.setVisible(true);
		this.addMouseListener(this);

		panJeu = new PanneauJeu(partie);
		panJeu.setLocation(LARGEUR_PANNEAU_LATERAL, 0);

		panLat = new PanneauLateral(partie);
		panLat.setBounds(0, 0, FenetrePrincipale.LARGEUR_PANNEAU_LATERAL, FenetrePrincipale.HAUTEUR_FENETRE);
		panLat.setAlignmentX(0);
		panLat.setAlignmentY(0);

		initialisertimer();
		panLat.labelTemps.setVisible(fenetreAccueil.optionTimer.isSelected());

		this.add(panLat);
		this.add(panJeu);

		panLat.boutonQuitterJeu.addActionListener(this);
		panLat.boutonNouvellePartie.addActionListener(this);
		panLat.boutonReinitialiserTournoi.addActionListener(this);

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == panLat.boutonQuitterJeu) {
			reinitialiserTimer();
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (!interactionPossible) {
			// Aucun "nouveau clic" du joueur ne peut etre pris en compte
			JOptionPane.showMessageDialog(this, "L'ordinateur reflechit, merci de ne pas cliquer");
			return;
		}
		if (displayPartieFinie())
			return;
		int x = e.getX();
		int y = e.getY();
		IJoueur JActif = partie.getJoueurActif();
		Arbitre arb = partie.getArbitre();
		GrilleJeu grille = partie.getGrille();

		// Si on a clique "dans" une colonne du jeu
		if (x >= X_DEBUT_JEU && x <= X_FIN_JEU && y >= Y_DEBUT_JEU && y <= Y_FIN_JEU) {
			int positionX = x - X_DEBUT_JEU;
			// Les nouveaux cliques ne sont pas e prendre en compte car un choix est en
			// cours
			interactionPossible = false;
			int colonne = proposerColonne(positionX);
			deroulementTour(grille, colonne, arb, JActif, partie);
			JActif = partie.getJoueurActif(); // JActif est une variable donc il faut l'actualiser
			if (!(JActif instanceof JoueurManuel)) {
				// JOptionPane.showMessageDialog(this, "IA reflechit");
				colonne = JActif.choisirColonne(grille);
				deroulementTour(grille, colonne, arb, JActif, partie);
			}
			interactionPossible = true;
		} else {
			JOptionPane.showMessageDialog(this, "Merci de cliquer sur le jeu! (x=" + x + ",y=" + y + ")");
			interactionPossible = true;
		}
	}

	/**
	 * Permet d'afficher la raison pour laquelle la partie s'est finie (si la grille est pleine et/ou si elle a ete gagnee)
	 *
	 * @return boolean
	 */
	private boolean displayPartieFinie() {
		if (partieFinie >= 0) {
			if (partieFinie == 0)
				JOptionPane.showMessageDialog(this, "La partie est finie: la grille est pleine.");
			else {
				JOptionPane.showMessageDialog(this, "La partie a ete gagnee par le joueur " + partie.getJoueurActif().getNom());
				panLat.actualiseScores();
			}
			timer = null;
			return true;
		}
		return false;
	}

	/**
	 * Methode permettant et decrivant le deroulement type d'un tour, pour tout joueur
	 *
	 * @param grille
	 * @param colonne
	 * @param arb
	 * @param JActif
	 * @param p
	 */
	private void deroulementTour(GrilleJeu grille, int colonne, Arbitre arb, IJoueur JActif, Partie p) {
		if (arb.colonneDisponible(grille, colonne)) {
			int ligneActive = p.ajouterJeton(colonne);
			panJeu.paint(getGraphics());

			boolean coupGagnant = arb.coupGagnant(grille, ligneActive, colonne, JActif.getCode());
			if (coupGagnant) {
				partieFinie = JActif.getCode();
				JActif.incrementerScore();
				displayPartieFinie();
			} else if (arb.grillePleine(grille)) {
				partieFinie = 0;
				displayPartieFinie();
			} else {
				p.changementJoueur();
				actualiseJoueurSelectionne(p);
				reinitialiserTimer();
				panLat.paintImmediately(panLat.getBounds());
			}
		} else {
			System.out.println("Veuillez choisir une colonne disponible");
		}
	}

	private void actualiseJoueurSelectionne(Partie p) {
		if (p.getJoueurActif() == p.getJoueur1()) {
			panLat.boutonRTourJ1.setSelected(true);
			panLat.boutonRTourJ2.setSelected(false);
		} else {
			panLat.boutonRTourJ1.setSelected(false);
			panLat.boutonRTourJ2.setSelected(true);
		}
	}

	/**
	 * Determine la colonne proposee par le joueur selon l'endroit oe il a clique
	 *
	 * @param positionX : position graphique en X dans le jeu
	 * @return int (numero de colonne choisi)
	 */
	private static int proposerColonne(int positionX) {
		int largeurColonne = FenetrePrincipale.HAUTEUR_JEU / (GrilleJeu.NB_COLONNES - 1);
		int c = positionX / largeurColonne;
		return c;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		panJeu.paint(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer && estPartieFinie()) {
			tempsEcoule++;
			// compte e rebours e partir de TEMPS_REPONSE_MAX que l'on affiche dans le
			// label.
			int compteARebours = TEMPS_REPONSE_MAX + 1 - tempsEcoule;
			// ici une fois que le timer tick, on rajoute 1 au compte. Ce compte sera
			// ensuite affiche sur un Label
			panLat.labelTemps.setText("Timer:" + String.valueOf(compteARebours));
			int colonneAleatoire = (int) (Math.random() * 7);
			if (tempsEcoule > TEMPS_REPONSE_MAX) {
				// Une fois que le compteur atteint TEMPS_REPONSE_MAX (on peut aussi en faire
				// une variable pourcomplexifier le jeu si voulu) on choisit une
				// colonne
				// au hasard, le compteur repart de 0 et c'est au joueur suivant
				while (!partie.getArbitre().colonneDisponible(partie.grille, colonneAleatoire)) {
					colonneAleatoire = (int) (Math.random() * 7);
				}
				deroulementTour(partie.grille, colonneAleatoire, partie.getArbitre(), partie.getJoueurActif(), partie);
			}
		}
		if (e.getSource() == panLat.boutonQuitterJeu) {
			setVisible(false);
			fenetreAccueil.setVisible(true);
			fenetreAccueil.requestFocus();
		}
		if (e.getSource() == panLat.boutonNouvellePartie) {
			partieFinie = -1;
			partie.nouvellePartie();
			actualiseJoueurSelectionne(partie);
			repaint();
			JOptionPane.showMessageDialog(this, "La partie recommence avec " + partie.getJoueurActif().getNom());
			initialisertimer();
			if (partie.getJoueurActif() instanceof JoueurIA) {
				int colonne = partie.getJoueurActif().choisirColonne(partie.getGrille());
				deroulementTour(partie.getGrille(), colonne, partie.getArbitre(), partie.getJoueurActif(), partie);
			}
		}
		if (e.getSource() == panLat.boutonReinitialiserTournoi) {
			partieFinie = -1;
			partie.reinitialiserTournoi();
			actualiseJoueurSelectionne(partie);
			repaint();
			JOptionPane.showMessageDialog(this, "La tournoi recommence avec " + partie.getJoueurActif().getNom());
			initialisertimer();
		}
	}

	/**
	 * Permet de signaler que la partie est finie, via un code
	 * 
	 * @return code
	 */
	private boolean estPartieFinie() {
		return partieFinie == -1;
	}

	/**
	 * Permet d'initialiser le timer, si l'option timer a été choisie
	 */
	private void initialisertimer() {
		timer = new Timer(1000, this);
		if (fenetreAccueil.optionTimer.isSelected()) {
			timer.start();
		}
	}

	/**
	 * Permet de réinitialiser le timer en l'arretant et en le relancant, si l'option timer a été choisie
	 */
	private void reinitialiserTimer() {
		tempsEcoule = 0;
		if (fenetreAccueil.optionTimer.isSelected()) {
			timer.stop();
			timer.start();
		}
	}

}
