package no.uis.bigdata.hadoop.model;

import no.uis.bigdata.hadoop.model.jaxb.Page;
import no.uis.bigdata.hadoop.service.XMLPageMarshaller;
import no.uis.bigdata.hadoop.service.XMLPageUnMarshaller;
import org.apache.hadoop.io.WritableComparable;

import javax.xml.bind.JAXBException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: maziarkaveh
 * Date: 18.10.13
 * Time: 20:59
 * To change this template use File | Settings | File Templates.
 */
public class PageWrapperWritable implements WritableComparable<PageWrapperWritable> {
    private Page value;

    public PageWrapperWritable(Page value) {
        this.value = value;
    }

    public PageWrapperWritable() {
    }

    public Page getValue() {
        return value;
    }

    @Override
    public int compareTo(PageWrapperWritable pageWrapperWritable) {
        return getValue().getId().compareTo(pageWrapperWritable.getValue().getId());
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        try {
            XMLPageMarshaller.marshallToDataOutput(value, dataOutput);
        } catch (JAXBException e) {
            e.printStackTrace();  //Todo log
        }
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        try {
            this.value = XMLPageUnMarshaller.unMarshallFromDataInput(dataInput);
        } catch (Exception e) {
            e.printStackTrace();  //Todo log
        }
    }
}
