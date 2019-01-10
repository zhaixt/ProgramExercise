package design_pattern.adapter;

/**
 * Created by zhaixt on 2019/1/9.
 * https://www.cnblogs.com/geek6/p/3951677.html  第6个
 * 适配器模式将某个类的接口转换成客户端期望的另一个接口表示，目的是消除由于接口不匹配所造成的类的兼容性问题。
 * 主要分为三类：类的适配器模式、对象的适配器模式、接口的适配器模式。
 */
public class AdapterTest {
    public static void main(String[] args) {
        //类的适配器模式,当希望将一个类转换成满足另一个新接口的类时，可以使用类的适配器模式，创建一个新类，继承原有的类，实现新的接口即可。
        Targetable target = new Adapter();
        target.method1();
        target.method2();

        //对象的适配器模式
        // 当希望将一个对象转换成满足另一个新接口的对象时，可以创建一个Wrapper类，持有原类的一个实例，在Wrapper类的方法中，调用实例的方法就行。
        Source source = new Source();
        Targetable targetObject = new Wrapper(source);
        targetObject.method1();
        targetObject.method2();


        //接口适配器
        //借助于一个抽象类，该抽象类实现了该接口，实现了所有的方法，而我们不和原始的接口打交道，只和该抽象类取得联系.
        // 我们写一个类，继承该抽象类，重写我们需要的方法就行,不需要实现接口中所有的方法
        Sourceable source1 = new SourceSub1();
        Sourceable source2 = new SourceSub2();

        source1.method1();
        source1.method2();//无用的，什么也没有
        source2.method1();//无用的，什么也没有
        source2.method2();
    }
}
