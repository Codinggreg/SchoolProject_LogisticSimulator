package Main.Controlleur.Workers;

import Main.Modele.Batiment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class XMLSimulationParser extends SwingWorker<HashMap<Integer,Batiment>,String>  {

    private File _xmlFile;

    public XMLSimulationParser(File file) {
    }


    @Override
    protected HashMap<Integer,Batiment> doInBackground() throws Exception {
        boolean temp=true;

        firePropertyChange("State",null,StateValue.DONE);
        return null;
    }

    public HashMap<Integer,Batiment> getBatiments(Document doc){

        if(doc !=null)
        {
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            Node metadonnees=doc.getDocumentElement().getElementsByTagName("metadonnees").item(0);
            NodeList usines=metadonnees.getChildNodes();
            for (int i = 0; i < usines.getLength(); i++) {
                Node n=usines.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element ele=(Element)n;
                    String type=ele.getAttribute("type");
                    System.out.println(type);
                    switch(type){
                        case "usine-matiere":
                        {

                            break;
                        }
                        case "usine-aile":{
                            break;
                        }
                        case "usine-moteur":{
                            break;
                        }
                        case "usine-assemblage":{
                            break;
                        }
                        case "entrepot":{
                            break;
                        }
                        default:{
                            System.out.println("Usine non-reconnue!");
                        }
                    }
                }

            }
        }
        return null;
    }
}
