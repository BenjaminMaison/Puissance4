package insa.puissance4.business.joueur;

import java.util.Scanner;

import insa.puissance4.business.GrilleJeu;

public class JoueurManuel extends AbstractJoueur {
	Scanner sc = new Scanner(System.in);

	public JoueurManuel(String n, int code) {
		super(n, code);
	}

	@Override
	public int choisirColonne(GrilleJeu _grilleJeu) {
		System.out.println("Veuillez rentrer un chiffre entre 1 et " + GrilleJeu.NB_COLONNES);
		int numColonne = sc.nextInt();
		return numColonne;
	}
}
