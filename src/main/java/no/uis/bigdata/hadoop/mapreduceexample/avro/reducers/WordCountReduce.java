package no.uis.bigdata.hadoop.mapreduceexample.avro.reducers;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountReduce extends Reducer<Text, LongWritable,  LongWritable,Text> {

    public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        try {
            long sum = 0;
            for (LongWritable value : values) {
                sum += value.get();
            }
            context.write(  new LongWritable(sum),key);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}