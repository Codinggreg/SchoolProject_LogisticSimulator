package Main.Modele;

import java.util.HashMap;

public abstract class Batiment {
    private Batiment _destination;
    private HashMap<String,Integer> _inventaire;

    public int getQuantiteInventaire(String classType){
        return 0;
    }

    public void ajouterInventaire(String classType, int quantiteAjoutee){

    }

    public abstract Composante extraireSortie();


    public abstract void avancerTour(int int_prod);
}
