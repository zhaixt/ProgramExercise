package pattern_design.bridge;

/**
 * Created by zhaixt on 2018/11/20.
 * https://www.cnblogs.com/geek6/p/3951677.html
 * 桥接模式，JDBC桥DriverManager
 * 桥接的用意是：将抽象化与实现化解耦，使得二者可以独立变化
 */
public class BirdgeTest {

    public static void main(String[] args) {

        Bridge bridge = new MyBridge();//DriverManager

        /*调用第一个对象*/
        Sourceable source1 = new SourceSub1(); //source相当于不同的connection
        bridge.setSource(source1);
        bridge.method();

        /*调用第二个对象*/
        Sourceable source2 = new SourceSub2();
        bridge.setSource(source2);
        bridge.method();
    }
}
