package com.qa.tests.api;


import com.qa.base.BaseApiTest;
import com.qa.utils.AuthUtils;
import com.qa.utils.BookingUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Epic("Booking API Tests")
@Feature("CRUD Operations")
public class BookingAPITest extends BaseApiTest {
    int bookingId; // store for reuse

    @Test(priority = 1)
    @Description("Create a new booking with valid payload")
    // Create Booking
    public void testCreateBooking(){
        bookingId =
                given()
                        .contentType("application/json")
                        .body(BookingUtils.createBookingPayload("krishna", "QA"))
                .when()
                        .post("/booking")
                .then()
                        .statusCode(200)
                        .extract()
                        .path("bookingid");
        System.out.println("Booking Created with ID: " + bookingId);
    }

    @Test(priority = 2, dependsOnMethods = "testCreateBooking")
    @Description("Read booking details by ID")
    // Read Booking
    public void testGetBooking(){
        given()
            .when()
                .get("/booking/" + bookingId) // replace with actual bookingId
            .then()
                .statusCode(200)
                .body("firstname", equalTo("krishna"));
    }
    @Test(priority = 3, dependsOnMethods = "testCreateBooking")
    @Description("Update booking with valid token")
    // Update Booking (requires token)
    public void testUpdateBooking() {
        String token = AuthUtils.getToken();
                given()
                        .contentType("application/json")
                        .cookie("token", token)
                        .body(BookingUtils.createBookingPayload("krishna", "Updated"))
                .when()
                        .put("/booking/" + bookingId)
                .then()
                        .statusCode(200)
                        .body("lastname", equalTo("Updated"));
    }
    @Test(priority = 4, dependsOnMethods = "testCreateBooking")
    @Description("Delete booking with valid token")
    // Delete Booking (requires token)
    public void testDeleteBooking(){
        String token = AuthUtils.getToken();
        given()
                .cookie("token", token)
        .when()
                .delete("/booking/" + bookingId)
        .then()
                .statusCode(201); // on successful return 201
    }





}
