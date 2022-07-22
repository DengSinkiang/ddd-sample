package com.sinkiang.ddd.sample.common.id.firework;

/**
 * @author dengxj
 * @date 2022/7/20 14:38
 */
public class FireWorkBuilder {
    private final FireWork fireWork;
    private static final int LARGEST_SEQ = (int)Math.pow((double)FireWorkSubString.len(), 5.0D);
    private final FireWorkSubString serviceId;
    private long step = 0L;
    private long timestamp = System.currentTimeMillis();
    private final FireWorkSubString timestampStr;
    private long offset;
    private long lastStep;

    FireWorkBuilder(FireWork fireWork) {
        this.timestampStr = FireWorkSubString.of(this.timestamp, 8);
        this.offset = 0L;
        this.lastStep = 0L;
        this.fireWork = fireWork;
        this.serviceId = FireWorkSubString.of(fireWork.serviceId, 2);
    }

    private void recalculateOffset(long timestamp) {
        int index = (int)(this.step % (long)FireWorkSubString.len());
        long before = this.fireWork.before[index];
        if (before != 0L && before - timestamp >= 0L) {
            this.offset = before - timestamp + 1L;
        }

    }

    public String buildId(int sequence) {
        sequence %= LARGEST_SEQ;
        if (sequence < 0) {
            sequence += LARGEST_SEQ;
        }

        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.timestamp < 0L) {
            this.fireWork.reportStepBack(this.step, this.timestamp);
            ++this.step;
        }

        this.step = Math.max(this.fireWork.flag, this.step);

        assert this.step >= 0L;

        if (this.step != this.lastStep) {
            this.recalculateOffset(this.timestamp);
            this.lastStep = this.step;
        }

        this.timestampStr.addAtFixLen(currentTimeMillis - this.timestamp);
        this.timestampStr.addAtFixLen(this.offset);
        this.timestamp = currentTimeMillis;
        return this.timestampStr + FireWorkSubString.of(this.step, 1).toString() + this.serviceId + FireWorkSubString.of(sequence, 5);
    }
}
