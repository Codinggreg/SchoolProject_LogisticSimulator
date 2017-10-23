package Main.Vue;

import Main.Modele.BatimentMetaData;
import Main.Vue.Icones.IconeBatiment;
import Main.Vue.Icones.IconeComposante;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.*;

public class FenetrePrincipale extends JFrame implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private static final String TITRE_FENETRE = "Laboratoire 1 : LOG121 - Simulation";
	private static final Dimension DIMENSION = new Dimension(700, 700);
	private PanneauPrincipal panneauPrincipal;

	public FenetrePrincipale() {
		panneauPrincipal = new PanneauPrincipal();
		MenuFenetre menuFenetre = new MenuFenetre();
		IconeComposante.chargerIcones();
		add(panneauPrincipal);
		add(menuFenetre, BorderLayout.NORTH);
		// Faire en sorte que le X de la fen�tre ferme la fen�tre
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(TITRE_FENETRE);
		setSize(DIMENSION);
		// Rendre la fen�tre visible
		setVisible(true);
		// Mettre la fen�tre au centre de l'�cran
		setLocationRelativeTo(null);
		// Emp�cher la redimension de la fen�tre
		setResizable(false);
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		switch(evt.getPropertyName()){
			case "NOTIFY":{
				System.out.println(evt.getNewValue());
				break;
			}
			case "ERROR":{
				System.out.println(evt.getNewValue());
				break;
			}
			case "COMPONENT":{
                IconeComposante ico=new IconeComposante(evt.getNewValue());
				panneauPrincipal.ajouterComposante(ico);
                break;
			}
            case "REPAINT":{
                repaint();
                break;
            }
		}
	}

    public void ajouterBatiments(HashMap<Integer, BatimentMetaData> metaData) {
        ArrayList<IconeBatiment> batiments=new ArrayList<>();
        for (HashMap.Entry<Integer, BatimentMetaData> entry : metaData.entrySet()) {
            BatimentMetaData data=entry.getValue();
            IconeBatiment temp=new IconeBatiment(data.Icones,data.Batiment);
            batiments.add(temp);
        }
        panneauPrincipal.setBatiments(batiments);
    }
    public void reset(){

	    panneauPrincipal.reset();
	    this.repaint();
    }
}
