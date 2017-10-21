package Main.Modele;

import java.awt.*;

public class Composante extends Unite{
    private Point _vitesse;
    private String _type;

    public Composante(int _id, Point _position, String type) {
        super(_id, _position);
        this._type=type;
        this._vitesse = null;//TODO:Calculer Vitesse selon position de destination et origine;
    }
    public String get_type(){
        return this._type;
    }
    public void avancer() {
    }

    public boolean arriveADestination() {
        return false;
    }
}
