package fork_join;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by zhaixt on 2018/11/9.
 * https://blog.csdn.net/ghsau/article/details/46287769
 */
public class PrintTask1 extends RecursiveTask<Integer> {
    private static final long serialVersionUID = -6196480027075657316L;

    private static final int THRESHOLD = 500000;

    private String[] array;

    private int low;

    private int high;



    @Override
    protected Integer compute() {
        return 1;
    }


}
