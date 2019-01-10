package design_pattern.strategy;

/**
 * Created by zhaixt on 2019/1/9.
 * 上下文是GymnasticsGame类，该类包含策略声明的变量，此变量可用于保存具体策略的引用。
 * 另外，GymnasticsGame类中包含一个double型数组a，a的元素代表各个裁判给选手的评分。
 * 该类中的getPersonScore(double a[])方法将委托具体策略的实例计算选手的最后得分。
 */
public class GymnasticsGame implements Game{
    ComputableStrategy strategy;

    public void setStrategy(ComputableStrategy strategy){
        this.strategy = strategy;
    }
    @Override
    public double getPersonScore(double[] a){
        if(strategy != null)
            return strategy.computeScore(a);
        else
            return 0;
    }
}
