package ru.javawebinar.topjava.datasource;

import java.util.concurrent.atomic.AtomicInteger;

public class CountId {
    private static volatile AtomicInteger countId = new AtomicInteger(0);

    private CountId() {
    }

    public static CountId getInstance() {
        countId.incrementAndGet();
        return new CountId();
    }

    public int getCountId() {
        return countId.get();
    }
}
