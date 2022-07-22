package com.sinkiang.ddd.sample.common.id.snowball;

import com.sinkiang.ddd.sample.common.id.firework.FireWorkGenerator;
import com.sinkiang.ddd.sample.common.id.firework.FireWorkStepBackHandler;

/**
 * @author dengxj
 * @date 2022/7/20 14:44
 */
public class SnowBallGenerator {
    public SnowBallGenerator() {
    }

    public static void init(int serviceId) {
        FireWorkGenerator.init(serviceId, null);
    }

    public static void init(int serviceId, FireWorkStepBackHandler handler) {
        FireWorkGenerator.init(serviceId, handler);
    }

    public static String nextId() {
        return FireWorkGenerator.nextId();
    }

}
