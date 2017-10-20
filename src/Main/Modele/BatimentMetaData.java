package Main.Modele;

import Main.Vue.Icones.TypeIcone;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class BatimentMetaData {
    public Point Position;
    public int ID;
    public int IDDestination;
    public String Type;
    public HashMap<TypeIcone,ImageIcon> Icones;
    public HashMap<String,Integer> Production;
    public int IntervalProd;
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