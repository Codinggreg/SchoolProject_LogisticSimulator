package Main.Modele.Usines;

import Main.Modele.Usine;
import Main.Modele.Composante;
import Main.Modele.Composantes.Aile;

public class UsineAile extends Usine {

    @Override
    public void SetQuantiteRequise(String s, int qty_min) {

    }

    public UsineAile(int interval) {
        super(interval);
    }

    @Override
    public Composante extraireSortie() {
        return new Aile();
    }

    @Override
    public void avancerTour(int int_prod) {

    }

}
