import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

class zad3 extends DefaultHandler {
    /*
     * Zadanie 3. Napisz program wykorzystujący SAX, który dla pliku XML dla każdego
     * elementu o nazwie grupa, które zawierają atrybut aktywny="tak" wyświetli w
     * konsoli sumę i średnią elementów liczba. Program powinien działać dla
     * dowolnych danych.
     */
    private static final String xmlFileName = "zad3.xml";
    private static int elementsQuantity = 0;
    private static Integer elementsSum = 0;
    private boolean inElementGrupa = false;
    private boolean inElementLiczba = false;

    public void startElement(String uri, String localName, String qName, Attributes atts) {
        if (qName.equals("grupa"))
            if (atts.getValue("aktywny").equals("tak"))
                inElementGrupa = true;

        if (qName.equals("liczba") && inElementGrupa){
            elementsQuantity++;
            inElementLiczba = true;
        }
    }

    public void characters(char[] ch, int start, int length){
        if (inElementLiczba){
            String liczba = new String(ch, start, length);
            if (!liczba.equals(""))
                elementsSum += Integer.parseInt(liczba);
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("grupa")) inElementGrupa = false;
        if (qName.equals("liczba")) inElementLiczba = false;
    }

    public void endDocument() {
        System.out.println("Ilość elementów: " + elementsQuantity);
        System.out.println("Średnia elementów: " + elementsSum.doubleValue()/elementsQuantity);
    }

    public static void main(String[] args) {
        ParserSupport myParser = new ParserSupport();
        myParser.performDemo(xmlFileName, new zad3());
    }
}