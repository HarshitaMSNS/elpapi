package com.heroku.restfulbooker;

import framework.Token;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PutTest extends Token{
    @Test
    public void testPutWithCookie() {
        JSONObject bookingDatesJSonBody = new JSONObject();
        bookingDatesJSonBody.put("checkin", "2018-01-01");
        bookingDatesJSonBody.put("checkout", "2019-01-01");

        JSONObject body = new JSONObject();
        body.put("firstname", "James");
        body.put("lastname", "Brown");
        body.put("totalprice", 111);
        body.put("depositpaid", true);
        body.put("bookingdates", bookingDatesJSonBody);
        body.put("additionalneeds", "Breakfast");

        Response response = RestAssured.given().contentType(ContentType.JSON)
                .cookie("token",getToken() ).body(body.toString())
                .put("https://restful-booker.herokuapp.com/booking/1");
        response.print();

        Assert.assertEquals(response.jsonPath().getString("firstname"),"James");
        Assert.assertEquals(response.jsonPath().getString("lastname"),"Brown");
        Assert.assertEquals(response.jsonPath().getInt("totalprice"),111);
        Assert.assertEquals(response.jsonPath().getBoolean("depositpaid"),true);
        Assert.assertEquals(response.jsonPath().getString("bookingdates.checkin"),"2018-01-01");
        Assert.assertEquals(response.jsonPath().getString("bookingdates.checkout"),"2019-01-01");
        Assert.assertEquals(response.jsonPath().getString("additionalneeds"),"Breakfast");
    }
}