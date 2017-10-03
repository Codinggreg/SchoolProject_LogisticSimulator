package Main.Modele.Usines;

import Main.Modele.Composantes.Metal;
import Main.Modele.Usine;
import Main.Modele.Composante;

public class UsineMatiere extends Usine {
    public UsineMatiere(int interval) {
        super(interval);
    }

    @Override
    public Composante extraireSortie() {
        return new Metal();
    }

    @Override
    public void avancerTour(int int_prod) {

    }

    @Override
    public void SetQuantiteRequise(String s, int qty_min) {

    }
}
