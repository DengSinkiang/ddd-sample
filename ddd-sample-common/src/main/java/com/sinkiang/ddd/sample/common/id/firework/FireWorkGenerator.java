package com.sinkiang.ddd.sample.common.id.firework;

/**
 * @author dengxj
 * @date 2022/7/20 14:38
 */
public class FireWorkGenerator {
    private static FireWork fireWork;

    public FireWorkGenerator() {
    }

    public static void init(int serviceId, FireWorkStepBackHandler handler) {
        fireWork = new FireWork(serviceId, handler);
    }

    public static String nextId() {
        return fireWork.nextId();
    }
}
