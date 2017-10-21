package Main.Modele.Usines;

import Main.Modele.Usine;
import Main.Modele.Composante;

import java.awt.*;

public class UsineAile extends Usine {


    public UsineAile(int interval, int id, Point position) {
        super(interval, id, position);
    }

    public Composante getComposante(){
        return new Composante(0,this.get_position(),"aile");
    }

}
