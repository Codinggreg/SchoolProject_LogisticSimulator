package Main;

import Main.Controlleur.Actions.ChangerStrategieAction;
import Main.Controlleur.Ventes.VenteAleatoire;
import Main.Controlleur.Ventes.VenteIntervalles;
import Main.Controlleur.Workers.Environnement;
import Main.Modele.Batiment;
import Main.Modele.BatimentMetaData;
import Main.Modele.Entrepot;
import Main.Modele.Usines.UsineAile;
import Main.Modele.Usines.UsineAvion;
import Main.Modele.Usines.UsineMatiere;
import Main.Modele.Usines.UsineMoteur;
import Main.Vue.FenetrePrincipale;

import java.util.HashMap;

/**
 * Classe principale s'occuppant de créer l'environnement et la fenêtre principale.
 */
public class Simulation {
    private static Environnement environnement;
    private static FenetrePrincipale fenetre;
	/**
	 * Cette classe repr�sente l'application dans son ensemble.
	 */
	public static void main(String[] args) {


	    fenetre = new FenetrePrincipale();
	    rechargerEnvironnement(null);
        environnement.setStrategie(new VenteAleatoire());
	}

    /**
     * Change la stratégie de vente de la classe Environnement
     * @param type le type de stratégie de vente à donner
     */
    public static void changerStrategie(ChangerStrategieAction.TypeStrategie type){
	    switch (type){
            case Aleatoire:
                environnement.setStrategie(new VenteAleatoire());
                break;
            case Intervalles:
                environnement.setStrategie(new VenteIntervalles());
                break;
        }
    }

    /**
     * Réinitialise la classe Environnement avec de nouvelles données
     * @param metaData les données à initialiser
     */
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
            fenetre.reset();
            fenetre.ajouterBatiments(metaData);
        }

        environnement=new Environnement(batiments);
        environnement.addPropertyChangeListener(fenetre);
        environnement.execute();

    }

    /**
     * Ajoute la destination à chaque batiments
     * @param batiments les batiments sans destinations
     * @param metaData les données contant la destination
     */
    private static void attribuerDestination(HashMap<Integer, Batiment> batiments,HashMap<Integer,BatimentMetaData> metaData){
	    metaData.forEach((key,value)->{
	        if(value.IDDestination>0)
            {
                batiments.get(key).set_destination(batiments.get(value.IDDestination));
            }
        });
    }

    /**
     * Créée les batiments selon les donnéées
     * @param data les données contenant les types de batiments à créer
     * @return un batiment créé
     */
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
