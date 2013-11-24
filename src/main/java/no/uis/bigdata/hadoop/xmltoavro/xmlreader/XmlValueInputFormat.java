package no.uis.bigdata.hadoop.xmltoavro.xmlreader;

import no.uis.bigdata.hadoop.common.model.XMLConvertible;
import no.uis.bigdata.hadoop.xmltoavro.model.XmlKey;
import no.uis.bigdata.hadoop.xmltoavro.model.XmlValue;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class XmlValueInputFormat<T extends XMLConvertible<T>> extends FileInputFormat<LongWritable, XmlValue<T>> {
    private static final Logger log =
            LoggerFactory.getLogger(XmlValueInputFormat.class);

    @Override
    public RecordReader<LongWritable, XmlValue<T>> createRecordReader(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        try {
            return new XmlValueRecordReader<T>((FileSplit) inputSplit,
                    HadoopCompat.getConfiguration(taskAttemptContext));
        } catch (IOException ioe) {
            log.error("Error while creating XmlKeyRecordReader", ioe);
            return null;
        }
    }


}