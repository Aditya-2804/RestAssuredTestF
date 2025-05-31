package LogInAPITest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class LogInTest {


    @Test
    public void LogInTest1(){

        System.out.println("LogIn Test");

        WebDriver driver = new EdgeDriver();
        driver.get("https://www.google.co.in/")
        String str  =  get("https://fakestoreapi.com/products").getBody().asString();
        System.out.println(str);

        driver.quit();

    }
}
