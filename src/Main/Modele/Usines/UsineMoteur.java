package Main.Modele.Usines;

import Main.Modele.Usine;
import Main.Modele.Composante;

import java.awt.*;

public class UsineMoteur extends Usine{
    public UsineMoteur(int interval, int id, Point position) {
        super(interval, id, position);
    }

    @Override
    public Composante getComposante() {
        return new Composante(0,this.get_position(),"moteur");
    }


}
