package Main.Vue.Icones;

import Main.Modele.Batiment;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("ALL")
public class IconeBatiment implements Observer{
    private HashMap<TypeIcone,ImageIcon> _images;
    private Batiment _modele;

    public IconeBatiment(HashMap<TypeIcone,ImageIcon> images,Batiment batiment){
        _images=images;
        _modele=batiment;
    }

    @Override
    public void update(Observable o, Object arg) {
        ImageIcon choix=choisirIcone();
    }
    private ImageIcon choisirIcone(){
        ImageIcon icone=null;
        int statutInventaire=_modele.getStatut();
        int PLEIN = 100;
        int VIDE = 0;
        int UNTIERS = 33;
        if(statutInventaire== VIDE) {
            icone=_images.get(TypeIcone.VIDE);
        }
        else if (statutInventaire< UNTIERS)
        {
            icone=_images.get(TypeIcone.UNTIER);
        }
        else if(statutInventaire>= UNTIERS && statutInventaire< PLEIN){
            icone=_images.get(TypeIcone.DEUXTIERS);
        }
        else if(statutInventaire>= PLEIN){
            icone=_images.get(TypeIcone.PLEIN);
        }
        return icone;
    }

    public void dessiner(Graphics g) {
        int posx = _modele.get_position().x;
        int posy = _modele.get_position().y;
        Image icone=choisirIcone().getImage();


        g.drawImage(choisirIcone().getImage(), posx, posy, null);

    }
    public void dessinerLigne(Graphics g){
        int posx = _modele.get_position().x;
        int posy = _modele.get_position().y;
        Image icone=choisirIcone().getImage();
        int offsetX=icone.getWidth(null)/2;
        int offsetY=icone.getHeight(null)/2;

        if(_modele.aUneDestination()){
            Point dest=new Point(_modele.get_destination().get_position());
            g.drawLine(posx+offsetX,posy+offsetY,dest.x+offsetX,dest.y+offsetY);
        }
    }
}
