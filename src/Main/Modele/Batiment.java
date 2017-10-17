package Main.Modele;

import java.util.HashMap;

public abstract class Batiment extends Unite{
    public static final int NOT_IN_INVENTORY = -1;
    protected HashMap<String,Production> _inventaire;
    private int _intervalProd;
    private int _intervalCourant;
    private Batiment _destination;

    public Batiment(int interval)
    {
        _inventaire=new HashMap<>();
        this._intervalProd = interval;
        this._intervalCourant=0;
    }

    public int getIntervalProd() {
        return _intervalProd;
    }

    public void set_intervalProd(int _intervalProd) {
        this._intervalProd = _intervalProd;
    }

    public int getIntervalCourant() {
        return _intervalCourant;
    }

    public void set_intervalCourant(int _intervalCourant) {
        this._intervalCourant = _intervalCourant;
    }
    public boolean isInventaireSuffisant(){
        for(Production prod: this._inventaire.values()){
            if(!prod.isQuantiteSuffisante()){
                return false;
            }
        }
        return true;
    }
    protected void ajouterInventaire(String type,int quantite){
        Production prod=_inventaire.get(type);
        if(prod!=null)
        {
            prod.ajoutInventaire(quantite);
        }
    }
    public abstract Composante getTypeSortie();
    protected abstract boolean peutProduire();

    public Composante extraireSortie()
    {
        if(peutProduire())
        {
            ajusterInventaire();
            return getTypeSortie();
        }
        return null;
    }

    private void ajusterInventaire(){
        for (Production prod :this._inventaire.values()){
            prod.produire();
        }
    }

    public int getQuantiteInventaire(String classType){
        Production prod=this._inventaire.get(classType);
        if(prod==null)
        {
            return -1;
        }
        return prod.get_quantiteEnInventaire();
    }
    public abstract void gererAjout(String classType, int quantiteAjoutee);

    /***
     *  Initialise la liste de production du batiment, ou l'inventaire d'un entrepot. Si le type de production existe deja, il n'est pas ecrase avec la nouvelle valeur.
     * @param s type de composante
     * @param qty_min quantite requis pour produire en sortie
     */
    public void AjouterTypeProduction(String s, int qty_min){
        if(!_inventaire.containsKey(s)) {
            Production prod = new Production(qty_min);
            _inventaire.put(s, prod);
        }
    }

    public void avancerTour(int int_prod){
        this._intervalCourant+=int_prod;
        if(_intervalCourant>this._intervalProd){
            this._intervalCourant=this._intervalCourant-this._intervalProd;
        }
    }

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

        public Production(int _quantiteRequise) {
            this._quantiteRequise = _quantiteRequise;
            this._quantiteEnInventaire=0;
        }

        public int get_quantiteRequise() {
            return _quantiteRequise;
        }

        public int get_quantiteEnInventaire() {
            return _quantiteEnInventaire;
        }
        public void ajoutInventaire(int ajout){
            int resultat=this._quantiteEnInventaire+ajout;
            if(resultat>=0) {
                this._quantiteEnInventaire = resultat;
            }
            else
            {
                this._quantiteEnInventaire=0;
            }
        }
        public boolean isQuantiteSuffisante(){
            return this._quantiteEnInventaire-this._quantiteRequise>=0;
        }

        public void produire() {
            if (isQuantiteSuffisante()){
                this._quantiteEnInventaire-=this._quantiteRequise;
            }
        }
    }
}
