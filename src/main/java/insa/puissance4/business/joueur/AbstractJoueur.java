package insa.puissance4.business.joueur;

import insa.puissance4.business.IJoueur;

public abstract class AbstractJoueur implements IJoueur {

	protected String nom;
	// code
	protected int code;

	/** nombre de manches gagnées par le joueur au sein d'une meme partie */
	private int score;

	public AbstractJoueur(String nom, int code) {
		this.nom = nom;
		this.code = code;
	}

	/**
	 * Retourne le score du joueur, a savoir le nombre de partie qu'il a gagné dans
	 * son tournoi
	 *
	 * @return score
	 */
	@Override
	public int getScore() {
		return score;
	}

// On ne peut pas utiliser un Setter car les joueurs pourraient tricher. On doit donc créer des méthodes pour les cas ou on doit modifier le score du joueur

	/**
	 * Permet de réinitialiser le score du joueur a 0
	 *
	 * @return int
	 */
	@Override
	public void reinitialiserScore() {
		score = 0;
	}

	/**
	 * Permet d'incrementer le score du joueur
	 *
	 * @return
	 */
	@Override
	public void incrementerScore() {
		score++;
	}

	/**
	 * Retourne le nom du joueur
	 *
	 * @return nom
	 */
	@Override
	public String getNom() {
		return nom;
	}

	/**
	 * Retourne le code associé au joueur
	 *
	 * @return code
	 */
	@Override
	public int getCode() {
		return code;
	}
}