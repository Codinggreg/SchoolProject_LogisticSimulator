package Main.Modele;

import java.util.HashMap;

public abstract class Batiment {
    public static final int NOT_IN_INVENTORY = -1;
    private Batiment _destination;
    private HashMap<String,Production> _inventaire;

    public Batiment()
    {
        _inventaire=new HashMap<>();
    }
    protected abstract Composante getTypeSortie();
    protected abstract boolean peutProduire();
    public Composante extraireSortie()
    {
        if(peutProduire())
        {
            return getTypeSortie();
        }
        return null;
    }
    public int getQuantiteInventaire(String classType){
        Production prod=this._inventaire.get(classType);
        if(prod==null)
        {
            return -1;
        }
        return prod.get_quantiteEnInventaire();
    }

    public void ajouterInventaire(String classType, int quantiteAjoutee){
        Production prod=_inventaire.get(classType);
        if(prod!=null)
        {
            prod.ajoutInventaire(quantiteAjoutee);
        }
    }
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

    public abstract void avancerTour(int int_prod);

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
    }
}
