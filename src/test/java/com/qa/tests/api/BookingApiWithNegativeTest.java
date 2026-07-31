package com.qa.tests.api;

import com.qa.base.BaseApiTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

@Epic("Booking API Tests")
@Feature("CRUD Operations")
public class BookingApiWithNegativeTest extends BaseApiTest {

    // Create Booking - with Invalid Payloads
    // - missing required fields(e.g. no firstname)
    // - wrong data type(e.g. totalPrice as a string instead of number)
    // - invalid date format in bookingdates

    @Test(priority = 1)
    @Description("Create a new booking with invalid firstname")
    @Step("Create a new Booking with invalid: {firstname}")
    public void testCreateBookingMissingFirstname() {
        given()
                .contentType("application/json")
                .body("{\"lastname\" :\"QA\",\"totalprice\":1500, \"depositpaid\":true}")
        .when()
                .post("/booking")
        .then()
                .statusCode(500); // API returns 500 for malformed payload

    }

    // Read Booking - with Non-existent ID
    // try to fetch a booking that doesn't exist
    @Test(priority = 2)
    @Description("Read booking invalid details")
    @Step("Read booking with invalid details: {999999}")
    public void testGetBookingInvalid() {
        given()
            .when()
                .get("/booking/999999") // unlikely to exist
            .then()
                .statusCode(404);
    }

    // Update Booking - with wrong token
    // use an invalid or expired token

    @Test(priority = 3)
    @Description("Update booking with invalid token")
    @Step
    public void testUpdateBookingWithInvalidToken() {
        given()
                .contentType("application/json")
                .cookie("token", "invalidToken123")
                .body("{\"firstname\": \"krishna\",\"lastname\":\"Fail\"}")
        .when()
                .put("/booking/1")
        .then()
                .statusCode(403); // Forbidden

    }

    // Delete Booking - With wrong token
    // try deleting with a bad token

    @Test(priority = 4)
    @Description("Delete booking with invalid token")
    @Step
    public void testDeleteBookingWithInvalidToken() {
        given()
                .cookie("token", "wrongTokne")
        .when()
                .delete("/booking/1")
        .then()
                .statusCode(403);
    }
}
