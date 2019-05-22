import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Scanner;

class zad2 extends DefaultHandler {
    /*
     * Zadanie 2. Napisz program wykorzystujący SAX, który dla dowolnego pliku XML
     * policzy i wyświetli w konsoli ile razy występuje element, którego nazwa
     * została pobrana przez użytkownika.
     */
    private static final String xmlFileName = "personal.xml";
    private static int elementsQuantity = 0;
    private static int elementsSum = 0;
    private String name;

    zad2() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwę elementu:");
        name = scanner.nextLine();
        scanner.close();
    }

    public void startElement(String uri, String localName, String qName, Attributes atts) {
        // System.out.println(qName + " " + name);
        if (qName.equals(name))
            elementsQuantity++;
    }

    public void endDocument() {
        System.out.println("Ilość elementów: " + elementsQuantity);
    }

    public static void main(String[] args) {
        ParserSupport myParser = new ParserSupport();
        myParser.performDemo(xmlFileName, new zad2());
    }
}