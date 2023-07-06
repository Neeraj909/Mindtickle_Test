package specbuilder;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class SpecBuilder {
    public static RequestSpecification getUserRequestSpecification(){
        return new RequestSpecBuilder().
                setBaseUri(Route.BASE_URI).
                setBasePath(Route.BASE_USERS).
                //setBaseUri(System.getProperty("BASE_URI")).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL).build();
    }
    public static ResponseSpecification getResponseSpecBuilder(){
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).build();
    }
    public static RequestSpecification getPetRequestSpec(){
        return new RequestSpecBuilder().
                setBaseUri(Route.BASE_URI).
                setBasePath(Route.BASE_PET).
                setContentType(ContentType.URLENC).
                log(LogDetail.ALL).build();
    }

}
