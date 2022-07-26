package com.sinkiang.ddd.sample.common.id.firework;

/**
 * @author dengxj
 * @date 2022/7/20 14:38
 */
public interface FireWorkStepBackHandler {
    int ARRAY_SIZE = FireWorkSubString.len();

    /**
     * xxx
     * @param before
     */
    default void notifyStepBack(long[] before) {
    }

    /**
     * xxx
     * @param serviceId
     * @return
     */
    default long[] getStepBackTimeRecordArray(int serviceId) {
        return new long[ARRAY_SIZE];
    }

    /**
     * xxx
     * @param serviceId
     * @param index
     * @param timeBeforeStepBack
     */
    default void setStepBackTimeRecordArray(int serviceId, int index, long timeBeforeStepBack) {
    }

    /**
     * xxx
     * @param serviceId
     * @param step
     */
    default void setStep(int serviceId, long step) {
    }

    /**
     * xxx
     * @param serviceId
     * @return
     */
    default long getStep(int serviceId) {
        return 0L;
    }
}
