package Modele;

public abstract class Batiment {
    private Batiment _destination;
    private int _inventaire;

    public abstract Composante getSortieProduction();
}
