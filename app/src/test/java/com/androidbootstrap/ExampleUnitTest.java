package com.androidbootstrap;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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


    @Test
    public void sub() throws Exception {
        List<String> list = new ArrayList();
        list.add("1");
        list.add("1");
        list.addAll(list);
        assertEquals(list.size(),4);
    }

}