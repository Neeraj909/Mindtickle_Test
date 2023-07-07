package com.test.swagger.apitestcases;

import com.test.swagger.annotation.TestCaseNotes;
import com.test.swagger.dataCreationFromModel.PetDataCreator;
import com.test.swagger.dataCreationFromModel.UserDataCreator;
import com.test.swagger.pojo.PetPojo;
import com.test.swagger.pojo.UserPojo;
import com.test.swagger.restapi.RestApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class PetAPITestCase extends BaseTest {
    Response response;
    PetDataCreator pet = new PetDataCreator();
    PetPojo petPojo;
    String status="";
    @Test(priority = 0)
    public void createMultiplePets() {
        status="available";
        petPojo=pet.petDataCreator(status);
        response= RestApi.petPost(petPojo);
        response.then().statusCode(200);
        updateResponseAndRequestIntoReport(writer.toString(), response.prettyPrint());
    }
    @Test(dependsOnMethods = "createMultiplePets", priority = 1)
    public void updatePets() {
        status="pending";
        petPojo=pet.petDataCreator(status);
        response= RestApi.petPost(petPojo);
        response.then().statusCode(200);
        updateResponseAndRequestIntoReport(writer.toString(), response.prettyPrint());
    }

    @Test(dependsOnMethods = "updatePets", priority = 2)
    public void getPetDetail() {
         response=RestApi.getPet(String.format("findByStatus?status=%s",status));
    }
}