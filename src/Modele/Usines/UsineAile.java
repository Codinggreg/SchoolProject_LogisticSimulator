package Modele.Usines;

import Modele.Composante;
import Modele.Composantes.Aile;
import Modele.Usine;

public class UsineAile extends Usine {
    @Override
    public Composante getSortieProduction() {
        return new Aile();
    }
}
