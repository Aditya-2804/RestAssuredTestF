package Services;

import Base.Baseclass;
import Payloads.Products;
import io.restassured.response.Response;

import java.util.List;

public class ProductsServices extends Baseclass {

    Baseclass baseclass = new Baseclass();


    public Response getProductsResponse(String endpoint){
        return baseclass.getResponse(endpoint);
    }

    public List<Products> getAllProducts(){
        return  baseclass.getResponse("/products").then()
                .extract()
                .jsonPath()
                .getList("", Products.class);
    }

    public Response postSingleProduct(Object payload){
        return baseclass.postRequest(payload,"https://fakestoreapi.com/products/",true);
    }
}
