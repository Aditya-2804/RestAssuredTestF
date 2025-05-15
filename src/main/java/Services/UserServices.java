package Services;

import Base.Baseclass;
import Payloads.Users;
import io.restassured.response.Response;

import java.util.List;

public class UserServices extends Baseclass {

    Baseclass baseclass = new Baseclass();

    public Response getUsers(String endpoint){
        return baseclass.getResponse(endpoint);
    }

    public List<Users> getListOfUsers(){
        return baseclass.getResponse("/users")
                .then()
                .extract()
                .jsonPath()
                .getList("", Users.class);
    }

    public Response createNewUser(String body){
        return baseclass.postRequest(body,"https://reqres.in/api/users",true);
    }

    public Response deleteUser(String endpoint){
        return baseclass.deleteRecord(endpoint,true);
    }

    public Response updateUser(String body){
        return baseclass.updateResponse(body,"https://reqres.in/api/users/2",true);
    }
}
