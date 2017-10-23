package Main.Helpers;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Classe générique contenant des méthodes pour charger des fichiers XML
 */
public class XMLHelper {

    /**
     * Charge un fichier XML
     * @param file Chemin vers le fichier sous formet d'objet File
     * @return Document XML
     */
    public static Document getXMLFile(File file){
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    Document doc=null;
    try{
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(file);
    } catch (ParserConfigurationException |IOException |SAXException e1) {
        e1.printStackTrace();
    }
    return doc;
}
}
