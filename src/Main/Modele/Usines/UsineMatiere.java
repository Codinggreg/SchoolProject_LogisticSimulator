package Main.Modele.Usines;

import Main.Modele.Composantes.Metal;
import Main.Modele.Usine;
import Main.Modele.Composante;

import java.awt.*;

public class UsineMatiere extends Usine {
    public UsineMatiere(int interval, int id, Point position) {
        super(interval, id, position);
    }

    @Override
    public Composante getTypeSortie() {
        return new Metal(0,this.get_position());
    }

    @Override
    protected boolean peutProduire() {
        return isBonTour()&&isProductionDefinie();
    }
}
