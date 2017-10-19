package Main.Modele.Usines;

import Main.Modele.Composantes.Moteur;
import Main.Modele.Usine;
import Main.Modele.Composante;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class UsineMoteur extends Usine{
    public UsineMoteur(int interval, int id, Point position) {
        super(interval, id, position);
    }

    @Override
    public Composante getTypeSortie() {
        return new Moteur(0,this.get_position());
    }


}
