package Main.Modele.Usines;

import Main.Modele.Composantes.Metal;
import Main.Modele.Usine;
import Main.Modele.Composante;

public class UsineMatiere extends Usine {
    public UsineMatiere(int entree, int interval) {
        super(entree, interval);
    }

    @Override
    public Composante extraireSortie() {
        return new Metal();
    }

}
