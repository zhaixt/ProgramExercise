import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhaixiaotong on 2016-6-28.
 */
//在typhon项目的RedisManageController类中，deleteScaledOutData方法用到了
public class CountDownLatchTest {
    final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final int PLAYER_AMOUNT = 5;
    public CountDownLatchTest() {
        // TODO Auto-generated constructor stub
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //对于每位运动员，CountDownLatch减1后即结束比赛
        CountDownLatch begin = new CountDownLatch(1);
        //对于整个比赛，所有运动员结束后才算结束
        CountDownLatch end = new CountDownLatch(PLAYER_AMOUNT);
        Player[] plays = new Player[PLAYER_AMOUNT];

        for(int i=0;i<PLAYER_AMOUNT;i++)
            plays[i] = new Player(i+1,begin,end);

        //设置特定的线程池，大小为5
        ExecutorService exe = Executors.newFixedThreadPool(PLAYER_AMOUNT);
        for(Player p:plays)
            exe.execute(p);            //分配线程
        System.out.println("Race begins!");
        begin.countDown();
        try{
            end.await();            //等待end状态变为0，即为比赛结束
            //注：await方法，调用此方法会一直阻塞当前线程，直到计时器的值为0
        }catch (InterruptedException e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            System.out.println("Race ends!");
            System.out.println("all work done at " + sdf.format(new Date()));
        }
        exe.shutdown();
    }



    public static class Player implements Runnable {

        private int id;
        private CountDownLatch begin;
        private CountDownLatch end;
        public Player(int i, CountDownLatch begin, CountDownLatch end) {
            // TODO Auto-generated constructor stub
            super();
            this.id = i;
            this.begin = begin;
            this.end = end;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            try{
                begin.await();        //等待begin的状态为0
                Thread.sleep((long)(Math.random()*100));    //随机分配时间，即运动员完成时间
                System.out.println("Play"+id+" arrived.");
            }catch (InterruptedException e) {
                // TODO: handle exception
                e.printStackTrace();
            }finally{
                end.countDown();    //使end状态减1，最终减至0
            }
        }
    }


}
