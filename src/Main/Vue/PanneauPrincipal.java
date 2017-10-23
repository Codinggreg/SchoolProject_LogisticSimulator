package Main.Vue;

import Main.Vue.Icones.IconeBatiment;
import Main.Vue.Icones.IconeComposante;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.*;

/**
 * Panneau s'occuppant de l'animation des composantes
 */
public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	

    private ArrayList<IconeComposante> _composantes;
	private ArrayList<IconeBatiment> _batiments;

	PanneauPrincipal(){
	    this._batiments=new ArrayList<>();
	    this._composantes=new ArrayList<>();
    }

	@Override
	public void paint(Graphics g) {
		super.paint(g);
        _batiments.forEach(p->p.dessinerLigne(g));
        
		for(Iterator<IconeComposante> ite=_composantes.iterator();ite.hasNext();){
		    IconeComposante comp=ite.next();
		    if(comp.peutEnlever()) {
		        comp.setModel(null);
                ite.remove();
            }else{
		        comp.dessiner(g);
            }
        }

        _batiments.forEach(p->p.dessiner(g));

    }

	void ajouterComposante(IconeComposante ico) {
	    _composantes.add(ico);
	}

    public void setBatiments(ArrayList<IconeBatiment> batiments) {
        this._batiments = batiments;
    }

    /**
     * Réinitialise l'animation
     */
    public void reset() {
        this._batiments.clear();
        this._composantes.clear();
    }
}