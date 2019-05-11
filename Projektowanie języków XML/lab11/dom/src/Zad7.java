import org.w3c.dom.*;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSParser;
import org.w3c.dom.ls.LSSerializer;

import java.io.FileOutputStream;

// rejestr do tworzenia implementacji DOM
// Implementacja DOM Level 3 Load & Save

public class Zad7 {
    public static Document document;
    public static void main(String[] args) {
        if (args.length == 0) {
            printUsage();
            System.exit(1);
        }

        try {
            /*
             * ustawienie systemowej wlasnosci (moze byc dokonane w innym
             * miejscu, pliku konfiguracyjnym w systemie itp.) konkretna
             * implementacja DOM
             */
            System.setProperty(DOMImplementationRegistry.PROPERTY,
                    "org.apache.xerces.dom.DOMXSImplementationSourceImpl");
            DOMImplementationRegistry registry = DOMImplementationRegistry
                    .newInstance();

            // pozyskanie implementacji Load & Save DOM Level 3 z rejestru
            DOMImplementationLS impl = (DOMImplementationLS) registry
                    .getDOMImplementation("LS");

            // stworzenie DOMBuilder
            LSParser builder = impl.createLSParser(
                    DOMImplementationLS.MODE_SYNCHRONOUS, null);

            // pozyskanie konfiguratora - koniecznie zajrzec do dokumentacji co
            // mozna poustawiac
            DOMConfiguration config = builder.getDomConfig();

            // stworzenie DOMErrorHandler i zarejestrowanie w konfiguratorze
            DOMErrorHandler errorHandler = getErrorHandler();
            config.setParameter("error-handler", errorHandler);

            // set validation feature
            config.setParameter("validate", Boolean.TRUE);

            // set schema language
            config.setParameter("schema-type",
                    "http://www.w3.org/2001/XMLSchema");

            // set schema location
            config.setParameter("schema-location",  args[1]);

            System.out.println("Parsowanie " + args[0] + "...");
            // sparsowanie dokumentu i pozyskanie "document" do dalszej pracy
            document = builder.parseURI(args[0]);

            // praca z dokumentem, modyfikacja zawartosci etc... np.
            // tutaj dodanie nowego pracownika poprzez skopiowanie innego

            //Zadanie 7. Napisz program, który dla pliku XML pobierze osobę, której atrybut id="k331"
            // oraz zmieni jej dane na Jan Kowalski i atrybut płeć na m. Stwórz odpowiedni plik XML-Schema
            // oraz pobierz dane osoby korzystając z metody getElementById.
            //Podpowiedź 1: plik XML-Schema powinieć mieć atrybut typu xsd:ID.
            //Podpowiedź 2: skorzystaj z konfiguracji danych XML na schematem.
            Element elem = document.getElementById("k331");
            elem.getElementsByTagName("imie").item(0).setTextContent("Jan");
            elem.getElementsByTagName("nazwisko").item(0).setTextContent("Kowalski");
            elem.getAttributeNode("plec").setValue("m");

            // pozyskanie serializatora
            LSSerializer domWriter = impl.createLSSerializer();
            // pobranie konfiguratora dla serializatora
            config = domWriter.getDomConfig();
            config.setParameter("xml-declaration", Boolean.TRUE);

            // pozyskanie i konfiguracja Wyjscia
            LSOutput dOut = impl.createLSOutput();
            dOut.setEncoding("latin2");
            dOut.setByteStream(new FileOutputStream("new_" + args[0]));

            System.out.println("Serializing document... ");
            domWriter.write(document, dOut);

            // Wyjscie na ekran
            // dOut.setByteStream(System.out);
            // domWriter.writeNode(System.out, document);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printUsage() {

        System.err.println("usage: java Dom3Demo uri");
        System.err.println();
        System.err
                .println("NOTE: You can only validate DOM tree against XML Schemas.");

    }

    public static DOMErrorHandler getErrorHandler() {
        return new DOMErrorHandler() {
            public boolean handleError(DOMError error) {
                short severity = error.getSeverity();
                if (severity == error.SEVERITY_ERROR) {
                    System.out.println("[dom3-error]: " + error.getMessage());
                }
                if (severity == error.SEVERITY_WARNING) {
                    System.out.println("[dom3-warning]: " + error.getMessage());
                }
                if (severity == error.SEVERITY_FATAL_ERROR) {
                    System.out.println("[dom3-fatal-error]: "
                            + error.getMessage());
                }
                return true;
            }
        };
    }

}
