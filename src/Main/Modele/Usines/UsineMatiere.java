package Main.Modele.Usines;

import Main.Modele.Usine;
import Main.Modele.Composante;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class UsineMatiere extends Usine implements Observer {
    public UsineMatiere(int interval, int id, Point position) {
        super(interval, id, position);
        this.setEnProduction(true);
    }

    @Override
    public Composante getComposante() {
        return new Composante(0,this.get_position(),"metal");
    }

    @Override
    protected void ajusterInventaire() {
        set_intervalCourant(0);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Observation");
    }
}
