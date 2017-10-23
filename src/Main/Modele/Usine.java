package Main.Modele;

import java.awt.*;

public abstract class Usine extends Batiment{

    @Override
    public int getStatut() {
        return (int)((double)this.getIntervalCourant()/this.getIntervalProd()*100);
    }

    /***
     *
     * @param interval interval de temps requis pour produire une composante
     */
    public Usine(int interval,int id,Point position){
        super(interval,id,position);
    }
    public boolean isBonTour(){
        int intervalCourant=this.getIntervalCourant();
        int intervalProd=this.getIntervalProd();
        return intervalCourant>=intervalProd&&intervalCourant>0;
    }
    @Override
    protected boolean peutExtraire() {
        return isBonTour();
    }
    @Override
    public void gererAjout(String classType, int quantiteAjoutee){
        this.ajouterInventaire(classType,quantiteAjoutee);
    }


}
