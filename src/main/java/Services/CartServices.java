package Services;

import Base.Baseclass;
import Payloads.Carts;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.*;

public class CartServices extends Baseclass {

    Baseclass baseclass = new Baseclass();

    public Response getCart(String endpoint){
       return baseclass.getResponse(endpoint);
    }

    public List<Carts> getCartList(){
        return baseclass.getResponse("/carts").then()
                .extract()
                .jsonPath()
                .getList("",Carts.class);
    }

}
