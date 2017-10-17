package Main.Modele;

import Main.Modele.Composantes.Avion;

public class Entrepot extends Batiment{
    private int _capacite;


    public Entrepot(int interval, int capacite){
        super(interval);
        _capacite=capacite;
    }

    @Override
    public Composante getTypeSortie() {
        return new Avion();
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
