import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

class zad1 extends DefaultHandler {
    /*
     * Zadanie 1. Napisz program wykorzystujący SAX, który dla dowolnego pliku XML
     * policzy i wyświetli w konsoli informacji ile dany plik zawiera elementów.
     */
    private static final String xmlFileName = "personal.xml";
    private static int elementsQuantity = 0;

    public void startElement(String uri, String localName, String qName, Attributes atts) {
        elementsQuantity++;
    }

    public void endDocument() {
        System.out.println("Ilość elementów: " + elementsQuantity);
    }

    public static void main(String[] args) {
        ParserSupport myParser = new ParserSupport();
        myParser.performDemo(xmlFileName, new zad1());
    }
}