package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTest {
    @Test
    public void sanityTest(){
        Assert.assertTrue(true, "Sanity check passed!");
    }
}
