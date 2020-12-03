package queue;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaixt
 * @date 2020/3/5 14:24
 */
public class DelayEvent implements Delayed {
    //必须实现Delayed接口，重写compareTo方法。该方法提供一个和getDelay方法一致的排序。即从队列里取过期元素的顺序即按照此顺序。
    Date startDate;
    String name;

    public DelayEvent(Date startDate) {
        super();
        this.startDate = startDate;
    }

    public DelayEvent(Date startDate, String name) {
        super();
        this.startDate = startDate;
        this.name = name;
    }

    @Override
    public int compareTo(Delayed delayed) {
        long result = this.getDelay(TimeUnit.NANOSECONDS)
                - delayed.getDelay(TimeUnit.NANOSECONDS);
        if (result < 0) {
            return -1;
        } else if (result > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public long getDelay(TimeUnit unit) {
        Date now = new Date();
        long diff = startDate.getTime() - now.getTime();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    public void printName() {
        System.out.println("my name:" + name + ", startTime " + startDate);
    }
}
