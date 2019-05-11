import org.w3c.dom.*;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSParser;
import org.w3c.dom.ls.LSSerializer;

import java.io.FileOutputStream;

// rejestr do tworzenia implementacji DOM
// Implementacja DOM Level 3 Load & Save

public class Zad4 {
    public static void main(String[] args) {
        if (args.length == 0) {
            printUsage();
            System.exit(1);
        }
        try {
            System.setProperty(DOMImplementationRegistry.PROPERTY, "org.apache.xerces.dom.DOMXSImplementationSourceImpl");
            DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
            DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("LS");
            LSParser builder = impl.createLSParser(DOMImplementationLS.MODE_SYNCHRONOUS, null);
            DOMConfiguration config = builder.getDomConfig();
            System.out.println("Parsowanie " + args[0] + "...");
            // sparsowanie dokumentu i pozyskanie "document" do dalszej pracy
            Document document = builder.parseURI(args[0]);

            //Zadanie 2. Napisz program, który dla dowolnego pliku XML (może zostać pobrany jako argument)
            //do wszystkich elementów bezpośrednio podrzędnych (dzieci) do elementu głównego, doda atrybut
            //aktywny="tak". Program pownien działać dla wszystkich plików o dowolnej zawartości.

            Node g = document.getDocumentElement();
            NodeList nodeList = g.getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++){
                if(nodeList.item(i).getNodeType() == Node.ELEMENT_NODE)
                    if (((Element)(((Element)nodeList.item(i)).getElementsByTagName("adres").item(0))).getAttributeNode("aktualny").getValue().equals("nie")){
                        nodeList.item(i).removeChild(((Element) nodeList.item(i)).getElementsByTagName("adres").item(0));
                    }
            }

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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printUsage() {
        System.err.println("usage: java Dom3Demo uri");
        System.err.println();
        System.err.println("NOTE: You can only validate DOM tree against XML Schemas.");
    }
}
