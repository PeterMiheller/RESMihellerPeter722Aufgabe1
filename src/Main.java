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

        gamesCapacity(events);
        galacticConfruntation(events);
        casesPerKonfrontation(events);


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
    public static void gamesCapacity(List<Hero> heros) {
        System.out.println("Enter a number: ");
        Scanner scanner = new Scanner(System.in);
        int inputCap = Integer.parseInt(scanner.nextLine());
        heros.stream().filter(c -> c.getGlobalerEinfluss() >= inputCap).toList().stream()
                .map(record -> record.getHeld()).forEach(System.out::println);

    }
    public static void galacticConfruntation(List<Hero> cases) {
        System.out.println("\nAll galactic confruntation: ");
        cases.stream().filter(c -> c.getKonfrontationstyp().equals("Galaktisch")).sorted(Comparator.comparing(Hero::getDate).reversed()).map(c -> c.getDate() + ": " + c.getHeld() + " - vs : " + c.getAntagonist()).forEach(System.out::println);
    }

    public static void casesPerKonfrontation(List<Hero> cases) {
        Map<String, Integer> numberCases = new HashMap<>();
        for (Hero c : cases) {
            String hospital = c.getKonfrontationstyp();
            numberCases.put(hospital, numberCases.getOrDefault(hospital, 0) + 1);
        }

        List<Map.Entry<String, Integer>> sortedCases = numberCases.entrySet().stream().sorted((c1, c2) -> {
            int countCompare = Integer.compare(c2.getValue(), c1.getValue());//absteigend
            if (countCompare == 0) {// 0 inseamna ca sunt egale valorile
                return c1.getKey().compareTo(c2.getKey());// Ascending by city name
            }
            return countCompare;
        }).toList();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/bericht_konfrontationen.txt"))) {
            for (Map.Entry<String, Integer> entry : sortedCases) {
                bw.write(entry.getKey() + "&" + entry.getValue());
                bw.newLine();
            }
            System.out.println("\n saved to 'bericht_konfrontationen.txt'.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

    }







}