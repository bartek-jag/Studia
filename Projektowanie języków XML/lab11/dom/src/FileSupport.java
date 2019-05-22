import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMErrorHandler;
import org.w3c.dom.Document;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSParser;
import org.w3c.dom.ls.LSSerializer;

import java.io.FileOutputStream;

class FileSupport {
    private DOMConfiguration config;
    private DOMImplementationLS impl;

    Document open(String xmlFileName) {
        try {
            System.setProperty(DOMImplementationRegistry.PROPERTY, "org.apache.xerces.dom.DOMXSImplementationSourceImpl");
            DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
            impl = (DOMImplementationLS) registry.getDOMImplementation("LS");
            LSParser builder = impl.createLSParser(DOMImplementationLS.MODE_SYNCHRONOUS, null);
            config = builder.getDomConfig();

            System.out.println("Parsowanie " + xmlFileName + "...");
            // sparsowanie dokumentu i pozyskanie "document" do dalszej pracy
            return builder.parseURI(xmlFileName);
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    Document open(String xmlFileName, String xsdFileName) {
        if (xmlFileName.length() == 0 || xsdFileName.length() == 0) {
            printUsage();
            System.exit(1);
        }
        try {
            System.setProperty(DOMImplementationRegistry.PROPERTY, "org.apache.xerces.dom.DOMXSImplementationSourceImpl");
            DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
            impl = (DOMImplementationLS) registry.getDOMImplementation("LS");
            LSParser builder = impl.createLSParser(DOMImplementationLS.MODE_SYNCHRONOUS, null);
            config = builder.getDomConfig();
            DOMErrorHandler errorHandler = getErrorHandler();
            config.setParameter("error-handler", errorHandler);
            config.setParameter("validate", Boolean.TRUE);
            config.setParameter("schema-type", "http://www.w3.org/2001/XMLSchema");
            config.setParameter("schema-location", xsdFileName);
            System.out.println("Parsowanie " + xmlFileName + "...");
            // sparsowanie dokumentu i pozyskanie "document" do dalszej pracy
            return builder.parseURI(xmlFileName);
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    void save(String xmlFileName, Document document) {
        try {
            // pozyskanie serializatora
            LSSerializer domWriter = impl.createLSSerializer();
            // pobranie konfiguratora dla serializatora
            config = domWriter.getDomConfig();
            config.setParameter("xml-declaration", Boolean.TRUE);

            // pozyskanie i konfiguracja Wyjscia
            LSOutput dOut = impl.createLSOutput();
            dOut.setEncoding("latin2");
            dOut.setByteStream(new FileOutputStream("new_" + xmlFileName));

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

    private static DOMErrorHandler getErrorHandler() {
        return error -> {
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
        };
    }
}