package no.uis.bigdata.hadoop.xml.xmlreader;

import no.uis.bigdata.hadoop.common.model.XMLConvertible;
import no.uis.bigdata.hadoop.xml.model.XmlKey;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class XmlKeyInputFormat<T extends XMLConvertible<T>> extends FileInputFormat<XmlKey<T>, NullWritable> {
    private static final Logger log =
            LoggerFactory.getLogger(XmlKeyInputFormat.class);

    @Override
    public RecordReader<XmlKey<T>, NullWritable> createRecordReader(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        try {
            return new XmlKeyRecordReader((FileSplit) inputSplit,
                    HadoopCompat.getConfiguration(taskAttemptContext));
        } catch (IOException ioe) {
            log.error("Error while creating XmlKeyRecordReader", ioe);
            return null;
        }
    }


}