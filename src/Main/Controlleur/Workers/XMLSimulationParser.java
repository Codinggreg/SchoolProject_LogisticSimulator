package Main.Controlleur.Workers;

import Main.Modele.Batiment;
import Main.Vue.Icones.TypeIcone;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Formatter;
import java.util.HashMap;

/**
 * Code de chargement de fichier XML inspire de: http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
 * Ce code assume que le fichier XML est valide
 */
public class XMLSimulationParser extends SwingWorker<HashMap<Integer,Batiment>,String>  {



    private File _xmlFile;

    public XMLSimulationParser(File file) {
        this._xmlFile=file;
    }


    @Override
    protected HashMap<Integer,Batiment> doInBackground() throws Exception {
        boolean temp=true;
        if(this._xmlFile==null){
            firePropertyChange("Error",null,"Aucun fichier Xml detecte.");
        }else
        {
            HashMap<String,ChargeurType> types=getTypesBatiments(this._xmlFile);



        }

        firePropertyChange("State",null,StateValue.DONE);
        return null;
    }

    private HashMap<String,ChargeurType> getTypesBatiments(File file){
        HashMap<String,ChargeurType> temp=new HashMap<>();
        Document doc=null;
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(file);
        }catch (ParserConfigurationException|IOException|SAXException e) {
            e.printStackTrace();
        }
        if(doc!=null){
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            Node metadonnees=doc.getDocumentElement().getElementsByTagName("metadonnees").item(0);
            NodeList usines=metadonnees.getChildNodes();

            for (int i = 0; i < usines.getLength(); i++) {
                ChargeurType type=new ChargeurType();
                Node n = usines.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element ele = (Element) n;

                    type.set_typeBatiment(ele.getAttribute("type"));

                    Element icones=(Element)ele.getElementsByTagName("icones").item(0);
                    type.set_icones(getIcones(icones.getElementsByTagName("icone")));

                    Element interval=(Element)ele.getElementsByTagName("interval-production").item(0);
                    type.set_intervalProd(Integer.parseInt(interval.getTextContent()));

                    NodeList entrees=ele.getElementsByTagName("entree");
                    type.set_production(getListeProduction(entrees));

                    temp.put(type.get_typeBatiment(),type);
                    System.out.println(type.toString());
                }
            }

        }
        return temp;
    }

    private HashMap<String,Integer> getListeProduction(NodeList entrees) {
        HashMap<String,Integer> temp=new HashMap<>();

        for (int i = 0; i < entrees.getLength(); i++) {
            Element ele=(Element)entrees.item(i);
            String type=ele.getAttribute("type");
            int quantite=Integer.parseInt(ele.getAttribute("quantite"));
            temp.put(type,quantite);
        }

        return temp;
    }

    private HashMap<TypeIcone,ImageIcon> getIcones(NodeList l){
        HashMap<TypeIcone,ImageIcon> temp=new HashMap<>();
        for (int i = 0; i < l.getLength(); i++) {
            Element item=(Element)l.item(i);
            ImageIcon icone=new ImageIcon(item.getAttribute("path"));

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
    private void getBatimentsPosition(){

    }

    private class ChargeurType{
        private HashMap<TypeIcone,ImageIcon> _icones;
        private HashMap<String,Integer> _production;
        private String _typeBatiment;
        private int _intervalProd;

        public int get_intervalProd() {
            return _intervalProd;
        }

        public void set_intervalProd(int _intervalProd) {
            this._intervalProd = _intervalProd;
        }



        public HashMap<TypeIcone, ImageIcon> get_icones() {
            return _icones;
        }

        public void set_icones(HashMap<TypeIcone, ImageIcon> _icones) {
            this._icones = _icones;
        }


        public HashMap<String, Integer> get_production() {
            return _production;
        }

        public void set_production(HashMap<String, Integer> _production) {
            this._production = _production;
        }

        public String get_typeBatiment() {
            return _typeBatiment;
        }

        public void set_typeBatiment(String _typeBatiment) {
            this._typeBatiment = _typeBatiment;
        }

        @Override
        public String toString() {
            StringBuilder string=new StringBuilder();
            string.append("Type:{\n");
            Formatter format=new Formatter(string);

            format.format(" Type:    %s\n",this._typeBatiment);
            format.format(" IntervalProd:   %d\n",this._intervalProd);

            format.format(" Icones:{\n");
            for(HashMap.Entry<TypeIcone, ImageIcon> entry : _icones.entrySet()) {
                TypeIcone key = entry.getKey();
                ImageIcon value = entry.getValue();
                String nomImage=new File(value.getDescription()).getName();

                format.format("  Icone:{\n");
                format.format("   Type: %s\n",key.toString());
                format.format("   Nom: %s\n",nomImage);
                format.format("  }\n");
            }
            format.format(" }\n");

            format.format(" Production:{\n");
            for(HashMap.Entry<String,Integer> entry: _production.entrySet()){
                String type=entry.getKey();
                Integer quantite=entry.getValue();

                format.format("  Entree:{\n");
                format.format("   Type: %s\n",type);
                format.format("   Quantite: %d\n",quantite);
                format.format("  }\n");
            }
            format.format(" }\n");


            string.append("}");
            return string.toString();
        }
    }
}
