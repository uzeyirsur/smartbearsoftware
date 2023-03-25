package com.example.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.StringReader;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * API Utilities class for common useful methods in API testing
 */
public class APIUtils {

    private static RequestSpecification request;
    private static Response response;

    /**
     * Sets the base URI for API requests
     *
     * @param baseURI The input baseURI string.
     */
    public static void setBaseURI(String baseURI) {
        RestAssured.baseURI = baseURI;
    }


    /**
     * Sends a GET request to the specified URL
     *
     * @param url         - The URL to send the GET request to
     * @param queryParams - Map of query parameters to be added to the URL (can be null)
     * @param headers     - Map of headers to be added to the GET request (can be null)
     * @return Response object containing the response of the GET request
     */
    public static Response sendGetRequest(String url, Map<String, String> queryParams, Map<String, String> headers) {
        RestAssured.baseURI = url;
        return RestAssured.given().queryParams(queryParams).headers(headers).get();
    }


    /**
     * Sends a POST request to the specified URL with specified payload
     *
     * @param endpoint - The URL to send the POST request to
     * @param payload  - Request body in JSON format
     * @param headers  - Map of headers to be added to the POST request (can be null)
     * @return Response object containing the response of the POST request
     */
    public static Response sendPostRequest(String endpoint, String payload, Map<String, String> headers) {
        RestAssured.baseURI = endpoint;
        return RestAssured.given().contentType(ContentType.JSON).body(payload).headers(headers).post();
    }

    /**
     * Sends a POST request with payload and specified content type to the endpoint URL
     *
     * @param endpoint    - The URL to send the POST request to
     * @param payload     - Request body
     * @param headers     - Map of headers to add to the POST request (optional)
     * @param contentType - Type of content (e.g. "JSON" or "XML")
     * @return Response object containing the result of the POST request
     */
    public static Response sendPostRequest(String endpoint, String payload, Map<String, String> headers, String contentType) {
        RestAssured.baseURI = endpoint;
        ContentType type = contentType.equalsIgnoreCase("JSON") ? ContentType.JSON : ContentType.XML;
        return RestAssured.given().contentType(type).body(payload).headers(headers).post();
    }


    /**
     * Sends a PUT request to the specified URL with specified payload
     *
     * @param endpoint - The URL to send the PUT request to
     * @param payload  - Request body in JSON format
     * @param headers  - Map of headers to be added to the PUT request (can be null)
     * @return Response object containing the response of the PUT request
     */
    public static Response sendPutRequest(String endpoint, String payload, Map<String, String> headers) {
        RestAssured.baseURI = endpoint;
        return RestAssured.given().contentType(ContentType.JSON).body(payload).headers(headers).put();
    }

    /**
     * Sends a PUT request to the specified URL with specified payload
     *
     * @param endpoint    - The URL to send the PUT request to
     * @param payload     - Request body in JSON format
     * @param headers     - Map of headers to be added to the PUT request (can be null)
     * @param contentType - Type of content (e.g. "JSON" or "XML")
     * @return Response object containing the response of the PUT request
     */
    public static Response sendPutRequest(String endpoint, String payload, String contentType, Map<String, String> headers) {
        RestAssured.baseURI = endpoint;
        ContentType type = contentType.equalsIgnoreCase("JSON") ? ContentType.JSON : ContentType.XML;
        return RestAssured.given().contentType(type).body(payload).headers(headers).put();
    }


    /**
     * Sends a DELETE request to the specified URL
     *
     * @param endpoint    - The URL to send the DELETE request to
     * @param queryParams - Map of query parameters to be added to the URL (can be null)
     * @param headers     - Map of headers to be added to the DELETE request (can be null)
     * @return Response object containing the response of the DELETE request
     */
    public static Response sendDeleteRequest(String endpoint, Map<String, String> queryParams,
                                             Map<String, String> headers) {
        RestAssured.baseURI = endpoint;
        return RestAssured.given().queryParams(queryParams).headers(headers).delete();
    }


    /**
     * Sends a GET request to the specified URL
     *
     * @param url The URL to send the GET request to
     * @return Response The response of the GET request
     */
    public static Response sendGetRequest(String url) {
        return given().when().get(url);
    }


    /**
     * Converts a JSON string to a pretty-printed JSON string.
     *
     * @param jsonString The input JSON string.
     * @return The pretty-printed JSON string.
     */
    public static String prettyPrintJson(String jsonString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonReader jsonReader = new JsonReader(new StringReader(jsonString));
        jsonReader.setLenient(true);
        JsonElement jsonElement = gson.fromJson(jsonReader, JsonElement.class);
        return gson.toJson(jsonElement);
    }


    /**
     * Returns the request specification for API requests
     *
     * @return RequestSpecification
     */
    public static RequestSpecification getRequestSpecification() {
        request = given().contentType(ContentType.JSON);
        return request;
    }


    /**
     * Sends a POST request to the specified URL with the specified payload
     *
     * @param url     - The URL to send the POST request to
     * @param payload - The request body as a string
     * @return Response object containing the response of the POST request
     */
    public static Response sendPostRequest(String url, String payload) {
        return RestAssured.given().contentType(ContentType.JSON).body(payload).post(url);
    }
}
