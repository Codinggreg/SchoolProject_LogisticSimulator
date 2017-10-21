package Main.Modele.Usines;

import Main.Modele.Composante;
import Main.Modele.Usine;

import java.awt.*;

public class UsineAvion extends Usine {
    public UsineAvion(int interval, int id, Point position) {
        super(interval, id, position);
    }

    @Override
    public Composante getComposante() {
        return new Composante(0,this.get_position(),"avion");
    }
}
