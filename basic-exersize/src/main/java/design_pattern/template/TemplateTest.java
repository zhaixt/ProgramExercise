package design_pattern.template;

/**
 * Created by zhaixt on 2019/1/9.
 */
public class TemplateTest {
    public static void main(String[] args) {
        String exp = "10+8";
        AbstractCalculator calPlus = new Plus();
        AbstractCalculator calMinus = new Minuxs();
        int result = calPlus.calculate(exp, "\\+");
        int result2 = calMinus.calculate(exp, "\\+");
        System.out.println(result);
        System.out.println(result2);
    }
}
