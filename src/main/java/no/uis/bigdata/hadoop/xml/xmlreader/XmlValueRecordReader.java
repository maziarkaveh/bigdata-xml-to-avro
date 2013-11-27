package no.uis.bigdata.hadoop.xml.xmlreader;

import no.uis.bigdata.hadoop.common.model.XMLConvertible;
import no.uis.bigdata.hadoop.xml.model.XmlValue;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DataOutputBuffer;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class XmlValueRecordReader<T extends XMLConvertible<T>>
        extends RecordReader<LongWritable, XmlValue<T>> {
    private static final Logger log =
            LoggerFactory.getLogger(XmlValueRecordReader.class);
    public static final String START_TAG_KEY = "xmlinput.start";
    public static final String END_TAG_KEY = "xmlinput.end";
    public static final String XML_CLASS_INPUT_KEY = "xml.class.input.key";

    private final byte[] startTag;
    private final byte[] endTag;
    private final long start;
    private final long end;
    private final FSDataInputStream fsin;
    private final DataOutputBuffer buffer = new DataOutputBuffer();
    private XmlValue<T> currentValue;
    private LongWritable currentKey;
    private Class<T> currentRecordClass;

    public XmlValueRecordReader(FileSplit split, Configuration conf)
            throws IOException {
        String recordClassName = conf.get(XML_CLASS_INPUT_KEY);
        if (recordClassName == null) {
            log.error(XML_CLASS_INPUT_KEY + " is not declared");
        }
        try {
            currentRecordClass = (Class<T>) Class.forName(recordClassName);
        } catch (ClassNotFoundException e) {
            log.error(recordClassName, e);
        }
        startTag = conf.get(START_TAG_KEY).getBytes("UTF-8");
        endTag = conf.get(END_TAG_KEY).getBytes("UTF-8");

        // open the file and seek to the start of the split
        start = split.getStart();
        end = start + split.getLength();
        Path file = split.getPath();
        FileSystem fs = file.getFileSystem(conf);
        fsin = fs.open(split.getPath());
        fsin.seek(start);
    }

    private boolean next(LongWritable key,XmlValue<T> value)
            throws IOException {
        if (fsin.getPos() < end && readUntilMatch(startTag, false)) {
            try {
                buffer.write(startTag);
                if (readUntilMatch(endTag, true)) {
                    key.set(fsin.getPos());
                    Text text = new Text();
                    text.set(buffer.getData(), 0, buffer.getLength());
                    try {
                        value.setData(currentValue.getData().unMarshall(text.toString()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            } finally {
                buffer.reset();
            }
        }
        return false;
    }

    @Override
    public void close() throws IOException {
        fsin.close();
    }

    @Override
    public float getProgress() throws IOException {
        return (fsin.getPos() - start) / (float) (end - start);
    }

    private boolean readUntilMatch(byte[] match, boolean withinBlock)
            throws IOException {
        int i = 0;
        while (true) {
            int b = fsin.read();
            // end of file:
            if (b == -1) {
                return false;
            }
            // save to buffer:
            if (withinBlock) {
                buffer.write(b);
            }

            // check if we're matching:
            if (b == match[i]) {
                i++;
                if (i >= match.length) {
                    return true;
                }
            } else {
                i = 0;
            }
            // see if we've passed the stop point:
            if (!withinBlock && i == 0 && fsin.getPos() >= end) {
                return false;
            }
        }
    }

    @Override
    public XmlValue<T> getCurrentValue()
            throws IOException, InterruptedException {
        return currentValue;
    }

    @Override
    public LongWritable getCurrentKey()
            throws IOException, InterruptedException {
        return currentKey;
    }

    @Override
    public void initialize(InputSplit split,
                           TaskAttemptContext context)
            throws IOException, InterruptedException {
    }

    @Override
    public boolean nextKeyValue()
            throws IOException, InterruptedException {
        currentKey = new LongWritable();
        currentValue = new XmlValue<T>(currentRecordClass);
        return next(currentKey, currentValue);
    }
}