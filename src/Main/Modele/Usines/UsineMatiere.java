package Main.Modele.Usines;

import Main.Modele.Entrepot;
import Main.Modele.Usine;
import Main.Modele.Composante;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class UsineMatiere extends Usine implements Observer {

    public UsineMatiere(int interval, int id, Point position) {
        super(interval, id, position);
        this.setEnProduction(true);
    }

    @Override
    public Composante getComposante() {
        return new Composante(0,this.get_position(),"metal");
    }

    @Override
    protected void ajusterInventaire() {
        set_intervalCourant(0);
    }

    @Override
    public void update(Observable o, Object arg) {
        Entrepot e=(Entrepot)o;
        if(e.getStatut()==0)
        {
            if(this.getIntervalProd()>=10) {
                this.ajusterProduction(-5);
            }
            set_productionArretee(false);
        }
        else if(e.estPlein()){
            set_productionArretee(true);
        }
        else if (e.presquePlein())
        {
            this.ajusterProduction(70);
            set_productionArretee(true);
        }
        else if (e.getStatut()>=50)
        {
            this.ajusterProduction(5);
            set_productionArretee(false);
        }
        else
        {
            set_productionArretee(false);
        }

        System.out.println("Vitesse"+this.getIntervalProd());
    }
}
