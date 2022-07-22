package com.sinkiang.ddd.sample.common.id.firework;

import java.util.Random;

/**
 * @author dengxj
 * @date 2022/7/20 14:37
 */
public class ClockBackMock {
    private static final Random random = new Random();
    private static long offset = 0L;

    public ClockBackMock() {
    }

    public static long getCurrentTimeMillis() {
        if (random.nextInt(1000) < 100) {
            offset += (long) random.nextInt(10000);
        }

        return System.currentTimeMillis() - offset;
    }

    public static void sleepMock(long ms) {
        offset -= ms;
    }
}
