package com.test.swagger.apitestcases;

import com.google.gson.JsonObject;
import com.test.swagger.annotation.TestCaseNotes;
import com.test.swagger.dataCreationFromModel.PetDataCreator;
import com.test.swagger.dataCreationFromModel.UserDataCreator;
import com.test.swagger.pojo.PetPojo;
import com.test.swagger.pojo.UserPojo;
import com.test.swagger.restapi.RestApi;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class PetAPITestCase extends BaseTest {
    PetDataCreator pet = new PetDataCreator();
    PetPojo petPojo;
    String status="";
    @Test(priority = 0,description="Test-1::Create multiple pets")
    public void createMultiplePets() {
        status="available";
        petPojo=pet.petDataCreator(status);
        response= RestApi.petPost(petPojo);
        response.then().statusCode(200);
        updateResponseAndRequestIntoReport(writer.toString(), response.prettyPrint());
    }
    @Test(dependsOnMethods = "createMultiplePets", priority = 1,description="Test-2::Update pet's status and other details (Statuses to be considered: available,pending and sold)")
    public void updatePets() {
        status="pending";
        petPojo=pet.petDataCreator(status);
        response= RestApi.petPost(petPojo);
        response.then().statusCode(200);
        updateResponseAndRequestIntoReport(writer.toString(), response.prettyPrint());
    }

    @Test(dependsOnMethods = "updatePets", priority = 2,description = "Test-3::Get pet by status and verify updated status of pet")
    public void getPetDetail() throws JSONException {
         response=RestApi.getPet(String.format("findByStatus?status=%s",status));
        JSONArray jsonArray = new JSONArray(response.asString());
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        String status=jsonObject.getString("status");
        Assert.assertEquals(status,status);
        updateResponseAndRequestIntoReport(writer.toString(), response.prettyPrint());

    }
}