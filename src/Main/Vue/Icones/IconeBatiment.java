package Main.Vue.Icones;

import Main.Modele.Batiment;

import javax.swing.*;
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
    private final int PLEIN=100;

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
        int statutInventaire=_modele.getStatutInventaire();
        if(statutInventaire==VIDE) {
            icone=_images.get(TypeIcone.VIDE);
        }
        else if (statutInventaire<UNTIERS)
        {
            icone=_images.get(TypeIcone.UNTIER);
        }
        else if(statutInventaire>=UNTIERS && statutInventaire<DEUXTIERS){
            icone=_images.get(TypeIcone.DEUXTIERS);
        }
        else if(statutInventaire==PLEIN){
            icone=_images.get(TypeIcone.PLEIN);
        }
        if(icone.equals(_iconeCourante))//ne change pas licone si cest la meme pour sauver des ressources cpu
        {
            icone=null;
        }
        return icone;
    }
}
