package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

    private static Properties properties;
//A static block runs as soon as the class is loaded into memory.
// This ensures that my configuration file is read only once at the very start of the execution, making the framework more efficient."
    static{
        try{
            // 1. Get the 'env' value from the Terminal/System.
            // If nothing is provided, default to 'qa'
            String env=System.getProperty("env");
            if(env==null){
                env="qa";
            }
            /// 2. Dynamically build the file path based on the environment

            String path="src/test/resources/config"+ env +".properties";
            System.out.println("Loading configuration for environment: " + env);

            FileInputStream input=new FileInputStream(path);
            properties=new Properties();
            properties.load(input);
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not load properties file.Check if file exists");
        }

    }
    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
