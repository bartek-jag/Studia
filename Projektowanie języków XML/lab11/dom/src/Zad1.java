import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Zad1 {

    private final static String XMLFileName = "zad1.xml";

    public static void main(String[] args) {
        FileSupport obs = new FileSupport();
        Document document = obs.open(XMLFileName);
        /*
         * Zadanie 1. Napisz program wykorzystujący DOM, który dla pliku XML doda nową
         * osobę o dowolnym imieniu i nazwisku.
         */
        Element newElem = document.createElement("osoba");
        Element imie = document.createElement("imie");
        imie.setTextContent("Bronek");
        Element nazwisko = document.createElement("nazwisko");
        nazwisko.setTextContent("Komar");
        newElem.appendChild(imie);
        newElem.appendChild(nazwisko);
        document.getFirstChild().insertBefore(newElem, null);

        obs.save(XMLFileName, document);
    }
}
