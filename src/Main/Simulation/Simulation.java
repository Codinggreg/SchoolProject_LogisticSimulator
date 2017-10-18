package Main.Simulation;

import Main.Controlleur.Environnement;
import Main.Controlleur.XMLSimulationParser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

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
