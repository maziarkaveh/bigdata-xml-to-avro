package no.uis.bigdata.hadoop.xmltoavro.mapreduce;

import no.uis.bigdata.hadoop.xmltoavro.model.PageWrapperWritable;
import no.uis.bigdata.hadoop.common.model.avro.Page;
import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroWrapper;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class Reduce extends Reducer<PageWrapperWritable, NullWritable, AvroKey<Page>,
        NullWritable> {

    public void reduce(PageWrapperWritable key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        try {
            Page page = new Page(key.getValue());
            AvroKey<Page> key1 = new AvroKey<Page>(page);
            context.write(key1, NullWritable.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}