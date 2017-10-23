package Main.Modele;

import java.awt.*;

public class Entrepot extends Batiment{
    private int _capacite;
    private final String TYPE_COMPOSANTE="avion";

    public Entrepot(int interval, int id, Point position, int _capacite) {
        super(interval, id, position);
        this._capacite = _capacite;
    }

    @Override
    public Composante getComposante() {
        setChanged();
        notifyObservers();
        return new Composante(0,this.get_position(),"avion");
    }
    public boolean presquePlein(){
            return _capacite-getQuantiteInventaire(TYPE_COMPOSANTE)<=1;
    }

    @Override
    public int getStatut() {

        return (int)((double)getQuantiteInventaire(TYPE_COMPOSANTE)/this._capacite*100);
    }

    @Override
    public void gererAjout(String classType, int quantiteAjoutee) {
        ajouterInventaire(classType,quantiteAjoutee);

        setChanged();
        notifyObservers();

    }

    public boolean estPlein(){
        return this.getQuantiteInventaire(TYPE_COMPOSANTE)>=this._capacite;
    }
    @Override
    protected boolean peutExtraire() {
        return isInventaireSuffisant();
    }
}
