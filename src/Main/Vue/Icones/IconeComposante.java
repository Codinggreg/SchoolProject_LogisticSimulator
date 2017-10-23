package Main.Vue.Icones;

import Main.Modele.Composante;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class IconeComposante {
    private ImageIcon _icon;
    private Composante _modele;
    static private HashMap<String,ImageIcon> _icones;
    public IconeComposante(Composante comp){
        this._modele=comp;
        chargerIcone();
    }
    public IconeComposante(Object obj){
        if(obj instanceof Composante){
            this._modele=(Composante) obj;
            chargerIcone();
        }
    }
    static public void chargerIcones(){
        String cheminRessources="src/ressources/";
        _icones=new HashMap<>();
        _icones.put("avion",new ImageIcon(IconeComposante.class.getClassLoader().getResource(cheminRessources+"avion.png")));
        _icones.put("aile",new ImageIcon(IconeComposante.class.getClassLoader().getResource(cheminRessources+"aile.png")));
        _icones.put("metal",new ImageIcon(IconeComposante.class.getClassLoader().getResource(cheminRessources+"metal.png")));
        _icones.put("moteur",new ImageIcon(IconeComposante.class.getClassLoader().getResource(cheminRessources+"moteur.png")));
    }
    private void chargerIcone(){

        this._icon = _icones.get(_modele.get_type());
    }
    public boolean peutEnlever(){
        return this._modele.is_peutEnlever();
    }

    public void dessiner(Graphics g) {
        if(!_modele.is_peutEnlever()) {
            int posx = _modele.get_position().x;
            int posy = _modele.get_position().y;
            g.drawImage(_icon.getImage(), posx, posy, null);
        }
    }

    public void setModel(Composante model) {
        this._modele = model;
    }
}
