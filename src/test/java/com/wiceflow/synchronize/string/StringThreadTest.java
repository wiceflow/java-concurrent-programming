package com.wiceflow.synchronize.string;

import org.junit.Test;

/**
 * @author BF
 * @date 2018/3/12
 */
public class StringThreadTest {
    private static final int THREAD_COUNT = 5;

    @Test
    public void testStringThread(){
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i=0;i<threads.length;i++){
            threads[i] = new Thread(new StringThread("192.168.1.1"));
        }
        for (int i=0;i<threads.length;i++){
            threads[i].start();
        }
        for (;;);
    }
}
