package TestCases;

import Base.Baseclass;
import Payloads.Carts;
import Payloads.Product;
import Payloads.Products;
import Services.CartServices;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class CartTestcases extends Baseclass {


    CartServices cartServices;

//    https://fakestoreapi.com/carts
    @Test(description = "validate Get All Cart VPs: statusCode,header(Content-type, Access-Control-Allow-Origin) ,list size,response time")
    public void getAllCartItems(){
        cartServices = new CartServices();
        Response response = cartServices.getCart("/carts");

        response.then()
                .statusCode(200)
                .header("Content-Type","application/json; charset=utf-8")
                .header("Access-Control-Allow-Origin","*");

        List<Carts> cartItems = cartServices.getCartList();
        Assert.assertTrue(response.getTime() < 2000," Response time should not be more than 2s");
        Assert.assertTrue(cartItems.size()>5,"cart list cannot be Empty");

        Carts cartItem = cartItems.get(1);
        System.out.println("cartItem : \n"+cartItem.toString());

    }

//  https://fakestoreapi.com/carts/{id}
//validate get single Cart
    @Test
    public void getSingleCartItem(){
        cartServices = new CartServices();
        Response response = cartServices.getCart("/carts/2");

        response.then()
                .statusCode(200)
                .header("Content-Type","application/json; charset=utf-8")
                .header("Access-Control-Allow-Origin","*")
                .body("id",equalTo(2))
                .body("userId",equalTo(1))
                .body("date",containsString("2020-01-02T00:00:00.000Z"));

        Carts carts =response.getBody().as(Carts.class);
        Assert.assertTrue(carts.getProducts().size()> 2,"Cart items should exceed 2 i.e = 3");
        System.out.println(carts.getProducts().size());
        System.out.println("Cart Item: \n"+carts.toString());

    }



}
