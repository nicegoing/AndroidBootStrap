package com.androidbootstrap;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        assertSame("字符串相等", "dd", "dd");
    }


    @Test(expected = ArithmeticException.class)
    public void sub() throws Exception {
        int i=4/0;
    }

}