package bean.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.util.UUID;

/**
 * Created by zhaixiaotong on 2016-12-20.
 */
public class BatchEventHandler implements EventHandler<TradeTransaction>,WorkHandler<TradeTransaction>  {
    @Override
    public void onEvent(TradeTransaction event, long sequence,
                        boolean endOfBatch) throws Exception {
        this.onEvent(event);
    }

    @Override
    public void onEvent(TradeTransaction event) throws Exception {
        //这里做具体的消费逻辑
//        event.setId(UUID.randomUUID().toString());//简单生成下ID
//        System.out.println(Thread.currentThread().getName() + "     :" + event.getId() + "  ,price:" + event.getPrice());
        System.out.println("BatchEventprocess thread is:"+Thread.currentThread().getName()+" ,"+ ReflectionToStringBuilder.toString(event));

    }
}
