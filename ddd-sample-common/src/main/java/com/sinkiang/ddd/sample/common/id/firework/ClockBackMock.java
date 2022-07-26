package com.sinkiang.ddd.sample.common.id.firework;

import java.util.Random;

/**
 * @author dengxj
 * @date 2022/7/20 14:37
 */
public class ClockBackMock {
    private static final Random RANDOM = new Random();
    private static long offset = 0L;

    public ClockBackMock() {
    }

    public static long getCurrentTimeMillis() {
        int bound = 1000;
        int bound2 = 10000;
        int random = 100;
        if (RANDOM.nextInt(bound) < random) {
            offset += (long) RANDOM.nextInt(bound2);
        }

        return System.currentTimeMillis() - offset;
    }

    public static void sleepMock(long ms) {
        offset -= ms;
    }
}
