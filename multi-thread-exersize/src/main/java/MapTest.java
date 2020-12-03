import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhaixiaotong on 2016-6-28.
 */
//在typhon项目的RedisManageController类中，deleteScaledOutData方法用到了
public class MapTest {
    final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final int PLAYER_AMOUNT = 5;

    public MapTest() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        /**
         *  线程同步，跳表，logN
         *  使用“空间换时间”,令链表的每个结点不仅记录next结点位置，还可以按照level层级分别记录后继第level个结点。
         * */
        ConcurrentSkipListMap skipListMap = new ConcurrentSkipListMap();
        skipListMap.put("age", "17");
        skipListMap.put("name", "xiaoming");
    }


}
