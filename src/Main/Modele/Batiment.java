package Main.Modele;

public abstract class Batiment {
    private Batiment _destination;
    private int _inventaire;

    public int getQuantiteInventaire(){
        return 0;
    }

    public void ajouterInventaire(int quantiteAjoutee){

    }

    public abstract Composante extraireSortie();
}
