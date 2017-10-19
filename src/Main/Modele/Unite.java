package Main.Modele;


import java.awt.*;
import java.util.Observable;

public abstract class Unite extends Observable {
    private int _id;
    private Point _position;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public Point get_position() {
        return _position;
    }

    public void set_position(Point _position) {
        this._position = _position;
    }

    public Unite(int _id, Point _position) {
        this._id = _id;

        this._position = _position;
    }
}
