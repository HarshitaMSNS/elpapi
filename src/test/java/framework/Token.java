package framework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

public class Token {
    public String getToken(){
        JSONObject token = new JSONObject();
        token.put("username", "admin");
        token.put("password", "password123");

        Response res = RestAssured.given().contentType(ContentType.JSON).body(token.toString())
                .post("https://restful-booker.herokuapp.com/auth");
        return res.jsonPath().getString("token");
    }
}
