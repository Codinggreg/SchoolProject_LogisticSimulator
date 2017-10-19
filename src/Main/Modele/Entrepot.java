package Main.Modele;

import Main.Modele.Composantes.Avion;

import java.awt.*;

public class Entrepot extends Batiment{
    private int _capacite;


    public Entrepot(int interval, int id, Point position, int _capacite) {
        super(interval, id, position);
        this._capacite = _capacite;
    }

    @Override
    public Composante getTypeSortie() {
        return new Avion(0,this.get_position());
    }

    @Override
    public int getStatutInventaire() {

        return (int)((double)getQuantiteInventaire(Avion.class.toString())/this._capacite*100);
    }

    @Override
    public void gererAjout(String classType, int quantiteAjoutee) {
        ajouterInventaire(classType,quantiteAjoutee);
        if(estPlein())
        {
            setChanged();
            notifyObservers();
        }
    }
    public boolean estPlein(){
        return this.getQuantiteInventaire(Avion.class.toString())==this._capacite;
    }
    @Override
    protected boolean peutProduire() {
        return isInventaireSuffisant();
    }
}
