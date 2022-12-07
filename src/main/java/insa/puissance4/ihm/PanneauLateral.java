package insa.puissance4.ihm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import insa.puissance4.business.Partie;

public class PanneauLateral extends JPanel {

	private static final Font FONT = new Font("Comic Sans MS", 1, 16);

	private static final Color COULEUR_FOND = Color.yellow;

	Partie p;

	JRadioButton boutonRTourJ1;
	JRadioButton boutonRTourJ2;

	JLabel labelTemps;

	JLabel labelScore1;
	JLabel labelScore2;

	JButton boutonQuitterJeu;
	JButton boutonNouvellePartie;
	JButton boutonReinitialiserTournoi;

	public PanneauLateral(Partie p) {
		this.p = p;

		JLabel labelTitreJeu = new JLabel("Puissance 4");
		labelTitreJeu.setFont(FONT);
		labelTitreJeu.setLayout(null);
		labelTitreJeu.setBounds(10, 10, 200, 50);
		labelTitreJeu.setForeground(Color.black);

		JLabel labelTourJoueur = new JLabel("Tour du joueur");
		labelTourJoueur.setBounds(10, 100, 200, 30);
		labelTourJoueur.setFont(FONT);

		boutonRTourJ1 = new JRadioButton();
		boutonRTourJ1.setText(p.getJoueur1().getNom());
		boutonRTourJ1.setBounds(10, 130, 150, 30);
		boutonRTourJ1.setBackground(COULEUR_FOND);
		boutonRTourJ1.setSelected(true);

		boutonRTourJ2 = new JRadioButton();
		boutonRTourJ2.setText(p.getJoueur2().getNom());
		boutonRTourJ2.setBounds(10, 160, 150, 30);
		boutonRTourJ2.setBackground(COULEUR_FOND);

		JLabel labelJoueur1 = new JLabel();
		labelJoueur1.setBounds(20, 300, 100, 30);
		labelJoueur1.setFont(FONT);
		labelJoueur1.setText(p.getJoueur1().getNom());

		JLabel labelJoueur2 = new JLabel();
		labelJoueur2.setBounds(120, 300, 100, 30);
		labelJoueur2.setFont(FONT);
		labelJoueur2.setText(p.getJoueur2().getNom());

		labelScore1 = new JLabel();
		labelScore1.setBounds(60, 340, 100, 30);
		labelScore1.setFont(FONT);
		labelScore1.setText(Integer.toString(p.getJoueur1().getScore()));

		labelScore2 = new JLabel();
		labelScore2.setBounds(160, 340, 100, 30);
		labelScore2.setFont(FONT);
		labelScore2.setText(Integer.toString(p.getJoueur2().getScore()));

		labelTemps = new JLabel();
		labelTemps.setBounds(20, 400, 150, 30);
		labelTemps.setFont(FONT);
		labelTemps.setText("Timer");

		boutonNouvellePartie = new JButton("Nouvelle partie");
		boutonNouvellePartie.setBounds(20, 500, 150, 30);

		boutonReinitialiserTournoi = new JButton("Réinitialiser le tournoi");
		boutonReinitialiserTournoi.setBounds(20, 550, 150, 30);

		boutonQuitterJeu = new JButton("Quitter le jeu");
		boutonQuitterJeu.setBounds(20, 600, 150, 30);

		this.add(labelTitreJeu);
		this.add(labelTourJoueur);
		this.add(boutonRTourJ1);
		this.add(boutonRTourJ2);
		this.add(labelJoueur1);
		this.add(labelJoueur2);
		this.add(labelScore1);
		this.add(labelScore2);
		this.add(labelTemps);
		this.add(boutonQuitterJeu);
		this.add(boutonNouvellePartie);
		this.add(boutonReinitialiserTournoi);

		setBackground(Color.pink);
		setLayout(null);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		setBackground(COULEUR_FOND);
		boutonRTourJ1.setText(p.getJoueur1().getNom());
		boutonRTourJ2.setText(p.getJoueur2().getNom());
		actualiseScores();

		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D ligneVerticale = new Rectangle2D.Float(110, 300, 3, 70);
		g2.fill(ligneVerticale);
		Rectangle2D ligneHorizontale = new Rectangle2D.Float(10, 330, 200, 3);
		g2.fill(ligneHorizontale);
	}

	public void actualiseScores() {
		labelScore1.setText(Integer.toString(p.getJoueur1().getScore()));
		labelScore2.setText(Integer.toString(p.getJoueur2().getScore()));
	}

}
