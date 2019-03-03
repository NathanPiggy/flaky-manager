package com.flaky.lifecycle.manager.flakylifecyclemanager.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class RandomUtilTest {

    @Test
    public void getRandomNumber() {
        assertTrue(RandomUtil.getRandomNumber() > 1);
    }
}