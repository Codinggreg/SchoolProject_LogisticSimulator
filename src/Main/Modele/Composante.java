package Main.Modele;

import java.awt.*;

public abstract class Composante extends Unite{
    private Point _vitesse;

    public Composante(int _id, Point _position) {
        super(_id, _position);
        this._vitesse = null;//TODO:Calculer Vitesse selon position de destination et origine;
    }
}
