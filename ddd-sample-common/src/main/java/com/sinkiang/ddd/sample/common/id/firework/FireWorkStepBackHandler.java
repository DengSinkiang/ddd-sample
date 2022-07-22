package com.sinkiang.ddd.sample.common.id.firework;

/**
 * @author dengxj
 * @date 2022/7/20 14:38
 */
public interface FireWorkStepBackHandler {
    int ARRAY_SIZE = FireWorkSubString.len();

    default void notifyStepBack(long[] before) {
    }

    default long[] getStepBackTimeRecordArray(int serviceId) {
        return new long[ARRAY_SIZE];
    }

    default void setStepBackTimeRecordArray(int serviceId, int index, long timeBeforeStepBack) {
    }

    default void setStep(int serviceId, long step) {
    }

    default long getStep(int serviceId) {
        return 0L;
    }
}
