package com.heroku.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteUserTest {
    @Test
    public void testDeleteUsers() {
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .delete("https://reqres.in/api/users/2");
        Assert.assertEquals(response.getStatusCode(),204);
    }
}
