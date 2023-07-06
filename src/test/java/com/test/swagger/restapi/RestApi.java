package com.test.swagger.restapi;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static specbuilder.SpecBuilder.getResponseSpecBuilder;
import static specbuilder.SpecBuilder.getUserRequestSpecification;

public class RestApi {
    public static Response post(String path, String token, Object request){
        return given(getUserRequestSpecification()).
                body(request).
                auth().oauth2(token).
                when().
                post(path).
                then().
                spec(getResponseSpecBuilder()).
                assertThat().
                statusCode(201).
                extract().response();
    }
    public static Response get(String path,String token){
        return  given((RequestSpecification) getResponseSpecBuilder()).
                auth().oauth2(token).
                when().
                get(path).
                then().
                spec(getResponseSpecBuilder()).extract().response();
    }

    public static Response put(String path,String token,Object request){
        return given().
                body(request).
                auth().oauth2(token).
                when().
                put(path).
                then().
                spec(getResponseSpecBuilder()).extract().response();
    }
}
