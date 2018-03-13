# Java线程池详解
为了更好的控制多线程，`JDK`提供了一套线程框架`Executor`，帮助开发人员有效的进行线程控制。它们都在`java.util.concurrent`包中，是`JDK`并发包的核心。其中有一个比较重要的类:`Executors`，它扮演了这线程工厂的角色，我们通过`Executors`可以创建特定功能的线程池。

## Executor创建线程池的方法
- `newFixedThreadPool()`方法，该方法返回一个固定数量的线程池，该方法返回一个固定数量的线程池，该方法的线程数始终不变，当有一个任务提交时，若线程池中空闲，则立即执行，若没有，则会被暂缓在一个任务队列中等待有空闲的线程去执行。

- `newStringThreadExecutor()`方法，创建一个线程的线程池，若空闲则执行，若没有空闲线程则暂缓在任务队列中。

- `newCachedThreadPool()`方法，返回一个可根据实际情况调整线程个数的线程池，不限制最大线程数量，若有任务则创建线程，若无任务则不创建线程。如果没有任务则线程在60s后自动回收（空闲时间60秒）。

- `newScheduledThreadPool()`方法，该方法返回一个`newSchededExecutorService`对象，但该线程池可以指定线程的数量。

## 自定义线程池
若`Executors`工厂类无法满足我们的需求，可以自己去创建自定义的线程池，其实Executors工厂类面创建线程方法其内部实现均实是用了`ThreadPoolExcutor`这个类，这个类可以自定义线程。构造方法如下：
````java
    public ThreadPoolExcutor(int corePoolSize,     // 核心线程数
                             int maximumPoolSize,  // 最大执行线程数
                             long keepAliveTime,   // 线程保留时间
                             TimeUtil unit,        // 时间单位
                             BlockingQueue<Runnable>workQueue,  // 线程缓存队列
                             ThreadFactory threadFactory        // 这个不用传
                             RejectedExecutionHandler handler   // 拒绝策略
                             ){...}
````
### 自定义线程池使用详细
这个构造方法对于队列是什么类型的比较关键：
- 在使用有界队列时：若有新的任务需要执行，如果线程池实际线程数小于corePoolSize，则优先创建线程，若大于corPoolSize，则会将任务加入队列，若队列已满，则在总线程数不大于maximumPoolSize的前期下，创建新的线程，若已经大于maximumPoolSize,则执行拒绝策略。或其他自定义方式

- 无界的任务队列： LinkedBlockingQueue。与有界队列相比，除非系统资源耗尽，否则无界队列不存在任务入队失败的情况。当有新的任务到来，系统的线程小于corePoolSize时，则新建线程执行任务。当达到corePoolSize后，就不会继续增加。若后续仍有新的任务加入，而没有空闲的线程资源，则任务会直接进入队列等待。若任务创建和处理的速度差异很大，无界队列会保持快速增长，直到耗尽系统内存。

### JDK拒绝策略
- AbortPolicy: 直接抛出异常组织系统正常工作

- CallerRunsPolicy： 只要线程池未关闭，该策略直接在调用者线程中，运行当前被丢弃的任务。

- DiscardOldestPolicy： 丢弃最老的一个请求，尝试再次提交当前任务。

- DiscardPolicy： 丢弃无法处理的任务，不给予任何处理。

如果需要自定义拒绝策略可以实现RejectedExecutionHandler接口
