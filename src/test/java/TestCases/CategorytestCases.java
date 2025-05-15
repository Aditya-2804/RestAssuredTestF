package TestCases;

import Base.Baseclass;
import Payloads.Categories;
import Services.CategoryServices;
import Utilities.Utils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;

public class CategorytestCases {

    CategoryServices categoryServices;

   Utils utils = new Utils();


//    https://api.escuelajs.co/api/v1/categories
    @Test
    public void getAlltheCatogories(){

        categoryServices = new CategoryServices();
        Response response = categoryServices.getCategories("https://api.escuelajs.co/api/v1/categories");
        response.then()
                .statusCode(200)
                .header("Content-Type","application/json; charset=utf-8")
                .header("Access-Control-Allow-Origin","*");

        List<Categories> categoriesList =  categoryServices.getCategoriesList("https://api.escuelajs.co/api/v1/categories");

        Categories categories2 = categoriesList.get(1);
        System.out.println(categories2.toString());
        Assert.assertTrue(categoriesList.size()>2, "Categories cannot be less than 2");
        System.out.println("Response Time: "+response.getTime());
        Assert.assertTrue(response.getTime() < 5000, "Response time should not exceed 2s");

    }

    @Test
    public void getSingleCategoryByID(){
        categoryServices = new CategoryServices();
        Response response = categoryServices.getCategories("https://api.escuelajs.co/api/v1/categories/1");
        response.then()
                .statusCode(200)
                .header("Content-Type","application/json; charset=utf-8")
                .header("Access-Control-Allow-Origin","*")
                .body("id",equalTo(1));
//                .body("name",containsString("Updated Category Name"))
//                .body("slug",containsString("updated-category-name"));


        Categories categories =response.then().extract().as(Categories.class);
        System.out.println(categories.toString());
    }

    @Test(groups = "Create")
    public void createCategory(){
        categoryServices = new CategoryServices();

        Response response = categoryServices.createNewCategory("{\n" +
                "  \"name\": \"New Category\",\n" +
                "  \"image\": \"https://placeimg.com/640/480/any\"\n" +
                "}");
        response.then()
                .statusCode(201)
                .log()
                .all();

        int ID = response.jsonPath().getInt("id");
        utils.writeToProperty(Baseclass.CATEGORY_ID,String.valueOf(ID));

    }

    /*
            #Body
        {
            "name": "Updated Category Name",
            "image": "https://placeimg.com/640/480/any"
            }
    */
    @Test(dependsOnGroups = "Create",groups = "Create")
    public void updatecategory(){

        int ID = Integer.parseInt(utils.readFromProperty(Baseclass.CATEGORY_ID));
        System.out.println(ID);
        categoryServices = new CategoryServices();
        Categories category5 = categoryServices.getCategories("https://api.escuelajs.co/api/v1/categories/"+ID)
                .then().extract().as(Categories.class);
        System.out.println("Category 5 Name Before: "+category5.getName());
        System.out.println("Category 5 Image Before: "+category5.getImage());

        Response category5new =categoryServices.updateCategory("{\n" +
                "            \"name\": \"Updated Category Name\",\n" +
                "            \"image\": \"https://placeimg.com/640/480/any\"\n" +
                "            }","https://api.escuelajs.co/api/v1/categories/"+ID);


        System.out.println("Category 5 Name After: "+category5new.jsonPath().getString("name"));
        System.out.println("Category 5 Image After: "+category5new.jsonPath().getString("image"));
    }

    @Test(dependsOnMethods = "updatecategory",groups = "Create")
    public void deleteCategory(){
        int ID = Integer.parseInt(utils.readFromProperty(Baseclass.CATEGORY_ID));
        categoryServices = new CategoryServices();
        categoryServices.deleteCatogory("https://api.escuelajs.co/api/v1/categories/"+ID)
                .then()
                .body(containsString("true"))
                .log().body();
    }

}
