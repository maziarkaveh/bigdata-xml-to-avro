package no.uis.bigdata.hadoop;

import no.uis.bigdata.hadoop.common.model.avro.Page;
import org.apache.avro.mapreduce.AvroJob;
import org.apache.hadoop.mapreduce.Job;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.hadoop.mapreduce.JobRunner;

import javax.annotation.PostConstruct;


public class JobTest extends AbstractBasicTest {


    @Autowired
    private JobRunner jobRunner;
    @Autowired
    @Qualifier("xml-to-avro-job")
    private Job xmlToAvroJob;
    @PostConstruct
    void init(){
        AvroJob.setMapOutputValueSchema(xmlToAvroJob, Page.SCHEMA$);
    }
    @Test
    public void testMap() throws Exception {
        jobRunner.call();

    }


}
