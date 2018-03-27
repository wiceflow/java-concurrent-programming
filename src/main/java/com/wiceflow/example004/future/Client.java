package com.wiceflow.example004.future;

/**
 * @author BF
 * @date 2018/3/22
 * 返回Data对象，立即返回FutureData,并开启ClientThread线程装配RealData
 */
public class Client {
    public Data request(final String requestStr){
        final FutureData future=new FutureData();
        // RealData构建过程很慢，所以在单独的线程中进行
        // 这里进入了FutureData的setRealData
        new Thread(){
            @Override
            public void run() {
                // 因为RealData装载很慢，所以在外部调用 FutureData的getResult的时候，getResult方法一直处于wait状态
                // 知道这里的RealData装载完毕后，future.setRealData才可以继续运行，同时唤醒getResult方法
                RealData realData=new RealData(requestStr);
                future.setRealData(realData);
            }
        }.start();
        // future会被立即返回
        return future;
    }
}
