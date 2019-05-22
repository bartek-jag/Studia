import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Zad5 {

    private static final String XMLFileName = "zad5.xml";

    public static void main(String[] args) {
        FileSupport obs = new FileSupport();
        Document document = obs.open(XMLFileName);
        /*
         * Zadanie 5. Napisz program wykorzystujący DOM, który dla pliku XML
         * zawierającego dane element osoba z podelementami imie, nazwisko, wiek usunie
         * dane osób, które są młodsze niż 40 lat oraz zmiejszy wiek o 30 pozostałych
         * osób. Program powinien działać dla dowolnych danych, zakładając, że w pliku
         * znajduje się przynajmniej jedna osoba.
         */
        Node g = document.getDocumentElement();
        NodeList nodeList = g.getChildNodes();
        int wiek = 0;
        if (nodeList.getLength() == 0)
            System.exit(1);
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                wiek = Integer
                        .parseInt(((Element) nodeList.item(i)).getElementsByTagName("wiek").item(0).getTextContent());
                if (wiek < 40)
                    g.removeChild(nodeList.item(i));
                else
                    ((Element) nodeList.item(i)).getElementsByTagName("wiek").item(0)
                            .setTextContent(String.valueOf(wiek - 30));
            }
        }
        obs.save(XMLFileName, document);
    }
}
