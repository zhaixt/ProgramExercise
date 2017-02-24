import bean.disruptor.TradeTransaction;
import bean.disruptor.TradeTransactionInDBHandler;
import com.lmax.disruptor.*;

import java.util.UUID;
import java.util.concurrent.*;

/**
* Created by zhaixiaotong on 2016-12-20.
*/




public class DisruptorTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int BUFFER_SIZE=1024;
        int THREAD_NUMBERS=4;
    /*
     * createSingleProducer创建一个单生产者的RingBuffer，
     * 第一个参数叫EventFactory，从名字上理解就是“事件工厂”，其实它的职责就是产生数据填充RingBuffer的区块。
     * 第二个参数是RingBuffer的大小，它必须是2的指数倍 目的是为了将求模运算转为&运算提高效率
     * 第三个参数是RingBuffer的生产都在没有可用区块的时候(可能是消费者（或者说是事件处理器） 太慢了)的等待策略
     */
        //有三种等待策略:
        // BlockingWaitStrategy 是最低效的策略，但其对CPU的消耗最小并且在各种不同部署环境中能提供更加一致的性能表现；
        //SleepingWaitStrategy 的性能表现跟 BlockingWaitStrategy 差不多，对 CPU 的消耗也类似，但其对生产者线程的影响最小，适合用于异步日志类似的场景；
        //YieldingWaitStrategy 的性能是最好的，适合用于低延迟的系统。在要求极高性能且事件处理线数小于 CPU 逻辑核心数的场景中，推荐使用此策略；例如，CPU开启超线程的特性。
        final RingBuffer<TradeTransaction> ringBuffer = RingBuffer.createSingleProducer(new EventFactory<TradeTransaction>() {
            @Override
            public TradeTransaction newInstance() {
                return new TradeTransaction();
            }
        }, BUFFER_SIZE,new YieldingWaitStrategy());

        //Disruptor 通过 java.util.concurrent.ExecutorService 提供的线程来触发 Consumer 的事件处理。例如：
        ExecutorService executors = Executors.newFixedThreadPool(THREAD_NUMBERS);
        //创建SequenceBarrier
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
//        TradeTransactionInDBHandler[] handlers = new TradeTransactionInDBHandler[THREAD_NUMBERS];
//        for (int i = 0; i < THREAD_NUMBERS; i++) {
//            handlers[i] = new TradeTransactionInDBHandler();
//        }
        //创建消息处理器
        BatchEventProcessor<TradeTransaction> transProcessor = new BatchEventProcessor<TradeTransaction>(ringBuffer, sequenceBarrier, new TradeTransactionInDBHandler());

        //这一部的目的是让RingBuffer根据消费者的状态	如果只有一个消费者的情况可以省略
        ringBuffer.addGatingSequences(transProcessor.getSequence());

        //把消息处理器提交到线程池
        executors.submit(transProcessor);
        //如果存多个消费者 那重复执行上面3行代码 把TradeTransactionInDBHandler换成其它消费者类

        Future<?> future=executors.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                long seq;
                for(int i=0;i<10;i++){
                    seq=ringBuffer.next();//占个坑	--ringBuffer一个可用区块

                    ringBuffer.get(seq).setPrice(Math.random()*9999);//给这个区块放入 数据  如果此处不理解，想想RingBuffer的结构图

                    ringBuffer.publish(seq);//发布这个区块的数据使handler(consumer)可见

                }
                return null;
            }
        });
        future.get();//等待生产者结束
        Thread.sleep(5000);//等上1秒，等消费都处理完成
        transProcessor.halt();//通知事件(或者说消息)处理器 可以结束了（并不是马上结束!!!）
        executors.shutdown();//终止线程
    }
}
