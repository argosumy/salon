package spdu2022.java.project.beutysalon.configs;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class Properties {
    private final Map<String, String> defaultProperty = Map.of("interval-for-slots","60");

    public String getProperty(String nameProperty) {
        final String PATH_TO_PROPERTIES = "src/main/resources/application.properties";
        java.util.Properties prop = new java.util.Properties();
        try(FileInputStream fileInputStream = new FileInputStream(PATH_TO_PROPERTIES)) {
            prop.load(fileInputStream);
            return prop.getProperty(nameProperty);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return defaultProperty.get(nameProperty);
        }
    }
}
