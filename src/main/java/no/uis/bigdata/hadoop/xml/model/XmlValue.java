package no.uis.bigdata.hadoop.xml.model;

import no.uis.bigdata.hadoop.common.model.XMLConvertible;

/**
 * Created with IntelliJ IDEA.
 * User: maziarkaveh
 * Date: 24.11.13
 * Time: 17:22
 * To change this template use File | Settings | File Templates.
 */
public class XmlValue<T extends XMLConvertible<T>> extends XmlWrapper<T> {
    public XmlValue(Class<T> clazz) {
        super(clazz);
    }

    public XmlValue(T data) {
        super(data);
    }
}
