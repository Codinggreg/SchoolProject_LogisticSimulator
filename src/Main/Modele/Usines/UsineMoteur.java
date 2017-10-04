package Main.Modele.Usines;

import Main.Modele.Composantes.Moteur;
import Main.Modele.Usine;
import Main.Modele.Composante;

import java.util.Observable;
import java.util.Observer;

public class UsineMoteur extends Usine implements Observer{
    public UsineMoteur(int interval) {
        super(interval);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    protected Composante getTypeSortie() {
        return new Moteur();
    }

    @Override
    public void avancerTour(int int_prod) {

    }

}