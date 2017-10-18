package Main;

import Main.Controlleur.Workers.Environnement;
import Main.Vue.FenetrePrincipale;

public class Simulation {
    static Environnement environnement;
	/**
	 * Cette classe repr�sente l'application dans son ensemble.
	 */
	public static void main(String[] args) {

        environnement = new Environnement();
		FenetrePrincipale fenetre = new FenetrePrincipale();



		environnement.addPropertyChangeListener(fenetre);
		environnement.execute();



	}

    public static void rechargerEnvironnement(){
        environnement.cancel(true);
        System.out.println("Environnement termine");
    }
}
