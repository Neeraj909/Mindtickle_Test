package com.test.swagger.apitestcases;
import com.test.swagger.annotation.TestCaseNotes;
import com.test.swagger.dataCreationFromModel.UserDataCreator;
import com.test.swagger.pojo.UserPojo;
import com.test.swagger.restapi.RestApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class UserAPITestCase extends BaseTest {
    Response response;
    public static List<UserPojo> listOfUser;
    String username = "";
    @Test(priority = 0,description="Test-1::Create multiple Users")
    public void createListOfUser() {
        UserDataCreator us = new UserDataCreator();
        listOfUser = us.createUser(5);
        response= RestApi.userPost("/createWithArray",listOfUser);
        response.then().statusCode(200);
        updateResponseAndRequestIntoReport(writer.toString(), response.prettyPrint());
    }
    @Test(dependsOnMethods = "createListOfUser", priority = 1,description = "Test-2::Update a user's username and other details")
    public void updateUserDetail() {
        UserDataCreator us = new UserDataCreator();
        UserPojo user = us.DataForUSerCreation();
        username = user.getUsername();
        response= RestApi.userPut(String.format("/%s",username),user);
        response.then().statusCode(200);
        updateResponseAndRequestIntoReport(writer.toString(), response.prettyPrint());
    }

    @Test(dependsOnMethods = "updateUserDetail", priority = 2,description ="Test-3::Get user by the updated username")
    public void getUserDetail() {
        String name=RestApi.getUser(String.format("/%s",username)).then().assertThat().statusCode(200).
                extract().response().path("username");
        Assert.assertEquals(name,username);
        updateResponseAndRequestIntoReport(writer.toString(),"UserName-->"+username);

    }
}