package me.zhengjie.service.watcher.modules.source.task.detectors.utils;


import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author wuhao
 * @createTime 2022-04-18 16:15:00
 */
public class ThreadPoolUtils {
    private static final ConcurrentMap<String, ExecutorService>
            THREAD_MAP = new ConcurrentHashMap<>();
    private static int APS = Runtime.getRuntime().availableProcessors();


    public static ExecutorService getReferConfigExecutor() {
        String name = "refreshConfigPool";
        return getExecutorService(name, APS, APS);
    }

    public static ExecutorService getMonitorExecutor() {
        String name = "monitorTaskPool";
        return getExecutorService(name, APS * 8, APS * 8);
    }

    public static ExecutorService getMeterExecutor() {
        String name = "meterTaskPool";
        return getExecutorService(name, APS * 16, APS * 16);
    }

    private static ExecutorService getExecutorService(String name, int core, int max) {
        return THREAD_MAP.computeIfAbsent(name, n -> {
            ThreadFactory factory = new ThreadFactoryBuilder()
                    .setNamePrefix(name)
                    .setDaemon(true)
                    .build();
            return new ThreadPoolExecutor(core, max, 0L,
                    TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), factory);
        });
    }

}
