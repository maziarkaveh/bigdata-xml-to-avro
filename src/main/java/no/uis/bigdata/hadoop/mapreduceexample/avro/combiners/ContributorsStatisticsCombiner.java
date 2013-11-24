package no.uis.bigdata.hadoop.mapreduceexample.avro.combiners;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ContributorsStatisticsCombiner extends Reducer<Text, LongWritable, Text, LongWritable> {

    public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        try {
            long sum = 0;
            for (LongWritable value : values) {
                sum += value.get();
            }
            context.write(key, new LongWritable(sum));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}