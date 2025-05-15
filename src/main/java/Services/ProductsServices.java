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

    public Response updateAProduct(Object payload){
        return baseclass.postRequest(payload,"https://fakestoreapi.com/products/",false);
    }

    public Response updateProduct(String body,String endpoint){
        return baseclass.updateResponse(body,endpoint,false);
    }

    public Response deleteProduct(String endpoint){
        return baseclass.deleteRecord(endpoint,false);
    }
}
