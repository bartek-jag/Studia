import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Zad2 {

    private static final String XMLFileName = "zad1.xml";

    public static void main(String[] args) {
        FileSupport obs = new FileSupport();
        Document document = obs.open(XMLFileName);
        /*
         * Zadanie 2. Napisz program wykorzystujący DOM, który dla dowolnego pliku XML
         * (może zostać pobrany jako argument) do wszystkich elementów bezpośrednio
         * podrzędnych (dzieci) do elementu głównego, doda atrybut aktywny="tak".
         * Program pownien działać dla wszystkich plików o dowolnej zawartości.
         */
        Node g = document.getDocumentElement();
        NodeList nodeList = g.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++)
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE)
                ((Element) nodeList.item(i)).setAttribute("aktywny", "tak");
        obs.save(XMLFileName, document);
    }
}
