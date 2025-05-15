package Utilities;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

public class Utils {

    public void writeToProperty(String key,String value){
//        C:\Users\Aditya\IdeaProjects\RestAssuredTest\src\main\resources\config.properties

        FileReader fileReader;
        FileWriter fileWriter;
        File file = new File("C:\\Users\\Aditya\\IdeaProjects\\RestAssuredTest\\src\\main\\resources\\config.properties");
        try {

            fileReader = new FileReader(file);
            fileWriter = new FileWriter(file);

            Properties properties = new Properties();
            properties.load(fileReader);

            properties.setProperty(key, value);
            properties.store(fileWriter,"Write To the File");
        }catch (Exception e){

        }
    }

    public String readFromProperty(String key){

        String value="";
        File file = new File("C:\\Users\\Aditya\\IdeaProjects\\RestAssuredTest\\src\\main\\resources\\config.properties");
        try{
            FileReader reader = new FileReader(file);

            Properties properties = new Properties();
            properties.load(reader);

            value = properties.getProperty(key);
            return value;
        }catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }
}
