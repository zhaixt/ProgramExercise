package fork_join;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by zhaixt on 2018/11/9.
 * https://blog.csdn.net/ghsau/article/details/46287769
 */
public class SumTask extends RecursiveTask<Integer> {
    private static final long serialVersionUID = -6196480027075657316L;

    private static final int THRESHOLD = 500000;

    private String[] array;

    private int low;

    private int high;

    public SumTask(String[] array, int low, int high) {
        this.array = array;
        this.low = low;
        this.high = high;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if (high - low <= THRESHOLD) {
            // 小于阈值则直接计算
            for (int i = low; i < high; i++) {
                System.out.println(array[i]);
            }
        } else {
            // 1. 一个大任务分割成两个子任务
            int mid = (low + high) >>> 1;
            SumTask left = new SumTask(array, low, mid);
            SumTask right = new SumTask(array, mid + 1, high);

            // 2. 分别计算
            left.fork();
            right.fork();

            // 3. 合并结果
            sum = left.join() + right.join();
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String[] array =genArray(10);
        System.out.println(Arrays.toString(array));
        // 1. 创建任务
        SumTask sumTask = new SumTask(array,0,array.length-1);
        long begin =System.currentTimeMillis();
        // 2. 创建线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 3. 提交任务到线程池
        forkJoinPool.submit(sumTask);
        // 4. 获取结果
        Integer result = sumTask.get();
        long end = System.currentTimeMillis();

        System.out.println(String.format("结果 %s 耗时 %sms", result, end - begin));

    }
    private static String[] genArray(int size) {
        String[] array = {"zhangfei,zhaoyun,guanyu,liubang,caocao,lusu,zhouyu,kongming"};
        return array;
    }
}
