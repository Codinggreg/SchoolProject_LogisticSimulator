package Main.Vue;

import Main.Controlleur.Actions.ChangerStrategieAction;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

/**
 * Panneau permettant de choisir la stratégie de vente
 */
public class PanneauStrategie extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanneauStrategie() {

		ButtonGroup groupeBoutons = new ButtonGroup();
		JRadioButton strategie1 = new JRadioButton("Vente Aléatoire");
		strategie1.setActionCommand(ChangerStrategieAction.TypeStrategie.Aleatoire.toString());
		JRadioButton strategie2 = new JRadioButton("Vente Intervale");
		strategie2.setActionCommand(ChangerStrategieAction.TypeStrategie.Intervalles.toString());
		JButton boutonConfirmer = new JButton("Confirmer");
		strategie1.setSelected(true);

        groupeBoutons.add(strategie1);
        groupeBoutons.add(strategie2);

        boutonConfirmer.setAction(new ChangerStrategieAction(groupeBoutons,"Confirmer"));
        boutonConfirmer.addActionListener((ActionEvent e)->{SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();});
		JButton boutonAnnuler = new JButton("Annuler");

		boutonAnnuler.addActionListener((ActionEvent e) -> {
			// Fermer la fen�tre du composant
			SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
		});


		add(strategie1);
		add(strategie2);		
		add(boutonConfirmer);
		add(boutonAnnuler);

	}

}
