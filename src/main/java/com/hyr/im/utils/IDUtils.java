package com.hyr.im.utils;

import java.util.Random;

public class IDUtils {
    /**
     * ååidçæ
     */
    public static Integer genItemId() {

        Random random = new Random();
        return random.nextInt(99);
    }
}
