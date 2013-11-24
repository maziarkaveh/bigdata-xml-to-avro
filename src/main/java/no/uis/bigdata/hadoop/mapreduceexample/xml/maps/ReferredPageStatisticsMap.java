package no.uis.bigdata.hadoop.mapreduceexample.xml.maps;

  import no.uis.bigdata.hadoop.common.model.jaxb.Page;
  import no.uis.bigdata.hadoop.common.model.jaxb.RevisionType;
  import no.uis.bigdata.hadoop.xmltoavro.model.XmlKey;
 import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReferredPageStatisticsMap extends Mapper<XmlKey<Page>, NullWritable  , Text, LongWritable> {
    private static final Pattern LINK_PATTERN = Pattern.compile("\\[\\[(.*?)\\]\\]");

    @Override
    protected void map( XmlKey<Page> key, NullWritable value, Context context) throws IOException, InterruptedException {
        for (Object o : key.getData().getRevisionOrUpload()) {
            if (!(o instanceof RevisionType)) {
                continue;
            }
            RevisionType revisionType = (RevisionType) o;

            Matcher matcher = LINK_PATTERN.matcher(revisionType.getText().getValue());
            while (matcher.find()) {
                context.write(new Text(matcher.group(1)), new LongWritable(1));
            }
        }
    }

}


