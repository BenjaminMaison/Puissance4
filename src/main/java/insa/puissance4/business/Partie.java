package insa.puissance4.business;

import insa.puissance4.business.joueur.JoueurIA;

public class Partie {
	public final static int GRILLE_PLEINE = 0;
	public final static int JOUEUR1_GAGNE = 1;
	public final static int JOUEUR2_GAGNE = 2;

	private final IJoueur joueur1;
	private final IJoueur joueur2;

	private IJoueur joueurActif;
	private IJoueur joueurDebutantPartie;

	private final Arbitre arbitre;

	public final GrilleJeu grille;

	/**
	 * * Constructeur en cas de mode de jeu "joueur VS joueur"
	 * 
	 * @param j1 : prendra les rouges
	 * @param j2 : prendra les verts
	 */
	public Partie(IJoueur j1, IJoueur j2) {
		joueur1 = j1;
		joueur2 = j2;
		joueurActif = joueur1;
		joueurDebutantPartie = joueur1;
		grille = new GrilleJeu();
		arbitre = new Arbitre();
	}

	/**
	 * Constructeur en cas de mode de jeu "joueur VS IA"
	 * 
	 * @param j: prendra les rouges
	 */
	public Partie(IJoueur j) {
		joueur1 = j;
		joueur2 = new JoueurIA(2, 7);
		joueurActif = joueur1;
		joueurDebutantPartie = joueur1;
		grille = new GrilleJeu();
		arbitre = new Arbitre();
	}

	/**
	 * Recommencer un tournoi entre 2 joueurs, dans les memes conditions: le score de chaque joueur est remis à 0 et le joueur 1 commence
	 */
	public void reinitialiserTournoi() {
		grille.vider();
		joueur1.reinitialiserScore();
		joueur2.reinitialiserScore();
		joueurActif = joueur1;
	}

	/**
	 * Lancer une nouvelle partie sans changer les parametres du tournoi mais en echangeant le tour des joueurs
	 */
	public void nouvellePartie() {
		grille.vider();
		echangerJoueurs();
	}

	/**
	 * Echanger les joueurs : le joueur ayant joue en 2eme a la partie precedente commence
	 */
	private void echangerJoueurs() {
		if (joueurDebutantPartie == joueur1) {
			joueurActif = joueur2;
			joueurDebutantPartie = joueurActif;
		} else {
			joueurActif = joueur1;
			joueurDebutantPartie = joueurActif;
		}
	}

	/**
	 * Ajouter un jeton dans la colonne c
	 *
	 * @param c : colonne
	 * @return ligne atteinte pour le jeton dans la colonne
	 */
	public int ajouterJeton(int c) {
		if (!arbitre.colonneDisponible(grille, c)) {
			return -1; // Si la colonne n'est pas disponible
		}
		if (joueurActif == joueur1) {
			return grille.ajouterJetonJoueur1(c);
		}
		return grille.ajouterJetonJoueur2(c);
	}

	/**
	 * Changer du joueur actif
	 */
	public void changementJoueur() {
		if (joueurActif == joueur1) {
			joueurActif = joueur2;
		} else {
			joueurActif = joueur1;
		}
	}

	public Arbitre getArbitre() {
		return arbitre;
	}

	public GrilleJeu getGrille() {
		return grille;
	}

	public IJoueur getJoueurActif() {
		return joueurActif;
	}

	public void setJoueurActif(IJoueur joueurActif) {
		this.joueurActif = joueurActif;
	}

	public IJoueur getJoueur1() {
		return joueur1;
	}

	public IJoueur getJoueur2() {
		return joueur2;
	}
}
