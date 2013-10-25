package no.uis.hadoop.hadoopinaction.book;

import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Random;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.KeyValueTextInputFormat;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import javax.swing.*;

public class MyJob0 extends Configured implements Tool {

    public static class MapClass extends MapReduceBase
            implements Mapper<Text, Text, Text, Text> {

        public void map(Text key, Text value,
                        OutputCollector<Text, Text> output,
                        Reporter reporter) throws IOException {

            output.collect(value, key);
        }
    }

    public static class Reduce extends MapReduceBase
            implements Reducer<Text, Text, Text, Text> {

        public void reduce(Text key, Iterator<Text> values,
                           OutputCollector<Text, Text> output,
                           Reporter reporter) throws IOException {

            String csv = "";
            while (values.hasNext()) {
                if (csv.length() > 0) csv += ",";
                csv += values.next().toString();
            }
            output.collect(key, new Text(csv));
        }
    }

    public static class Reduce2 extends MapReduceBase
            implements Reducer<Text, Text, Text, IntWritable> {
        public void reduce(Text key, Iterator<Text> values,
                           OutputCollector<Text, IntWritable> output,
                           Reporter reporter) throws IOException {
            int count = 0;
            while (values.hasNext()) {
                values.next();
                count++;
            }
            output.collect(key, new IntWritable(count));
        }
    }

    public int run(String[] args) throws Exception {
        Configuration conf = getConf();

        JobConf job = new JobConf(conf, MyJob0.class);

        Path in = new Path(args[0]);
        Path out = new Path(args[1]);
        FileInputFormat.setInputPaths(job, in);
        FileOutputFormat.setOutputPath(job, out);

        job.setJobName("MyJob0");
        job.setMapperClass(MapClass.class);
        job.setReducerClass(Reduce.class);

        job.setInputFormat(KeyValueTextInputFormat.class);
        job.setOutputFormat(TextOutputFormat.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.set("key.value.separator.in.input.line", ",");

        JobClient.runJob(job);

        return 0;
    }

    public static void main(String[] args) throws Exception {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        int res = ToolRunner.run(new Configuration(), new MyJob0(), new String[]{"/Users/maziarkaveh/Develop/codes/uis/bigdata/hadoop/data/cite75_99.txt", "/Users/maziarkaveh/Develop/codes/uis/bigdata/hadoop/data/" + new Random().nextInt() + ".txt"});

        long l = Calendar.getInstance().getTimeInMillis() - timeInMillis;
        JOptionPane.showMessageDialog(null, l);
        System.out.print(l);
        System.exit(res);
    }
}
