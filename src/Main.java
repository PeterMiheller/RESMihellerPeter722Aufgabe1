import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * The main class that handles the applications
 */
public class Main {

    /**
     * The main entry point of the application.
     * Reads  data from a file and processes it.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        String filePath = System.getProperty("user.dir") + "\\src\\marvel_konfrontationen.xml";
        List<Hero> events = readXMLFile(filePath);





    }



    /**
     * Reads an XML file and parses it into a list
     * @param filename  the path to the file
     * @return a list of parsed objects
     */
    public static List<Hero> readXMLFile(String filename) {
        List<Hero> events = new ArrayList<>();
        try {
            // Initialize the XML parser
            File xmlFile = new File(filename);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            // Normalize the XML structure
            document.getDocumentElement().normalize();

            // Get all "log" elements
            NodeList nodeList = document.getElementsByTagName("log");

            // Iterate through each "log"
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Extract values from the XML
                    int id = Integer.parseInt(element.getElementsByTagName("Id").item(0).getTextContent().trim());
                    String held = element.getElementsByTagName("Held").item(0).getTextContent().trim();
                    String antagonist = element.getElementsByTagName("Antagonist").item(0).getTextContent().trim();
                    String konfrontationstyp = element.getElementsByTagName("Konfrontationstyp").item(0).getTextContent().trim();
                    String ort = element.getElementsByTagName("Ort").item(0).getTextContent().trim();
                    String datum = element.getElementsByTagName("Datum").item(0).getTextContent().trim();
                    Double globalerEinfluss = Double.parseDouble(element.getElementsByTagName("GlobalerEinfluss").item(0).getTextContent().trim());

                    events.add(new Hero(id, held, antagonist, konfrontationstyp, ort, datum, globalerEinfluss));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return events;
    }



}