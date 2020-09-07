package com.heroku.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PatchUsersTest {
    @Test
    public void testPatchUsers() {
        JSONObject body = new JSONObject();
        body.put("name", "morpheus");
        body.put("job", "Zion resident");

        Response response = RestAssured.given().contentType(ContentType.JSON)
                .body(body.toString()).patch("https://reqres.in/api/users/2");

        response.print();
        Assert.assertEquals(response.jsonPath().getString("name"),"morpheus");
    }
}
