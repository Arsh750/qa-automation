package com.qa.utils;

import io.qameta.allure.Attachment;

public class TestUtils {
    @Attachment(value = "Response", type = "application/json")
    public static String attachResponse(String response){
        return response;
    }
}
