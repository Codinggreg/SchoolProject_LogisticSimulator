package Main.Modele;

public abstract class Usine extends Batiment{

    /***
     *
     * @param interval interval de temps requis pour produire une composante
     */
    public Usine(int interval){
        super(interval);
    }
    public boolean isBonTour(){
        int intervalCourant=this.getIntervalCourant();
        int intervalProd=this.getIntervalProd();
        return intervalCourant-intervalProd==0&&intervalCourant>0;
    }
    @Override
    protected boolean peutProduire() {
        if(isInventaireSuffisant()&&isBonTour()){
            return true;
        }
        return false;
    }
    @Override
    public void gererAjout(String classType, int quantiteAjoutee){
        this.ajouterInventaire(classType,quantiteAjoutee);
    }


}
