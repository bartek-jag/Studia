import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

class zad4 extends DefaultHandler {
    /*
     * Zadanie 4. Napisz program wykorzystujący SAX, który dla pliku XML wyświetli w
     * konsoli najmniejszą liczbę, która jest podelementem elementu grupa
     * zawierającą atrybut wartosc="min" oraz największą liczbę, która jest
     * podelementem elementu grupa zawierającą atrybut wartosc="max". Program
     * powinien działać dla dowolnych danych.
     */
    private static final String xmlFileName = "zad4.xml";
    private boolean inElementGrupa = false;
    private boolean inElement = false;
    private boolean min = false;
    private Integer retVal = null;

    public void startElement(String uri, String localName, String qName, Attributes atts) {
        // System.out.println(uri + " " + localName + " " + qName);
        if (qName.equals("grupa")) {
            if (atts.getValue("wartosc") != null) {
                if (atts.getValue("wartosc").equals("min")) {
                    inElementGrupa = true;
                    min = true;
                } else if (atts.getValue("wartosc").equals("max")) {
                    inElementGrupa = true;
                    min = false;
                }
            }
        } else if (inElementGrupa)
            inElement = true;
    }

    public void characters(char[] ch, int start, int length) {
        // System.out.println("b");
        if (inElementGrupa && inElement) {
            // System.out.println("a");
            // System.out.println(new String(ch, start, length));
            int val = Integer.parseInt(new String(ch, start, length));
            // System.out.println(val);
            if (retVal == null)
                retVal = val;
            if (min) {
                if (retVal > val)
                    retVal = val;
            } else if (retVal < val)
                retVal = val;
        }

    }

    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("grupa")) {
            inElementGrupa = false;
            if (min)
                System.out.println("Min: " + retVal);
            else
                System.out.println("Max: " + retVal);
            retVal = null;
        }
        inElement = false;
    }

    public static void main(String[] args) {
        ParserSupport myParser = new ParserSupport();
        myParser.performDemo(xmlFileName, new zad4());
    }
}