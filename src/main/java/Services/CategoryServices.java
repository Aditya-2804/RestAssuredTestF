package Services;

import Base.Baseclass;
import Payloads.Categories;
import io.restassured.response.Response;

import java.util.List;

public class CategoryServices {

    Baseclass baseclass = new Baseclass();

    public List<Categories> getCategoriesList(String endpoint){
        return baseclass.getResponse(endpoint).then()
                .extract()
                .jsonPath()
                .getList("", Categories.class);
    }

    public Response getCategories(String endpoint){
        return baseclass.getResponse(endpoint);
    }

    /*  {
        "name": "New Category",
        "image": "https://placeimg.com/640/480/any"
        }
    */
    public Response createNewCategory(Object body){
        return baseclass.postRequest(body,"https://api.escuelajs.co/api/v1/categories/",false);
    }

    /*
      #Body
        {
        "name": "Updated Category Name",
        "image": "https://placeimg.com/640/480/any"
          }
      */
    public Response updateCategory(String body,String endpoint){
        return baseclass.updateResponse(body,endpoint,false);
    }

    public Response deleteCatogory(String endpoint){
        return baseclass.deleteRecord(endpoint,false);
    }


}
