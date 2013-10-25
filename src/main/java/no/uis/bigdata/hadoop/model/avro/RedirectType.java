/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package no.uis.bigdata.hadoop.model.avro;

@SuppressWarnings("all")
public class RedirectType extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
    public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"RedirectType\",\"namespace\":\"no.uis.bigdata.hadoop.model.avro\",\"fields\":[{\"name\":\"value\",\"type\":\"string\",\"default\":\"\"},{\"name\":\"title\",\"type\":[\"null\",\"string\"],\"default\":\"null\"}]}");
    public java.lang.CharSequence value;
    public java.lang.CharSequence title;

    public org.apache.avro.Schema getSchema() {
        return SCHEMA$;
    }

    // Used by DatumWriter.  Applications should not call.
    public java.lang.Object get(int field$) {
        switch (field$) {
            case 0:
                return value;
            case 1:
                return title;
            default:
                throw new org.apache.avro.AvroRuntimeException("Bad index");
        }
    }

    public RedirectType(no.uis.bigdata.hadoop.model.jaxb.RedirectType redirect) {
        value = redirect.getValue();
        title = redirect.getTitle();
    }

    // Used by DatumReader.  Applications should not call.
    @SuppressWarnings(value = "unchecked")
    public void put(int field$, java.lang.Object value$) {
        switch (field$) {
            case 0:
                value = (java.lang.CharSequence) value$;
                break;
            case 1:
                title = (java.lang.CharSequence) value$;
                break;
            default:
                throw new org.apache.avro.AvroRuntimeException("Bad index");
        }
    }
}
