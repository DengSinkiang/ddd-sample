package com.sinkiang.ddd.sample.common.id.firework;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dengxj
 * @date 2022/7/20 14:37
 */
public class FireWork {
    private final AtomicInteger seq = new AtomicInteger(0);
    private final ThreadLocal<FireWorkBuilder> threadLocal = new ThreadLocal<>();
    long flag;
    int serviceId;
    long[] before;
    private FireWorkStepBackHandler handler;

    FireWork(int serviceId, FireWorkStepBackHandler handler) {
        this.serviceId = serviceId;
        if (handler != null) {
            this.before = handler.getStepBackTimeRecordArray(serviceId);
            this.flag = handler.getStep(serviceId);
        }

        if (this.before == null) {
            this.before = new long[FireWorkSubString.len()];
        }

    }

    void reportStepBack(long reportFlag, long beforeMill) {
        synchronized (this) {
            if (this.flag < reportFlag) {
                this.flag = reportFlag;
                if (this.handler != null) {
                    this.handler.setStep(this.serviceId, this.flag);
                }
            }

            int index = (int) (reportFlag % (long) FireWorkSubString.len());
            if (beforeMill > this.before[index]) {
                this.before[index] = beforeMill;
                if (this.handler != null) {
                    this.handler.setStepBackTimeRecordArray(this.serviceId, index, beforeMill);
                }
            }

        }
    }

    private FireWorkBuilder getGenerator() {
        FireWorkBuilder fireWork = this.threadLocal.get();
        if (fireWork == null) {
            fireWork = new FireWorkBuilder(this);
            this.threadLocal.set(fireWork);
        }

        return fireWork;
    }

    public String nextId() {
        int sequence = this.seq.getAndAdd(1);
        return this.getGenerator().buildId(sequence);
    }
}
