package design_pattern.strategy;

/**
 * Created by zhaixt on 2019/1/9.
 * 具体策略,几何平均值方案
 */
public class StrategyTwo implements ComputableStrategy {
    public double computeScore(double[] a) {
        double score = 0 , multi = 1;
        int n = a.length;
        for(int i = 0;i < a.length;i++){
            multi = multi*a[i];
        }
        score = Math.pow(multi,1.0/n);
        return score;
    }
}
