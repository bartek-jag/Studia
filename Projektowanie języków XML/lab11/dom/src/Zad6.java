import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Zad6 {

    private static final String XMLFileName = "zad6.xml";

    public static void main(String[] args) {
        FileSupport obs = new FileSupport();
        Document document = obs.open(XMLFileName);
        /*
         * Zadanie 6. Napisz program wykorzystujący DOM, który dla pliku XML dla każdego
         * elementu o nazwie grupa, które zawierają atrybut aktywny="tak" doda element
         * suma i srednia w którym umieszczone zostaną odpowiednie wartości oraz usuń
         * wszystkie atrybuty aktywny. Program powinien działać dla dowolnych danych.
         */
        Node g = document.getDocumentElement();
        NodeList nodeList = g.getChildNodes();
        if (nodeList.getLength() == 0)
            System.exit(1);
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                if (((Element) nodeList.item(i)).getAttributeNode("aktywny").getValue().equals("tak")) {
                    Element suma = document.createElement("suma");
                    int sum = 0;
                    Element srednia = document.createElement("suma");
                    NodeList items = nodeList.item(i).getChildNodes();
                    for (int j = 0; j < items.getLength(); j++) {
                        if (items.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            // System.out.println(items.item(j)).getTextContent() + "a");
                            sum += Integer.parseInt(items.item(j).getTextContent());
                        }
                    }
                    suma.setTextContent(Integer.toString(sum));
                    srednia.setTextContent(Double.toString(sum / items.getLength()));
                    nodeList.item(i).appendChild(suma);
                    nodeList.item(i).appendChild(srednia);

                }
                ((Element) nodeList.item(i)).removeAttribute("aktywny");
            }
        }
        obs.save(XMLFileName, document);
    }
}
