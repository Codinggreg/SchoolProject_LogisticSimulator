package Modele.Usines;

import Modele.Composante;
import Modele.Composantes.Avion;
import Modele.Usine;

public class UsineAvion extends Usine {
    @Override
    public Composante getSortieProduction() {
        return new Avion();
    }
}
