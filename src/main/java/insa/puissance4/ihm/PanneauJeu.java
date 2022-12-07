package insa.puissance4.ihm;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import insa.puissance4.business.GrilleJeu;
import insa.puissance4.business.Partie;

public class PanneauJeu extends JPanel {
	private static final int LARGEUR_CASE = FenetrePrincipale.LARGEUR_JEU / GrilleJeu.NB_COLONNES;
	private static final int HAUTEUR_CASE = FenetrePrincipale.HAUTEUR_JEU / GrilleJeu.NB_LIGNES;
	Partie p;

	public PanneauJeu(Partie p) {
		super();
		this.p = p;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int positionX = FenetrePrincipale.X_DEBUT_JEU;
		int positionY = FenetrePrincipale.Y_DEBUT_JEU;

		int rayon = 40;

		int plateau[][] = p.grille.grille;

		for (int i = 0; i < GrilleJeu.NB_LIGNES; i++) {
			for (int j = 0; j < GrilleJeu.NB_COLONNES; j++) {

				int positionXRect = positionX + (j * LARGEUR_CASE);
				int positionYRect = positionY + (i * LARGEUR_CASE);
				int positionXCercle = positionXRect + (HAUTEUR_CASE - 2 * rayon) / 2;
				int positionYCercle = positionYRect + (HAUTEUR_CASE - 2 * rayon) / 2;

				g.setColor(Color.blue);
				g.fillRect(positionXRect, positionYRect, LARGEUR_CASE, HAUTEUR_CASE);

				if (plateau[i][j] == GrilleJeu.CODE_J2) {
					g.setColor(Color.red);
				} else if (plateau[i][j] == GrilleJeu.CODE_J1) {
					g.setColor(Color.green);
				} else {
					g.setColor(Color.white);
				}
				g.fillOval(positionXCercle, positionYCercle, 2 * rayon, 2 * rayon);
			}
		}
	}
}
