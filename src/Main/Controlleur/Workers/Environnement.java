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
		this._batiments=new HashMap<>();
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
            try {


                Thread.sleep(DELAI);
                if (!_batiments.isEmpty()) {
                    _batiments.values().stream().filter(p -> p instanceof Usine).forEach(p -> p.avancerTour(AVANCEMENT_TOUR));
                    _composantes.forEach(p -> p.avancer());
                    _composantes.forEach(p -> verifierCollisions(p));
                    _batiments.values().stream().filter(p -> p instanceof Usine).forEach(p -> produireComposante((Usine)p));
                }

            } catch (InterruptedException i){

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
		return null;
	}
	private void produireComposante(Usine usine){
	    Composante comp=usine.extraireSortie();
	    if(comp!=null){
	        _composantes.add(comp);
            firePropertyChange("NOTIFY",null,"Usine: {"+usine.getClass().getName()+"} Produit");
        }

    }
	private void verifierCollisions(Composante comp){
	    if(comp.arriveADestination())
        {

            Batiment destination=comp.get_destination();

            String message=String.format("Ajout de composante {%s} a l'usine {%s}",comp.get_type(),destination.getClass().getName());

            destination.gererAjout(comp.get_type(), AJOUT_QUANTITE);
            firePropertyChange("NOTIFY",null,message);
        }
    }

}