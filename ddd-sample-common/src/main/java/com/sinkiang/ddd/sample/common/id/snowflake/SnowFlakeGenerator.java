package com.sinkiang.ddd.sample.common.id.snowflake;

import com.sinkiang.ddd.sample.common.id.snowball.SnowBallGenerator;

/**
 * @author dengxj
 * @date 2022/7/20 14:46
 */
public class SnowFlakeGenerator {
    private static SnowflakeIdWorker worker;

    public SnowFlakeGenerator() {
    }

    public static void init(long workId, long dataCenterId) {
        worker = new SnowflakeIdWorker(dataCenterId, workId);
    }

    public static String getNextId() {
        return String.valueOf(worker.nextId());
    }
}
