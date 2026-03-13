package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

    private static Properties properties;
//A static block runs as soon as the class is loaded into memory by JVM.
// This ensures that my configuration file is read only once at the very start of the execution, making the framework more efficient."
    static{
        try{
            // 1. Get the 'env' value from the Terminal/System.
            String env=System.getProperty("env", "qa");// If nothing is provided, default to 'qa'
            /// 2. Dynamically build the file path based on the environment
         //File.separator automatically picks '\' for Windows and '/' for Linux/Mac-so that as per OS validator we can use from diff OS
            String path="src"+ File.separator+"test"+File.separator+"resources"+File.separator+"config"+File.separator+ env +".properties";
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
