package no.uis.bigdata.hadoop;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.mapreduce.JobRunner;


/**
 * Created with IntelliJ IDEA.
 * Page: maziarkaveh
 * Date: 14.10.13
 * Time: 21:01
 * To change this template use File | Settings | File Templates.
 */
public class JobTest extends AbstractBasicTest {


    @Autowired
    private JobRunner jobRunner;
//    @Value("#{T(no.uis.bigdata.hadoop.common.model.avro.Page).Schema}")
//    private String d;

    @Test
    public void testMap() throws Exception {

//        parser.parse("{\"type\":\"record\",\"name\":\"Page\",\"namespace\":\"no.uis.bigdata.hadoop.common.model.avro\",\"fields\":[{\"name\":\"title\",\"type\":\"string\",\"default\":\"\"},{\"name\":\"ns\",\"type\":\"string\",\"default\":\"\"},{\"name\":\"id\",\"type\":\"string\",\"default\":\"\"},{\"name\":\"redirect\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"RedirectType\",\"fields\":[{\"name\":\"value\",\"type\":\"string\",\"default\":\"\"},{\"name\":\"title\",\"type\":[\"null\",\"string\"],\"default\":\"null\"}]}],\"default\":\"null\"},{\"name\":\"restrictions\",\"type\":[\"null\",\"string\"],\"default\":\"null\"},{\"name\":\"revisionOrUpload\",\"type\":[\"null\",{\"type\":\"array\",\"items\":[{\"type\":\"record\",\"name\":\"\",\"fields\":[{\"name\":\"id\",\"type\":\"string\",\"default\":\"\"},{\"name\":\"parentid\",\"type\":[\"null\",\"string\"],\"default\":\"null\"},{\"name\":\"timestamp\",\"type\":{\"type\":\"record\",\"name\":\"_Date\",\"doc\":\"A date stored as a timestamp, in ms UTC.\",\"fields\":[{\"name\":\"timestamp\",\"type\":\"long\",\"default\":\"0\"}]}},{\"name\":\"contributor\",\"type\":{\"type\":\"record\",\"name\":\"ContributorType\",\"fields\":[{\"name\":\"username\",\"type\":[\"null\",\"string\"],\"default\":\"null\"},{\"name\":\"id\",\"type\":[\"null\",\"string\"],\"default\":\"null\"},{\"name\":\"ip\",\"type\":[\"null\",\"string\"],\"default\":\"null\"},{\"name\":\"deleted\",\"type\":[\"null\",{\"type\":\"enum\",\"name\":\"DeletedFlagType\",\"symbols\":[\"DELETED\",\"UNSET\"]}],\"default\":\"null\"}]}},{\"name\":\"minor\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"_Reference\",\"doc\":\"A reference to an entity, similar to the IDREF object in XML schemas\",\"fields\":[{\"name\":\"id\",\"type\":\"string\",\"default\":\"\"}]}],\"default\":\"null\"},{\"name\":\"comment\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"CommentType\",\"fields\":[{\"name\":\"value\",\"type\":\"string\",\"default\":\"\"},{\"name\":\"deleted\",\"type\":[\"null\",\"DeletedFlagType\"],\"default\":\"null\"}]}],\"default\":\"null\"},{\"name\":\"text\",\"type\":{\"type\":\"record\",\"name\":\"TextType\",\"fields\":[{\"name\":\"value\",\"type\":\"string\",\"default\":\"\"},{\"name\":\"space\",\"type\":[\"null\",\"string\"],\"default\":\"null\"},{\"name\":\"deleted\",\"type\":[\"null\",\"DeletedFlagType\"],\"default\":\"null\"},{\"name\":\"id\",\"type\":[\"null\",\"string\"],\"default\":\"null\"},{\"name\":\"bytes\",\"type\":[\"null\",\"string\"],\"default\":\"null\"}]}},{\"name\":\"sha1\",\"type\":\"string\",\"default\":\"\"},{\"name\":\"model\",\"type\":\"string\",\"default\":\"\"},{\"name\":\"format\",\"type\":\"string\",\"default\":\"\"}]},{\"type\":\"record\",\"name\":\"UploadType\",\"fields\":[{\"name\":\"timestamp\",\"type\":\"_Date\"},{\"name\":\"contributor\",\"type\":\"ContributorType\"},{\"name\":\"comment\",\"type\":[\"null\",\"string\"],\"default\":\"null\"},{\"name\":\"filename\",\"type\":\"string\",\"default\":\"\"},{\"name\":\"src\",\"type\":\"string\",\"default\":\"\"},{\"name\":\"size\",\"type\":\"string\",\"default\":\"\"}]}]}],\"default\":\"null\"},{\"name\":\"discussionthreadinginfo\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"DiscussionThreadingInfo\",\"fields\":[{\"name\":\"threadSubject\",\"type\":\"string\",\"default\":\"\"},{\"name\":\"threadParent\",\"type\":\"string\",\"default\":\"\"},{\"name\":\"threadAncestor\",\"type\":\"string\",\"default\":\"\"},{\"name\":\"threadPage\",\"type\":\"string\",\"default\":\"\"},{\"name\":\"threadID\",\"type\":\"string\",\"default\":\"\"},{\"name\":\"threadAuthor\",\"type\":\"string\",\"default\":\"\"},{\"name\":\"threadEditStatus\",\"type\":\"string\",\"default\":\"\"},{\"name\":\"threadType\",\"type\":\"string\",\"default\":\"\"}]}],\"default\":\"null\"}]}");
//        no.uis.avro.generated.Page page = XMLPageUnMarshaller.unMarshallFromDataInput(new DataInputStream(new FileInputStream("/Users/maziarkaveh/Develop/codes/uis/bigdata/hadoop/data/xaa.xml")));
//        String s = XMLPageMarshaller.marshallToString(page);
//        Page page1=  XMLPageUnMarshaller.unMarshallFromString(s);
//        System.out.println(page1==page);
//        File file = new File("dd");
//         Page page1 = new Page(page);
//        Page page2 = new Page(page);
//        Page page3 = null;
//
//        DatumWriter<Page> pageDatumWriter = new SpecificDatumWriter<Page>(Page.class);
//        DataFileWriter<Page> dataFileWriter = new DataFileWriter<Page>(pageDatumWriter);
//        dataFileWriter.create(no.uis.bigdata.hadoop.common.model.avro.Page.SCHEMA$, new File("/Users/maziarkaveh/Develop/codes/uis/bigdata/hadoop/data/s/t.txt"));
//        dataFileWriter.append(page1);
//        dataFileWriter.append(page2);
//        dataFileWriter.append(page3);
//        dataFileWriter.close();
//
//
//        DatumReader<Page> pageDatumReader = new SpecificDatumReader<Page>(Page.class);
//        DataFileReader<Page> dataFileReader = new DataFileReader<Page>(file, pageDatumReader);
//        while (dataFileReader.hasNext()) {
//
//            page3 = dataFileReader.next(page3);
//            System.out.println(page3);
//        }
    }


}
