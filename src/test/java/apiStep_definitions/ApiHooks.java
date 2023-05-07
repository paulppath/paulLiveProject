package apiStep_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ApiHooks
{
    private static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        ApiHooks.token = token;
    }

    @Before("@token")
    public static void retrieveToken() {
        RestAssured.baseURI = "https://tla-school-api.herokuapp.com/api/school/programs/devcourse";

        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("password", ConfigReader.readProperty("password"));
//        map.put("username", ConfigReader.readProperty("username"));

        Response response = RestAssured.given()
                .queryParams(map)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post("/public/users/login")
                .then()
                .log().all()
                .extract()
                .response();
        token = response.jsonPath().getString("token");
        if (token != null) {
            ApiHooks.setToken(token);
            System.out.println("Token retrieved: " + token);
        } else {
            System.out.println("Failed to retrieve token");
        }
    }


    @After("@token")
    public void clearToken() {
        ApiHooks.setToken(null);
    }
}

