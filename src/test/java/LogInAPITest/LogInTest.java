package LogInAPITest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class LogInTest {


    @Test
    public void LogInTest1() throws Exception{

        System.out.println("LogIn Test");


        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        String str  =  get("https://fakestoreapi.com/products").getBody().asString();
        System.out.println(driver.getCurrentUrl());
        driver.findElement(By.id("APjFqb")).sendKeys("Aditya Naik");
        driver.findElement(By.id("APjFqb")).sendKeys(Keys.ENTER);

        Thread.sleep(5*1000);
        System.out.println(driver.getCurrentUrl());
        System.out.println(str);

        driver.quit();


    }
}
