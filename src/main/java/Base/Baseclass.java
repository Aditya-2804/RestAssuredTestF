package Base;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Baseclass {

    private static final String BASE_URL = "https://fakestoreapi.com";
    public static final String CATEGORY_ID  = "CategoryId";
    private RequestSpecification requestSpecification;

    public Baseclass() {
        requestSpecification = given().baseUri(BASE_URL);
    }

    public Response getResponse(String endpoint){
        return requestSpecification.get(endpoint);
    }

    public Response postRequest(Object payload,String postUrl,boolean isReqresUrl){
        if (isReqresUrl){
            System.out.println(postUrl+ " : this is ReqRes URL");
            requestSpecification.header("x-api-key", "reqres-free-v1");
        }
        return requestSpecification
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(postUrl);
    }

    public Response deleteRecord(String endpoint,boolean isReqResURL){
        if (isReqResURL){
            requestSpecification.header("x-api-key", "reqres-free-v1");
        }
        return requestSpecification
                .header("x-api-key","reqres-free-v1")
                .contentType(ContentType.JSON)
                .when()
                .delete(endpoint);
    }

    public Response updateResponse(String body,String endpoint,boolean isReqResURL){
        if (isReqResURL){
            requestSpecification.header("x-api-key", "reqres-free-v1");
        }
        return requestSpecification
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put(endpoint);
    }
}
