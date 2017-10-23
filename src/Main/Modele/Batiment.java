package Main.Modele;

import java.awt.*;
import java.util.HashMap;
import java.util.Observer;

public abstract class Batiment extends Unite{
    public static final int NOT_IN_INVENTORY = -1;
    protected HashMap<String, Production> _inventaire;
    private int _intervalProd;
    private int _intervalCourant;

    private boolean _enProduction;
    private boolean _productionArretee;
    public Batiment(int interval, int id, Point position) {
        super(id, position);
        _inventaire = new HashMap<>();
        this._intervalProd = interval;
        this._intervalCourant = 0;
        _enProduction = false;
        _productionArretee=false;
    }

    public boolean is_productionArretee() {
        return _productionArretee;
    }

    public void set_productionArretee(boolean _productionArretee) {
        this._productionArretee = _productionArretee;
    }

    public boolean is_enProduction() {
        return _enProduction;
    }

    public void setProduction(HashMap<String, Integer> productions) {
        for (HashMap.Entry<String, Integer> entry : productions.entrySet()) {
            this.ajouterTypeProduction(entry.getKey(), entry.getValue());
        }
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

    public boolean isProductionDefinie() {
        return !this._inventaire.isEmpty();
    }

    public boolean isInventaireSuffisant() {
        if (!isProductionDefinie()) {
            return false;
        }
        for (Production prod : this._inventaire.values()) {
            if (!prod.isQuantiteSuffisante()) {
                return false;
            }
        }
        return true;
    }

    protected void ajouterInventaire(String type, int quantite) {
        Production prod = _inventaire.get(type);
        if (prod != null) {
            prod.ajoutInventaire(quantite);
            verifierPeutProduire();
        }
    }
    private void verifierPeutProduire(){
        if(isInventaireSuffisant()){
            _enProduction=true;
        }
    }
    public abstract int getStatut();

    public abstract Composante getComposante();

    protected abstract boolean peutExtraire();

    public Composante extraireSortie() {
        if (peutExtraire()) {
            ajusterInventaire();
            verifierPeutProduire();
            Composante comp=getComposante();
            if(this.aUneDestination()) {
                comp.set_destination(this.get_destination());
            }
            return comp;
        }
        return null;
    }

    protected void ajusterInventaire() {
        for (Production prod : this._inventaire.values()) {
            prod.produire();
        }
        _intervalCourant = 0;
        _enProduction=false;
    }

    public int getQuantiteInventaire(String classType) {
        Production prod = this._inventaire.get(classType);
        if (prod == null) {
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
    public void ajouterTypeProduction(String s, int qty_min) {
        if (!_inventaire.containsKey(s)) {
            Production prod = new Production(qty_min);
            _inventaire.put(s, prod);
        }
    }

    public void avancerTour(int int_prod) {
        if (_enProduction&&!_productionArretee) {//aurait pu utiliser un patron state pour gerer ca
            this._intervalCourant += int_prod;
        }
    }

    protected void setEnProduction(boolean b) {
        _enProduction=b;
    }

    public void ajusterProduction(int i) {
        this._intervalProd+=i;
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
            this._quantiteEnInventaire = 0;
        }

        public int get_quantiteRequise() {
            return _quantiteRequise;
        }

        public int get_quantiteEnInventaire() {
            return _quantiteEnInventaire;
        }

        public void ajoutInventaire(int ajout) {
            int resultat = this._quantiteEnInventaire + ajout;
            if (resultat >= 0) {
                this._quantiteEnInventaire = resultat;
            } else {
                this._quantiteEnInventaire = 0;
            }
        }

        public boolean isQuantiteSuffisante() {
            return this._quantiteEnInventaire - this._quantiteRequise >= 0;
        }

        public void produire() {
            this._quantiteEnInventaire -= this._quantiteRequise;
        }
    }
}
