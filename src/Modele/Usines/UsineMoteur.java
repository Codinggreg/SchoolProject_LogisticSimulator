package Modele.Usines;

import Modele.Composante;
import Modele.Composantes.Moteur;
import Modele.Usine;

import java.util.Observable;
import java.util.Observer;

public class UsineMoteur extends Usine implements Observer{
    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public Composante getSortieProduction() {
        return new Moteur();
    }
}
