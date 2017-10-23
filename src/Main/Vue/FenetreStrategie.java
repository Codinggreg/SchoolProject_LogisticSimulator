package Main.Vue;

import java.awt.Dimension;

import javax.swing.JFrame;

public class FenetreStrategie extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String TITRE_FENETRE = "Sélectionnez votre stratégie de vente";
	private static final Dimension DIMENSION = new Dimension(250, 100);

	public FenetreStrategie() {
		PanneauStrategie panneauStrategie = new PanneauStrategie();
		add(panneauStrategie);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle(TITRE_FENETRE);
		setSize(DIMENSION);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
}
