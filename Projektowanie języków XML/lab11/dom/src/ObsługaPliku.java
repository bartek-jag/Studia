import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.Document;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSParser;
import org.w3c.dom.ls.LSSerializer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

class ObslugaPliku {
    private DOMConfiguration config;
    private DOMImplementationLS impl;
    Document otworz(String[] args) {

        if (args.length == 0) {
            printUsage();
            System.exit(1);
        }
        try {
            System.setProperty(DOMImplementationRegistry.PROPERTY, "org.apache.xerces.dom.DOMXSImplementationSourceImpl");
            DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
            impl = (DOMImplementationLS) registry.getDOMImplementation("LS");
            LSParser builder = impl.createLSParser(DOMImplementationLS.MODE_SYNCHRONOUS, null);
            config = builder.getDomConfig();
            System.out.println("Parsowanie " + args[0] + "...");
            // sparsowanie dokumentu i pozyskanie "document" do dalszej pracy
            return builder.parseURI(args[0]);
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    void zapisz(String[] args, Document document){
        try{
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