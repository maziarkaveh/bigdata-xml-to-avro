package no.uis.bigdata.hadoop.mapreduceexample.avro.reducers;

import no.uis.bigdata.hadoop.common.model.avro.Page;
import no.uis.bigdata.hadoop.xmltoavro.model.PageWrapperWritable;
import org.apache.avro.mapred.AvroWrapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountReduce extends Reducer<Text, LongWritable, AvroWrapper<Page>,
        NullWritable> {

    public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        try {

            context.write(new AvroWrapper<Page>(null), NullWritable.get());
        } catch (Exception e) {
            return;
        }
    }
}