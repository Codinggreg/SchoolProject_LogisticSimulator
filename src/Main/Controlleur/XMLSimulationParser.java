package Main.Controlleur;

import Main.Modele.Batiment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;

public class XMLSimulationParser {

    public static HashMap<Integer,Batiment> getBatiments(Document doc){

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
    }
}
