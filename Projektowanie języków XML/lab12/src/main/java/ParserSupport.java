import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.FactoryConfigurationError;
import java.io.IOException;

class ParserSupport {
     void performDemo(String uri, DefaultHandler contentHandler) {
        try {
            // instancja parsera bezposrednio z org.xml.sax.helpers.XMLReaderFactory
            XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");

            // Konfiguracja parsera
            // rejestracja procedur obslugi zawartosci -
            // rejestracja naszej obslugi aplikacji i jej logiki
            parser.setContentHandler(contentHandler);
            // wlaczenie validacji po schema, uwaga na "apache" w nazwie
            parser.setFeature("http://apache.org/xml/features/validation/schema", true);
            // wlaczenie obslugi przestrzeni nazw
            parser.setFeature("http://xml.org/sax/features/namespaces", true);
            // przetwarzanie encji zewnetrznych ogolnych
            parser.setFeature("http://xml.org/sax/features/external-general-entities", true);
            // przetwarzanie encji zewnetrzynych parametrycznych
            parser.setFeature("http://xml.org/sax/features/external-parameter-entities", true);
            // wlasciwe przetwarzanie,
            parser.parse(uri);
        } catch (IOException e) {
            System.out.println("Blad przy wczytywaniu pliku " + uri + e.getMessage());
        } catch (SAXException e) {
            System.out.println("Blad przy przetwarzaniu pliku " + uri + e.getMessage());
        } catch (FactoryConfigurationError e) {
            System.out.println("Blad przy tworzeniu fabryki " + uri + e.getMessage());
        }
    }
}