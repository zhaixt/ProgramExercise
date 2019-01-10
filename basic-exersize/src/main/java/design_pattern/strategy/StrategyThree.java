package design_pattern.strategy;

import java.util.Arrays;

/**
 * Created by zhaixt on 2019/1/9.
 * 具体策略，(去掉最大值和最小值)算术平均值方案
 */
public class StrategyThree implements ComputableStrategy {
    public double computeScore(double[] a) {
        if(a.length <= 2)
            return 0;
        double score = 0,sum = 0;
        Arrays.sort(a);
        for(int i = 1;i < a.length-1;i++){
            sum += a[i];
        }
        score = sum/(a.length-2);
        return score;
    }
}
