package no.uis.bigdata.hadoop.xmltoavro.xmlreader;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;

public class XmlRecordReader
        extends RecordReader<LongWritable, Text> {

    public static final String START_TAG_KEY = "xmlinput.start";
    public static final String END_TAG_KEY = "xmlinput.end";

    private final byte[] startTag;
    private final byte[] endTag;
    private final long start;
    private final long end;
    private final FSDataInputStream fsin;
    private final DataOutputBuffer buffer = new DataOutputBuffer();
    private LongWritable currentKey;
    private Text currentValue;

    public XmlRecordReader(FileSplit split, Configuration conf)
            throws IOException {
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

    private boolean next(LongWritable key, Text value)
            throws IOException {
        if (fsin.getPos() < end && readUntilMatch(startTag, false)) {
            try {
                buffer.write(startTag);
                if (readUntilMatch(endTag, true)) {
                    key.set(fsin.getPos());
                    value.set(buffer.getData(), 0, buffer.getLength());
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
    public LongWritable getCurrentKey()
            throws IOException, InterruptedException {
        return currentKey;
    }

    @Override
    public Text getCurrentValue()
            throws IOException, InterruptedException {
        return currentValue;
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
        currentValue = new Text();
        return next(currentKey, currentValue);
    }
}