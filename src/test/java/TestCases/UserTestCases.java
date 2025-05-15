package TestCases;

import Base.Baseclass;
import Payloads.Users;
import Services.UserServices;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class UserTestCases extends Baseclass {

    UserServices userServices;
    int id =0;


    //validate Get All Users
//    https://fakestoreapi.com/users
    @Test
    public void getAllUsers(){
        userServices = new UserServices();
        Response response = userServices.getUsers("/users");
        response.then()
                .statusCode(200)
                .header("Content-Type","application/json; charset=utf-8")
                .header("Access-Control-Allow-Origin","*");


        System.out.println(response.prettyPrint());
        List<Users> usersList = userServices.getListOfUsers();
        Assert.assertTrue(usersList.size() > 5,"Users should not be less that 5");
        Assert.assertTrue(response.getTime() < 5000, "Response time should not exceed 2s");

        Users user = usersList.get(2);
        System.out.println(user.toString());
    }

//    https://fakestoreapi.com/users/{id}
//validate get single Users
    @Test
    public void getSingleUser(){

        userServices = new UserServices();
        Response response = userServices.getUsers("/users/3");
        response.then()
                .statusCode(200)
                .header("Content-Type","application/json; charset=utf-8")
                .header("Access-Control-Allow-Origin","*")
                .body("id",equalTo(3))
                .body("username",containsString("kevinryan"))
                .body("email",containsString("kevin@gmail.com"))
                .body("password",containsString("kev02937@"));

        Assert.assertTrue(response.getTime() < 2000,"Response time should not exceed 2s");

        Users user = response.as(Users.class);
        System.out.println(user.toString());
    }

    //Add new Users
    @Test
    public void createNewUser(){

        userServices = new UserServices();
        Response response = userServices.createNewUser("{\n" +
                        "    \"name\": \"Spaces\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}");

        response.then()
                .statusCode(201)
                .body("name",containsString("Spaces"))
                .body("job",containsString("leader"))
                .log()
                .all();

        System.out.println(response.jsonPath().getInt("id"));

    }
    //update Users
    @Test
    public void updateuser(){

        userServices = new UserServices();
        Response userID2 = userServices.getUsers("https://reqres.in/api/users/2");
        System.out.println("Before Update: \n"+userID2.prettyPrint());
        Response userID2Updated= userServices.updateUser("{\n" +
                "    \"name\": \"Master\",\n" +
                "    \"job\": \"Maven resident\"\n" +
                "}");

        System.out.println("After Update: \n"+userID2Updated.prettyPrint());
        userID2Updated.then()
                .statusCode(200);


    }



    // delete Users
    @Test
    public void deleteUser(){

        userServices = new UserServices();
        Response response = userServices.deleteUser("https://reqres.in/api/users/418");

        response.then()
                .statusCode(204)
                .log()
                .body();
    }



}
