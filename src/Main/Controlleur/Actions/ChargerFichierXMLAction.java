package Main.Controlleur.Actions;

import Main.Controlleur.XMLSimulationParser;
import Main.Simulation.Simulation;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public class ChargerFichierXMLAction extends AbstractAction implements PropertyChangeListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        File file=selectionnerFichier();
        if(file != null)
        {
            XMLSimulationParser xmlWorker=new XMLSimulationParser(file);
            xmlWorker.addPropertyChangeListener(this);
            xmlWorker.execute();
        }

    }
    private File selectionnerFichier(){
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("S�lectionnez un fichier de configuration");
        fileChooser.setAcceptAllFileFilterUsed(false);

        FileNameExtensionFilter filtre = new FileNameExtensionFilter(".xml", "xml");
        fileChooser.addChoosableFileFilter(filtre);

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        switch(evt.getPropertyName()){
            case "state":{
                switch ((SwingWorker.StateValue)evt.getNewValue()){
                    case DONE: {

                        System.out.println("Chargement Fichier Fini");
                        Simulation.rechargerEnvironnement();
                        break;
                    }
                }
                break;
            }

        }

    }
}
