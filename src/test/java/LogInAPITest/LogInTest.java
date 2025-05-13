package LogInAPITest;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class LogInTest {


    @Test
    public void LogInTest1(){

        System.out.println("LogIn Test");

        String str  =  get("https://fakestoreapi.com/products").getBody().asString();
        System.out.println(str);

    }
}
