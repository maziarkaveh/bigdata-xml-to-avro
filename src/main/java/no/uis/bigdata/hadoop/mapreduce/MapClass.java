package no.uis.bigdata.hadoop.mapreduce;

import no.uis.bigdata.hadoop.model.PageWrapperWritable;
import no.uis.bigdata.hadoop.service.XMLPageUnMarshaller;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MapClass extends Mapper<LongWritable, Text,  PageWrapperWritable,NullWritable> {

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        PageWrapperWritable pageWrapperWritable = null;
        try {

            pageWrapperWritable = new PageWrapperWritable(XMLPageUnMarshaller.unMarshallFromString(value.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        context.write(  pageWrapperWritable,NullWritable.get());
    }
}