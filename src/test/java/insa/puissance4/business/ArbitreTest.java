package insa.puissance4.business;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ArbitreTest {
	@Test
	public void testCoupValable() {
		Arbitre arbitre = new Arbitre();
		GrilleJeu grille = new GrilleJeu();
		Assert.assertTrue(arbitre.colonneDisponible(grille, 0));
		grille.ajouterJetonJoueur1(0);
		Assert.assertTrue(arbitre.colonneDisponible(grille, 0));
		grille.ajouterJetonJoueur2(0);
		Assert.assertTrue(arbitre.colonneDisponible(grille, 0));
		grille.ajouterJetonJoueur1(0);
		Assert.assertTrue(arbitre.colonneDisponible(grille, 0));
		grille.ajouterJetonJoueur2(0);
		Assert.assertTrue(arbitre.colonneDisponible(grille, 0));
		grille.ajouterJetonJoueur1(0);
		Assert.assertTrue(arbitre.colonneDisponible(grille, 0));
		grille.ajouterJetonJoueur2(0);
		Assert.assertFalse(arbitre.colonneDisponible(grille, 0));
	}

	@Test
	public void testGagnePartieHorizontale() {

		Arbitre arbitre = new Arbitre();
		GrilleJeu grille = new GrilleJeu();
		int ligne = grille.ajouterJetonJoueur1(0);
		Assert.assertFalse(arbitre.coupGagnant(grille, ligne, 0, 1));
		ligne = grille.ajouterJetonJoueur2(0);
		Assert.assertFalse(arbitre.coupGagnant(grille, ligne, 0, 2));
		ligne = grille.ajouterJetonJoueur1(1);
		Assert.assertFalse(arbitre.coupGagnant(grille, ligne, 1, 1));
		ligne = grille.ajouterJetonJoueur2(1);
		Assert.assertFalse(arbitre.coupGagnant(grille, ligne, 1, 2));
		ligne = grille.ajouterJetonJoueur1(2);
		Assert.assertFalse(arbitre.coupGagnant(grille, ligne, 2, 1));
		ligne = grille.ajouterJetonJoueur2(2);
		Assert.assertFalse(arbitre.coupGagnant(grille, ligne, 2, 2));
		ligne = grille.ajouterJetonJoueur1(3);
		Assert.assertTrue(arbitre.coupGagnant(grille, ligne, 3, 1), "le joueur 1 a gagné");

		testGagnePartieHorizontaleMilieu(arbitre);
		testGagnePartieHorizontaleAvant(arbitre);
	}

	private static void testGagnePartieHorizontaleAvant(Arbitre arbitre) {
		GrilleJeu grille = new GrilleJeu();
		//// @formatter:off
		grille.definirLigne(0, new int[] {  0,  0,  0,  0,  0,  0,  0 });
		grille.definirLigne(1, new int[] {  0,  0,  0,  0,  0,  0,  0 });
		grille.definirLigne(2, new int[] {  0,  0,  0,  0,  0,  0,  0 });
		grille.definirLigne(3, new int[] {  0,  0,  0,  0,  0,  0,  0 });
		grille.definirLigne(4, new int[] {  0,  2,  2,  2,  0,  0,  0 });
		grille.definirLigne(5, new int[] {  0,  1,  1,  1,  0,  0,  0 });
		// @formatter:on
		int ligne = grille.ajouterJetonJoueur1(0);
		Assert.assertTrue(arbitre.coupGagnant(grille, ligne, 0, 1), "le joueur 1 a gagné");
		Assert.assertTrue(arbitre.victoireHorinzontale(grille, ligne, 0), "le joueur 1 a gagné");
		Assert.assertTrue(arbitre.victoireHorinzontale(grille, ligne, 3), "le joueur 1 a gagné");
	}

	private static void testGagnePartieHorizontaleMilieu(Arbitre arbitre) {
		GrilleJeu grille = new GrilleJeu();
		//// @formatter:off
		grille.definirLigne(0, new int[] {  0,  0,  0,  0,  0,  0,  0 });
		grille.definirLigne(1, new int[] {  0,  0,  0,  0,  0,  0,  0 });
		grille.definirLigne(2, new int[] {  0,  0,  0,  0,  0,  0,  0 });
		grille.definirLigne(3, new int[] {  0,  0,  0,  0,  0,  0,  0 });
		grille.definirLigne(4, new int[] {  2,  2,  0,  2,  0,  0,  0 });
		grille.definirLigne(5, new int[] {  1,  1,  0,  1,  0,  0,  0 });
		// @formatter:on
		int ligne = grille.ajouterJetonJoueur1(2);
		Assert.assertTrue(arbitre.coupGagnant(grille, ligne, 2, 1), "le joueur 1 a gagné");
	}

	@Test
	public void testGagnePartieVerticale() {

		Arbitre arbitre = new Arbitre();
		GrilleJeu grille = new GrilleJeu();
		int ligne = grille.ajouterJetonJoueur1(0);
		Assert.assertFalse(arbitre.coupGagnant(grille, ligne, 0, 1));
		ligne = grille.ajouterJetonJoueur2(1);
		Assert.assertFalse(arbitre.coupGagnant(grille, ligne, 1, 2));
		ligne = grille.ajouterJetonJoueur1(0);
		Assert.assertFalse(arbitre.coupGagnant(grille, ligne, 0, 1));
		ligne = grille.ajouterJetonJoueur2(1);
		Assert.assertFalse(arbitre.coupGagnant(grille, ligne, 1, 2));
		ligne = grille.ajouterJetonJoueur1(0);
		Assert.assertFalse(arbitre.coupGagnant(grille, ligne, 0, 1));
		ligne = grille.ajouterJetonJoueur2(1);
		Assert.assertFalse(arbitre.coupGagnant(grille, ligne, 1, 2));
		ligne = grille.ajouterJetonJoueur1(0);
		Assert.assertTrue(arbitre.coupGagnant(grille, ligne, 0, 1), "le joueur 1 a gagné");
		Assert.assertTrue(arbitre.victoireVerticale(grille, ligne, 0), "le joueur 1 a gagné");
	}

	@Test
	public void testGrillePleine() {
		Arbitre arbitre = new Arbitre();
		GrilleJeu grille = new GrilleJeu();
		//// @formatter:off
		grille.definirLigne(0, new int[] {  2,  2,  1,  1,  2,  2,  1 });
		grille.definirLigne(1, new int[] {  1,  1,  2,  2,  1,  1,  2 });
		grille.definirLigne(2, new int[] {  2,  2,  1,  1,  2,  2,  1 });
		grille.definirLigne(3, new int[] {  1,  1,  2,  2,  1,  1,  2 });
		grille.definirLigne(4, new int[] {  2,  2,  1,  2,  2,  2,  1 });
		grille.definirLigne(5, new int[] {  1,  1,  2,  1,  1,  1,  2 });
		// @formatter:on
		Assert.assertTrue(arbitre.grillePleine(grille));
	}

	@Test
	/**
	 * Sens de bas gauche vers haut droit /
	 */
	public void testPartieGagneAntiDiagonale() {
		Arbitre arbitre = new Arbitre();
		GrilleJeu grille = new GrilleJeu();
		//// @formatter:off
		grille.definirLigne(0, new int[] { 0, 0, 0, 0, 0, 0, 0 });
		grille.definirLigne(1, new int[] { 1, 0, 2, 0, 0, 2, 1 });
		grille.definirLigne(2, new int[] { 2, 0, 1, 0, 0, 2, 2 });
		grille.definirLigne(3, new int[] { 1, 1, 2, 1, 1, 1, 2 });
		grille.definirLigne(4, new int[] { 2, 2, 1, 2, 2, 2, 1 });
		grille.definirLigne(5, new int[] { 1, 1, 2, 1, 1, 1, 2 });
		// @formatter:on
		Assert.assertEquals(partieGagne(grille, arbitre), GrilleJeu.CASE_VIDE);
		int ligne = grille.ajouterJetonJoueur1(4);
		Assert.assertTrue(arbitre.victoireAntiDiag(grille, ligne, 4, GrilleJeu.CODE_J1));
		Assert.assertTrue(arbitre.victoireAntiDiag(grille, 5, 1, GrilleJeu.CODE_J1));
	}

	/**
	 * On teste que 4 jetons alignes \ permettent la victoires
	 */
	@Test
	public void testGagnePartieDiagonale() {
		Arbitre arbitre = new Arbitre();
		GrilleJeu grille = new GrilleJeu();
		//// @formatter:off
		grille.definirLigne(0, new int[] {  0,  0,  0,  0,  0,  0,  0 });
		grille.definirLigne(1, new int[] {  0,  0,  0,  0,  0,  0,  0 });
		grille.definirLigne(2, new int[] {  0,  0,  1,  0,  0,  0,  0 });
		grille.definirLigne(3, new int[] {  0,  2,  2,  1,  0,  0,  0 });
		grille.definirLigne(4, new int[] {  0,  2,  2,  1,  1,  0,  0 });
		grille.definirLigne(5, new int[] {  0,  2,  1,  2,  1,  0,  0 });
		// @formatter:on
		int colonneGagnante = 5;
		int ligne = grille.ajouterJetonJoueur1(colonneGagnante);
		Assert.assertTrue(arbitre.coupGagnant(grille, ligne, colonneGagnante, GrilleJeu.CODE_J1),
				"le joueur 1 a gagné");
		Assert.assertTrue(arbitre.victoireDiag(grille, ligne, colonneGagnante, GrilleJeu.CODE_J1));
		Assert.assertTrue(arbitre.victoireDiag(grille, 2, 2, GrilleJeu.CODE_J1));
		Assert.assertTrue(arbitre.victoireDiag(grille, 3, 3, GrilleJeu.CODE_J1));
		Assert.assertTrue(arbitre.victoireDiag(grille, 4, 4, GrilleJeu.CODE_J1));
	}

	/**
	 * On teste que 5 jetons alignes \ permettent la victoires
	 */
	@Test
	public void testGagnePartieDiagonale2() {
		Arbitre arbitre = new Arbitre();
		GrilleJeu grille = new GrilleJeu();
		//// @formatter:off
		grille.definirLigne(0, new int[] {  0,  0,  0,  0,  0,  0,  0 });
		grille.definirLigne(1, new int[] {  1,  0,  0,  0,  0,  0,  0 });
		grille.definirLigne(2, new int[] {  2,  1,  0,  2,  0,  0,  0 });
		grille.definirLigne(3, new int[] {  1,  2,  0,  2,  0,  0,  0 });
		grille.definirLigne(4, new int[] {  2,  1,  1,  1,  0,  0,  0 });
		grille.definirLigne(5, new int[] {  1,  2,  2,  2,  1,  0,  0 });
		// @formatter:on
		int ligne = grille.ajouterJetonJoueur1(2);
		Assert.assertTrue(arbitre.coupGagnant(grille, ligne, 2, 1), "le joueur 1 a gagné");
		Assert.assertTrue(arbitre.victoireDiag(grille, 1, 0, GrilleJeu.CODE_J1));
		Assert.assertTrue(arbitre.victoireDiag(grille, 2, 1, GrilleJeu.CODE_J1));
		Assert.assertTrue(arbitre.victoireDiag(grille, 3, 2, GrilleJeu.CODE_J1));
		Assert.assertTrue(arbitre.victoireDiag(grille, 4, 3, GrilleJeu.CODE_J1));
		Assert.assertTrue(arbitre.victoireDiag(grille, 5, 4, GrilleJeu.CODE_J1));
	}

	/**
	 * Victoire avec 5 jetons alignés (Un coup est gagant c+quand il y a 4 jetons
	 * jetons alignés OU PLUS)
	 */
	@Test
	public void testCasReel() {
		Arbitre arbitre = new Arbitre();
		GrilleJeu grille = new GrilleJeu();
		//// @formatter:off
		grille.definirLigne(0, new int[] {  0,  0,  0,  0,  0,  0,  0 });
		grille.definirLigne(1, new int[] {  0,  0,  0,  2,  0,  1,  0 });
		grille.definirLigne(2, new int[] {  0,  0,  1,  2,  0,  1,  0 });
		grille.definirLigne(3, new int[] {  0,  2,  2,  1,  0,  2,  0 });
		grille.definirLigne(4, new int[] {  0,  1,  1,  1,  0,  1,  0 });
		grille.definirLigne(5, new int[] {  0,  2,  2,  1,  2,  2,  2 });
		// @formatter:on
		int colonneGagnante = 4;
		int ligne = grille.ajouterJetonJoueur1(colonneGagnante);
		Assert.assertTrue(arbitre.coupGagnant(grille, ligne, colonneGagnante, GrilleJeu.CODE_J1));
	}

	/**
	 * Définit si la partie est gagnée ou pas après le dernier coup joué
	 *
	 * @param g
	 * @param colonne
	 * @param ligne
	 * @param codeJActif
	 * @return status de la partie
	 */
	public int partieGagne(GrilleJeu g, Arbitre arbitre) {
		for (int i = 0; i < GrilleJeu.NB_LIGNES; i++)
			for (int j = 0; j < GrilleJeu.NB_COLONNES; j++) {
				if (g.grille[i][j] == GrilleJeu.CODE_J1 && arbitre.coupGagnant(g, i, j, GrilleJeu.CODE_J1)) {
					return GrilleJeu.CODE_J1;
				}
				if (g.grille[i][j] == GrilleJeu.CODE_J2 && arbitre.coupGagnant(g, i, j, GrilleJeu.CODE_J2))
					return GrilleJeu.CODE_J2;
			}
		return GrilleJeu.CASE_VIDE;

	}
}
