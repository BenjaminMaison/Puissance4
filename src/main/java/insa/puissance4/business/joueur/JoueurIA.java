package insa.puissance4.business.joueur;

import insa.puissance4.business.GrilleJeu;

public class JoueurIA extends AbstractJoueur {
	private final IA ia;

	public JoueurIA(int code, int strength) {
		super("Ordinateur", code);
		ia = new IA(strength);
	}

	@Override
	public int choisirColonne(GrilleJeu _grilleJeu) {
		return this.ia.meilleurCoup(_grilleJeu.grille, false);
	}
}
