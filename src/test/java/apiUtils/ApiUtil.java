package apiUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ApiUtil {

    public Map<String, Object> createRequestMap(String username, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", username);
        map.put("password", password);
        return map;
    }

    public String getTokens(String endpoint, Map<String, Object> queryParams) {
        Response response;
        if (queryParams != null) {
            response = RestAssured.given()
                    .queryParams(queryParams)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .post(endpoint)
                    .then()
                    .extract()
                    .body()
                    .jsonPath()
                    .get("token");
        } else {
            response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .post(endpoint);
        }
        if (response.getStatusCode() != HttpStatus.SC_OK) {
            System.out.println("Failed to get token. Response: " + response.getBody().asString());
            throw new RuntimeException("Failed to get token");
        }
        return response.getBody().jsonPath().get("token");
    }



    /**
     * Sends a POST request with the given endpoint and request body.
     * Returns the response from the API.
     *
     * @param endpoint  The endpoint URL for the POST request.
     * @param requestBody  The request body as a Map<String, Object>.
     * @param queryParams  The query parameters as a Map<String, Object> (optional).
     * @return  The response from the API as a Response object.
     */
    public static Response sendPostRequest(String endpoint, Map<String, Object> requestBody, Map<String, Object> queryParams) {
        Response response;
        if (queryParams != null) {
            response = RestAssured.given()
                    .queryParams(queryParams)
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .post(endpoint);
        } else {
            response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .post(endpoint);
        }
        return response;
    }

    /**
     * Sends a POST request with the given endpoint and request body.
     * Returns the response from the API as a string.
     *
     * @param endpoint  The endpoint URL for the POST request.
     * @param requestBody  The request body as a Map<String, Object>.
     * @param queryParams  The query parameters as a Map<String, Object> (optional).
     * @return  The response from the API as a string.
     */
    public static String sendPostRequestAndGetResponseBody(String endpoint, Map<String, Object> requestBody, Map<String, Object> queryParams) {
        Response response = sendPostRequest(endpoint, requestBody, queryParams);
        return response.getBody().asString();
    }

    /**
     * Sends a POST request with the given endpoint and request body.
     * Returns the response status code as an integer.
     *
     * @param endpoint  The endpoint URL for the POST request.
     * @param requestBody  The request body as a Map<String, Object>.
     * @param queryParams  The query parameters as a Map<String, Object> (optional).
     * @return  The response status code as an integer.
     */
    public static int sendPostRequestAndGetStatusCode(String endpoint, Map<String, Object> requestBody, Map<String, Object> queryParams) {
        Response response = sendPostRequest(endpoint, requestBody, queryParams);
        return response.getStatusCode();
    }

}
