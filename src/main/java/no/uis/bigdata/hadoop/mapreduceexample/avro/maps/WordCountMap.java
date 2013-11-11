package no.uis.bigdata.hadoop.mapreduceexample.avro.maps;

import no.uis.bigdata.hadoop.common.model.avro.Page;
import no.uis.bigdata.hadoop.common.model.avro.RevisionType;
import org.apache.avro.mapred.AvroKey;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordCountMap extends Mapper<AvroKey<Page>, NullWritable, Text, NullWritable> {
    @Override
    protected void map(AvroKey<Page> key, NullWritable value, Context context) throws IOException, InterruptedException {
        for (Object o : key.datum().revisionOrUpload) {
            if(o instanceof RevisionType){
                RevisionType revisionType=(RevisionType) o;
                System.out.println(new Text(revisionType.text.value.toString()));
                context.write(new Text(revisionType.text.value.toString()), NullWritable.get());
            }
        }

    }



}