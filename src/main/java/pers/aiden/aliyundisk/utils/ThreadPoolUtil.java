package pers.aiden.aliyundisk.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 范淼
 * @Date: 2022-05-04
 * @Description:
 */
public class ThreadPoolUtil {
    public static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0,
            10,10, TimeUnit.SECONDS, new SynchronousQueue<>());
}
