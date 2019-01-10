package design_pattern.template;

/**
 * Created by zhaixt on 2019/1/9.
 */
public class TemplateTest {
    public static void main(String[] args) {
        String exp = "8+8";
        AbstractCalculator cal = new Plus();
        int result = cal.calculate(exp, "\\+");
        System.out.println(result);
    }
}
