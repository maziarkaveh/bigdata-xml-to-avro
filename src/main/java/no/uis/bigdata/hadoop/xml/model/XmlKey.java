package no.uis.bigdata.hadoop.xml.model;

import no.uis.bigdata.hadoop.xml.xmlreader.XMLConvertible;

/**
 * Created with IntelliJ IDEA.
 * User: maziarkaveh
 * Date: 24.11.13
 * Time: 17:22
 * To change this template use File | Settings | File Templates.
 */
public class XmlKey<T extends XMLConvertible<T>> extends XmlWrapper<T> {
    public XmlKey(Class<T> clazz) {
        super(clazz);
    }

    public XmlKey(T data) {
        super(data);
    }
}