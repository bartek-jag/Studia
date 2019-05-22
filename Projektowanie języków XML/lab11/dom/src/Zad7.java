import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Zad7 {

    private static final String XMLFileName = "zad7.xml";
    private static final String XSDFileName = "zad7.xsd";

    public static void main(String[] args) {
        FileSupport obs = new FileSupport();
        Document document = obs.open(XMLFileName, XSDFileName);
        /*
         * Zadanie 7. Napisz program wykorzystujący DOM, który dla pliku XML pobierze
         * osobę, której atrybut id="k331" oraz zmieni jej dane na Jan Kowalski i
         * atrybut płeć na m. Stwórz odpowiedni plik XML-Schema oraz pobierz dane osoby
         * korzystając z metody getElementById.
         */
        Element elem = document.getElementById("k331");
        elem.getElementsByTagName("imie").item(0).setTextContent("Jan");
        elem.getElementsByTagName("nazwisko").item(0).setTextContent("Kowalski");
        elem.getAttributeNode("plec").setValue("m");
        obs.save(XMLFileName, document);
    }
}
