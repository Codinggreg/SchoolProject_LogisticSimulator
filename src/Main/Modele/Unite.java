package Main.Modele;


import java.awt.*;
import java.util.Observable;

public abstract class Unite extends Observable {
    private Integer _id;
    private Point _position;
    private Batiment _destination;

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Point get_position() {
        return _position;
    }

    public void set_position(Point _position) {
        this._position = _position;
    }
    public Batiment get_destination() {
        return _destination;
    }

    public void set_destination(Batiment _destination) {
        this._destination = _destination;
    }
    public Unite(int _id, Point _position) {
        this._id = _id;

        this._position = _position;
    }
}
