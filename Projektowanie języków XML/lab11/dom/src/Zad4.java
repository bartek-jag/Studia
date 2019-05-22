import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Zad4 {

    private static final String XMLFileName = "zad4.xml";

    public static void main(String[] args) {
        FileSupport obs = new FileSupport();
        Document document = obs.open(XMLFileName);
        /*
         * Zadanie 4. Napisz program wykorzystujący DOM, który usunie z pliku XML,
         * adresy (elementy adres), które mają atrybut aktualny="nie". Program pownien
         * działać dla dowolnych danych.
         */
        Node g = document.getDocumentElement();
        NodeList nodeList = g.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE)
                if (((Element) (((Element) nodeList.item(i)).getElementsByTagName("adres").item(0)))
                        .getAttributeNode("aktualny").getValue().equals("nie")) {
                    nodeList.item(i).removeChild(((Element) nodeList.item(i)).getElementsByTagName("adres").item(0));
                }
        }
        obs.save(XMLFileName, document);
    }
}
