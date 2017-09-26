package Modele.Usines;

import Modele.Composante;
import Modele.Composantes.Metal;
import Modele.Usine;

public class UsineMatiere extends Usine {
    @Override
    public Composante getSortieProduction() {
        return new Metal();
    }
}
