package Main.Modele.Usines;

import Main.Modele.Composantes.Moteur;
import Main.Modele.Usine;
import Main.Modele.Composante;

import java.util.Observable;
import java.util.Observer;

public class UsineMoteur extends Usine{
    public UsineMoteur(int interval) {
        super(interval);
    }
    @Override
    public Composante getTypeSortie() {
        return new Moteur();
    }


}
