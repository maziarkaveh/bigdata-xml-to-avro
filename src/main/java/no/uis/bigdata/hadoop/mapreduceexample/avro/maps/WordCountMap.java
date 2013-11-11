package no.uis.bigdata.hadoop.mapreduceexample.avro.maps;

import no.uis.bigdata.hadoop.common.model.avro.Page;
import org.apache.avro.mapred.AvroKey;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordCountMap extends Mapper<AvroKey<Page>, NullWritable, Text, LongWritable> {
    @Override
    protected void map(AvroKey<Page> key, NullWritable value, Context context) throws IOException, InterruptedException {
        System.out.println(key);
    }



}