package Main.Vue.Icones;

import Main.Modele.Batiment;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class IconeBatiment implements Observer{
    private ImageIcon _iconeCourante;
    private HashMap<TypeIcone,ImageIcon> _images;
    private Batiment _modele;

    private final int UNTIERS=33;
    private final int DEUXTIERS=66;
    private final int VIDE=0;
    private final int PLEIN=90;//Pour laisser le temps a laffichage dafficher 3 barres

    public IconeBatiment(HashMap<TypeIcone,ImageIcon> images,Batiment batiment){
        _images=images;
        _modele=batiment;
    }

    @Override
    public void update(Observable o, Object arg) {
        ImageIcon choix=choisirIcone();
        if(choix!=null){
            _iconeCourante=choix;
        }
    }
    private ImageIcon choisirIcone(){
        ImageIcon icone=null;
        int statutInventaire=_modele.getStatut();
        if(statutInventaire==VIDE) {
            icone=_images.get(TypeIcone.VIDE);
        }
        else if (statutInventaire<UNTIERS)
        {
            icone=_images.get(TypeIcone.UNTIER);
        }
        else if(statutInventaire>=UNTIERS && statutInventaire<PLEIN){
            icone=_images.get(TypeIcone.DEUXTIERS);
        }
        else if(statutInventaire>=PLEIN){
            icone=_images.get(TypeIcone.PLEIN);
        }
        return icone;
    }

    public void dessiner(Graphics g) {
        int posx = _modele.get_position().x;
        int posy = _modele.get_position().y;
        g.drawImage(choisirIcone().getImage(), posx, posy, null);
    }
}
