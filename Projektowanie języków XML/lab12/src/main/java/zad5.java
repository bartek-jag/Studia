import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

class zad5 extends DefaultHandler {
    /*
     * Zadanie 5. Napisz program wykorzystujący SAX, który dla pliku XML wyświetli w
     * konsoli statystykę będącą średnią wieku osób zamieszkałych w poszczególnych
     * miastach. Program powinien działać dla dowolnych danych. Przykład: 
     * Gdańsk: 43.76 
     * Gdynia: 33.31 
     * Sopot: 31.76
     */
    private static final String xmlFileName = "zad5.xml";
    private boolean inElementOsoba = false;
    private boolean inElementWiek = false;
    private boolean inElementMiejsce = false;

    private int lastAge;

    private Map<String, Integer> sum = new HashMap<String, Integer>();
    private Map<String, Integer> quantity = new HashMap<String, Integer>();

    public void startElement(String uri, String localName, String qName, Attributes atts) {
        // System.out.println(uri + " " + localName + " " + qName);
        if (qName.equals("osoba"))
            inElementOsoba = true;
        else if (qName.equals("wiek"))
            inElementWiek = true;
        else if (qName.equals("miejsce"))
            inElementMiejsce = true;

    }

    public void characters(char[] ch, int start, int length) {
        // System.out.println("b");
        if (inElementMiejsce) {
            String miejsce = new String(ch, start, length);
            if (!sum.containsKey(miejsce)) {
                sum.put(miejsce, lastAge);
                quantity.put(miejsce, 1);
            } else {
                Integer sumwiek = sum.get(miejsce);
                int quantitywiek = quantity.get(miejsce);
                sum.remove(miejsce);
                quantity.remove(miejsce);
                sum.put(miejsce, sumwiek + lastAge);
                quantity.put(miejsce, quantitywiek + 1);
            }
        }

        if (inElementWiek) {
            lastAge = Integer.parseInt(new String(ch, start, length));
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("osoba"))
            inElementOsoba = false;
        if (qName.equals("wiek"))
            inElementWiek = false;
        if (qName.equals("miejsce"))
            inElementMiejsce = false;
    }

    public void endDocument() {
        for (String s : sum.keySet()) {
            System.out.println(s + ": " + sum.get(s).doubleValue() / quantity.get(s));
        }
    }

    public static void main(String[] args) {
        ParserSupport myParser = new ParserSupport();
        myParser.performDemo(xmlFileName, new zad5());
    }
}