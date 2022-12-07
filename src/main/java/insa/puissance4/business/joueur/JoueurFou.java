package insa.puissance4.business.joueur;

import insa.puissance4.business.GrilleJeu;

public class JoueurFou extends AbstractJoueur {

	public JoueurFou(String n, int code) {
		super(n, code);
	}

	public int choisirColonne(GrilleJeu g) {
		return (int) Math.floor(Math.random() * 7);
	}

}
