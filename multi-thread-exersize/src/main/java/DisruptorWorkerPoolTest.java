import bean.disruptor.TradeTransaction;
import bean.disruptor.TradeTransactionInDBHandler;
import com.lmax.disruptor.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhaixiaotong on 2016-12-20.
 */
public class DisruptorWorkerPoolTest {
    public static void main(String[] args) throws InterruptedException {
        int BUFFER_SIZE=1024;
        int THREAD_NUMBERS=4;
        EventFactory<TradeTransaction> eventFactory=new EventFactory<TradeTransaction>() {
            public TradeTransaction newInstance() {
                return new TradeTransaction();
            }
        };
        RingBuffer<TradeTransaction> ringBuffer=RingBuffer.createSingleProducer(eventFactory, BUFFER_SIZE);

        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();



        //Disruptor 通过ExecutorService 提供的线程来触发 Consumer 的事件处理。我认为是跟handler对应的
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUMBERS);
        //单消费者
//        WorkHandler<TradeTransaction> workHandlers=new TradeTransactionInDBHandler();
//        WorkerPool<TradeTransaction> workerPool=new WorkerPool<TradeTransaction>(ringBuffer, sequenceBarrier, new IgnoreExceptionHandler(), handlers);//一个workhandler就是单线程

        //多消费者，每个线程分配一个消费者
        WorkHandler<TradeTransaction>[] handlers = new TradeTransactionInDBHandler[THREAD_NUMBERS];
        for (int i = 0; i < THREAD_NUMBERS; i++) {
            handlers[i] = new TradeTransactionInDBHandler();
        }
		/*
		 * 这个类代码很简单的，亲自己看哈！~
		 */
        WorkerPool<TradeTransaction> workerPool=new WorkerPool<TradeTransaction>(ringBuffer, sequenceBarrier, new IgnoreExceptionHandler(), handlers);//多消费者

        workerPool.start(executor);




        //下面这个生产8个数据，图简单就写到主线程算了
        for(int i=0;i<8;i++){
            long seq=ringBuffer.next();
            ringBuffer.get(seq).setPrice(Math.random()*9999);
            ringBuffer.publish(seq);//发布这个区块的数据使handler(consumer)可见
        }

        Thread.sleep(1000);
        workerPool.halt();
        executor.shutdown();
    }
}
