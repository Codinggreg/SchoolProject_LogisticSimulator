package Main.Controlleur.Workers;

import Main.Controlleur.IVenteStrategie;
import Main.Controlleur.Ventes.VenteAleatoire;
import Main.Modele.Batiment;
import Main.Modele.Composante;
import Main.Modele.Entrepot;
import Main.Modele.Usine;
import Main.Modele.Usines.UsineMatiere;


import javax.swing.SwingWorker;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class Environnement extends SwingWorker<Object, String>{
    private static final int AVANCEMENT_TOUR = 1;
    private static final int AJOUT_QUANTITE = 1;
    private boolean actif = true;
	private static final int DELAI = 5;
	private HashMap<Integer,Batiment> _batiments;
    private ArrayList<Composante> _composantes;
    private IVenteStrategie _venteStrategie;
	public Environnement(HashMap<Integer, Batiment> batiments) {
		super();
		this._venteStrategie=new VenteAleatoire();
		this._batiments=new HashMap<>();
		if(!batiments.isEmpty()) {
            this._batiments = batiments;
            List<UsineMatiere> us=_batiments.values().stream().filter(p->p instanceof UsineMatiere).map(u->(UsineMatiere)u).collect(Collectors.toList());
            _batiments.values().stream().filter(p->p instanceof Entrepot).forEach(entrepot->{
                us.forEach(mat->entrepot.addObserver(mat));
            });
        }
        _composantes=new ArrayList<>();
	}


	@Override
	protected Object doInBackground() throws Exception {
        firePropertyChange("NOTIFY",null,"Environnement En Marche");
        while(actif) {
            try {


                Thread.sleep(DELAI);
                if (!_batiments.isEmpty()) {

                    avancerTour();
                    avancerComposantes();
                    vente();
                    production();
                    verifierCollisions(_composantes.iterator());

                }
                firePropertyChange("REPAINT",null,"");

            } catch (InterruptedException i){
                this.actif=false;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        firePropertyChange("NOTIFY",null,"Environnement Termine");
		return null;
	}

    private void production() {
        _batiments.values().stream().filter(p -> p instanceof Usine).forEach(p -> produireComposante((Usine)p));
    }

    private void vente() {
        if(_venteStrategie.vendre())
        {
            String message=String.format("Vente d'un avion utilisant la strategie {%s}",this._venteStrategie.getClass().getSimpleName());
            _batiments.values().stream().filter(p->p instanceof Entrepot).forEach((p->p.extraireSortie()));
            firePropertyChange("NOTIFY",null,message);
        }
    }

    private void avancerComposantes() {
        _composantes.forEach(p -> p.avancer());
    }

    private void avancerTour() {
        _batiments.values().stream().filter(p -> p instanceof Usine).forEach(p -> p.avancerTour(AVANCEMENT_TOUR));
    }

    private void produireComposante(Usine usine){
	    Composante comp=usine.extraireSortie();
	    if(comp!=null){
	        _composantes.add(comp);
	        firePropertyChange("NOTIFY",null,String.format("Production de composante {%s} par usine {%s}",comp.get_type(),usine.getClass().getSimpleName()));
            firePropertyChange("COMPONENT",null,comp);
        }

    }
	private void verifierCollisions(Iterator<Composante> ite){
	    while( ite.hasNext()){
	        Composante comp= ite.next();
            if(comp.arriveADestination())
            {

                Batiment destination=comp.get_destination();

                String message=String.format("Ajout de composante {%s} a l'usine {%s}",comp.get_type(),destination.getClass().getSimpleName());

                destination.gererAjout(comp.get_type(), AJOUT_QUANTITE);
                comp.set_peutEnlever(true);

                ite.remove();
                firePropertyChange("NOTIFY",null,message);
            }
        }

    }

    public void setStrategie(IVenteStrategie srategie) {
        this._venteStrategie = srategie;
    }
}