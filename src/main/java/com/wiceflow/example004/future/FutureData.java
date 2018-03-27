package com.wiceflow.example004.future;

/**
 * @author BF
 * @date 2018/3/22
 * Future数据，构造很快，但是是一个虚拟的数据，需要装配RealData
 * FutureData是Future模式的核心，是RealData的真实代理，封装了等待RealData的过程。
 */
public class FutureData implements Data {
    /**
     * FutureData是RealData的包装
     */
    protected RealData realData=null;
    protected boolean isReady=false;

    public synchronized void setRealData(RealData realData){
        if (isReady){
            return;
        }
        this.realData=realData;
        isReady=true;
        //RealData已被注入，通知getResult()
        notifyAll();
    }


    @Override
    public synchronized String getResult() {
        //等待RealData构造完成
        while (!isReady){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 由RealData实现
        return realData.result;
    }
}
