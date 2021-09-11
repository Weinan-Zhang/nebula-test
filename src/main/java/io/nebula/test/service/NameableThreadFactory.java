package io.nebula.test.service;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NameableThreadFactory implements ThreadFactory {
    private final AtomicInteger threadsNum = new AtomicInteger();
    private final String namePattern;

    public NameableThreadFactory(String namePattern) {
        this.namePattern = namePattern + "-%d";
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, String.format(namePattern, threadsNum.addAndGet(1)));
    }
}
