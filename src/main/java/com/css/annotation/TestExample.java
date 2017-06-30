package com.css.annotation;

/**
 * Created by kishore on 8/2/17.
 */
@TesterInfo(
        priority = TesterInfo.Priority.HIGH,
        createdBy = "Kishore Routhu",
        tags = {"tests", "Commerce"}
        )
public class TestExample {

    @Test
    void testA() {
        if(true)
            throw new RuntimeException("This test always failed");
    }

    @Test(enabled = false)
    void testB() {
        if (false)
            throw new RuntimeException("This test always passed");
    }

    @Test(enabled = true)
    void testC() {
        if(10 > 1)
            System.out.println("This test always passed");
    }
}
