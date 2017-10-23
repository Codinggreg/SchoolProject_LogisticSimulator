package Main.Modele;

import java.awt.*;
import java.util.HashMap;

/**
 * Batiment pouvant produire ou vendre des composantes
 */
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

    /**
     * Définit la production d'un batiment
     * @param productions liste d'entrees contenant la quantite requise selon le type
     */
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

    /**
     * Détermine si le nombre d'entrée est suffisant pour produire
     * @return vrai si le nombre est suffisant
     */
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

    /**
     * Ajoute un type de composante dans l'inventaire du batiment
     * La production doit avoir été définie auparavant avec setProduction
     * @param type le type de composante à ajouter
     * @param quantite la quantité à ajouter
     */
    protected void ajouterInventaire(String type, int quantite) {
        Production prod = _inventaire.get(type);
        if (prod != null) {
            prod.ajoutInventaire(quantite);
            verifierPeutProduire();
        }
    }

    /**
     * Démarre le compteur de production si l'inventaire est suffisant
     */
    private void verifierPeutProduire(){
        if(isInventaireSuffisant()){
            _enProduction=true;
        }
    }

    /**
     * Retourne le statut du batiment sous forme de pourcentage
     * @return
     */
    public abstract int getStatut();

    /**
     * Crée une nouvelle composante
     * @return Composante
     */
    public abstract Composante getComposante();

    /**
     * Détermine si l'inventaire est suffisant ou si c'est le bon tour pour produire
     * @return vrai si l'extraction est possible
     */
    protected abstract boolean peutExtraire();

    /**
     * Définit le fil à suivre pour produire une composante
     * @return Composante si la production est réussie
     */
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

    /**
     * Enlève la quantité requise en inventaire lors d'une production
     */
    protected void ajusterInventaire() {
        for (Production prod : this._inventaire.values()) {
            prod.produire();
        }
        _intervalCourant = 0;
        _enProduction=false;
    }

    /**
     * Retourne la quantité d'une composante en inventaire
     * @param classType le type de composante
     * @return la quantité en inventaire
     */
    public int getQuantiteInventaire(String classType) {
        Production prod = this._inventaire.get(classType);
        if (prod == null) {
            return -1;
        }
        return prod.get_quantiteEnInventaire();
    }

    /**
     * Définit le comportement lors d'un ajout de composante au batiment
     * @param classType le type de composante
     * @param quantiteAjoutee la quantite ajoutee
     */
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

    /**
     * Incremente le tour de production du batiment si celui-ci est en mode production
     * @param int_prod
     */
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

    /**
     * Classe qui s'occupe de la gestion de l'inventaire
     */
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
