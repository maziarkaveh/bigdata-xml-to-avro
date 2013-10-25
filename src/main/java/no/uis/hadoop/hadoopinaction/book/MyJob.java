package no.uis.hadoop.hadoopinaction.book;

import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Random;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import javax.swing.*;

public class MyJob extends Configured implements Tool {
    
    public static class MapClass extends Mapper<LongWritable, Text, Text, Text> {
        
        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            System.out.println(value.toString());
            String[] citation = value.toString().split(" ");
            context.write(new Text(citation[1]), new Text(citation[0]));
        }
    }
    
    public   class Reduce extends Reducer<Text, Text, Text, Text> {
        
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
                           
            String csv = "";
            for (Text val:values) {
                if (csv.length() > 0) csv += ",";
                csv += val.toString();
            }
            
            context.write(key, new Text(csv));
        }
    }
    
    public int run(String[] args) throws Exception {
        Configuration conf = getConf();
        conf.setBoolean("mapred.compress.map.output", true);
        conf.setClass("mapred.map.output.compression.codec",
                GzipCodec.class,
                CompressionCodec.class);
        Job job = new Job(conf, "MyJob");
        job.setJarByClass(MyJob.class);
        
        Path in = new Path(args[0]);
        Path out = new Path(args[1]);
        FileInputFormat.setInputPaths(job, in);
        FileOutputFormat.setOutputPath(job, out);
        
        job.setMapperClass(MapClass.class);
        job.setReducerClass(Reduce.class);
        
        job.setInputFormatClass(TextInputFormat.class);
         job.setOutputFormatClass(TextOutputFormat.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        
        System.exit(job.waitForCompletion(true)?0:1);
        
        return 0;
    }
    
    public static void main(String[] args) throws Exception {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        int res = ToolRunner.run(new Configuration(), new MyJob(), new String[]{"/Users/maziarkaveh/Downloads/enwiki-latest-pages-articles.xml.bz2", "/Users/maziarkaveh/Develop/codes/uis/bigdata/hadoop/data/" + new Random().nextInt() });

        long l = Calendar.getInstance().getTimeInMillis() - timeInMillis;
//        JOptionPane.showMessageDialog(null, l);
        System.out.println(l);
        System.exit(res);
    }
}
