package insa.puissance4.business;

import org.testng.Assert;
import org.testng.annotations.Test;

import insa.puissance4.business.joueur.JoueurIA;

public class JoueurIATest {

	@Test
	public void testChoisirColonneTes1() {
		GrilleJeu grille = new GrilleJeu();
		JoueurIA jouerIA = new JoueurIA(2, 7);
		grille.definirLigne(0, new int[] { 0, 0, 0, 0, 0, 0, 0 });
		grille.definirLigne(1, new int[] { 1, 0, 2, 0, 0, 2, 1 });
		grille.definirLigne(2, new int[] { 2, 0, 1, 0, 0, 2, 2 });
		grille.definirLigne(3, new int[] { 1, 1, 2, 1, 1, 1, 2 });
		grille.definirLigne(4, new int[] { 2, 2, 1, 2, 2, 2, 1 });
		grille.definirLigne(5, new int[] { 1, 1, 2, 1, 1, 1, 2 });
		int colonneIA = jouerIA.choisirColonne(grille);
		Assert.assertEquals(colonneIA, 4);
	}

	@Test
	public void testChoisirColonneTes2() {
		GrilleJeu grille = new GrilleJeu();
		JoueurIA jouerIA = new JoueurIA(2, 7);
		grille.definirLigne(0, new int[] { 0, 0, 0, 0, 0, 0, 0 });
		grille.definirLigne(1, new int[] { 0, 0, 0, 0, 0, 0, 0 });
		grille.definirLigne(2, new int[] { 0, 0, 0, 0, 0, 0, 0 });
		grille.definirLigne(3, new int[] { 0, 0, 0, 0, 0, 0, 0 });
		grille.definirLigne(4, new int[] { 0, 0, 0, 0, 0, 0, 0 });
		grille.definirLigne(5, new int[] { 2, 0, 1, 1, 0, 0, 0 });
		int colonneIA = jouerIA.choisirColonne(grille);
		Assert.assertTrue(colonneIA == 1 || colonneIA == 4, "Choix realisé:" + colonneIA);
	}

	@Test
	public void testChoisirColonneTes3() {
		GrilleJeu grille = new GrilleJeu();
		JoueurIA jouerIA = new JoueurIA(2, 7);
		grille.definirLigne(0, new int[] { 0, 0, 0, 0, 0, 0, 0 });
		grille.definirLigne(1, new int[] { 0, 0, 0, 0, 0, 0, 0 });
		grille.definirLigne(2, new int[] { 2, 0, 0, 0, 0, 0, 0 });
		grille.definirLigne(3, new int[] { 2, 0, 0, 0, 0, 0, 0 });
		grille.definirLigne(4, new int[] { 1, 0, 0, 0, 0, 0, 0 });
		grille.definirLigne(5, new int[] { 2, 1, 1, 1, 0, 0, 0 });
		int colonneIA = jouerIA.choisirColonne(grille);
		Assert.assertTrue(colonneIA == 4, "Choix realisé:" + colonneIA);
	}

	@Test
	public void testChoisirColonneTes4() {
		GrilleJeu grille = new GrilleJeu();
		JoueurIA jouerIA = new JoueurIA(2, 7);
		grille.definirLigne(0, new int[] { 0, 0, 0, 0, 0, 0, 0 });
		grille.definirLigne(1, new int[] { 0, 0, 0, 0, 0, 0, 0 });
		grille.definirLigne(2, new int[] { 0, 0, 0, 2, 0, 0, 0 });
		grille.definirLigne(3, new int[] { 0, 0, 2, 2, 2, 1, 0 });
		grille.definirLigne(4, new int[] { 0, 1, 2, 1, 2, 1, 0 });
		grille.definirLigne(5, new int[] { 2, 1, 1, 2, 1, 1, 1 });
		int colonneIA = jouerIA.choisirColonne(grille);
		Assert.assertTrue(colonneIA == 1, "Choix realisé:" + colonneIA);
	}

}
