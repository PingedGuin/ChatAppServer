package com.app.common.id;

import org.springframework.stereotype.Service;

@Service
public class SnowflakeIdService {
    private static final long EPOCH = 1700000000000L;

    private final long workerId;

    private long sequence = 0;

    public SnowflakeIdService() {
        this.workerId = 1;
    }

    public synchronized long generate() {

        long timestamp = System.currentTimeMillis() - EPOCH;

        sequence = (sequence + 1) & 4095;

        return (timestamp << 22)
                | (workerId << 12)
                | sequence;
    }

}
