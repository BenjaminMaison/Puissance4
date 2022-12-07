package insa.puissance4.business;

public interface IJoueur {

	/**
	 * Renvoie la colonne choisit par le joueur pour cette grille de jeu
	 *
	 * @param g : grille de jeu
	 * @return
	 */
	int choisirColonne(GrilleJeu g);

	/**
	 * Retourne le score du joueur, a savoir le nombre de partie qu'il a gagné dans
	 * son tournoi
	 *
	 * @return score
	 */
	public int getScore();

	/**
	 * Permet de réinitialiser le score du joueur a 0
	 *
	 * @return int
	 */
	void reinitialiserScore();

	/**
	 * Permet d'incrementer le score du joueur
	 *
	 * @return
	 */
	void incrementerScore();

	/**
	 * Retourne le nom du joueur
	 *
	 * @return nom
	 */
	String getNom();

	/**
	 * Retourne le code associé au joueur
	 *
	 * @return code;
	 */
	int getCode();
}
