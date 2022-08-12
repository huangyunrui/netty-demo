package com.hyr.im.utils;

import java.util.Random;

public class IDUtils {
    /**
     * 商品id生成
     */
    public static Integer genItemId() {

        Random random = new Random();
        return random.nextInt(99);
    }
}
