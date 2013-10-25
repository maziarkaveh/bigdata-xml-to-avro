package no.uis.bigdata.hadoop.service;

import no.uis.bigdata.hadoop.model.jaxb.Page;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.DataOutput;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: maziarkaveh
 * Date: 18.10.13
 * Time: 13:24
 * To change this template use File | Settings | File Templates.
 */
public class XMLPageMarshaller {

    public static synchronized javax.xml.bind.Marshaller getNewInstance() throws JAXBException {
        Marshaller marshaller = JAXBContext.newInstance("no.uis.bigdata.hadoop.model.jaxb").createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        return marshaller;
    }

    public static synchronized String marshallToString(Page page) throws JAXBException {
        StringWriter stringWriter = new StringWriter();
        getNewInstance().marshal(page, stringWriter);
        return stringWriter.toString();
    }

    public static synchronized void marshallToDataOutput(Page page, DataOutput dataOutput) throws JAXBException, IOException {
        String str = marshallToString(page);
        Scanner scanner = new Scanner(str);

        while (scanner.hasNextLine()) {
            dataOutput.writeUTF(scanner.nextLine());
        }
    }

    private XMLPageMarshaller() {
    }
}
