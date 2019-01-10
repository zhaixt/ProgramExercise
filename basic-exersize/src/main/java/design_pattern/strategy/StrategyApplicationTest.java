package design_pattern.strategy;

/**
 * Created by zhaixt on 2019/1/9.
 * https://www.cnblogs.com/liuzhen1995/p/5983642.html
 * 策略模式是处理算法不同变体的一种成熟模式，策略模式通过接口或抽象类封装算法的标识，即在接口中定义一个抽象方法，实现该接口的类将实现接口中的抽象方法。
 * 策略模式把针对一个算法标识的一系列具体算法分别封装在不同的类中，使得各个类给出的具体算法可以相互替换。
 *
 * Context上下文: 环境类,GymnasticsGame
 * Strategy: 抽象策略类,ComputableStrategy接口
 * ConcreteStrategy: 具体策略类，StrategyOne
 */
public class StrategyApplicationTest {
    public static void main(String[] args) {
        GymnasticsGame game = new GymnasticsGame();       //上下文对象
        game.setStrategy(new StrategyOne());              //上下文对象使用策略一
        Person zhang = new Person();
        zhang.setName("张三");
        double[] a = {9.12,9.25,8.87,9.99,6.99,7.88};
        Person li = new Person();
        li.setName("李四");
        double[] b = {9.15,9.26,8.97,9.89,6.97,7.89};
        zhang.setScore(game.getPersonScore(a));
        li.setScore(game.getPersonScore(b));
        System.out.printf("%s最后得分：%5.3f%n",zhang.getName(),zhang.getScore());
        System.out.printf("%s最后得分：%5.3f%n",li.getName(),li.getScore());
        game.setStrategy(new StrategyTwo());              //上下文对象使用策略二
        zhang.setScore(game.getPersonScore(a));
        li.setScore(game.getPersonScore(b));
        System.out.println("使用几何平均值方案：");
        System.out.printf("%s最后得分：%5.3f%n",zhang.getName(),zhang.getScore());
        System.out.printf("%s最后得分：%5.3f%n",li.getName(),li.getScore());
        game.setStrategy(new StrategyThree());              //上下文对象使用策略三
        zhang.setScore(game.getPersonScore(a));
        li.setScore(game.getPersonScore(b));
        System.out.println("使用(去掉最大值和最小值)算术平均值方案：");
        System.out.printf("%s最后得分：%5.3f%n",zhang.getName(),zhang.getScore());
        System.out.printf("%s最后得分：%5.3f%n",li.getName(),li.getScore());
    }
}
