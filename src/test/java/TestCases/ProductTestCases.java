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


//    https://api.escuelajs.co/api/v1/products/
    //"id": 110,
    @Test
    public void createNewProduct(){
               productsServices = new ProductsServices();
        Response response = productsServices.updateAProduct("{\n" +
                "  \"title\": \"Latest new new Product\",\n" +
                "  \"price\": 10,\n" +
                "  \"description\": \"A description\",\n" +
                "  \"categoryId\": 1,\n" +
                "  \"images\": [\"https://placehold.co/600x400\"]\n" +
                "}");

        response.then()
                .statusCode(201)
                .log()
                .all();
    }


    @Test
    public void updateProduct(){

        productsServices = new ProductsServices();
        Response productID110 = productsServices.getResponse("https://api.escuelajs.co/api/v1/products/110");
        System.out.println("Product ID 110 Before: \n"+productID110.prettyPrint());
        int priceBefore =productID110.jsonPath().getInt("price");
        String titleBefore =productID110.jsonPath().getString("title");

        System.out.println("Price and Title Before Updating: "+priceBefore+" AND "+titleBefore);

        Response productID110New = productsServices.updateProduct("{\n" +
                "  \"title\": \"Change title\",\n" +
                "  \"price\": 100\n" +
                "}","https://api.escuelajs.co/api/v1/products/110");

        productID110New.then()
                .statusCode(200)
                .log()
                .all();

        int priceAfter =productID110.jsonPath().getInt("price");
        String titleAfter =productID110.jsonPath().getString("title");
        System.out.println("Price and Title After Update: "+priceAfter+" AND "+titleAfter);

    }


    @Test
    public void deleteProduct(){
        productsServices = new ProductsServices();
        Response response = productsServices.deleteProduct("https://api.escuelajs.co/api/v1/products/110");

        response.then()
                .statusCode(200)
                .log()
                .all();
    }

}
