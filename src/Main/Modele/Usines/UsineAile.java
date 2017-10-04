package Main.Modele.Usines;

import Main.Modele.Usine;
import Main.Modele.Composante;
import Main.Modele.Composantes.Aile;

public class UsineAile extends Usine {


    public UsineAile(int interval) {
        super(interval);
    }
    public Composante getTypeSortie(){
        return new Aile();
    }


    @Override
    public void avancerTour(int int_prod) {

    }

}
