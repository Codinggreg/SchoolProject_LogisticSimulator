package Main.Modele;

import Main.Modele.Composantes.Avion;
import javafx.beans.Observable;

public class Entrepot extends Batiment{
    private int _capacite;


    public Entrepot(int interval){
        super(interval);
    }

    @Override
    public Composante getTypeSortie() {
        return new Avion();
    }

    @Override
    public void gererAjout(String classType, int quantiteAjoutee) {
        ajouterInventaire(classType,quantiteAjoutee);
        if(isFull())
        {
            setChanged();
            notifyObservers();
        }
    }
    private boolean isFull(){
        return false;
    }
    @Override
    protected boolean peutProduire() {
        return false;
    }
}
