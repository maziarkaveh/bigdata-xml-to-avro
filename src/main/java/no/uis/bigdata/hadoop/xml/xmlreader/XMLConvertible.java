package no.uis.bigdata.hadoop.xml.xmlreader;

import javax.xml.bind.JAXBException;

/**
 * Created with IntelliJ IDEA.
 * User: maziarkaveh
 * Date: 24.11.13
 * Time: 18:04
 * To change this template use File | Settings | File Templates.
 */
public interface XMLConvertible<T> {

    String marshall(T o) throws Exception;

    T unMarshall(String str) throws Exception;

}
