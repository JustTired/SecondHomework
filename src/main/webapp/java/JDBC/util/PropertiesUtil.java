package JDBC.util;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.Properties;

@UtilityClass
public class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();


    static {
        loadProperties();
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties() {
        try (var input = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
