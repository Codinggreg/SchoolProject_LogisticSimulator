package Main.Modele;

public abstract class Usine extends Batiment{
    private int _quantiteEntreeMin;
    protected int _intervalProd;
    protected int _intervalCourant;

    /***
     *
     * @param interval interval de temps requis pour produire une composante
     */
    public Usine(int interval){
        this.set_intervalProd(interval);
        this.set_intervalCourant(0);
    }


    public int get_intervalProd() {
        return _intervalProd;
    }

    public void set_intervalProd(int _intervalProd) {
        this._intervalProd = _intervalProd;
    }

    public int get_intervalCourant() {
        return _intervalCourant;
    }

    public void set_intervalCourant(int _intervalCourant) {
        this._intervalCourant = _intervalCourant;
    }

    @Override
    protected boolean peutProduire() {
        return this._intervalCourant%this._intervalProd==0;
    }
}
