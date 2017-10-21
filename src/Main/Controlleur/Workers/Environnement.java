package Main.Controlleur.Workers;

import Main.Modele.Batiment;
import Main.Modele.Composante;
import Main.Modele.Entrepot;
import Main.Modele.Usine;


import javax.swing.SwingWorker;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class Environnement extends SwingWorker<Object, String>{
    public static final int AVANCEMENT_TOUR = 1;
    public static final int AJOUT_QUANTITE = 1;
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
        _composantes=new ArrayList<>();
	}
/*
* Avance tour
* Avance Composantes
* Vente
* Collisions
* Extrait composantes
* MiseAJourFenetre
*
* */
	@Override
	protected Object doInBackground() throws Exception {
		while(actif) {
			Thread.sleep(DELAI);
            _batiments.values().stream().filter(p->p instanceof Usine).forEach(p->p.avancerTour(AVANCEMENT_TOUR));
            _composantes.forEach(Composante::avancer);
            _composantes.forEach(p->verifierCollisions(p));
            //_batiments.values().stream().filter(p->p instanceof Entrepot).forEach(p->p.);
			/**
			 * C'est ici que vous aurez � faire la gestion de la notion de tour.
			 */
			firePropertyChange("TEST", null, "Ceci est un test");
		}
		return null;
	}
	private void verifierCollisions(Composante comp){
	    if(comp.arriveADestination())
        {
            Batiment destination=comp.get_destination();
            destination.gererAjout(comp.get_type(), AJOUT_QUANTITE);
        }
    }

}