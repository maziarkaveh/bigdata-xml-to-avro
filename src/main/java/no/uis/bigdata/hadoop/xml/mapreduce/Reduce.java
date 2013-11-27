package no.uis.bigdata.hadoop.xml.mapreduce;

import no.uis.bigdata.hadoop.common.model.avro.Page;
import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class Reduce extends Reducer<Text, AvroValue<Page>,
        AvroKey<Page>,NullWritable> {

    public void reduce(Text key, Iterable<AvroValue<Page>> values, Context context) throws IOException, InterruptedException {
        for (AvroValue value : values) {
            context.write(new AvroKey<Page>((Page) value.datum()), NullWritable.get());
        }

    }
}