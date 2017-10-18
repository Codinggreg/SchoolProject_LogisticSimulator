package Main;

import Main.Controlleur.Workers.Environnement;

public class Simulation {
    static Environnement environnement;
	/**
	 * Cette classe repr�sente l'application dans son ensemble.
	 */
	public static void main(String[] args) {

        environnement = new Environnement();
		Simulation.FenetrePrincipale fenetre = new Simulation.FenetrePrincipale();



		environnement.addPropertyChangeListener(fenetre);
		environnement.execute();



	}

    public static void rechargerEnvironnement(){
        environnement.cancel(true);
        System.out.println("Environnement termine");
    }
}
