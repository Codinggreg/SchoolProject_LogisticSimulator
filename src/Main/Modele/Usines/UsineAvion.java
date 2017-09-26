package Main.Modele.Usines;

import Main.Modele.Composantes.Avion;
import Main.Modele.Composante;
import Main.Modele.Usine;

public class UsineAvion extends Usine {
    public UsineAvion(int entree, int interval) {
        super(entree, interval);
    }

    @Override
    public Composante extraireSortie() {
        return new Avion();
    }
}
