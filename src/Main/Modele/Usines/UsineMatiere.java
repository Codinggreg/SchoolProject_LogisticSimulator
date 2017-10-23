package Main.Modele.Usines;

import Main.Modele.Entrepot;
import Main.Modele.Usine;
import Main.Modele.Composante;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Usine Produisant du Metal
 */
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

    /**
     * Ajuste la production selon l'état de la classe Entrepot
     * @param o l'entrepot
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        Entrepot e=(Entrepot)o;
        int statut=e.getStatut();
        if(e.getStatut()==0)
        {
            if(this.getIntervalProd()>=40) {
                this.ajusterProduction(-20);
            }
            set_productionArretee(false);
        }
        else if(statut==100){
            set_productionArretee(true);
        }
        else if (statut>=80)
        {
            this.ajusterProduction(70);
            set_productionArretee(true);
        }
        else if (statut>=50)
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
