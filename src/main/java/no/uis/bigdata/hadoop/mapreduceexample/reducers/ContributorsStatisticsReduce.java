package no.uis.bigdata.hadoop.mapreduceexample.reducers;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ContributorsStatisticsReduce extends Reducer<Text, LongWritable,Text, LongWritable> {
    private static final Logger log =
            LoggerFactory.getLogger(ContributorsStatisticsReduce.class);
    public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        try {
            long sum = 0;
            for (LongWritable value : values) {
                sum += value.get();
            }
            context.write(key, new LongWritable(sum));
        } catch (Exception e) {
           log.error("",e);
         }
    }
}