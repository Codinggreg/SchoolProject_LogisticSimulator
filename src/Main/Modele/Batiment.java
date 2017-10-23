package Main.Modele;

import java.awt.*;
import java.util.HashMap;

public abstract class Batiment extends Unite{
    private HashMap<String, Production> _inventaire;
    private int _intervalProd;
    private int _intervalCourant;

    private boolean _enProduction;
    private boolean _productionArretee;
    Batiment(int interval, int id, Point position) {
        super(id, position);
        _inventaire = new HashMap<>();
        this._intervalProd = interval;
        this._intervalCourant = 0;
        _enProduction = false;
        _productionArretee=false;
    }

    protected void set_productionArretee(boolean _productionArretee) {
        this._productionArretee = _productionArretee;
    }

    public void setProduction(HashMap<String, Integer> productions) {
        for (HashMap.Entry<String, Integer> entry : productions.entrySet()) {
            this.ajouterTypeProduction(entry.getKey(), entry.getValue());
        }
    }

    public int getIntervalProd() {
        return _intervalProd;
    }

    public int getIntervalCourant() {
        return _intervalCourant;
    }

    public void set_intervalCourant(int _intervalCourant) {
        this._intervalCourant = _intervalCourant;
    }

    private boolean isProductionDefinie() {
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

    @SuppressWarnings("SameParameterValue")
    protected void setEnProduction(boolean b) {
        _enProduction=b;
    }

    public void ajusterProduction(int i) {
        this._intervalProd+=i;
    }

    private class Production {
        private int _quantiteRequise;
        private int _quantiteEnInventaire;

        Production(int _quantiteRequise) {
            this._quantiteRequise = _quantiteRequise;
            this._quantiteEnInventaire = 0;
        }

        int get_quantiteEnInventaire() {
            return _quantiteEnInventaire;
        }

        void ajoutInventaire(int ajout) {
            int resultat = this._quantiteEnInventaire + ajout;
            if (resultat >= 0) {
                this._quantiteEnInventaire = resultat;
            } else {
                this._quantiteEnInventaire = 0;
            }
        }

        boolean isQuantiteSuffisante() {
            return this._quantiteEnInventaire - this._quantiteRequise >= 0;
        }

        void produire() {
            this._quantiteEnInventaire -= this._quantiteRequise;
        }
    }
}
