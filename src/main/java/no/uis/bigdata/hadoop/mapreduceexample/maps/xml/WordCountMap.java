package no.uis.bigdata.hadoop.mapreduceexample.maps.xml;

import no.uis.bigdata.hadoop.common.model.jaxb.Page;
import no.uis.bigdata.hadoop.common.model.jaxb.RevisionType;
import no.uis.bigdata.hadoop.xml.model.XmlKey;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordCountMap extends Mapper<XmlKey<Page>, NullWritable, Text, LongWritable> {
    @Override
    protected void map(XmlKey<Page> key, NullWritable value, Context context) throws IOException, InterruptedException {
        for (Object o : key.getData().getRevisionOrUpload()) {
            if (!(o instanceof RevisionType)) {
                continue;
            }
            RevisionType revisionType = (RevisionType) o;
            for (String s : revisionType.getText().getValue().toString().split(" ")) {
                context.write(new Text(s), new LongWritable(1));
            }
        }
    }

}


