package com.qa.tests.api;

import com.qa.base.BaseApiTest;
import com.qa.utils.TestUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Epic("Authentication API")
@Feature("Login & Token Management")
public class AuthApiTest extends BaseApiTest {
    @Test
    @Description("Verify login with valid credentials returns a token ")
    @Step("Logging in with username: {username}")
    public void testLoginSuccess(){
        ValidatableResponse validatable  =
        given()
                .contentType("application/json")
                .body("{\"username\":\"admin\",\"password\": \"password123\"}")
        .when()
                .post("/auth")
        .then()
                .statusCode(200)
                .body("token", notNullValue());

        String responseBody = validatable.extract().response().getBody().asString();
        TestUtils.attachResponse(responseBody);

    }

    @Test
    @Description("Verify login with Invalid credentials returns a invalid token")
    @Step("Logging in with invalid username: {username}")
    public void testLoginFailure(){
        ValidatableResponse validatable  =
        given()
                .contentType("application/json")
                .body("{\"username\":\"wrong\",\"password\": \"badpass\"}")
        .when()
                .post("/auth")
        .then()
                .statusCode(200)
                .body("reason", equalTo("Bad credentials"));
        String responseBody = validatable.extract().response().getBody().asString();
        TestUtils.attachResponse(responseBody);
    }

//    @Test
//    public void modifyAuthSuccess(){
//        String token =
//        given()
//                .contentType("application/json")
//                .body("{\"username\":\"admin\",\"password\": \"password123\"}")
//        .when()
//                .post("/auth")
//        .then()
//                .statusCode(200)
////                .body("token", notNullValue());
//                .extract()
//                .path("token");
//        System.out.println("Generated token: " + token);
//
//    }

}
