package Main.Controlleur.Workers;

import Main.Helpers.XMLHelper;
import Main.Modele.BatimentMetaData;
import Main.Vue.Icones.TypeIcone;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Objects;

/**
 * Code de chargement de fichier XML inspire de: http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
 * Ce code assume que le fichier XML est valide
 */
@SuppressWarnings("CanBeFinal")
public class XMLSimulationParser extends SwingWorker<HashMap<Integer,BatimentMetaData>,String>  {



    private File _xmlFile;

    public XMLSimulationParser(File file) {
        this._xmlFile=file;
    }


    @Override
    protected HashMap<Integer,BatimentMetaData> doInBackground() throws Exception {
        HashMap<Integer,BatimentMetaData> temp=new HashMap<>();
        if(this._xmlFile==null){
            firePropertyChange("Error",null,"Aucun fichier Xml detecte.");
        }else
        {
            Document doc=XMLHelper.getXMLFile(this._xmlFile);
            if(doc!=null){
                HashMap<String,BatimentMetaData> types=getTypesBatiments(doc);
                temp=chargerBatiments(types,doc);
                setDestinationBatiments(temp,doc);
            }



        }

        firePropertyChange("State",null,StateValue.DONE);
        return temp;
    }

    /**
     * Charge la balise Simulation
     * @param types les types d'usine
     * @param doc le fichier xml
     * @return collection de BatimentMetaData contenant les données des usines
     */
    private HashMap<Integer,BatimentMetaData> chargerBatiments(HashMap<String, BatimentMetaData> types,Document doc) {
        HashMap<Integer,BatimentMetaData> temp=new HashMap<>();

        Element simulation=(Element)doc.getDocumentElement().getElementsByTagName("simulation").item(0);
        NodeList usines=simulation.getElementsByTagName("usine");
        for (int i = 0; i < usines.getLength(); i++) {
            Element usine=(Element)usines.item(i);
            int id=Integer.parseInt(usine.getAttribute("id"));
            int posx=Integer.parseInt(usine.getAttribute("x"));
            int posy=Integer.parseInt(usine.getAttribute("y"));
            String type=usine.getAttribute("type");
            BatimentMetaData meta=new BatimentMetaData(types.get(type),id,new Point(posx,posy));
            temp.put(meta.ID,meta);
        }

        return temp;
    }


    /**
     * Charge la destination des batiments dans la balise chemins
     * @param batiments collection des batiments
     * @param doc fichier XML
     */
    private void setDestinationBatiments(HashMap<Integer,BatimentMetaData> batiments,Document doc){
        Element simulation=(Element)doc.getDocumentElement().getElementsByTagName("simulation").item(0);
        Element destinations=(Element)simulation.getElementsByTagName("chemins").item(0);
        NodeList chemins=destinations.getElementsByTagName("chemin");
        for (int i = 0; i < chemins.getLength(); i++) {
            Element chemin=(Element)chemins.item(i);
            int idDest=Integer.parseInt(chemin.getAttribute("vers"));
            int idBat=Integer.parseInt(chemin.getAttribute("de"));
            if(idDest>=0)
            {
                BatimentMetaData bat=batiments.get(idBat);
                bat.IDDestination=idDest;
            }
        }
    }

    /**
     * Charge les types de batiments
     * @param doc fichier xml
     * @return Collection de BatimentMetaData contenant les données des types d'usines
     */
    private HashMap<String,BatimentMetaData> getTypesBatiments(Document doc){
        HashMap<String,BatimentMetaData> temp=new HashMap<>();


            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            Node metadonnees=doc.getDocumentElement().getElementsByTagName("metadonnees").item(0);
            NodeList usines=metadonnees.getChildNodes();

            for (int i = 0; i < usines.getLength(); i++) {
                BatimentMetaData type=new BatimentMetaData();
                Node n = usines.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element ele = (Element) n;

                    type.Type=ele.getAttribute("type");

                    Element icones=(Element)ele.getElementsByTagName("icones").item(0);
                    type.Icones=getIcones(icones.getElementsByTagName("icone"));

                    Element interval=(Element)ele.getElementsByTagName("interval-production").item(0);
                    if(interval!=null){
                        type.IntervalProd=Integer.parseInt(interval.getTextContent());
                    }



                    NodeList entrees=ele.getElementsByTagName("entree");
                    type.Production=getListeProduction(entrees);

                    temp.put(type.Type,type);
                }
            }
        return temp;
    }

    /**
     * Charge la production de chaque type d'Usine
     * @param entrees la balise entree de chaque type d'usine
     * @return Collection contenant le type et la quantite
     */
    private HashMap<String,Integer> getListeProduction(NodeList entrees) {
        HashMap<String,Integer> temp=new HashMap<>();

        for (int i = 0; i < entrees.getLength(); i++) {
            Element ele=(Element)entrees.item(i);
            String type=ele.getAttribute("type");
            String prodNum=ele.getAttribute("quantite");
            if(Objects.equals(prodNum, "")){
                prodNum=ele.getAttribute("capacite");
            }
            int quantite=Integer.parseInt(prodNum);
            temp.put(type,quantite);
        }

        return temp;
    }

    /**
     * Chargement des icones pour chaque type d'usine
     * @param l balise icones pour un type d'usine
     * @return Collection mappant chaque type d'icone a une icone
     */
    private HashMap<TypeIcone,ImageIcon> getIcones(NodeList l){
        HashMap<TypeIcone,ImageIcon> temp=new HashMap<>();
        for (int i = 0; i < l.getLength(); i++) {
            Element item=(Element)l.item(i);
            ImageIcon icone=new ImageIcon(getClass().getClassLoader().getResource(item.getAttribute("path")));

            switch (item.getAttribute("type")){
                case "un-tier":{
                    temp.put(TypeIcone.UNTIER,icone);
                    break;
                }
                case "vide":{
                    temp.put(TypeIcone.VIDE,icone);
                    break;
                }
                case "deux-tiers":{
                    temp.put(TypeIcone.DEUXTIERS,icone);
                    break;
                }
                case "plein":{
                    temp.put(TypeIcone.PLEIN,icone);
                    break;
                }
            }
        }
        return temp;
    }
}
