package Main.Modele;

import java.awt.*;
import java.util.Vector;

/**
 * Classe contenant les données propre à une composante
 */
public class Composante extends Unite{
    private Point _vitesse;
    private String _type;
    private boolean _peutEnlever;

    public boolean is_peutEnlever() {
        return _peutEnlever;
    }

    public void set_peutEnlever(boolean _peutEnlever) {
        this._peutEnlever = _peutEnlever;
    }

    public Composante(int _id, Point _position, String type) {
        super(_id, _position);
        this._type=type;
        this._vitesse = null;//TODO:Calculer Vitesse selon position de destination et origine;
        this._peutEnlever=false;
    }
    public String get_type(){
        return this._type;
    }

    /**
     * Augment la position de la composante selon la vitesse
     */
    public void avancer() {
        Point position=this.get_position();
        position.translate(_vitesse.x,_vitesse.y);

    }

    @Override
    public void set_destination(Batiment _destination) {
        super.set_destination(_destination);
        this._vitesse=calculerVitesse(this.get_destination().get_position());
    }

    /**
     * Calcule la vitesse du composante en calculant le vecteur unitaire du vecteur de la position vers la destination
     * @param destination la destination de la composante
     * @return vitesse
     */
    public Point calculerVitesse(Point destination){
        double distancex=destination.getX()-this.get_position().x;
        double distancey=destination.y-this.get_position().y;
        double distance=this.get_position().distance(destination);

        return new Point((int)(Math.round(distancex/distance)),(int)(Math.round(distancey/distance)));
    }

    /**
     * détermine si la composante est arrivé à sa destination
     * @return vrai si oui
     */
    public boolean arriveADestination() {


        double distance=this.get_position().distance(this.get_destination().get_position());
        return distance>-1&&distance<1;
    }

    public void setVitesse(int x, int y) {
        this._vitesse=new Point(x,y);
    }
}
