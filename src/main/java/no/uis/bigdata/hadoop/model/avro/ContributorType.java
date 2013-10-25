/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package no.uis.bigdata.hadoop.model.avro;

@SuppressWarnings("all")
public class ContributorType extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
    public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"ContributorType\",\"namespace\":\"no.uis.bigdata.hadoop.model.avro\",\"fields\":[{\"name\":\"username\",\"type\":[\"null\",\"string\"],\"default\":\"null\"},{\"name\":\"id\",\"type\":[\"null\",\"string\"],\"default\":\"null\"},{\"name\":\"ip\",\"type\":[\"null\",\"string\"],\"default\":\"null\"},{\"name\":\"deleted\",\"type\":[\"null\",{\"type\":\"enum\",\"name\":\"DeletedFlagType\",\"symbols\":[\"DELETED\",\"UNSET\"]}],\"default\":\"null\"}]}");
    public java.lang.CharSequence username;
    public java.lang.CharSequence id;
    public java.lang.CharSequence ip;
    public no.uis.bigdata.hadoop.model.avro.DeletedFlagType deleted;

    public org.apache.avro.Schema getSchema() {
        return SCHEMA$;
    }

    // Used by DatumWriter.  Applications should not call.
    public java.lang.Object get(int field$) {
        switch (field$) {
            case 0:
                return username;
            case 1:
                return id;
            case 2:
                return ip;
            case 3:
                return deleted;
            default:
                throw new org.apache.avro.AvroRuntimeException("Bad index");
        }
    }

    // Used by DatumReader.  Applications should not call.
    @SuppressWarnings(value = "unchecked")
    public void put(int field$, java.lang.Object value$) {
        switch (field$) {
            case 0:
                username = (java.lang.CharSequence) value$;
                break;
            case 1:
                id = (java.lang.CharSequence) value$;
                break;
            case 2:
                ip = (java.lang.CharSequence) value$;
                break;
            case 3:
                deleted = (no.uis.bigdata.hadoop.model.avro.DeletedFlagType) value$;
                break;
            default:
                throw new org.apache.avro.AvroRuntimeException("Bad index");
        }
    }
}
