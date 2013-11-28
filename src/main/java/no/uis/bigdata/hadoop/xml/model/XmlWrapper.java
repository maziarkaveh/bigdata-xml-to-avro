package no.uis.bigdata.hadoop.xml.model;

import no.uis.bigdata.hadoop.xml.xmlreader.XMLConvertible;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created with IntelliJ IDEA.
 * User: maziarkaveh
 * Date: 24.11.13
 * Time: 17:19
 * To change this template use File | Settings | File Templates.
 */
public abstract class XmlWrapper<T extends XMLConvertible<T>> {
    private static final Logger log =
            LoggerFactory.getLogger(XmlWrapper.class);
    private T data;

    public XmlWrapper(Class<T> clazz) {
        try {
             data   = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public XmlWrapper(T data) {
        this.data = data;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XmlWrapper that = (XmlWrapper) o;

        if (data != null ? !data.equals(that.data) : that.data != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return data != null ? data.hashCode() : 0;
    }
    public Class<T> getTypeParameterClass()
    {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;
        return (Class<T>) paramType.getActualTypeArguments()[0];
    }

    @Override
    public String toString() {
        return "XmlWrapper{" +
                "data=" + data +
                '}';
    }
}
