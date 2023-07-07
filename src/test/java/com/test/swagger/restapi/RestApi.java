package com.test.swagger.restapi;

import com.test.swagger.apitestcases.BaseTest;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static com.test.swagger.specbuilder.SpecBuilder.*;
import static com.test.swagger.specbuilder.SpecBuilder.getUserRequestSpecification;
import static io.restassured.RestAssured.given;

public class RestApi extends BaseTest {
    public static Response userPost(String path, Object request){
        return given(getUserRequestSpecification()).
                filter(new RequestLoggingFilter(captor)).
                body(request).
                when().
                post(path).
                then().
                spec(getResponseSpecBuilder()).
                extract().response();
    }
    public static Response getUser(String path){
        return  given((getUserRequestSpecification())).
                filter(new RequestLoggingFilter(captor)).
                when().
                get(path).
                then().
                spec(getResponseSpecBuilder()).extract().response();
    }

    public static Response userPut(String path,Object request){
        return given(getUserRequestSpecification()).
                filter(new RequestLoggingFilter(captor)).
                body(request).
                when().
                put(path).
                then().
                spec(getResponseSpecBuilder()).extract().response();
    }

    public static Response petPost(Object request){
        return given(getPetRequestSpec()).
                filter(new RequestLoggingFilter(captor)).
                body(request).
                when().
                post().
                then().
                spec(getResponseSpecBuilder()).
                extract().response();
    }
    public static Response getPet(String path){
        return  given((getPetRequestSpec())).
                filter(new RequestLoggingFilter(captor)).
                when().
                get(path).
                then().
                spec(getResponseSpecBuilder()).extract().response();
    }


}
