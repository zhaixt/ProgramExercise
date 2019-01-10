package design_pattern.template;

/**
 * Created by zhaixt on 2019/1/9.
 */
public class Plus extends AbstractCalculator {
    @Override
    public int calculate(int num1,int num2){
        return num1+num2;
    }
}
