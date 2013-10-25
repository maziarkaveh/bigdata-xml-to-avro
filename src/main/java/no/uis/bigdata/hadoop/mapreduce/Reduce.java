package no.uis.bigdata.hadoop.mapreduce;

import no.uis.bigdata.hadoop.model.PageWrapperWritable;
import no.uis.bigdata.hadoop.model.avro.Page;
import org.apache.avro.mapred.AvroJob;
import org.apache.avro.mapred.AvroWrapper;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class Reduce extends Reducer<PageWrapperWritable, NullWritable, AvroWrapper<Page>,
        NullWritable> {

    public void reduce(PageWrapperWritable key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        try {
            Page page = new Page(key.getValue());
            context.write(new AvroWrapper<Page>(page), NullWritable.get());
        } catch (Exception e) {
            return;
        }
    }
}