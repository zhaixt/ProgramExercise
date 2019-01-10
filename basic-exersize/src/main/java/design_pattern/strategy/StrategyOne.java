package design_pattern.strategy;

/**
 * Created by zhaixt on 2019/1/9.
 * 具体策略,加法平均值方案
 */
public class StrategyOne implements  ComputableStrategy {
    public double computeScore(double[] a){
        double score = 0 , sum = 0;
        for(int i = 0;i < a.length;i++){
            sum += a[i];
        }
        score = sum/a.length;
        return score;
    }

}
