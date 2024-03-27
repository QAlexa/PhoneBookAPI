package helpers;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesWriter {
    public static void main(String[] args) {
        writeProperties("myKey", "myValue");//, false);
    }

    private static final String PROPERTIES_FILE_PATH = "src/main/resources/appdata.properties";

        public static void writeProperties(String key, String value) {
            Properties properties = new Properties();
            FileInputStream fileInputStream = null;
            FileOutputStream fileOutputStream = null;

            try {
                File file = new File(PROPERTIES_FILE_PATH);
                if (!file.exists()) {
                    file.createNewFile();
                }

                fileInputStream = new FileInputStream(PROPERTIES_FILE_PATH);
                properties.load(fileInputStream);

                properties.setProperty(key, value);

                StringBuilder stringBuilder = new StringBuilder();
                for (String propKey : properties.stringPropertyNames()) {
                    stringBuilder.append(propKey)
                            .append("=")
                            .append(properties.getProperty(propKey))
                            .append(System.lineSeparator());
                }

                fileOutputStream = new FileOutputStream(PROPERTIES_FILE_PATH);
                fileOutputStream.write(stringBuilder.toString().getBytes());

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }

                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

//    public static void writeProperties(String key, String value, boolean cleanFile) {
//
//        Properties properties = new Properties();
//        FileInputStream fileInputStream = null;
//        FileOutputStream fileOutputStream = null;
//        try {
//
//            if (!Files.exists(Paths.get(PROPERTIES_FILE_PATH))) {
//                Files.createFile(Paths.get(PROPERTIES_FILE_PATH));
//            }
//            fileInputStream = new FileInputStream(PROPERTIES_FILE_PATH);
//            properties.load(fileInputStream);
//            if (cleanFile) {
//                properties.clear();
//            }
//            properties.setProperty(key, value);
//            fileOutputStream = new FileOutputStream(PROPERTIES_FILE_PATH);
//            properties.store(fileOutputStream, "Key-Value");
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (fileInputStream != null) {
//                    fileInputStream.close();
//                }
//                if (fileOutputStream != null) {
//                    fileOutputStream.close();
//                }
//            } catch (IOException exception) {
//                exception.printStackTrace();
//            }
//        }
//
//    }
//}




