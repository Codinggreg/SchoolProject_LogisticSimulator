package Main.Modele;

import Main.Vue.Icones.TypeIcone;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Classe permettant de transférer les données d'une usine à la vue et au controlleur
 */
public class BatimentMetaData {
    public Point Position;
    public int ID;
    public int IDDestination;
    public String Type;
    public HashMap<TypeIcone,ImageIcon> Icones;
    public HashMap<String,Integer> Production;
    public int IntervalProd;
    public Batiment Batiment;
    public BatimentMetaData(BatimentMetaData type, int id, Point point){
        this.Type=type.Type;
        this.Icones=type.Icones;
        this.Production=type.Production;
        this.IntervalProd=type.IntervalProd;
        this.Position=point;
        this.ID=id;
    }
    public BatimentMetaData(){}

}
