package no.uis.bigdata.hadoop.mapreduceexample.xml.maps;

import no.uis.bigdata.hadoop.common.model.avro.Page;
import no.uis.bigdata.hadoop.common.model.avro.RevisionType;
import org.apache.avro.mapred.AvroKey;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class ContributorsStatisticsMap extends Mapper<AvroKey<Page>, NullWritable, Text, LongWritable> {
    @Override
    protected void map(AvroKey<Page> key, NullWritable value, Context context) throws IOException, InterruptedException {
        if(key.datum().revisionOrUpload.size()>1){
            System.out.printf("");
        }
        for (Object o : key.datum().revisionOrUpload) {
            if (!(o instanceof RevisionType)) {
                continue;
            }
            RevisionType revisionType = (RevisionType) o;
            if(revisionType.contributor!=null && !StringUtils.isEmpty(String.valueOf(revisionType.contributor.username))) {
                context.write(new Text(String.valueOf(revisionType.contributor.username)), new LongWritable(1));
            }
        }
    }

}


