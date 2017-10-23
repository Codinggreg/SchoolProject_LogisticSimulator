package Main.Controlleur.Actions;

import Main.Simulation;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ChangerStrategieAction extends AbstractAction {
    private ButtonGroup _group;
    public ChangerStrategieAction(ButtonGroup group, String nom){
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
