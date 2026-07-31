package com.qa.base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseApiTest {
    @BeforeClass
    public void setup(){
        // Replace with your AUT base URL
        RestAssured.baseURI = "http://restful-booker.herokuapp.com";
    }
}
