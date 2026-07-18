package org.springblade.modules.iot.common.utils;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ThreadUtil extends org.springblade.core.tool.utils.ThreadUtil {

    public static ScheduledThreadPoolExecutor newScheduled(int poolSize, String threadName) {
        return new ScheduledThreadPoolExecutor(poolSize, (Runnable r) -> {
            SecurityManager s = System.getSecurityManager();
            ThreadGroup group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            Thread t = new Thread(group, r,
                    threadName,
                    0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        });
    }
}
