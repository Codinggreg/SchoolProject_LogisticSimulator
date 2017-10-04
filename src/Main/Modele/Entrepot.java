package Main.Modele;

public class Entrepot extends Batiment {
    private int _capacite;

    @Override
    protected boolean peutProduire() {
        return false;
    }

    @Override
    protected Composante getTypeSortie() {
        return null;
    }

    @Override
    public void avancerTour(int int_prod) {

    }
}
