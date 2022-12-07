package insa.puissance4.business.joueur;

import java.util.ArrayList;
import java.util.Collections;

import insa.puissance4.business.GrilleJeu;

public class IA {

	int difficulte;

	public IA(int d_) {
		difficulte = d_;
	}

	public int meilleurCoup(int[][] grille, boolean tour) {
		int valeur;
		ArrayList<Integer> colonne = new ArrayList<Integer>();
		ArrayList<Integer> eval = new ArrayList<Integer>();
		ArrayList<int[][]> prochainePos = prochainePosition(grille, tour);

		for (int[][] pos : prochainePos) {
			if (pos != null) {
				eval.add(minMax(pos, !tour, -10000, 10000, difficulte)); // on évalue chacune des prochaines positions
			} else {
				eval.add(null);
			}
		}

		ArrayList<Integer> sortedEval = new ArrayList<>(); //tableau trié des évaluations

		for (Integer integer : eval) {
			if (integer != null) {
				sortedEval.add(integer);
			}
		}

		Collections.sort(sortedEval);

		if (tour) {
			valeur = sortedEval.get(sortedEval.size() - 1);
		} else {
			valeur = sortedEval.get(0);
		}

		for (int k = 0; k < eval.size(); k++) {
			if (eval.get(k) != null && eval.get(k) == valeur) {
				colonne.add(k);
			}
		}

		int index = (int) (colonne.size() * Math.random()); //parmi les coups de meilleurs évaluations, on en choisi un au hasard
		return colonne.get(index);
	}

	private int[][] copierGrille(int[][] grille) {
		int[][] copie = new int[grille.length][grille[0].length];

		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[0].length; j++) {
				copie[i][j] = grille[i][j];
			}
		}
		return copie;
	}

	private ArrayList<int[][]> prochainePosition(int[][] grille, boolean tour) {
		int codeJoueur;
		if (tour) {
			codeJoueur = GrilleJeu.CODE_J1;
		} else {
			codeJoueur = GrilleJeu.CODE_J2;
		}

		ArrayList<int[][]> resultat = new ArrayList<>();

		for (int i = 0; i < grille[0].length; i++) {
			int j = 0;
			int val = 0;
			while (j < grille.length && val == 0) {
				val = grille[j][i];
				j++;
				/*
				 * System.out.println("j:" + j); System.out.println("val:" + val);
				 */
			}

			if (j > 1) {
				int[][] prochaineGrille = copierGrille(grille);
				if (val == 0) {
					prochaineGrille[j - 1][i] = codeJoueur;
				} else {
					prochaineGrille[j - 2][i] = codeJoueur;
				}
				resultat.add(prochaineGrille);
			} else {
				resultat.add(null);
			}
		}
		return resultat;

	}

	private void majJoueur2(int[][] res, int count) {
		if (count == 2) {
			res[1][0]++;
		}
		if (count == 3) {
			res[1][1]++;
		}
		if (count >= 4) {
			res[1][2]++;
		}
	}

	private void majJoueur1(int[][] res, int count) {
		if (count == 2) {
			res[0][0]++;
		}
		if (count == 3) {
			res[0][1]++;
		}
		if (count >= 4) {
			res[0][2]++;
		}
	}

	private int[][] alignementVerti(int[][] grille) {
		int[][] res = new int[2][3];
		int countA = 0;
		int countB = 0;

		for (int i = 0; i < grille[0].length; i++) {
			for (int j = 0; j < grille.length; j++) {

				if (grille[j][i] == GrilleJeu.CODE_J1) {
					countA++;
					majJoueur2(res, countB);
					countB = 0;
				} else if (grille[j][i] == GrilleJeu.CODE_J2) {
					countB++;
					majJoueur1(res, countA);
					countA = 0;
				}
			}
			majJoueur1(res, countA);
			majJoueur2(res, countB);
			countA = 0;
			countB = 0;
		}
		return res;
	}

	private int[][] alignementHori(int[][] grille) {
		int[][] res = new int[2][3];
		int countA = 0;
		int countB = 0;

		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[0].length; j++) {

				if (grille[i][j] == GrilleJeu.CODE_J1) {
					countA++;
					majJoueur2(res, countB);
					countB = 0;
				} else if (grille[i][j] == GrilleJeu.CODE_J2) {
					countB++;
					majJoueur1(res, countA);
					countA = 0;
				} else {
					majJoueur2(res, countB);
					majJoueur1(res, countA);
					countA = 0;
					countB = 0;
				}
			}
			majJoueur2(res, countB);
			majJoueur1(res, countA);
			countA = 0;
			countB = 0;
		}
		return res;
	}

	private int[][] alignementDiag(int[][] grille) {
		int countA;
		int countB;

		int[][] res = new int[2][3];
		int l = 0;
		int c = 0;

		for (int i = 0; i <= 2; i++) {
			l = i;
			c = 0;

			countA = 0;
			countB = 0;

			while (GrilleJeu.estDansTab(grille, l, c)) {
				if (grille[l][c] == GrilleJeu.CODE_J1) {
					countA++;
					majJoueur2(res, countB);
					countB = 0;
				} else if (grille[l][c] == GrilleJeu.CODE_J2) {
					countB++;
					majJoueur1(res, countA);
					countA = 0;
				} else {
					majJoueur2(res, countB);
					majJoueur1(res, countA);
					countA = 0;
					countB = 0;
				}
				l++;
				c++;
			}
			majJoueur2(res, countB);
			majJoueur1(res, countA);
		}

		for (int i = 1; i <= 3; i++) {
			l = 0;
			c = i;
			countA = 0;
			countB = 0;

			while (GrilleJeu.estDansTab(grille, l, c)) {

				if (grille[l][c] == GrilleJeu.CODE_J1) {
					countA++;
					majJoueur2(res, countB);
					countB = 0;
				} else if (grille[l][c] == GrilleJeu.CODE_J2) {
					countB++;
					majJoueur1(res, countA);
					countA = 0;
				} else {
					majJoueur2(res, countB);
					majJoueur1(res, countA);
					countA = 0;
					countB = 0;
				}
				l++;
				c++;
			}
			majJoueur2(res, countB);
			majJoueur1(res, countA);
		}
		return res;
	}

	private int[][] alignementAntiDiag(int[][] grille) {
		int countA;
		int countB;

		int[][] res = new int[2][3];
		int l = 0;
		int c = 0;

		// on parcourt les 3 premieres anti-diagonales
		for (int i = 0; i <= 3; i++) {
			l = 5;
			c = i;
			countA = 0;
			countB = 0;

			while (GrilleJeu.estDansTab(grille, l, c)) {

				if (grille[l][c] == GrilleJeu.CODE_J1) {
					countA++;
					majJoueur2(res, countB);
					countB = 0;
				} else if (grille[l][c] == GrilleJeu.CODE_J2) {
					countB++;
					majJoueur1(res, countA);
					countA = 0;
				} else {
					majJoueur2(res, countB);
					majJoueur1(res, countA);
					countA = 0;
					countB = 0;
				}
				l--;
				c++;
			}
			majJoueur2(res, countB);
			majJoueur1(res, countA);
		}

		// on parcourt les 2 dernieres anti-diagonales
		for (int i = 4; i >= 3; i--) {
			l = i;
			c = 0;

			countA = 0;
			countB = 0;

			while (GrilleJeu.estDansTab(grille, l, c)) {

				if (grille[l][c] == GrilleJeu.CODE_J1) {
					countA++;
					majJoueur2(res, countB);
					countB = 0;
				} else if (grille[l][c] == GrilleJeu.CODE_J2) {
					countB++;
					majJoueur1(res, countA);
					countA = 0;
				} else {
					majJoueur2(res, countB);
					majJoueur1(res, countA);
					countA = 0;
					countB = 0;
				}
				l--;
				c++;
			}
			majJoueur2(res, countB);
			majJoueur1(res, countA);
		}
		return res;

	}

	private int evalFonction(int[][] grille) {
		int eval = 0;
		int[][] nbHori = alignementHori(grille);
		int[][] nbVerti = alignementVerti(grille);
		int[][] nbDiag = alignementDiag(grille);
		int[][] nbAntiDiag = alignementAntiDiag(grille);

		eval += nbHori[0][0] + 10 * nbHori[0][1] + nbVerti[0][0] + 10 * nbVerti[0][1] + 10 * nbDiag[0][1]
				+ 10 * nbAntiDiag[0][1];
		eval += nbDiag[0][0] + nbAntiDiag[0][0];
		eval -= (nbHori[1][0] + 10 * nbHori[1][1] + nbVerti[1][0] + 10 * nbVerti[1][1] + 10 * nbDiag[1][1]
				+ 10 * nbAntiDiag[1][1]);
		eval -= (nbDiag[1][0] + nbAntiDiag[1][0]);

		return eval;
	}

	private int GagneOuPerdu(int[][] grille) {

		int res = 0;
		int[][] nbHori = alignementHori(grille);
		int[][] nbVerti = alignementVerti(grille);
		int[][] nbDiag = alignementDiag(grille);
		int[][] nbAntiDiag = alignementAntiDiag(grille);
		if (nbHori[0][2] > 0 || nbVerti[0][2] > 0 || nbDiag[0][2] > 0 || nbAntiDiag[0][2] > 0) {
			res = 1;
		}
		if (nbHori[1][2] > 0 || nbVerti[1][2] > 0 || nbDiag[1][2] > 0 || nbAntiDiag[1][2] > 0) {
			res = -1;
		}
		return res;
	}

	private int minMax(int[][] grille, boolean joueur, int _alpha, int _beta, int profondeur) {
		int etat = GagneOuPerdu(grille);
		int valeur;
		if (joueur) {
			valeur = -10000;
		} else {
			valeur = 10000;
		}

		if (etat == 1) {
			return 1000 * (profondeur + 1); // on multiplie par la profondeur afin de prioriser les coups gagnants les proches
		}
		if (etat == -1) {
			return -1000 * (profondeur + 1);
		}

		if (profondeur == 0) {
			return evalFonction(grille);
		}

		ArrayList<int[][]> prochainePos = prochainePosition(grille, joueur);
		int alpha = _alpha;
		int beta = _beta;
		for (int[][] position : prochainePos) {
			if (position != null) {
				int nouvelleVal = minMax(position, !joueur, alpha, beta, profondeur - 1);
				if (joueur) {
					valeur = Math.max(nouvelleVal, valeur);
					alpha = Math.max(nouvelleVal, alpha);
				} else {
					valeur = Math.min(nouvelleVal, valeur);
					beta = Math.min(nouvelleVal, beta);
				}
				if (beta <= alpha) {
					break;
				}
			}
		}
		return valeur;
	}
}
