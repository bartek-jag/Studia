import org.w3c.dom.*;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSParser;
import org.w3c.dom.ls.LSSerializer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;

// rejestr do tworzenia implementacji DOM
// Implementacja DOM Level 3 Load & Save

public class Zad3 {
    public static void main(String[] args) {

        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement("faktura");
            document.appendChild(root);

            // employee element
            Element klienci = document.createElement("klienci");
            Element produkty = document.createElement("produkty");

            root.appendChild(klienci);
            root.appendChild(produkty);

            Element osoba = document.createElement("osoba");
            Element imie = document.createElement("imie");
            Element nazwisko = document.createElement("nazwisko");
            imie.setTextContent("Jan");
            nazwisko.setTextContent("Kowalski");
            osoba.setAttribute("plec", "m");
            osoba.appendChild(imie);
            osoba.appendChild(nazwisko);

            klienci.appendChild(osoba);

            osoba = (Element) osoba.cloneNode(true);
            osoba.getElementsByTagName("imie").item(0).setTextContent("Anna");
            osoba.getElementsByTagName("nazwisko").item(0).setTextContent("Kowalska");
            osoba.getAttributeNode("plec").setValue("k");
            klienci.appendChild(osoba);

            Element produkt = document.createElement("produkt");
            produkt.setAttribute("id", "1");
            produkt.setAttribute("kod", "7501054530107");
            produkt.setAttribute("rodzaj", "chemia");
            Element nazwa = document.createElement("nazwa");
            Element ilosc = document.createElement("ilosc");
            Element cena = document.createElement("cena");
            nazwa.setTextContent("Proszek do prania");
            ilosc.setTextContent("3");
            cena.setTextContent("24.99");
            cena.setAttribute("waluta", "zł");
            produkt.appendChild(nazwa);
            produkt.appendChild(ilosc);
            produkt.appendChild(cena);
            produkty.appendChild(produkt);

            produkt = (Element) produkt.cloneNode(true);
            produkt.getAttributeNode("id").setValue("2");
            produkt.getAttributeNode("kod").setValue("7213052740210");
            produkt.getAttributeNode("rodzaj").setValue("spożywczy");
            produkt.getElementsByTagName("nazwa").item(0).setTextContent("Bułka tarta");
            produkt.removeChild(produkt.getElementsByTagName("ilosc").item(0));
            produkt.getElementsByTagName("cena").item(0).setTextContent("6.30");
            produkty.appendChild(produkt);

            produkt = (Element) produkt.cloneNode(true);
            produkt.getAttributeNode("id").setValue("3");
            produkt.getAttributeNode("kod").setValue("7501054530108");
            produkt.getAttributeNode("rodzaj").setValue("chemia");
            produkt.getElementsByTagName("nazwa").item(0).setTextContent("Płyn do mycia naczyń");
            produkt.getElementsByTagName("cena").item(0).setTextContent("11.99");
            produkty.appendChild(produkt);


            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("new_zad3.xml"));

            // If you use
            // StreamResult result = new StreamResult(System.out);
            // the output will be pushed to the standard output ...
            // You can use that for debugging

            transformer.transform(domSource, streamResult);

            System.out.println("Done creating XML File");

        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
    }

    private static void printUsage() {
        System.err.println("usage: java Dom3Demo uri");
        System.err.println();
        System.err.println("NOTE: You can only validate DOM tree against XML Schemas.");
    }
}
