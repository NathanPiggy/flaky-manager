package com.flaky.lifecycle.manager.flakylifecyclemanager.util;

public class RandomUtil {
    public static int getRandomNumber() {
        int randomNum = (int)(Math.random()*10);
        System.out.println("random result:" + randomNum);
        return randomNum;
    }
}
