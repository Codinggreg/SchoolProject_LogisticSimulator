package Main.Modele;

public abstract class Usine extends Batiment{
    private int _quantiteEntreeMin;
    private int _intervalProd;
    private int _intervalCourant;

    /***
     *
     * @param interval interval de temps requis pour produire une composante
     */
    public Usine(int interval){
        this._intervalProd=interval;
        this._intervalCourant=0;
    }

    /***
     *
     * @param s type de composante
     * @param qty_min quantite requis pour produire en sortie
     */
    public abstract void SetQuantiteRequise(String s, int qty_min);
    
    private class Production {
        private int _quantiteRequise;
        private int _quantiteEnInventaire;

        /***
         *
         * @param _quantiteRequise Quantite requise pour produire en inventaire.
         * @param _quantiteEnInventaire Quantite de composante presentement en inventaire.
         */
        public Production(int _quantiteRequise, int _quantiteEnInventaire) {
            this._quantiteRequise = _quantiteRequise;
            this._quantiteEnInventaire = _quantiteEnInventaire;
        }

        public int get_quantiteRequise() {
            return _quantiteRequise;
        }

        public int get_quantiteEnInventaire() {
            return _quantiteEnInventaire;
        }
    }
}
