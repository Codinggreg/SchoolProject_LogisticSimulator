package Main.Modele.Usines;

import Main.Modele.Usine;
import Main.Modele.Composante;
import Main.Modele.Composantes.Aile;

public class UsineAile extends Usine {

    public UsineAile(int entree, int interval) {
        super(entree, interval);
    }

    @Override
    public Composante extraireSortie() {
        return new Aile();
    }
}
