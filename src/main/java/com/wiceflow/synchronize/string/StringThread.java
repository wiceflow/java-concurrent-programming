package com.wiceflow.synchronize.string;

/**
 * @author BF
 * @date 2018/3/12
 * 记synchronize下使用String字符串遇到的一个问题
 */
public class StringThread implements Runnable{

    private static final String LOCK_PREFIX = "XXX-";

    private String ip;

    public StringThread(String ip) {
        this.ip = ip;
    }

    @Override
    public void run() {
        String lock = buildLock();

        synchronized (lock){
            System.out.println("[ 线程开始运行了 ]");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("[ 线程结束了 ]");
        }

    }

    private String buildLock(){
        StringBuilder sb = new StringBuilder();
        sb.append(LOCK_PREFIX);
        sb.append(ip);
        String lock = sb.toString().intern();
        System.out.println("构建了锁[" + lock + "]");
        return lock;
    }
}
