package com.heroku.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class GetBookingTest {
    @Test
    public void testGetBooking() {
        Response response = RestAssured.given().contentType(ContentType.JSON).get("https://restful-booker.herokuapp.com/booking/1");
        response.print();

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(response.body().jsonPath().getString("lastname"), "Smith");

    }


}
