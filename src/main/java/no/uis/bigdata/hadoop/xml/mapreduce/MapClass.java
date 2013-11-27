package no.uis.bigdata.hadoop.xml.mapreduce;

import no.uis.bigdata.hadoop.common.model.jaxb.Page;
import no.uis.bigdata.hadoop.xml.model.XmlValue;
import org.apache.avro.mapred.AvroValue;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MapClass extends Mapper<LongWritable  ,XmlValue<Page>, Text, AvroValue<no.uis.bigdata.hadoop.common.model.avro.Page>> {

    public void map(LongWritable key, XmlValue<Page> value, Context context) throws IOException, InterruptedException {
        try {

            no.uis.bigdata.hadoop.common.model.avro.Page page = new no.uis.bigdata.hadoop.common.model.avro.Page(value.getData());
            AvroValue<no.uis.bigdata.hadoop.common.model.avro.Page> avroValue = new AvroValue<no.uis.bigdata.hadoop.common.model.avro.Page>(page);
            context.write(new Text(page.id.toString()), avroValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}