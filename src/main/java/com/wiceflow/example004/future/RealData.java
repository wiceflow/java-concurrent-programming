package com.wiceflow.example004.future;

/**
 * @author BF
 * @date 2018/3/22
 * 真是数据，其构造过程是比较慢的
 */
public class RealData implements Data {
    protected final String result;
    public RealData(String para){
        StringBuffer buffer=new StringBuffer();
        System.out.println("RealData 正在构造真实数据。。。");
        for (int i=0;i<10;i++){
            buffer.append(para);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("RealData 真实数据构造完成。。。");
        result=buffer.toString();
    }
    @Override
    public String getResult() {
        return result;
    }
}