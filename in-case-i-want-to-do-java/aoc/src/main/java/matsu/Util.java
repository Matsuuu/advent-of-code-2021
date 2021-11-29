package matsu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Util {

    public static Util _instance;

    public static Util getInstance() {
        if (Util._instance == null) {
            Util._instance = new Util();
        }
        return Util._instance;
    }

    public String readFileResource(String fileName) {  
        try {
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(fileName);
            InputStreamReader streamReader = new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8);

            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder fileContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line);
                fileContent.append("\n");
            }

            return fileContent.toString();
        } catch (IOException e) {
            System.err.println("IO Exception reading file " + e.getMessage());
            return "";
        }
    }

    public static String readFile(String fileName) {
        return getInstance().readFileResource(fileName);
    }
}
