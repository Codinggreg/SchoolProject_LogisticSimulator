package Main.Modele.Usines;

import Main.Modele.Composantes.Avion;
import Main.Modele.Composante;
import Main.Modele.Usine;

public class UsineAvion extends Usine {
    public UsineAvion(int interval) {
        super(interval);
    }

    @Override
    public Composante extraireSortie() {
        return new Avion();
    }

    @Override
    public void avancerTour(int int_prod) {

    }

    @Override
    public void SetQuantiteRequise(String s, int qty_min) {

    }
}
