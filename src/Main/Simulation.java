package Main;

import Main.Controlleur.Workers.Environnement;
import Main.Modele.Batiment;
import Main.Modele.BatimentMetaData;
import Main.Modele.Entrepot;
import Main.Modele.Usines.UsineAile;
import Main.Modele.Usines.UsineAvion;
import Main.Modele.Usines.UsineMatiere;
import Main.Modele.Usines.UsineMoteur;
import Main.Vue.FenetrePrincipale;

import java.awt.*;
import java.util.HashMap;

public class Simulation {
    static Environnement environnement;
    private static FenetrePrincipale fenetre;
	/**
	 * Cette classe repr�sente l'application dans son ensemble.
	 */
	public static void main(String[] args) {


	    fenetre = new FenetrePrincipale();
	    rechargerEnvironnement(null);


	}

    public static void rechargerEnvironnement(HashMap<Integer,BatimentMetaData> metaData) {
        HashMap<Integer, Batiment> batiments = new HashMap<>();


        if (environnement != null && !environnement.isCancelled()) {
            environnement.cancel(true);
        }
        if (metaData != null && !metaData.isEmpty()){
            for (HashMap.Entry<Integer, BatimentMetaData> entry : metaData.entrySet()) {
                batiments.put(entry.getKey(), chargerBatiment(entry.getValue()));
            }
            attribuerDestination(batiments,metaData);
            fenetre.ajouterBatiments(metaData);
        }

        environnement=new Environnement(batiments);
        environnement.addPropertyChangeListener(fenetre);
        environnement.execute();

    }
    private static void attribuerDestination(HashMap<Integer, Batiment> batiments,HashMap<Integer,BatimentMetaData> metaData){
	    metaData.forEach((key,value)->{
	        if(value.IDDestination>0)
            {
                batiments.get(key).set_destination(batiments.get(value.IDDestination));
            }
        });
    }
    private static Batiment chargerBatiment(BatimentMetaData data){

        Batiment batiment=null;
        switch (data.Type){
            case "usine-matiere":
            {
                batiment=new UsineMatiere(data.IntervalProd,data.ID,data.Position);
                break;
            }
            case "usine-assemblage":{
                batiment=new UsineAvion(data.IntervalProd,data.ID,data.Position);
                break;
            }
            case "usine-aile":{
                batiment=new UsineAile(data.IntervalProd,data.ID,data.Position);
                break;
            }
            case "usine-moteur":{
                batiment=new UsineMoteur(data.IntervalProd,data.ID,data.Position);
                break;
            }
            case "entrepot":{
                int capacite=data.Production.get("avion");
                data.Production.replace("avion",1);//remplace la production de avion par 1 pour les calculs d'inventaire
                batiment=new Entrepot(data.IntervalProd,data.ID,data.Position,capacite);
                break;
            }
        }
        data.Batiment=batiment;
        if(!data.Production.isEmpty()){
            batiment.setProduction(data.Production);
        }
        return batiment;
    }
}
