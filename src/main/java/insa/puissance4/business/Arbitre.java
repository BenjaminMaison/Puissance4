package insa.puissance4.business;

/**
 * intelligence vérifiant le respect des regles et désignant le gagnant
 */

public class Arbitre {
	/**
	 * Définit le nombre d'autres jetons alignés avec celui que l'in vient de placer pour avoir un coup gagnant
	 */
	private static int NB_AUTRES_JETONS_ALIGNES = 3;

	public Arbitre() {

	}

	/**
	 * Vérifier que le coup est acceptable, c'est a dire que la colonne est disponible
	 *
	 * @param g
	 * @param colonne
	 * @return boolean
	 */
	public boolean colonneDisponible(GrilleJeu g, int colonne) {
		boolean colonneDispo = false;
		if (g.grille[0][colonne] == 0) {
			colonneDispo = true;
		}
		return colonneDispo;
	}

	/**
	 * Définit si le joueur a réalisé un coup gagnant
	 *
	 * @param g
	 * @param colonne
	 * @param ligne
	 * @param codeJActif
	 * @return boolean (partie gagnée ou pas)
	 */
	public boolean coupGagnant(GrilleJeu g, int ligne, int colonne, int codeJActif) {
		boolean partieGagnee = false;

		// tests accession méthodes : assezLigne, assezDiago et assezAntiDiago
		// --> permettent de ne pas effectuer systematiquement toutes les methodes "victoire..." mais de n'y acceder que si des conditions rendant possible
		// cette victoire sont remplies
		boolean assezLignes = (ligne <= 3);

		if (assezLignes) {
			if (victoireVerticale(g, ligne, colonne)) {
				return true;
			}
		}
		if (victoireHorinzontale(g, ligne, colonne)) {
			return (true);
		}

		if (assezDiago(ligne, colonne)) {
			if (victoireDiag(g, ligne, colonne, codeJActif)) {
				return (true);
			}
		}
		if (assezAntiDiago(ligne, colonne)) {
			if (victoireAntiDiag(g, ligne, colonne, codeJActif)) {
				return (true);
			}
		}

		return partieGagnee;
	}

	/**
	 * Determiner si un coup joue (pour une position (l,c) donnée) permet de gagner la partie en alignant 4 jetons (ou plus) de maniere horizontale
	 * 
	 * @param g
	 * @param colonne
	 * @param ligne
	 * @return
	 */
	public boolean victoireHorinzontale(GrilleJeu g, int ligne, int colonne) {
		boolean victoireH = false;
		int nbJetonAdjacents = 0;

		int j = colonne;
		while (j < GrilleJeu.NB_COLONNES - 1 && g.grille[ligne][j] == g.grille[ligne][j + 1] && nbJetonAdjacents <= NB_AUTRES_JETONS_ALIGNES) {
			nbJetonAdjacents++;
			j++;
		}

		if (nbJetonAdjacents != NB_AUTRES_JETONS_ALIGNES) {
			j = colonne + nbJetonAdjacents;
			nbJetonAdjacents = 0;
			while (j > 0 && g.grille[ligne][j] == g.grille[ligne][j - 1] && nbJetonAdjacents <= NB_AUTRES_JETONS_ALIGNES) {
				nbJetonAdjacents++;
				j--;
			}
		}

		if (nbJetonAdjacents >= NB_AUTRES_JETONS_ALIGNES) {
			victoireH = true;
		}

		return victoireH;
	}

	/**
	 * Determiner si un coup joue (pour une position (l,c) donnée) permet de gagner la partie en alignant 4 jetons (ou plus) de maniere verticale
	 * 
	 * @param g
	 * @param colonne
	 * @param ligne
	 * @return
	 */
	public boolean victoireVerticale(GrilleJeu g, int ligne, int colonne) {
		boolean victoireV = false;
		int nbJetonAdjacents = 0;

		int i = ligne;
		while (i < GrilleJeu.NB_LIGNES - 1 && g.grille[i][colonne] == g.grille[i + 1][colonne] && nbJetonAdjacents <= NB_AUTRES_JETONS_ALIGNES) {
			nbJetonAdjacents++;
			i++;
		}
		if (nbJetonAdjacents >= NB_AUTRES_JETONS_ALIGNES) {
			victoireV = true;
		}

		return victoireV;

	}

	/**
	 * Determiner au vu de l'emplacement d'un jeton la possibilite que ce coup soit un coup gagnant dans le sens diagonal
	 *
	 * @param l
	 * @param c
	 * @return
	 */
	private static boolean assezDiago(int l, int c) {
		boolean assezDiag = true;
		if ((c >= 4 && l <= c - 4) || (l >= 3 && c <= l - 3)) {
			return false;
		}
		return assezDiag;
	}

	/**
	 * Determiner au vu de l'emplacement d'un jeton la possibilite que ce coup soit un coup gagnant dans le sens antidiagonal
	 * 
	 * @param ligne
	 * @param colonne
	 * @return boolean
	 */
	public boolean assezAntiDiago(int ligne, int colonne) {
		boolean assezAntiDiag = true;
		if ((colonne >= 4 && ligne >= 5 - ligne + 4) || (ligne <= 2 && colonne <= 2 - ligne)) {
			return false;
		}
		return assezAntiDiag;
	}

	/**
	 * VictoireAntiDiag : détermine s'il y a des coups gagnants sur les antidiagonales pour une position (l,c) donnée et pour un joueur donné (Sens de bas
	 * gauche vers haut droit /)
	 *
	 * @param g
	 * @param l
	 * @param c
	 * @param joueur
	 * @return boolean
	 */
	public boolean victoireAntiDiag(GrilleJeu g, int l, int c, int joueur) {
		int i = l + 1;
		int j = c - 1;
		int k = 0;
		boolean condition = true;
		int points = 0;
		// parcours anti diagonal descendant
		while (g.estDansTab(i, j) && condition && points <= NB_AUTRES_JETONS_ALIGNES && k < 4) {
			if (g.grille[i][j] == joueur) {
				points++;
				i++;
				j--;
			} else {
				condition = false;
			}
			k++;
		}

		// parcours anti diagonal ascendant
		i = l - 1;
		j = c + 1;
		k = 0;
		condition = true;
		while (g.estDansTab(i, j) && condition && points <= NB_AUTRES_JETONS_ALIGNES && k < 4) {
			if (g.grille[i][j] == joueur) {
				points++;
				i--;
				j++;
			} else {
				condition = false;
			}
			k++;
		}

		return points >= NB_AUTRES_JETONS_ALIGNES;

	}

	/**
	 * victoireDiag : détermine s'il y a des coups gagnants sur les diagonales (Sens de haut gauche vers bas droit \)
	 *
	 * @param g
	 * @param l
	 * @param c
	 * @param joueur
	 * @return boolean
	 */
	public boolean victoireDiag(GrilleJeu g, int l, int c, int joueur) {
		int i = l + 1;
		int j = c + 1;
		int k = 0;
		boolean condition = true;
		int points = 0;
		// parcours diagonal descendant
		while (g.estDansTab(i, j) && condition && points <= NB_AUTRES_JETONS_ALIGNES && k < 4) {
			if (g.grille[i][j] == joueur) {
				points++;
				i++;
				j++;
			} else {
				condition = false;
			}
			k++;
		}

		// parcours diagonal ascendant
		i = l - 1;
		j = c - 1;
		condition = true;
		k = 0;
		// condition = g.grille[i][j] == g.grille[l][c];

		while (g.estDansTab(i, j) && condition && points <= NB_AUTRES_JETONS_ALIGNES && k < 4) {
			if (g.grille[i][j] == joueur) {
				points++;
				i--;
				j--;
			} else {
				condition = false;
			}
			k++;
		}
		return points >= NB_AUTRES_JETONS_ALIGNES;
	}

	/**
	 * Definit si la grille est pleine ou pas
	 *
	 * @param g
	 * @return boolean
	 */
	public boolean grillePleine(GrilleJeu g) {
		boolean grillePleine = true;
		for (int i = 0; i < g.grille[0].length; i++) {
			if (g.grille[0][i] == 0) {
				return (grillePleine = false);
			}
		}
		return grillePleine;

	}

}
