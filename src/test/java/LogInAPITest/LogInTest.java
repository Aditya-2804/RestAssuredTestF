package LogInAPITest;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class LogInTest {


    @Test
    public void LogInTest1(){

        System.out.println("LogIn Test");
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }

    }
}
