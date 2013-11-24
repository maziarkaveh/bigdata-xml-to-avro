package no.uis.bigdata.hadoop.common.model.jaxb.parser;

import no.uis.bigdata.hadoop.common.model.jaxb.Page;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: maziarkaveh
 * Date: 18.10.13
 * Time: 13:24
 * To change this template use File | Settings | File Templates.
 */
public class XMLPageUnMarshaller {

    public static synchronized Unmarshaller getNewInstance() throws JAXBException {
        Unmarshaller unmarshaller = JAXBContext.newInstance("no.uis.bigdata.hadoop.common.model.jaxb").createUnmarshaller();
        return unmarshaller;
    }

    public static synchronized Page unMarshallFromString(String string) throws JAXBException {
        StringReader stringReader = new StringReader(string);
        JAXBElement<Page> unmarshal = (JAXBElement) getNewInstance().unmarshal(stringReader);
        return unmarshal.getValue();
    }

    private XMLPageUnMarshaller() {
    }

    public static synchronized Page unMarshallFromDataInput(DataInput dataInput) throws JAXBException, IOException {
        StringBuffer stringBuffer = new StringBuffer();

        for (; ; ) {
            try {
                stringBuffer.append(dataInput.readUTF());
            } catch (IOException e) {
                break;
            }
        }
        return unMarshallFromString(stringBuffer.toString());
    }
}
