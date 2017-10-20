package Main.Controlleur.Workers;

import Main.Modele.Batiment;
import Main.Modele.Composante;
import Main.Modele.Entrepot;


import javax.swing.SwingWorker;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class Environnement extends SwingWorker<Object, String>{
	private boolean actif = true;
	private static final int DELAI = 100;
	private HashMap<Integer,Batiment> _batiments;
	private HashMap<Integer,Entrepot> _entrepots;
	private ArrayList<Composante> _composantes;

	public Environnement(HashMap<Integer, Batiment> batiments) {
		super();
		if(!batiments.isEmpty()) {
            this._batiments = batiments;
            this._entrepots = new HashMap<>();
            _batiments.forEach((key, value) -> {
                if (value instanceof Entrepot) {
                    _entrepots.put(key, (Entrepot) value);
                }
            });
        }
	}

	@Override
	protected Object doInBackground() throws Exception {
		while(actif) {
			Thread.sleep(DELAI);

			/**
			 * C'est ici que vous aurez � faire la gestion de la notion de tour.
			 */
			firePropertyChange("TEST", null, "Ceci est un test");
		}
		return null;
	}

}