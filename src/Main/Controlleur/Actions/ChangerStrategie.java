package Main.Controlleur.Actions;

import Main.Simulation;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ChangerStrategie extends AbstractAction {
    ButtonGroup _group;
    public ChangerStrategie(ButtonGroup group,String nom){
        super(nom);
        this._group=group;
    }
    public enum TypeStrategie{
        Aleatoire,
        Intervalles
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Simulation.changerStrategie(TypeStrategie.valueOf(_group.getSelection().getActionCommand()));
    }
}
