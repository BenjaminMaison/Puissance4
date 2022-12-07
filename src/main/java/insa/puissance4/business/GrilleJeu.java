package insa.puissance4.business;

public class GrilleJeu {

	public static final int CODE_J1 = 1;
	public static final int CODE_J2 = 2;
	public static final int CASE_VIDE = 0;

	public static final int NB_COLONNES = 7;
	public static final int NB_LIGNES = 6;
	public int[][] grille;

	public GrilleJeu() {
		grille = new int[NB_LIGNES][NB_COLONNES];
	}

	/**
	 * Renvoie le jeton situe a la position [l,c]
	 * 
	 * @param l
	 * @param c
	 * @return
	 */
	public int getJeton(int l, int c) {
		return grille[l][c];
	}

	/**
	 * Ajouter un jeton pour le joueur 1
	 * 
	 * @param c
	 * @return ligne ou le jeton a ete place
	 */
	public int ajouterJetonJoueur1(int c) {
		return placerJeton(c, CODE_J1);
	}

	/**
	 * Ajouter un jeton pour le joueur 2
	 * 
	 * @param c
	 * @return ligne ou le jeton a ete place
	 */
	public int ajouterJetonJoueur2(int c) {
		return placerJeton(c, CODE_J2);
	}

	/**
	 * A partir d'une colonne donnée et après avoir identifier le joueur, placer le jeton sur la case la plus basse libre de la colonne. Cela revient a
	 * changer le statut de la case dans la grille
	 * 
	 * @param c
	 * @param codeJoueur
	 * @return ligne ou le jeton a ete place, s'il a ete place, sinon un code signifiant une erreur
	 */
	private int placerJeton(int c, int codeJoueur) {
		for (int i = 0; i < NB_LIGNES; i++) {
			if (i == NB_LIGNES - 1 || grille[i + 1][c] != CASE_VIDE) {
				// j'ai trouvé la case a remplir
				grille[i][c] = codeJoueur;

				// renvoie la ligne où le jeton a été placé
				return i;
			}
		}
		// impossible de placer le jeton (cas d'erreur)
		return -1;
	}

	public boolean diagonalesPossibles() {
		return true;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < NB_LIGNES; i++) {
			for (int j = 0; j < NB_COLONNES; j++) {
				int valeur = grille[i][j];
				if (valeur >= 0)
					buffer.append(" ");
				buffer.append(valeur);
				buffer.append(" ");
			}
			buffer.append("\n");
		}
		return buffer.toString();
	}

	void definirLigne(int ligne, int[] values) {
		grille[ligne] = values;
	}

	/**
	 *
	 * @param Verifie si un jeton est dans le tableau (ici dans la grille)
	 * @param i       (ligne)
	 * @param j       (colonne)
	 * @return statut
	 */
	public boolean estDansTab(int i, int j) {
		return estDansTab(grille, i, j);
	}

	/**
	 *
	 * @param Verifie si un jeton est dans un tableau donne
	 * @param i       (ligne)
	 * @param j       (colonne)
	 * @return
	 */
	public static boolean estDansTab(int[][] grille, int i, int j) {
		return (i >= 0 && i < grille.length && j >= 0 && j < grille[0].length);
	}

	/**
	 * Verifie que le numero donne en parametre correspond au numero d'une colonne de notre grille
	 * 
	 * @param c
	 * @return
	 */
	public static boolean estColonnePossible(int c) {
		return 0 <= c && c < NB_COLONNES;
	}

	/**
	 * Vide la grille de tous ses jetons (pour ne laisser que des cases vides)
	 */
	public void vider() {
		for (int i = 0; i < NB_LIGNES; i++) {
			for (int j = 0; j < NB_COLONNES; j++) {
				grille[i][j] = CASE_VIDE;
			}
		}
	}

}
