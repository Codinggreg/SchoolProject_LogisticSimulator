package Main.Modele;

public abstract class Usine extends Batiment{
    private int _quantiteEntreeMin;
    private int _intervalProd;
    private int _intervalCourant;

    /***
     *
     * @param entree quantite de composantes requises pour produire
     * @param interval interval de temps requis pour produire une composante
     */
    public Usine(int entree,int interval){
        this._intervalProd=interval;
        this._quantiteEntreeMin=entree;
        this._intervalCourant=0;
    }
}
