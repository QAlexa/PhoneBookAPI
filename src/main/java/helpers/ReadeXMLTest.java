package helpers;

public class ReadeXMLTest {

    public static void main(String[] args) {
        String key = "user001";
        String PROPERTIES_PATH = "src/main/resources/data.xml";
        //PropertiesWriterXML.setProperties("user001","John", true);
        PropertiesWriterXML propertiesWriterXML = new PropertiesWriterXML(PROPERTIES_PATH);
        propertiesWriterXML.setProperties(key,"John", true);
        String res = PropertiesReaderXML.getProperty(key);
        System.out.println("RESULT :" + res);

    }
}
