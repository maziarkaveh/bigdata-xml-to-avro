package no.uis.bigdata.hadoop;

import no.uis.bigdata.hadoop.common.model.avro.Page;
import org.apache.avro.mapreduce.AvroJob;
import org.apache.hadoop.mapreduce.Job;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.hadoop.mapreduce.JobRunner;

public class MainClass {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml");

        Job xmlJob = context.getBean("xml-to-avro-job", Job.class);
        AvroJob.setMapOutputValueSchema(xmlJob, Page.SCHEMA$);
        JobRunner jobRunner = context.getBean(JobRunner.class);
        jobRunner.call();
    }
}