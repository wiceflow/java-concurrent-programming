package com.wiceflow.example004.future;

/**
 * @author BF
 * @date 2018/3/22
 * Future的简单实现。
 * 系统启动，调用client发出请求
 * 建立了数据对象，延迟调用
 * 在别的业务处理完后再调用，此时数据已经装载完毕。
 */

public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        // 这里会立即返回，因为得到的是FutureData,而不是RealData
        Data data = client.request("name");
        System.out.println("发送请求完毕。。。");
        try {
            // 模拟其他业务逻辑处理
            // 处理过程中，RealData被创建，充分利用了等待的时间。
            System.out.println("Main 正在调用其他业务逻辑。。。");
            Thread.sleep(12000);
            System.out.println("Main 其他业务处理完成。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 使用真实的数据
        System.out.println("数据= " + data.getResult());
    }
}
