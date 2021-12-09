package com.secure.dex.utils;

import java.util.concurrent.*;

public class ThreadTool {

    private static final int CORE_SIZE = Runtime.getRuntime().availableProcessors() + 1;
    private static final long TIMEOUT = 60L;
    private static final BlockingQueue<Runnable> BLOCKING_QUEUE = new LinkedBlockingQueue<>(128);

    private static final Executor calculatePool = new ThreadPoolExecutor(CORE_SIZE, CORE_SIZE, TIMEOUT, TimeUnit.SECONDS, BLOCKING_QUEUE);

    public static void execute(Runnable target) {
        calculatePool.execute(target);
    }
}