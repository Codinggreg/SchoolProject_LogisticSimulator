package Main.Modele;

import java.awt.*;
import java.util.Vector;

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
        Point position=this.get_position();
        position.translate(_vitesse.x,_vitesse.y);

    }

    @Override
    public void set_destination(Batiment _destination) {
        super.set_destination(_destination);
        this._vitesse=calculerVitesse(this.get_destination().get_position());
    }
    public Point calculerVitesse(Point destination){
        double distancex=destination.getX()-this.get_position().x;
        double distancey=destination.y-this.get_position().y;
        double distance=this.get_position().distance(destination);

        Point vitesse=new Point((int)(distancex/distance),(int)(distancey/distance));
        return vitesse;
    }
    public boolean arriveADestination() {
        return this.get_position().equals(this.get_destination().get_position());
    }

    public void setVitesse(int x, int y) {
        this._vitesse=new Point(x,y);
    }
    public void setVitesse(Point point){
        this._vitesse=point;
    }
}
