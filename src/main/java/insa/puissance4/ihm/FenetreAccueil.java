package insa.puissance4.ihm;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import insa.puissance4.business.GrilleJeu;
import insa.puissance4.business.IJoueur;
import insa.puissance4.business.joueur.JoueurIA;
import insa.puissance4.business.joueur.JoueurManuel;

public class FenetreAccueil extends JFrame implements ActionListener {
	FenetrePrincipale fenetreJeu;
	JLabel labelTitre;

	JLabel labelChoixModeJeu;
	JRadioButton boutonRPvp;
	JRadioButton boutonRPve;
	ButtonGroup boutonModeDeJeu;

	JLabel labelChoixDifficulte;
	JRadioButton boutonRFacile;
	JRadioButton boutonRMoyen;
	JRadioButton boutonRDifficile;
	ButtonGroup boutonGDifficulte;
	int difficulte;

	JLabel labelNomJoueur;
	JTextField joueur1;
	JTextField joueur2;

	JButton boutonJouer;
	boolean estJouable;

	JCheckBox optionTimer;

	public FenetreAccueil() {
		super("Puissance 4 !");
		setSize(400, 500);
		setLocation(100, 100);
		setLayout(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		labelTitre = new JLabel("Puissance 4 !");
		labelTitre.setFont(new Font("Comic Sans MS", 1, 20));
		labelTitre.setBounds(getWidth() / 2 - 65, 10, 130, 20);

		labelChoixModeJeu = new JLabel("Choisissez le mode de jeu");
		labelChoixModeJeu.setFont(new Font("Comic Sans MS", 1, 15));
		labelChoixModeJeu.setBounds(20, 50, 200, 20);

		boutonRPvp = new JRadioButton("Joueur Vs Joueur");
		boutonRPvp.setBounds(20, 80, 200, 20);
		boutonRPvp.addActionListener(this);

		// boutonR... = bouton radio
		boutonRPve = new JRadioButton("Joueur Vs Ordinateur");
		boutonRPve.setBounds(20, 110, 200, 20);
		boutonRPve.addActionListener(this);

		boutonModeDeJeu = new ButtonGroup();
		boutonModeDeJeu.add(boutonRPve);
		boutonModeDeJeu.add(boutonRPvp);

		labelChoixDifficulte = new JLabel("Choisissez la difficulte");
		labelChoixDifficulte.setFont(new Font("Comic Sans MS", 1, 15));
		labelChoixDifficulte.setBounds(20, 140, 200, 20);
		labelChoixDifficulte.setVisible(false);

		boutonRFacile = new JRadioButton("Facile");
		boutonRFacile.setBounds(20, 170, 200, 20);
		boutonRFacile.addActionListener(this);

		boutonRMoyen = new JRadioButton("Moyen");
		boutonRMoyen.setBounds(20, 200, 200, 20);
		boutonRMoyen.addActionListener(this);

		boutonRDifficile = new JRadioButton("IMPOSSIBLE");
		boutonRDifficile.setBounds(20, 230, 200, 20);
		boutonRDifficile.addActionListener(this);

		// boutonG... = groupe de boutons
		boutonGDifficulte = new ButtonGroup();
		boutonGDifficulte.add(boutonRFacile);
		boutonGDifficulte.add(boutonRMoyen);
		boutonGDifficulte.add(boutonRDifficile);
		boutonRFacile.setVisible(false);
		boutonRMoyen.setVisible(false);
		boutonRDifficile.setVisible(false);

		labelNomJoueur = new JLabel();
		labelNomJoueur.setFont(new Font("Comic Sans MS", 1, 15));
		labelNomJoueur.setVisible(false);

		joueur1 = new JTextField();
		joueur1.setText("Joueur 1");
		joueur1.setVisible(true);

		joueur2 = new JTextField();
		joueur2.setVisible(true);
		joueur2.setText("Joueur 2");

		optionTimer = new JCheckBox("Option timer : temps limite pour jouer un coup !");
		optionTimer.setVisible(false);

		boutonJouer = new JButton("Jouer");
		boutonJouer.setBounds(getWidth() / 2 - 100, 360, 200, 50);
		boutonJouer.setFont(new Font("Comic Sans MS", 2, 25));
		boutonJouer.addActionListener(this);
		boutonJouer.setVisible(true);

		// Ordonnes comme selon l'affichage, de haut en bas
		this.add(labelTitre);
		this.add(labelChoixModeJeu);
		this.add(boutonRPvp);
		this.add(boutonRPve);
		this.add(labelChoixDifficulte);
		this.add(boutonRFacile);
		this.add(boutonRMoyen);
		this.add(boutonRDifficile);
		this.add(labelNomJoueur);
		this.add(joueur1);
		this.add(joueur2);
		this.add(optionTimer);
		this.add(boutonJouer);

		estJouable = false;
		boutonJouer.setEnabled(estJouable);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		estJouable = (boutonRPve.isSelected() && (boutonRFacile.isSelected() || boutonRMoyen.isSelected() || boutonRDifficile.isSelected()))
				|| (boutonRPvp.isSelected());
		boutonJouer.setEnabled(estJouable);

		// si le mode de jeu choisi est "Joueur VS Ordinateur"
		if (e.getSource() == boutonRPve) {
			labelChoixDifficulte.setVisible(true);
			boutonRFacile.setVisible(true);
			boutonRMoyen.setVisible(true);
			boutonRDifficile.setVisible(true);
			labelNomJoueur.setText("Veuillez entrer le nom du joueur");
			labelNomJoueur.setBounds(20, 260, 300, 20);
			labelNomJoueur.setVisible(true);

			joueur1.setBounds(20, 290, 200, 20);
			joueur1.setVisible(true);

			optionTimer.setBounds(20, 320, 300, 20);
			optionTimer.setVisible(true);
		}
		// si le mode dde jeu choisi est "Joueur VS Joueur"
		if (e.getSource() == boutonRPvp) {
			labelChoixDifficulte.setVisible(false);
			boutonRFacile.setVisible(false);
			boutonRMoyen.setVisible(false);
			boutonRDifficile.setVisible(false);

			labelNomJoueur.setText("Veuillez entrer le nom des joueurs");
			labelNomJoueur.setBounds(20, 140, 300, 20);
			labelNomJoueur.setVisible(true);

			joueur1.setBounds(20, 170, 200, 20);
			joueur1.setVisible(true);
			joueur2.setBounds(20, 200, 200, 20);
			joueur2.setVisible(true);

			optionTimer.setBounds(20, 230, 350, 20);
			optionTimer.setVisible(true);
		}
		// Si le joueur tente de lancer la partie, il faut verifier qu'il a bien entre tous les parametres necessaires au deroulement du jeu
		if (e.getSource() == boutonJouer) {
			if (boutonRPvp.isSelected()) {
				IJoueur j1 = new JoueurManuel(joueur1.getText(), GrilleJeu.CODE_J1);
				IJoueur j2 = new JoueurManuel(joueur2.getText(), GrilleJeu.CODE_J2);
				fenetreJeu = new FenetrePrincipale(this, j1, j2);
			} else {
				if (boutonRFacile.isSelected()) {
					difficulte = 1;
				} else if (boutonRMoyen.isSelected()) {
					difficulte = 4;
				} else {
					difficulte = 8;
				}
				IJoueur j1 = new JoueurManuel(joueur1.getText(), GrilleJeu.CODE_J1);
				IJoueur jIA = new JoueurIA(GrilleJeu.CODE_J2, difficulte);

				fenetreJeu = new FenetrePrincipale(this, j1, jIA);
				System.out.println(difficulte);

			}
			setVisible(false);
			fenetreJeu.setVisible(true);
			fenetreJeu.requestFocus();
		}
	}

	public static void main(String[] args) {
		FenetreAccueil f = new FenetreAccueil();
		f.repaint();
	}

}
