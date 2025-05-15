package TestCases;

import Base.Baseclass;
import Payloads.Products;
import Payloads.Rating;
import Services.ProductsServices;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

public class ProductTestCases extends Baseclass {

    ProductsServices productsServices;


    //      https://fakestoreapi.com/products
    @Test(description = "validate Get All Products VPs: statusCode,header(Content-type, Access-Control-Allow-Origin) ,list size,response time")
    public void getAllProducts(){
        productsServices = new ProductsServices();
        Response response = productsServices.getProductsResponse("/products");

        response.then()
                .statusCode(200)
                .header("Content-Type","application/json; charset=utf-8")
                .header("Access-Control-Allow-Origin","*");

        List<Products> allProducts = productsServices.getAllProducts();
        System.out.println("Total Products: "+allProducts.size());
        Products secondProduct = allProducts.get(1);
        System.out.println(secondProduct.toString());
        Assert.assertTrue(allProducts.size() > 15,"Product List cannot be Less than 15");
        Assert.assertTrue(response.getTime() < 2000,"Response time should not exceeded 2 seconds!");

        System.out.println(allProducts.get(1).toString());

    }

//    https://fakestoreapi.com/products/{id}
//    Products{id=2, title='Mens Casual Premium Slim Fit T-Shirts ', price=22.3, description='Slim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket.', category='men's clothing'}
    @Test(description = "Get a single product's ID, Title, Price, Category and header(Content-type, Access-Control-Allow-Origin) and response time ")
    public void getSingleProduct(){
        productsServices = new ProductsServices();
        Response response =productsServices.getProductsResponse("/products/2");
        response.then()
                .statusCode(200)
                .header("Content-Type","application/json; charset=utf-8")
                .header("Access-Control-Allow-Origin","*")
                .body("id",equalTo(2))
                .body("title",containsString("Mens Casual Premium Slim Fit T-Shirts"))
                .body("price",equalTo(22.3F))
                .body("category",containsString("men's clothing"));

        Assert.assertTrue(response.getTime() < 2000,"Response time should not exceeded 2 seconds!");
        Products product = response.getBody().as(Products.class);
        System.out.println("Product Object: \n"+product.toString());
    }


    //Add new Product
//    https://fakestoreapi.com/products
    @Test
    public void addNewProduct(){

        productsServices = new ProductsServices();
//        Products products = new Products(143,"New Product",2.14F,"This is a testing prototype product its in the beta mode cyrrrently","Sci-fi","ww//pininterest.bs",new Rating());
//        Response response =productsServices.postSingleProduct(products);
//
//        response.then()
//                .statusCode(200)                    // 201 to check the product is added
//                .header("Content-Type","application/json; charset=utf-8")
//                .header("Access-Control-Allow-Origin","*");
//
//        Assert.assertTrue(response.getTime() <2000," Response time should not exceed 2s");
//        System.out.println(response.prettyPrint());
//


//        given()
//                .contentType(ContentType.JSON)
//                .header("x-api-key","reqres-free-v1")
//                .body("{\n" +
//                        "    \"name\": \"morpheus\",\n" +
//                        "    \"job\": \"leader\"\n" +
//                        "}")
//
//                .when()
//                .post("https://reqres.in/api/users")
//                .then()
//                .log()
//                .all();





    }

    //update Product
    // delete product

}
