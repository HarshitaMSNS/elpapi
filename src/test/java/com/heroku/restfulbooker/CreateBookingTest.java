package com.heroku.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateBookingTest {
    @Test
    public void testCreateNewBooking() {
        JSONObject bookingDatesJSonBody = new JSONObject();
        bookingDatesJSonBody.put("checkin", "2018-01-01");
        bookingDatesJSonBody.put("checkout", "2019-01-01");

        JSONObject body = new JSONObject();
        body.put("firstname", "XYZHarsh");
        body.put("lastname", "Varma");
        body.put("totalprice", 120);
        body.put("depositpaid", true);
        body.put("bookingdates", bookingDatesJSonBody);
        body.put("additionalneeds", "Lunch");

        Response response = RestAssured.given().contentType(ContentType.JSON).body(body.toString()).post("https://restful-booker.herokuapp.com/booking");
        response.print();

        Assert.assertEquals(response.jsonPath().getString("booking.firstname"),"XYZHarsh");
        Assert.assertEquals(response.jsonPath().getString("booking.lastname"),"Varma");
        Assert.assertEquals(response.jsonPath().getInt("booking.totalprice"),120);
        Assert.assertEquals(response.jsonPath().getBoolean("booking.depositpaid"),true);
        Assert.assertEquals(response.jsonPath().getString("booking.bookingdates.checkin"),"2018-01-01");
        Assert.assertEquals(response.jsonPath().getString("booking.bookingdates.checkout"),"2019-01-01");
        Assert.assertEquals(response.jsonPath().getString("booking.additionalneeds"),"Lunch");
    }
}
