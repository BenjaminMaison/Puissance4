package insa.puissance4.business;

import org.testng.Assert;
import org.testng.annotations.Test;

import insa.puissance4.business.joueur.JoueurManuel;

public class PartieTest {

	@Test
	public void jouerTest() {
		IJoueur joueur1 = new JoueurManuel("Ali", GrilleJeu.CODE_J1);
		IJoueur joueur2 = new JoueurManuel("Benjamin", GrilleJeu.CODE_J2);
		Partie partie = new Partie(joueur1, joueur2);
		int resultat = jouer(partie);
		Assert.assertNotEquals(resultat, -1, "Resultat" + resultat);
		if (resultat == 0)
			System.out.println("Pas de gagnant, la grille est pleine");
		IJoueur joueurActif = partie.getJoueurActif();
		System.out.println("Le gagnant est le joueur " + joueurActif.getCode() + ":" + joueurActif.getNom());
	}

	// Méthode jouer pour test
	public int jouer(Partie partie) {
		boolean partieGagnee = false;
		boolean grillePleine = false;
		Arbitre arbitre = new Arbitre();
		while (!grillePleine && !partieGagnee) {
			IJoueur joueurActif = partie.getJoueurActif();
			int colonneActive = partie.getJoueurActif().choisirColonne(partie.getGrille());
			while (arbitre.colonneDisponible(partie.getGrille(), colonneActive) == false) {
				System.out.println("Colonne indispo, veuillez en choisir une autre");
				colonneActive = joueurActif.choisirColonne(partie.getGrille());
			}
			colonneActive = colonneActive - 1;
			int ligneActive = partie.ajouterJeton(colonneActive);
			partieGagnee = arbitre.coupGagnant(partie.getGrille(), ligneActive, colonneActive, joueurActif.getCode());
			System.out.print(partie.getGrille());
			if (partieGagnee) {
				return joueurActif.getCode();
			}
			partie.changementJoueur();
			grillePleine = arbitre.grillePleine(partie.getGrille());
		}
		if (grillePleine)
			return 0;
		return -1;
	}
}
